package wuwei.server.operator;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;


public class MOV {
	private static int px=0,py=0;
	

	public static ArrayList<String> exe(String cmdBody) throws AWTException{
		ArrayList<String> ackMsg=new ArrayList<String>();//返回值
		int splitId=cmdBody.indexOf(",");
		double a,b;
		System.out.println("[CHCK] splitId"+splitId);
		if(splitId>0){
			String arr[]=cmdBody.split(",");
			a=Double.parseDouble(arr[0]);
			b=Double.parseDouble(arr[1]);
			System.out.println("[WW]: a="+a+"\tb="+b);
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();                    // 获得Toolkit对象
			Dimension dimension = toolkit.getScreenSize();     // 获得Dimension对象
			int screenHeight = dimension.height;               // 获得屏幕的高度
			int screenWidth = dimension.width;                 // 获得屏幕的宽度
			final Robot rb=new Robot();
			int ia=(int)a;
			int ib=(int)b;
			if(ia==a || ib==b){
				//有 一个整数
				//计算移动后的点坐标
				int wx=px+ia;
				int wy=py+ib;
				//越界判断，鼠标坐标落在屏幕外面
				if(wx<0){
					wx=0;
				}
				if(wx>screenWidth){
					wx=screenWidth;
				}
				if(wy<0){
					wy=0;
				}
				if(wy>screenHeight){
					wy=screenHeight;
				}
				//移动鼠标，并且更新鼠标的做坐标
				px=wx;
				py=wy;
				System.out.println("[CHECK POS] int a  and int b");
				rb.mouseMove(px, py);
			}else{
				//比例移动
				int wx=screenWidth*ia;
				int wy=screenHeight*ib;
				//越界判断，鼠标坐标落在屏幕外面
				if(wx<0){
					wx=0;
				}
				if(wx>screenWidth){
					wx=screenWidth;
				}
				if(wy<0){
					wy=0;
				}
				if(wy>screenHeight){
					wy=screenHeight;
				}
				//移动鼠标，并且更新鼠标的做坐标
				px=wx;
				py=wy;
				System.out.println("[CHECK POS] both a or b not int ");
				rb.mouseMove(px, py);
			}
		}
		return ackMsg;
	}
//	class myMouseMonListernr implements MouseMotionListener{
//
//		private int x,y;
//		@Override
//		public void mouseDragged(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseMoved(MouseEvent e) {
//			// TODO Auto-generated method stub
//			this.x = e.getX();
//			this.y = e.getY();
//		}
//		
//	}
}
