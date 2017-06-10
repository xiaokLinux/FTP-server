package wuwei.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class DIR extends BaseOperator{
	private static File file=new File("c:/");//Ĭ����fileָ��c��
	/**
	 * 
	 * @param cmdBody
	 * @return
	 */
	public ArrayList<String> exe(String cmdBody){
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ�������ָ
		File[] listFiles;
		boolean isRoot=false;
		if(cmdBody.equals("...")){
			isRoot=true;
			ackMsg.add("");
		}else if(cmdBody.equals("..")){
			/**
			 * �����ϼ�Ŀ¼,�ͻ��˴�����Ӻ���
			 * �ͷ���ת��Ϊ�Ϸ���·������
			 */
			file=file.getParentFile();
		}else{
			/**
			 * ��������Ŀ¼
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
				String fileDate=dateFormat.format(new Date(lastModified));//��ȡ�ļ��޸�ʱ��
				String fileSize="0";
				String isDir="2";
				ackMsg.add(fileName+">"+fileDate+">"+fileSize+">"+isDir+">");
				
			}
		}else{
			String pwd="";
			try {
				//��ǰ·��
				pwd=file.getCanonicalPath();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("pwd="+pwd);
			ackMsg.add(pwd);
			ackMsg.add("..."+">"+""+">"+"0"+">"+"1"+">");//ռ�У���ʾ��Ŀ¼
			String[] pwdSplits=pwd.split("/");
			String[] pwdSplits2=pwd.split("\\\\");
			if(pwdSplits.length>1 | pwdSplits2.length>1){
				//�ж��Ƿ���һ��Ŀ¼�����Ƕ�������Ŀ¼��ʾ����
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
					String fileDate=dateFormat.format(new Date(lastModified));//��ȡ�ļ��޸�ʱ��
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
