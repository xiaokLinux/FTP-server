package wuwei.server.operator;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;
/**
 * rol:value
 * value可正，可负，整型数据字符串，可使得远程端实现鼠标的向上滚或向下滚操作
 * rol:3
 * 控制鼠标滚轮，向下滚动3格
 * rol:-5
 * 控制鼠标滚轮，向上滚动5格
 * 
 * @author Administrator
 *返回值
 *第1行：为"ok"状态或异常信息
 *第2行：若为"ok"状态，返回指令字符串
 */
public class ROL extends BaseOperator {
	private  Robot robot;
	public  ArrayList<String> exe(String cmdBody) throws AWTException{
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		int a=Integer.parseInt(cmdBody);
		robot=new Robot();
//		Toolkit toolkit = Toolkit.getDefaultToolkit();                    // 获得Toolkit对象
//		Dimension dimension = toolkit.getScreenSize();     // 获得Dimension对象
//		int screenHeight = dimension.height;               // 获得屏幕的高度
//		int screenWidth = dimension.width;                 // 获得屏幕的宽度
//		if()
		robot.mouseWheel(a);
		ackMsg.add("ok");
		ackMsg.add("ROL:"+cmdBody);
		return ackMsg;
	}
}
