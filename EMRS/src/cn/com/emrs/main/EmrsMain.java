package cn.com.emrs.main;

import java.applet.Applet;

import cn.com.emrs.ui.MainFrame;

public class EmrsMain extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8655424191217522530L;

	public void init(){

		MainFrame frame = new MainFrame("设备维修记录管理系统");
		
		frame.setLocation(100, 100);
		frame.setVisible(true);
	}
}
