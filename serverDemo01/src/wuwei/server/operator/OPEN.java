package wuwei.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OPEN extends BaseOperator {
	public ArrayList<String> exe(String cmdBody) throws Exception{
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		Desktop desk=Desktop.getDesktop();  
		File file=new File(cmdBody);
		Desktop desktop=Desktop.getDesktop();//����Desktop�����windowsע��ĳ������Ӧ�ļ�
		desktop.open(file);
		ackMsg.add("OPEN:"+cmdBody);
//		try  
//		{  
//		    File file=new File(cmdBody);//����һ��java�ļ�ϵͳ  
//		    desk.open(file); //����open��File f���������ļ�   
//		}catch(Exception e)  
//		{  
//		    System.out.println(e.toString());
//		    ackMsg.add(0, "BAD");
//		    ackMsg.add(1,""+cmdBody);//����ִ�й�������
//		    return ackMsg;
//		}
//		ackMsg.add(0, "OK");
//		ackMsg.add(1,""+cmdBody);//����ִ�й�������
		
		return ackMsg;
	}
}
