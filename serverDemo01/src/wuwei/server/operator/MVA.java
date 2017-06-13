package wuwei.server.operator;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;
/**
 * 鼠标的绝对移动
 * @author Administrator
 *
 */
public class MVA extends BaseOperator {

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
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
			if(a>1 || b>1){
				//有 一个整数
				//计算移动后的点坐标
				int wx=(int) a;
				int wy=(int) b;
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

				System.out.println("[CHECK POS] int a  and int b");
				rb.mouseMove(wx, wy);
				MOV.setPx(wx);
				MOV.setPy(wy);
			}else{
				//比例移动
				int wx=(int) (screenWidth*a);
				int wy=(int) (screenHeight*b);
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
				System.out.println("[CHECK POS] both a or b not int ");
				rb.mouseMove(wx, wy);
				MOV.setPx(wx);
				MOV.setPy(wy);
			}
		}
		ackMsg.add("ok");
		ackMsg.add("MVA(now):"+MOV.getPx()+","+MOV.getPy());
		return ackMsg;
	}

}
