package wuwei.server.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CommonRobot {
	 /** 
     * ��굥���������,Ҫ˫������������ 
     *  
     * @param r 
     * @param x 
     *            x����λ�� 
     * @param y 
     *            y����λ�� 
     * @param delay 
     *            �ò�������ӳ�ʱ�� 
     */  
    public static void clickLMouse(Robot r, int x, int y, int delay) {  
        r.mouseMove(x, y);  
        r.mousePress(InputEvent.BUTTON1_MASK);  
        r.delay(10);  
        r.mouseRelease(InputEvent.BUTTON1_MASK);  
        r.delay(delay);  
  
    }  
  
    /** 
     * ����һ�,Ҫ˫������������ 
     *  
     * @param r 
     * @param x 
     *            x����λ�� 
     * @param y 
     *            y����λ�� 
     * @param delay 
     *            �ò�������ӳ�ʱ�� 
     */  
    public static void clickRMouse(Robot r, int x, int y, int delay) {  
        r.mouseMove(x, y);  
        r.mousePress(InputEvent.BUTTON3_MASK);  
        r.delay(10);  
        r.mouseRelease(InputEvent.BUTTON3_MASK);  
        r.delay(delay);  
  
    }  
  
    /** 
     * �������루һ��ֻ������һ���ַ��� 
     *  
     * @param r 
     * @param ks 
     *            ����������ַ����� 
     * @param delay 
     *            ����һ��������ӳ�ʱ�� 
     */  
    public static void pressKeys(Robot r, int[] ks, int delay) {  
        for (int i = 0; i < ks.length; i++) {  
            r.keyPress(ks[i]);  
            r.delay(delay);  
        }  
    }  
    /** 
     * �������루һ��ֻ������һ���ַ��� 
     *  
     * @param r 
     * @param ks 
     *            ����������ַ����� 
     * @param delay 
     *            ����һ��������ӳ�ʱ�� 
     */  
    public static void upKeys(Robot r, int[] ks, int delay) {  
        for (int i = 0; i < ks.length; i++) {  
            r.keyRelease(ks[i]);  
            r.delay(delay);  
        }  
    } 
    /** 
     * ���� 
     *  
     * @param r 
     * @throws InterruptedException 
     */  
    void doCopy(Robot r) throws InterruptedException {  
        Thread.sleep(3000);  
        r.setAutoDelay(200);  
        r.keyPress(KeyEvent.VK_CONTROL);  
        r.keyPress(KeyEvent.VK_C);  
        r.keyRelease(KeyEvent.VK_CONTROL);  
        r.keyRelease(KeyEvent.VK_C);  
    }  
  
    /** 
     * ճ�� 
     *  
     * @param r 
     * @throws InterruptedException 
     */  
    void doParse(Robot r) throws InterruptedException {  
        r.setAutoDelay(500);  
        Thread.sleep(2000);  
        r.mouseMove(300, 300);  
        r.mousePress(InputEvent.BUTTON1_MASK);  
        r.mouseRelease(InputEvent.BUTTON1_MASK);  
        r.keyPress(KeyEvent.VK_CONTROL);  
        r.keyPress(KeyEvent.VK_V);  
        r.keyRelease(KeyEvent.VK_CONTROL);  
        r.keyRelease(KeyEvent.VK_V);  
    }  
  
    /** 
     * ��׽ȫ��Ľ 
     *  
     * @param r 
     * @return 
     */  
    public Icon captureFullScreen(Robot r) {  
        BufferedImage fullScreenImage = r.createScreenCapture(new Rectangle(  
                Toolkit.getDefaultToolkit().getScreenSize()));  
        ImageIcon icon = new ImageIcon(fullScreenImage);  
        return icon;  
    }  
  
    /** 
     * ��׽��Ļ��һ���������� 
     *  
     * @param r 
     * @param x 
     *            x����λ�� 
     * @param y 
     *            y����λ�� 
     * @param width 
     *            ���εĿ� 
     * @param height 
     *            ���εĸ� 
     * @return 
     */  
    public Icon capturePartScreen(Robot r, int x, int y, int width, int height) {  
        r.mouseMove(x, y);  
        BufferedImage fullScreenImage = r.createScreenCapture(new Rectangle(  
                width, height));  
        ImageIcon icon = new ImageIcon(fullScreenImage);  
        return icon;  
    }
}
