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
		ArrayList<String> ackMsg=new ArrayList<String>();//����ֵ
		int splitId=cmdBody.indexOf(",");
		double a,b;
		System.out.println("[CHCK] splitId"+splitId);
		if(splitId>0){
			String arr[]=cmdBody.split(",");
			a=Double.parseDouble(arr[0]);
			b=Double.parseDouble(arr[1]);
			System.out.println("[WW]: a="+a+"\tb="+b);
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();                    // ���Toolkit����
			Dimension dimension = toolkit.getScreenSize();     // ���Dimension����
			int screenHeight = dimension.height;               // �����Ļ�ĸ߶�
			int screenWidth = dimension.width;                 // �����Ļ�Ŀ��
			final Robot rb=new Robot();
			int ia=(int)a;
			int ib=(int)b;
			if(ia==a || ib==b){
				//�� һ������
				//�����ƶ���ĵ�����
				int wx=px+ia;
				int wy=py+ib;
				//Խ���жϣ��������������Ļ����
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
				//�ƶ���꣬���Ҹ�������������
				px=wx;
				py=wy;
				System.out.println("[CHECK POS] int a  and int b");
				rb.mouseMove(px, py);
			}else{
				//�����ƶ�
				int wx=screenWidth*ia;
				int wy=screenHeight*ib;
				//Խ���жϣ��������������Ļ����
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
				//�ƶ���꣬���Ҹ�������������
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
