package wuwei.server.operator;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;
/**
 * rol:value
 * value�������ɸ������������ַ�������ʹ��Զ�̶�ʵ���������Ϲ������¹�����
 * rol:3
 * ���������֣����¹���3��
 * rol:-5
 * ���������֣����Ϲ���5��
 * 
 * @author Administrator
 *����ֵ
 *��1�У�Ϊ"ok"״̬���쳣��Ϣ
 *��2�У���Ϊ"ok"״̬������ָ���ַ���
 */
public class ROL extends BaseOperator {
	private  Robot robot;
	public  ArrayList<String> exe(String cmdBody) throws AWTException{
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		int a=Integer.parseInt(cmdBody);
		robot=new Robot();
//		Toolkit toolkit = Toolkit.getDefaultToolkit();                    // ���Toolkit����
//		Dimension dimension = toolkit.getScreenSize();     // ���Dimension����
//		int screenHeight = dimension.height;               // �����Ļ�ĸ߶�
//		int screenWidth = dimension.width;                 // �����Ļ�Ŀ��
//		if()
		robot.mouseWheel(a);
		ackMsg.add("ok");
		ackMsg.add("ROL:"+cmdBody);
		return ackMsg;
	}
}
