package cn.com.emrs.util;

import java.awt.Component;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmrsTools {
	
	public static EmrsTools instance;
	
	private EmrsTools(){
		
	}
	
	public static EmrsTools getInstance(){
		if(null == instance){
			return new EmrsTools();
		}
		return instance;
	}
	
	/**
	 * 获取界面值
	 * @param c
	 * @return
	 */
	public Map<String,String> getUIValues(Component c){
		String key = "" ,value = "";
		Map<String,String> map = new HashMap<String, String>();
		Container container = c.getParent();
		Component[] components = container.getComponents();
		for(Component component : components){
			if(component instanceof JTextField){
				key = ((JTextField)component).getName();
				value = ((JTextField)component).getText();
			}else if(component instanceof JTextArea){
				key = ((JTextArea)component).getName();
				value = ((JTextArea)component).getText();
			}else if(component instanceof JComboBox){
				key = ((JComboBox)component).getName();
				value = (String) ((JComboBox)component).getSelectedItem();
			}
			map.put(key, value);
		}
		
		return map;
	}
	
	public static String getCurrentTime(){
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}
	
}
