package wuwei.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class DIR extends BaseOperator{
	private static File file=new File("c:/");//默认下file指向c盘
	/**
	 * 
	 * @param cmdBody
	 * @return
	 */
	public ArrayList<String> exe(String cmdBody){
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值，避免空指
		File[] listFiles;
		boolean isRoot=false;
		if(cmdBody.equals("...")){
			isRoot=true;
			ackMsg.add("");
		}else if(cmdBody.equals("..")){
			/**
			 * 返回上级目录,客户端处理更加合适
			 * 客服端转化为合法的路径即可
			 */
			file=file.getParentFile();
		}else{
			/**
			 * 正常返回目录
			 */
			file=new File(cmdBody);
		}	
		if(isRoot){
			listFiles=File.listRoots();
			for(File mfile:listFiles){
				if(!mfile.canRead()){
					continue;
				}
				String fileName=mfile.getPath();
				long lastModified=mfile.lastModified();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String fileDate=dateFormat.format(new Date(lastModified));//获取文件修改时间
				String fileSize="0";
				String isDir="2";
				ackMsg.add(fileName+">"+fileDate+">"+fileSize+">"+isDir+">");
				
			}
		}else{
			String pwd="";
			try {
				//当前路径
				pwd=file.getCanonicalPath();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("pwd="+pwd);
			ackMsg.add(pwd);
			ackMsg.add("..."+">"+""+">"+"0"+">"+"1"+">");//占行，显示根目录
			String[] pwdSplits=pwd.split("/");
			String[] pwdSplits2=pwd.split("\\\\");
			if(pwdSplits.length>1 | pwdSplits2.length>1){
				//判断是否是一级目录，若是二级以上目录显示。。
				ackMsg.add(".."+">"+""+">"+"0"+">"+"1"+">");
			}
			listFiles=file.listFiles();
			if(listFiles!=null){
				for(File mfile:listFiles){
					if(!mfile.canRead()){
						System.out.println("here");
						continue;
					}
					String fileName=mfile.getName();
					long lastModified=mfile.lastModified();
					SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String fileDate=dateFormat.format(new Date(lastModified));//获取文件修改时间
					String fileSize="0";
					String isDir="1";
					if(!mfile.isDirectory()){
						isDir="0";
						fileSize=""+mfile.length();
					}
					ackMsg.add(fileName+">"+fileDate+">"+fileSize+">"+isDir+">");
				}
			}
		}
		return ackMsg;				
	}
	public boolean checkCanAccess(File f){
		if(f.canRead()){
			return false;
		}
		if(f.isHidden()){
			return false;
		}
		return true;
	}
}
