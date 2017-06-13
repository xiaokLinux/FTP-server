package wuwei.server.operator;

import java.awt.AWTException;
import java.util.ArrayList;

/**
 * 此处识别所有的命令头，然后调用相应的执行函数
 * @author Administrator
 *
 */
public class Operator {

		public static ArrayList<String> execCmd(String cmdHead,String cmdBody) throws Exception{
			
			ArrayList<String> msgBackList=new ArrayList<String>();
			//dir:开头的命令
			if(cmdHead.equals("dir")){
				System.out.println("[TEST-DIR] "+cmdBody);
				msgBackList=new DIR().exe(cmdBody);
				return msgBackList;
			}
			//open:开头的命令 cmdBody必须是绝对路径
			if(cmdHead.equals("open")){
				System.out.println("[TEST-OPEN] "+cmdBody);
				msgBackList=new OPEN().exe(cmdBody);
			}
			//key:开头的热键操作
			if(cmdHead.equals("key")){
				System.out.println("[TEST-KEY] "+cmdBody);
				msgBackList=new KEY().exe(cmdBody);
			}
			//鼠标操作集合
			if(cmdHead.equals("mov")){
				System.out.println("[TEST-MOV] "+cmdBody);
				msgBackList=new MOV().exe(cmdBody);
			}
			if(cmdHead.equals("mva")){
				System.out.println("[TEST-MVA] "+cmdBody);
				msgBackList=new MVA().exe(cmdBody);
			}
			if(cmdHead.equals("clk")){
				System.out.println("[TEST-CLK] "+cmdBody);
				msgBackList=new CLK().exe(cmdBody);
			}
			if(cmdHead.equals("rol")){
				System.out.println("[TEST-ROL] "+cmdBody);
				msgBackList=new ROL().exe(cmdBody);
			}
			return msgBackList;
		}
}
