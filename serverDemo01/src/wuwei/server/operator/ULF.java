package wuwei.server.operator;

import java.io.File;
import java.util.ArrayList;

import wuwei.server.socket.FileDownLoadSocketThread;
import wuwei.server.socket.FileUpLoadSocketThread;

public class ULF extends BaseOperator {

	private FileUpLoadSocketThread fup;
	/**
	 * ulf:remoteFile_path_file_name
	 * ���ܣ��ϴ�Զ�̷����ָ���ļ�����Ŀ¼��Ϣ����������ģʽ
	 * ulf:remoteFile_path_file_name?filemode
	 * ulf:remoteFile_path_file_name?filemode
	 * filemodeֵΪ-1��������ģʽ�������������Ӧ�ļ����򴴽��ļ��������ļ�0λ�ÿ�ʼ���䣻�����������Ӧ�ļ����򴴽����ļ����ļ���������ԭ�ļ�����(1)�����ּӱ꣬���ļ�0λ�ÿ�ʼ���䡣
	 * �����������ļ�test.txt���ڷ�����ģʽ�£�Ҫ��֤���ܸ��Ƿ���˵��ļ�����˷��������������ϴ����ļ��Զ�����Ϊtest(1).txt���������test.txt��test(1).txt�����ڣ����Զ�����test(2).txt���Դ����ơ�
	 * filemodeֵ���ڵ���1������ģʽ������˴���ģʽΪ����������޸��ļ����򴴽����ļ��������ļ�0λ�ÿ�ʼ���䣻��������и��ļ�����ȡ���ļ����ȣ���֮�ͻ��ˣ��ͻ���ȡ���ļ�����֮������ݴ�������ˣ�ʵ����������
	 * ����˷�����Ϣ��ʽ
	 * ��1�У�״̬����ok��Ϊ����������Ϊ�쳣��Ϣ
	 * ��2�У� ��Ϊ"ok"״̬��������������ơ�ulf��
	 * ��3�У���Ϊ"ok"״̬�����ض˿ںţ��ַ�����Ϣ)
	 * ��4�У� ��Ϊ"ok"״̬,���ط�����ļ��ĳ��ȣ��ַ�����Ϣ)��������ģʽ����ֵΪ0
	 * ����˵������������⿪��һ���̺߳�ServerSocket�ṩ�ϴ����������Ҫ�����¿�ServerSocket�Ķ˿ںţ�����ͻ���������ģʽ����Ҫ֪��������Ѵ����ݴ�С�������Ҫ���ط�����ļ����ȣ��ó�����ָ���ļ�ͷ��filePosition=0��ʼ���ļ�β�ĳ��ȡ�
	 */
	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		int splitIdx=cmdBody.indexOf("?");//
		String filename="";
		int mode=0;
		if(splitIdx<1){
			filename=cmdBody;
		}else{
			String[] array=cmdBody.split("?");
			filename=array[0];
			mode=Integer.parseInt(array[1]);
		}
		fup=new FileUpLoadSocketThread(new File(filename), mode);
		fup.start();
//		ftd.join();
		System.err.println(fup);
		ackMsg.add("ok");
		ackMsg.add("DLF");
		ackMsg.add(""+fup.get_port());
		ackMsg.add(""+fup.get_fileLength());
		return ackMsg;
	}

}
