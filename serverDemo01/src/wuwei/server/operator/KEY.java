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
		int splitId=cmdBody.indexOf("+");//-1 û��+����A���ͣ���֮��B���͵�
		/**
		 * ����A ����,������ָ�� key:keySymbol �����˷���keySymbol����İ������������� key:VK_A����key:vk_a��
		 * ������ִ�а��°���A���ͷŰ���A�������൱�ڰ���һ��A����
		 */
		if(-1==splitId){
			CommonRobot.pressKeys(rb,new int[]{VisualKeyMap.getVisualKey(cmdBody.toUpperCase())}, 500);
			CommonRobot.upKeys(rb,new int[]{VisualKeyMap.getVisualKey(cmdBody.toUpperCase())}, 500);
		}		
		/**
		 * ����B ��ϼ�,��ϰ���ָ�� key:sym1+sym2+...symn,symn+...sym2+sym1�������ִ���Ȱ���sym1��
		 * �ٰ���sym2�����˳��һֱ����symn�����ͷ�symn...����ͷ�sym1����Ӣ�Ķ���֮ǰ��Ϊ���µļ����Ұ���+�ŵ�˳���£�
		 * Ӣ�Ķ��ź����Ϊ�ͷż����Ұ���+��˳���ͷţ������Ϳ��Թ���������ɵ�����ȼ���������Ҫalt+tab������
		 * ����key:VK_ALT+VK_TAB,VK_TAB+VK_ALTʵ��
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
