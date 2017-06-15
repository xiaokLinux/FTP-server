package wuwei.server.socket;

import java.awt.AWTException;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import wuwei.server.operator.Operator;

public class CmdServerSocketThread extends Thread {

	int port=8023;
	private BufferedReader bufferedReader;
	private OutputStreamWriter writer;
	private ServerSocket serverSocket;
	ArrayList<String> msgBackList=new ArrayList<String>();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			serverSocket=new ServerSocket(port);
			doCmdTask();
		}catch(Exception e){
			e.printStackTrace();
			close();
		}
		super.run();
	}
	private void close() {
		// TODO Auto-generated method stub
		if(serverSocket!=null){
			try {
				serverSocket.close();
				System.out.println("The CmdServerSocketThread is closed!");	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void doCmdTask() {
		// TODO Auto-generated method stub
		while(true){
			if(serverSocket.isClosed()){
				System.out.println("[CRASH] Ther server is closed!Please Create anthor CmdServerSocketThread");
				break;
			}
			
			System.out.println("[TEST] Wariting connecting....");
			Socket socket;
			try {
				socket=serverSocket.accept();
				Inet4Address inetAddress=(Inet4Address) socket.getInetAddress();
				System.out.println("[TEST] Client connect From:"+inetAddress.getHostAddress()+"\t"+inetAddress.getHostName());
				try{
					getAndDealCmd(socket);//���ܴ�������
				}catch(Exception e){
					cmdFail(e.toString());//����������msgBackList�д�Ŵ�����Ϣ					
				}
				writeBackMsg(socket);//��д
				bufferedReader.close();
				writer.close();
				socket.close();
				
				System.out.println("[OK] server deal done!");
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
				close();//serverSocket�ر�,�˳���ѭ�����߳̽���	
				System.out.println("[CRASH] "+e.toString());
			}
		}
	}
	private void getAndDealCmd(Socket socket) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> cmdList=readSocketMsg(socket);
		msgBackList.clear();
		if(cmdList.size()==1){
			String cmd=cmdList.get(0);
			System.out.println("[TEST] cmd:"+cmd);
			processCmd(cmd);
			System.out.println("single cmd");
		}else if(cmdList.size()>1){
			for(int i=0;i<cmdList.size();i++){
				String cmd=cmdList.get(i);
				System.out.println("[TEST] cmd:"+cmd);
				processCmd(cmd);
				System.out.println("compbol cmd");
			}
		}
	}
	private void processCmd(String cmd) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[TEST] Client cmd:"+cmd);
		int splitIdx=cmd.indexOf(":");
		if(splitIdx<1){
			cmdFail();
			return;
		}
		String cmdHead=cmd.substring(0,splitIdx);
		String cmdBody=cmd.substring(splitIdx+1);
		msgBackList.addAll(Operator.execCmd(cmdHead.toLowerCase(), cmdBody));//����ǰ׺ͳһΪСд
	}
	private void cmdFail() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * �쳣����������Ϊ�쳣����Ϣ�����᷵��client
	 * @param msg
	 */
	private void cmdFail(String msg) {
		// TODO Auto-generated method stub
		System.out.println("[Fail-CMD-EXE]");
		msgBackList.clear();
		msgBackList.add(msg);
	}
	public void writeBackMsg(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		BufferedOutputStream os=new BufferedOutputStream(socket.getOutputStream());
		writer=new OutputStreamWriter(os,"gb2312");
		writer.write(""+msgBackList.size()+"\n");
		writer.flush();
		for(int i=0;i<msgBackList.size();i++){
			writer.write(msgBackList.get(i)+"\n");
			writer.flush();
		}
		
	}
	public ArrayList<String> readSocketMsg(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> msgList=new ArrayList<String>();
		InputStream inputStream=socket.getInputStream();
		InputStreamReader reader=new InputStreamReader(inputStream,"gb2312");
		bufferedReader=new BufferedReader(reader);
		String lineNumStr=bufferedReader.readLine();
		int lineNum=Integer.parseInt(lineNumStr);
		for(int i=0;i<lineNum;i++){
			String str=bufferedReader.readLine();
			msgList.add(str);
		}
		return msgList;
	}
	
	
}
