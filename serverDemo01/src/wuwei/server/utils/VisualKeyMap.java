package wuwei.server.utils;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class VisualKeyMap {
	//单例模式，保存所有的vk和宏的对应信息
	//只此一张
	private static HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
    private static final VisualKeyMap VISUAL_KEY_MAP = new VisualKeyMap();

    private VisualKeyMap() {// 在私有的构造函数中对hashVisualKeyMap赋值，完成映射对象的赋值，该构造函数只在
        // private static final VisualKeyMap VISUAL_KEY_MAP=new  VisualKeyMap()静态变量中new出一次
        // 无论调用多少次VisualKeyMap.getInstance()方法只返回VISUAL_KEY_MAP对象，而不会再调用构造函数new出对象
        // 若要取客户端发送的"vk_space"对应的java.awt.event.KeyEvent.VK_SPACE值，则可通过VisualKeyMap.getInstance().getVisualKey("vk_space")实现
    	
    	// 此处省略需要增加的映射操作
        hashMap.put("VK_0", KeyEvent.VK_0);//大写的Key，以方便录入，客户端发送大小写不区分
        hashMap.put("VK_ENTER", KeyEvent.VK_ENTER);//回车
     	hashMap.put("VK_ESCAPE", KeyEvent.VK_ESCAPE);//esc
     	hashMap.put("VK_ALT", KeyEvent.VK_ALT);//alt
     	hashMap.put("VK_F4", KeyEvent.VK_F4);//F4 
     	hashMap.put("VK_WINDOWS", KeyEvent.VK_WINDOWS);//windows
     	hashMap.put("VK_R", KeyEvent.VK_R);//windows
     	hashMap.put("VK_E", KeyEvent.VK_E);//windows
     	
    }


    public static int getVisualKey(String key) {
        //调用时只需VisualKeyMap.getVisualKey(String key)即可
        return hashMap.get(key.toUpperCase());//把key转为大写
    }
}
