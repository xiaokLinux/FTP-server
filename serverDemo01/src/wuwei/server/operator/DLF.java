package wuwei.server.operator;

import java.io.File;
import java.util.ArrayList;

import wuwei.server.socket.FileDownLoadSocketThread;

public class DLF extends BaseOperator {

	private FileDownLoadSocketThread ftd;
	/**
	 * dlf:remoteFile_path_file_name
	 * 功能：下载远程服务端指定文件（含目录信息)
	 * dlf:remoteFile_path_file_name?filePosition
	 * 功能:下载远程服务端指定文件（含目录信息）,并从第filePosition处开始下载（断点续传功能）
	 * 服务端返回信息格式
	 * 第1行：状态，“ok”为正常，否则为异常信息
	 * 第2行： 若为"ok"状态，返回命令的名称“dlf”
	 * 第3行：若为"ok"状态，返回端口号（字符串信息)
	 * 第4行： 若为"ok"状态,返回文件长度（字符串信息)
	 * 额外说明：服务端另外开启一个线程和ServerSocket提供下载服务，因此需要返回新开ServerSocket的端口号，另外客户端需要实时知道下载进度，因此需要返回文件总长度，该总长度是指从filePosition=0开始到文件尾的总长度。
	 */
	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
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
