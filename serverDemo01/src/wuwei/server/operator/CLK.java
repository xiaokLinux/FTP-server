package wuwei.server.operator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * clk:left
 *鼠标左键按下再释放
 *clk:right
 *鼠标右键按下再释放
 *clk:left_press
 *鼠标左键按下，不释放，配合mov或者mva指令可实现拖动
 *clk:left_release
 *鼠标左键释放，clk:left_press配合其他指令操作后，需要调用clk:left_release释放
 *clk:right_press
 *鼠标右键按下，不释放
 *clk:right_release
 *鼠标右键释放
 * @author Administrator
 *
 */
public class CLK extends BaseOperator{
	private Robot robot;
	public  ArrayList<String> exe(String cmdBody) throws AWTException{
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		String cm=cmdBody.toLowerCase();
		robot=new Robot();
		if(cmdBody.equalsIgnoreCase("left")){
			//左键点击
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
