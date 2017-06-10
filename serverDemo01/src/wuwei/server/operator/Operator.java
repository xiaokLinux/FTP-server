package wuwei.server.operator;

import java.awt.AWTException;
import java.util.ArrayList;

/**
 * �˴�ʶ�����е�����ͷ��Ȼ�������Ӧ��ִ�к���
 * @author Administrator
 *
 */
public class Operator {

		public static ArrayList<String> execCmd(String cmdHead,String cmdBody) throws AWTException{
			
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
				msgBackList=OPEN.exe(cmdBody);
			}
			//key:��ͷ���ȼ�����
			if(cmdHead.equals("key")){
				System.out.println("[TEST-KEY] "+cmdBody);
				msgBackList=KEY.exe(cmdBody);
			}
			
			if(cmdHead.equals("mov")){
				System.out.println("[TEST-MOV] "+cmdBody);
				msgBackList=MOV.exe(cmdBody);
			}
			return msgBackList;
		}
}
