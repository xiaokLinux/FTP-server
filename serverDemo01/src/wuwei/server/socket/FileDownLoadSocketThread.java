package wuwei.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FileDownLoadSocketThread extends Thread{

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
			download(file,filePos);
		}catch(Exception e){
			e.printStackTrace();
			close();
		}
		super.run();
	}

	public FileDownLoadSocketThread(File fi,long filePos) {
	    // TODO Auto-generated constructor stub
	    try {
	    	if(serverSocket==null)
	    		serverSocket = new ServerSocket(0);//��̬������ö˿�
	        System.out.println("�ļ�����:" + (int) fi.length());
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
	    //�ϴ��ļ����������ļ����䵽��������	       
		
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
	public void download(File filename,long position){
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
							//�����ļ���
							DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
			                DataOutputStream ps = new DataOutputStream(socket.getOutputStream());
			                ps.writeUTF(filename.getName());
			                ps.flush();
			                
			                int bufferSize = 8192;
			                byte[] buf = new byte[bufferSize];
			                while (true) {
			                    int read = 0;
			                    if (fis != null) {
			                        read = fis.read(buf);
			                    }

			                    if (read == -1) {
			                        break;
			                    }
			                    ps.write(buf, 0, read);
			                }
			                ps.flush();
			                // ע��ر�socket����Ŷ����Ȼ�ͻ��˻�ȴ�server�����ݹ�����
			                // ֱ��socket��ʱ���������ݲ�������                
			                fis.close();
			                socket.close();                
			                System.out.println("�ļ��������");
						}
					}
				}catch(Exception e){
					cmdFail(e.toString());//����������msgBackList�д�Ŵ�����Ϣ					
				}
				bufferedReader.close();
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
