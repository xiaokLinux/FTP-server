package test;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.filechooser.FileSystemView;


public class mian {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		myclip();
		System.out.println("over");
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
	public static void myclip(){
		String refContent="android2";
		String vc = refContent.trim();  
	    StringSelection ss = new StringSelection(vc);  
	      
	    Clipboard sysClb=null;  
	    sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();  
	    sysClb.setContents(ss,null);  
	}
}
