package test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class testdownload {

	private String ip = "10.132.253.122";// ���óɷ�����IP
    private int port = 8833;//���ö˿ں�
    public void UpFile(String filePath){
    //�ϴ��ļ����������ļ����䵽��������
        try {
        Socket socket = new Socket(ip,port); 
            while (true) {
             // ѡ����д�����ļ�
                File fi = new File(filePath);
                System.out.println("�ļ�����:" + (int) fi.length());
               /* DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                dis.readByte();
                */
                DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
                DataOutputStream ps = new DataOutputStream(socket.getOutputStream());
                //���ļ��������ȴ����ͻ��ˡ�����Ҫ������������ƽ̨�������������Ĵ�������Ҫ�ӹ���������Բμ�Think In Java 4th�����ֳɵĴ��롣
                ps.writeUTF(fi.getName());
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
                System.out.println("port:"+socket.getPort());
                break;
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }  
}
    
    public void DownFile(){
    	//�ӷ������������ļ�
    	try { 
    		Socket socket = new Socket(ip,port);
           while(true){  
               DataInputStream is = new  DataInputStream(socket.getInputStream());   
               OutputStream os = socket.getOutputStream();                  
               //1���õ��ļ���       
               String filename="E:\\";
               filename += is.readUTF();
             
               System.out.println("�����ɵ��ļ���Ϊ:"+filename);  
               FileOutputStream fos = new FileOutputStream(filename);  
               byte[] b = new byte[1024]; 
               int length = 0;  
               while((length=is.read(b))!=-1){  
                   //2����socket������д���ļ��������ȥ  
                   fos.write(b, 0, length);  
               }  
               fos.flush();  
               fos.close();               
               is.close();  
               socket.close();  
           }  
             
       } catch (IOException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       }   
}  
	public static void main(String[] args) {
		String filepath="D:\\14211160137 ��ά ����ҵ.doc";
	    new testdownload().UpFile(filepath);
		System.out.println("over");
	}

}
