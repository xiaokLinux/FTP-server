package wuwei.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;

public class OPEN {
	public static ArrayList<String> exe(String cmdBody){
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		Desktop desk=Desktop.getDesktop();  
		try  
		{  
		    File file=new File(cmdBody);//创建一个java文件系统  
		    desk.open(file); //调用open（File f）方法打开文件   
		}catch(Exception e)  
		{  
		    System.out.println(e.toString());
		    ackMsg.add(0, "BAD");
		    ackMsg.add(1,""+cmdBody);//回显执行过的命令
		    return ackMsg;
		}
		ackMsg.add(0, "OK");
		ackMsg.add(1,""+cmdBody);//回显执行过的命令
		return ackMsg;
	}
}
