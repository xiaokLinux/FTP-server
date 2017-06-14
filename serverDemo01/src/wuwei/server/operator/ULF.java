package wuwei.server.operator;

import java.io.File;
import java.util.ArrayList;

import wuwei.server.socket.FileDownLoadSocketThread;
import wuwei.server.socket.FileUpLoadSocketThread;

public class ULF extends BaseOperator {

	private FileUpLoadSocketThread fup;
	/**
	 * ulf:remoteFile_path_file_name
	 * 功能：上传远程服务端指定文件（含目录信息），非续传模式
	 * ulf:remoteFile_path_file_name?filemode
	 * ulf:remoteFile_path_file_name?filemode
	 * filemode值为-1，非续传模式，若服务端无相应文件，则创建文件，并从文件0位置开始传输；若服务端有相应文件，则创建新文件，文件的名称在原文件上用(1)等数字加标，从文件0位置开始传输。
	 * 例如服务端有文件test.txt，在非续传模式下，要保证不能覆盖服务端的文件，因此服务端需程序处理，对上传的文件自动改名为test(1).txt；若服务端test.txt和test(1).txt均存在，则自动创建test(2).txt，以此类推。
	 * filemode值大于等于1，续传模式，服务端处理模式为：若服务端无该文件，则创建该文件，并从文件0位置开始传输；若服务端有该文件，则取该文件长度，告之客户端，客户端取该文件长度之后的内容传给服务端，实现续传功能
	 * 服务端返回信息格式
	 * 第1行：状态，“ok”为正常，否则为异常信息
	 * 第2行： 若为"ok"状态，返回命令的名称“ulf”
	 * 第3行：若为"ok"状态，返回端口号（字符串信息)
	 * 第4行： 若为"ok"状态,返回服务端文件的长度（字符串信息)，非续传模式返回值为0
	 * 额外说明：服务端另外开启一个线程和ServerSocket提供上传服务，因此需要返回新开ServerSocket的端口号，另外客户端在续传模式下需要知道服务端已传数据大小，因此需要返回服务端文件长度，该长度是指从文件头部filePosition=0开始到文件尾的长度。
	 */
	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
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
