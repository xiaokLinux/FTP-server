package wuwei.server.operator;

import java.io.File;
import java.util.ArrayList;

import wuwei.server.socket.FileDownLoadSocketThread;

public class DLF extends BaseOperator {

	private FileDownLoadSocketThread ftd;
	/**
	 * dlf:remoteFile_path_file_name
	 * ���ܣ�����Զ�̷����ָ���ļ�����Ŀ¼��Ϣ)
	 * dlf:remoteFile_path_file_name?filePosition
	 * ����:����Զ�̷����ָ���ļ�����Ŀ¼��Ϣ��,���ӵ�filePosition����ʼ���أ��ϵ��������ܣ�
	 * ����˷�����Ϣ��ʽ
	 * ��1�У�״̬����ok��Ϊ����������Ϊ�쳣��Ϣ
	 * ��2�У� ��Ϊ"ok"״̬��������������ơ�dlf��
	 * ��3�У���Ϊ"ok"״̬�����ض˿ںţ��ַ�����Ϣ)
	 * ��4�У� ��Ϊ"ok"״̬,�����ļ����ȣ��ַ�����Ϣ)
	 * ����˵������������⿪��һ���̺߳�ServerSocket�ṩ���ط��������Ҫ�����¿�ServerSocket�Ķ˿ںţ�����ͻ�����Ҫʵʱ֪�����ؽ��ȣ������Ҫ�����ļ��ܳ��ȣ����ܳ�����ָ��filePosition=0��ʼ���ļ�β���ܳ��ȡ�
	 */
	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		int splitIdx=cmdBody.indexOf("?");//
		String filename="";
		long filePosition=0L;
		if(splitIdx<1){
			filename=cmdBody;
		}else{
			String[] array=cmdBody.split("?");
			filename=array[0];
			filePosition=Long.parseLong(array[1]);
		}
		ftd=new FileDownLoadSocketThread(new File(filename), filePosition);
		ftd.start();
//		ftd.join();
		System.err.println(ftd);
		ackMsg.add("ok");
		ackMsg.add("DLF");
		ackMsg.add(""+ftd.get_port());
		ackMsg.add(""+ftd.get_fileLength());
		System.out.println("port:"+ftd.get_port());
		return ackMsg;
	}
	
	
}
