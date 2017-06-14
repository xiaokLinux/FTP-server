package wuwei.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FileUpLoadSocketThread extends Thread {
	private long filePos=0l;
	private File file=null;
	private ServerSocket serverSocket=null;
	private BufferedReader bufferedReader;
	private OutputStreamWriter writer;
	ArrayList<String> msgBackList=new ArrayList<String>();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			updownload(file,filePos);
		}catch(Exception e){
			e.printStackTrace();
			close();
		}
		super.run();
	}

	public FileUpLoadSocketThread(File fi,int mode) {
	    // TODO Auto-generated constructor stub
	    try {
	    	if(serverSocket==null)
	    		serverSocket = new ServerSocket(0);//动态分配可用端口
	        System.out.println("文件长度:" + (int) fi.length());
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    this.file=fi;
	    this.filePos=filePos;

	}
	
	public long getFilePos() {
		return filePos;
	}

	public void setFilePos(long filePos) {
		this.filePos = filePos;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void UpFile(String filePath){
	    //上传文件，将本地文件传输到服务器端	       
		
	}
	public int get_port() {
		if(serverSocket!=null)
			return serverSocket.getLocalPort();
		return -1;
	}
	public long get_fileLength(){
		if(file!=null)
			return file.length();
		return -1;
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
	public void updownload(File filename,long position){
		while(true){
			if(serverSocket.isClosed()){
				System.out.println("[CRASH] Ther server is closed!Please Create anthor CmdServerSocketThread");
				break;
			}
			
			System.out.println("[DLF] Wariting connecting....");
			Socket socket;
			try {
				socket=serverSocket.accept();
				Inet4Address inetAddress=(Inet4Address) socket.getInetAddress();
				System.out.println("[DLF] Client connect From:"+inetAddress.getHostAddress()+"\t"+inetAddress.getHostName());
				try{
					ArrayList<String> cmdList=readSocketMsg(socket);
					System.out.println(cmdList.size());
					if(cmdList.size()==1){
						if(cmdList.get(0).equals("connect")){
							//发送文件流
							DataInputStream is = new  DataInputStream(socket.getInputStream());   
				            OutputStream os = socket.getOutputStream();    
			                //1、得到文件名       
			                System.out.println("新生成的文件名为:"+filename);  
			                
			                FileOutputStream fos = new FileOutputStream(filename); 
			                byte[] b = new byte[1024]; 
			                int length = 0;  
			                while((length=is.read(b))!=-1){  
			                    //2、把socket输入流写到文件输出流中去  
			                    fos.write(b, 0, length);  
			                }  
			                fos.flush();  
			                fos.close();               
			                is.close();  
			                socket.close();               
			                System.out.println("文件传输完成");
						}
					}
				}catch(Exception e){
					cmdFail(e.toString());//若出错，则在msgBackList中存放错误信息					
				}
				bufferedReader.close();
				socket.close();
				
				System.out.println("[OK] server deal done!");
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
				close();//serverSocket关闭,退出死循环，线程结束	
				System.out.println("[CRASH] "+e.toString());
			}
		}
	}
	private void cmdFail(String string) {
		// TODO Auto-generated method stub
		System.out.println("[Fail-CMD-EXE]");
		msgBackList.clear();
	}

	private ArrayList<String> readSocketMsg(Socket socket) throws IOException {
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

	public void downloadWithBegin(File filename,long position){
		
	}
}
