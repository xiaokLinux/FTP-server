package wuwei.server.operator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;

public class CMD extends BaseOperator {

	/**
	 * ���ܣ�����ϵͳcmd���ִ�д�parameters��command����
	 * 
	 * ����˷�����Ϣ��ʽ
	 * ��1�У�״̬����ok��Ϊ����������Ϊ�쳣��Ϣ
	 * ��2�У���Ϊ"ok"״̬������ָ���ַ���
	 * ex:
	 * cmd:www.wzu.edu.cn
	 * �൱�ڵ���cmd.exe /c www.wzu.edu.cn
	 *  Runtime.getRuntime().exec("cmd /c start www.wzu.edu.cn")
	 */
	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		Runtime.getRuntime().exec("cmd /c start "+cmdBody);
		
		ackMsg.add("ok");
		ackMsg.add("CMD:"+cmdBody);
		return ackMsg;
	}
	/**
	 * ����Thread.sleep(int sleeptime)�������߳�����sleeptime����msΪ��λ
	 * @param cmdBody
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> slp(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		int sleeptime=Integer.parseInt(cmdBody);
		Thread.sleep(sleeptime);
		
		ackMsg.add("ok");
		ackMsg.add("SLP:"+cmdBody);
		return ackMsg;
	}
	/**
	 * ��somestring������Զ�̶˽��㴦����Android�ͻ�������һ�����ĺ��ֻ������ַ����󣬿��ô�������������Ϣ������Զ�̶˽��㴦����������Զ�̶˵����뷨��������������o
	 * @param cmdBody
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> cps(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		
//		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//��ȡ���а�
//		Transferable tText = new StringSelection(cmdBody);//cmdBodyΪString�ַ�������Ҫ�����������������
//		clip.setContents(tText, null); //���ü��а�����
		String vc=cmdBody.trim();
		StringSelection ss=new StringSelection(vc);
		Clipboard sysClb=null;  
	    sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();  
	    sysClb.setContents(ss,null); 
		ackMsg.add("ok");
		ackMsg.add("CPS:"+cmdBody);
		return ackMsg;
	}
	/**
	 * ����:�������ǰ׺������nΪִ�еĴ�����������ʹ�ã��첽��������for�е������ڷ�����ύ����������״̬����������ִ������ٷ���״̬
	 * @param cmdBody
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> FOR(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		Runtime.getRuntime().exec("cmd /c start "+cmdBody);
		
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//��ȡ���а�
		Transferable tText = new StringSelection(cmdBody);//cmdBodyΪString�ַ�������Ҫ�����������������
		clip.setContents(tText, null); //���ü��а�����
		ackMsg.add("ok");
		ackMsg.add("FOR:"+cmdBody);
		return ackMsg;
	}
}
