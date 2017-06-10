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

		//��ϼ���VK_ALT+VK_TAB,VK_TAB+VK_ALT��ʾ�Ȱ���alt�����ٰ���tab�������ͷ�alt�������ͷ�alt��
		//����ǰ��ı�ʾ���µ�˳�򣬶��ź���ı�ʾ�ͷż���˳��
		ArrayList<String> ackMsg=new ArrayList<String>();
		robot=new Robot();
		int splitIdx=cmdBody.indexOf(",");//-1 û��+����A���ͣ���֮��B���͵�
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
//		 * ����A ����,������ָ�� key:keySymbol �����˷���keySymbol����İ������������� key:VK_A����key:vk_a��
//		 * ������ִ�а��°���A���ͷŰ���A�������൱�ڰ���һ��A����
//		 */
//		if(-1==splitId){
//			CommonRobot.pressKeys(rb,new int[]{VisualKeyMap.getVisualKey(cmdBody.toUpperCase())}, 500);
//			CommonRobot.upKeys(rb,new int[]{VisualKeyMap.getVisualKey(cmdBody.toUpperCase())}, 500);
//		}		
//		/**
//		 * ����B ��ϼ�,��ϰ���ָ�� key:sym1+sym2+...symn,symn+...sym2+sym1�������ִ���Ȱ���sym1��
//		 * �ٰ���sym2�����˳��һֱ����symn�����ͷ�symn...����ͷ�sym1����Ӣ�Ķ���֮ǰ��Ϊ���µļ����Ұ���+�ŵ�˳���£�
//		 * Ӣ�Ķ��ź����Ϊ�ͷż����Ұ���+��˳���ͷţ������Ϳ��Թ���������ɵ�����ȼ���������Ҫalt+tab������
//		 * ����key:VK_ALT+VK_TAB,VK_TAB+VK_ALTʵ��
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
