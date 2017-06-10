package wuwei.server.operator;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;


import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import wuwei.server.utils.CommonRobot;
import wuwei.server.utils.VisualKeyMap;

public class KEY extends BaseOperator {

	private Robot robot;
	public  ArrayList<String> exe(String cmdBody) throws Exception{

		//组合键：VK_ALT+VK_TAB,VK_TAB+VK_ALT表示先按下alt键，再按下tab键，在释放alt键，再释放alt键
		//逗号前面的表示按下的顺序，逗号后面的表示释放键的顺序
		ArrayList<String> ackMsg=new ArrayList<String>();
		robot=new Robot();
		int splitIdx=cmdBody.indexOf(",");//-1 没有+号是A类型，反之是B类型的
		if(splitIdx<1){
			int splitIdx2=cmdBody.indexOf("+");
			if(splitIdx2<1){
				singleKeyPress(cmdBody);
			}else{
				simpleComboKeyPress(cmdBody);
			}
		}else{
			String keyPressStr=cmdBody.substring(0,splitIdx);
			String keyReleaseStr=cmdBody.substring(splitIdx+1);
			comboKeyPress(keyPressStr,keyReleaseStr);
		}
		ackMsg.add("key:"+cmdBody);
		return ackMsg;
//		/**
//		 * 类型A 单键,单按键指令 key:keySymbol 向服务端发送keySymbol代表的按键操作，例如 key:VK_A或者key:vk_a，
//		 * 则服务端执行按下按键A并释放按键A操作，相当于按了一次A键盘
//		 */
//		if(-1==splitId){
//			CommonRobot.pressKeys(rb,new int[]{VisualKeyMap.getVisualKey(cmdBody.toUpperCase())}, 500);
//			CommonRobot.upKeys(rb,new int[]{VisualKeyMap.getVisualKey(cmdBody.toUpperCase())}, 500);
//		}		
//		/**
//		 * 类型B 组合键,组合按键指令 key:sym1+sym2+...symn,symn+...sym2+sym1，服务端执行先按下sym1，
//		 * 再按下sym2，如此顺序，一直按到symn，再释放symn...最后释放sym1。即英文逗号之前的为按下的键，且按照+号的顺序按下，
//		 * 英文逗号后面的为释放键，且按照+号顺序释放，这样就可以构建灵活自由的组合热键，例如需要alt+tab操作，
//		 * 可用key:VK_ALT+VK_TAB,VK_TAB+VK_ALT实现
//		 */
//		else{
//			String[] KeyStrs=cmdBody.split("\\+");
//			int[] ks=new int[KeyStrs.length];
//			for(int i=0;i<KeyStrs.length;i++){
//				ks[i]=VisualKeyMap.getVisualKey(KeyStrs[i].toUpperCase());
//			}
//			CommonRobot.pressKeys(rb, ks, 1);
//			CommonRobot.upKeys(rb, ks, 1);
//		}
//		ackMsg.add(0,"Done!");
//		return ackMsg;
	}
	private void comboKeyPress(String keyPressStr, String keyReleaseStr) {
		// TODO Auto-generated method stub
		String[] keyPressArray=keyPressStr.split("\\+");
		String[] keyReleaseArray=keyReleaseStr.split("\\+");
		for(int i=0;i<keyPressArray.length;i++){
			int keycode=VisualKeyMap.getVisualKey(keyPressArray[i]);
			robot.keyPress(keycode);
		}
		for(int i=0;i<keyReleaseArray.length;i++){
			int keycode=VisualKeyMap.getVisualKey(keyPressArray[i]);
			robot.keyRelease(keycode);
		}
	}
	private void simpleComboKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		String[] keyPressArray=cmdBody.split("\\+");
		for(int i=0;i<keyPressArray.length;i++){
			int keycode=VisualKeyMap.getVisualKey(keyPressArray[i]);
			robot.keyPress(keycode);
		}
		for(int i=keyPressArray.length-1;i>=0;i--){
			int keycode=VisualKeyMap.getVisualKey(keyPressArray[i]);
			robot.keyRelease(keycode);
		}
	}
	private void singleKeyPress(String cmdBody) {
		// TODO Auto-generated method stub
		int keycode=VisualKeyMap.getVisualKey(cmdBody);
		robot.keyPress(keycode);
		robot.keyRelease(keycode);
	}
}
