package wuwei.server.utils;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class VisualKeyMap {
	//����ģʽ���������е�vk�ͺ�Ķ�Ӧ��Ϣ
	//ֻ��һ��
	private static HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
    private static final VisualKeyMap VISUAL_KEY_MAP = new VisualKeyMap();

    private VisualKeyMap() {// ��˽�еĹ��캯���ж�hashVisualKeyMap��ֵ�����ӳ�����ĸ�ֵ���ù��캯��ֻ��
        // private static final VisualKeyMap VISUAL_KEY_MAP=new  VisualKeyMap()��̬������new��һ��
        // ���۵��ö��ٴ�VisualKeyMap.getInstance()����ֻ����VISUAL_KEY_MAP���󣬶������ٵ��ù��캯��new������
        // ��Ҫȡ�ͻ��˷��͵�"vk_space"��Ӧ��java.awt.event.KeyEvent.VK_SPACEֵ�����ͨ��VisualKeyMap.getInstance().getVisualKey("vk_space")ʵ��
    	
    	// �˴�ʡ����Ҫ���ӵ�ӳ�����
        hashMap.put("VK_0", KeyEvent.VK_0);//��д��Key���Է���¼�룬�ͻ��˷��ʹ�Сд������
        hashMap.put("VK_ENTER", KeyEvent.VK_ENTER);//�س�
     	hashMap.put("VK_ESCAPE", KeyEvent.VK_ESCAPE);//esc
     	hashMap.put("VK_ALT", KeyEvent.VK_ALT);//alt
     	hashMap.put("VK_F4", KeyEvent.VK_F4);//F4 
     	hashMap.put("VK_WINDOWS", KeyEvent.VK_WINDOWS);//windows
     	hashMap.put("VK_R", KeyEvent.VK_R);//windows
     	hashMap.put("VK_E", KeyEvent.VK_E);//windows
     	
    }


    public static int getVisualKey(String key) {
        //����ʱֻ��VisualKeyMap.getVisualKey(String key)����
        return hashMap.get(key.toUpperCase());//��keyתΪ��д
    }
}
