package wuwei.server.operator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;

public class CMD extends BaseOperator {

	/**
	 * 功能：调用系统cmd命令，执行带parameters的command命令
	 * 
	 * 服务端返回信息格式
	 * 第1行：状态，“ok”为正常，否则为异常信息
	 * 第2行：若为"ok"状态，返回指令字符串
	 * ex:
	 * cmd:www.wzu.edu.cn
	 * 相当于调用cmd.exe /c www.wzu.edu.cn
	 *  Runtime.getRuntime().exec("cmd /c start www.wzu.edu.cn")
	 */
	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		Runtime.getRuntime().exec("cmd /c start "+cmdBody);
		
		ackMsg.add("ok");
		ackMsg.add("CMD:"+cmdBody);
		return ackMsg;
	}
	/**
	 * 调用Thread.sleep(int sleeptime)方法，线程休眠sleeptime，以ms为单位
	 * @param cmdBody
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> slp(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		int sleeptime=Integer.parseInt(cmdBody);
		Thread.sleep(sleeptime);
		
		ackMsg.add("ok");
		ackMsg.add("SLP:"+cmdBody);
		return ackMsg;
	}
	/**
	 * 将somestring拷贝到远程端焦点处，当Android客户端输入一连串的汉字或其它字符串后，可用此命令将所输入的信息拷贝到远程端焦点处，避免了用远程端的输入法繁琐地输入文字o
	 * @param cmdBody
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> cps(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		
//		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//获取剪切板
//		Transferable tText = new StringSelection(cmdBody);//cmdBody为String字符串，需要拷贝进剪贴板的内容
//		clip.setContents(tText, null); //设置剪切板内容
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
	 * 功能:组合命令前缀，其中n为执行的次数，不单独使用，异步操作，即for中的任务在服务端提交后，立即返回状态，不等任务执行完毕再返回状态
	 * @param cmdBody
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> FOR(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		Runtime.getRuntime().exec("cmd /c start "+cmdBody);
		
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//获取剪切板
		Transferable tText = new StringSelection(cmdBody);//cmdBody为String字符串，需要拷贝进剪贴板的内容
		clip.setContents(tText, null); //设置剪切板内容
		ackMsg.add("ok");
		ackMsg.add("FOR:"+cmdBody);
		return ackMsg;
	}
}
