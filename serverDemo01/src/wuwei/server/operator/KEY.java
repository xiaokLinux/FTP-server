package wuwei.server.operator;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import wuwei.server.utils.CommonRobot;
import wuwei.server.utils.VisualKeyMap;

public class KEY {

	public static ArrayList<String> exe(String cmdBody) throws AWTException{

		ArrayList<String> ackMsg=new ArrayList<String>();
		final Robot rb=new Robot();
		int splitId=cmdBody.indexOf("+");//-1 没有+号是A类型，反之是B类型的
		/**
		 * 类型A 单键,单按键指令 key:keySymbol 向服务端发送keySymbol代表的按键操作，例如 key:VK_A或者key:vk_a，
		 * 则服务端执行按下按键A并释放按键A操作，相当于按了一次A键盘
		 */
		if(-1==splitId){
			CommonRobot.pressKeys(rb,new int[]{VisualKeyMap.getVisualKey(cmdBody.toUpperCase())}, 500);
			CommonRobot.upKeys(rb,new int[]{VisualKeyMap.getVisualKey(cmdBody.toUpperCase())}, 500);
		}		
		/**
		 * 类型B 组合键,组合按键指令 key:sym1+sym2+...symn,symn+...sym2+sym1，服务端执行先按下sym1，
		 * 再按下sym2，如此顺序，一直按到symn，再释放symn...最后释放sym1。即英文逗号之前的为按下的键，且按照+号的顺序按下，
		 * 英文逗号后面的为释放键，且按照+号顺序释放，这样就可以构建灵活自由的组合热键，例如需要alt+tab操作，
		 * 可用key:VK_ALT+VK_TAB,VK_TAB+VK_ALT实现
		 */
		else{
			String[] KeyStrs=cmdBody.split("\\+");
			int[] ks=new int[KeyStrs.length];
			for(int i=0;i<KeyStrs.length;i++){
				ks[i]=VisualKeyMap.getVisualKey(KeyStrs[i].toUpperCase());
			}
			CommonRobot.pressKeys(rb, ks, 1);
			CommonRobot.upKeys(rb, ks, 1);
		}
		ackMsg.add(0,"Done!");
		return ackMsg;
	}
}
