package wuwei.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OPEN extends BaseOperator {
	public ArrayList<String> exe(String cmdBody) throws Exception{
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		Desktop desk=Desktop.getDesktop();  
		File file=new File(cmdBody);
		Desktop desktop=Desktop.getDesktop();//利用Desktop对象打开windows注册的程序打开相应文件
		desktop.open(file);
		ackMsg.add("OPEN:"+cmdBody);
//		try  
//		{  
//		    File file=new File(cmdBody);//创建一个java文件系统  
//		    desk.open(file); //调用open（File f）方法打开文件   
//		}catch(Exception e)  
//		{  
//		    System.out.println(e.toString());
//		    ackMsg.add(0, "BAD");
//		    ackMsg.add(1,""+cmdBody);//回显执行过的命令
//		    return ackMsg;
//		}
//		ackMsg.add(0, "OK");
//		ackMsg.add(1,""+cmdBody);//回显执行过的命令
		
		return ackMsg;
	}
}
