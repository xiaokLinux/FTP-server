package test;

import java.io.File;
import java.text.DecimalFormat;

import javax.swing.filechooser.FileSystemView;


public class mian {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File[] rootdirs=File.listRoots();
		 for(int i=0;i<rootdirs.length;i++)
		    {
		      System.out.println("下面是"+rootdirs[i]+"分区的搜索");
		}
		// TODO Auto-generated method stub
//		FileSystemView fsv = FileSystemView.getFileSystemView();	
//		File[] fs = File.listRoots();
//		for (int i = 0; i < fs.length; i++) {
//            System.out.println(fsv.getSystemDisplayName(fs[i]));
//            System.out.print("总大小" + FormetFileSize(fs[i].getTotalSpace()));
//            System.out.println("剩余" + FormetFileSize(fs[i].getFreeSpace()));
//        }
		
	}

	private static String FormetFileSize(long fileS) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
	}

}
