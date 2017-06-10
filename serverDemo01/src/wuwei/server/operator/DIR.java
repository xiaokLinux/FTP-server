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
//			/**
//			 * 列出根目录
//			 */
//			Desktop dp=Desktop.getDesktop();
//			try{
//				File[] rootdirs=File.listRoots();
//				 for(int i=0;i<rootdirs.length;i++)
//				 {
//					 ackMsg.add(rootdirs[i]+">"+rootdirs[i].length()+">");
//					 System.out.println("[MSG] "+rootdirs[i]+">"+rootdirs[i].length());
//				 }
//			}catch(Exception e){
//				e.printStackTrace();
//			}
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
//			try{
//				File file=new File(cmdBody);
//				File[] listFiles=file.listFiles();
//				ackMsg.add(cmdBody+"//");//当前路径
//				for(File mfile:listFiles){
//					String fileName=mfile.getName();
//					long lastModified=mfile.lastModified();
//					SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					String fileDate=dateFormat.format(new Date(lastModified));
//					String fileSize="0";
//					String isDir="1";
//					if(!mfile.isDirectory()){
//						isDir="0";
//						fileSize=""+mfile.length();
//					}
//					ackMsg.add(fileName+">"+fileDate+">"+fileSize+">"+isDir+">");
//					System.out.println("[MSG] "+fileName+">"+fileDate+">"+fileSize+">"+isDir+">");
//				}			
//			}catch(Exception e){
//				throw e;
//			}
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
		}
		return ackMsg;				
	}
}
