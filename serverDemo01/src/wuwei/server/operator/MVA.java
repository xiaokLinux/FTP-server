package wuwei.server.operator;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;
/**
 * ���ľ����ƶ�
 * @author Administrator
 *
 */
public class MVA extends BaseOperator {

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
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
			if(a>1 || b>1){
				//�� һ������
				//�����ƶ���ĵ�����
				int wx=(int) a;
				int wy=(int) b;
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

				System.out.println("[CHECK POS] int a  and int b");
				rb.mouseMove(wx, wy);
				MOV.setPx(wx);
				MOV.setPy(wy);
			}else{
				//�����ƶ�
				int wx=(int) (screenWidth*a);
				int wy=(int) (screenHeight*b);
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
