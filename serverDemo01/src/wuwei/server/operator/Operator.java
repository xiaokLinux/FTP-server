package wuwei.server.operator;

import java.awt.AWTException;
import java.util.ArrayList;

/**
 * �˴�ʶ�����е�����ͷ��Ȼ�������Ӧ��ִ�к���
 * @author Administrator
 *
 */
public class Operator {

		public static ArrayList<String> execCmd(String cmdHead,String cmdBody) throws Exception{
			
			ArrayList<String> msgBackList=new ArrayList<String>();
			//dir:��ͷ������
			if(cmdHead.equals("dir")){
				System.out.println("[TEST-DIR] "+cmdBody);
				msgBackList=new DIR().exe(cmdBody);
				return msgBackList;
			}
			//open:��ͷ������ cmdBody�����Ǿ���·��
			if(cmdHead.equals("open")){
				System.out.println("[TEST-OPEN] "+cmdBody);
				msgBackList=new OPEN().exe(cmdBody);
			}
			//key:��ͷ���ȼ�����
			if(cmdHead.equals("key")){
				System.out.println("[TEST-KEY] "+cmdBody);
				msgBackList=new KEY().exe(cmdBody);
			}
			//����������
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
