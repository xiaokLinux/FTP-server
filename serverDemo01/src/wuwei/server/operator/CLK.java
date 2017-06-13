package wuwei.server.operator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * clk:left
 *�������������ͷ�
 *clk:right
 *����Ҽ��������ͷ�
 *clk:left_press
 *���������£����ͷţ����mov����mvaָ���ʵ���϶�
 *clk:left_release
 *�������ͷţ�clk:left_press�������ָ���������Ҫ����clk:left_release�ͷ�
 *clk:right_press
 *����Ҽ����£����ͷ�
 *clk:right_release
 *����Ҽ��ͷ�
 * @author Administrator
 *
 */
public class CLK extends BaseOperator{
	private Robot robot;
	public  ArrayList<String> exe(String cmdBody) throws AWTException{
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		String cm=cmdBody.toLowerCase();
		robot=new Robot();
		if(cmdBody.equalsIgnoreCase("left")){
			//������
			robot.mousePress(KeyEvent.BUTTON1_MASK);
			robot.mouseRelease(KeyEvent.BUTTON1_MASK);
		}
		if(cmdBody.equalsIgnoreCase("right")){
			robot.mousePress(KeyEvent.BUTTON3_MASK);
			robot.mouseRelease(KeyEvent.BUTTON3_MASK);		
		}
		if(cmdBody.equalsIgnoreCase("left_press")){
			robot.mousePress(KeyEvent.BUTTON1_MASK);
		}
		if(cmdBody.equalsIgnoreCase("left_release")){
			robot.mouseRelease(KeyEvent.BUTTON1_MASK);
		}
		if(cmdBody.equalsIgnoreCase("right_press")){
			robot.mousePress(KeyEvent.BUTTON3_MASK);
		}
		if(cmdBody.equalsIgnoreCase("right_release")){
			robot.mouseRelease(KeyEvent.BUTTON3_MASK);				
		}
		ackMsg.add("ok");
		ackMsg.add("CLK:"+cmdBody);
		return ackMsg;
	}
}
