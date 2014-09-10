package cn.com.emrs.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * @author 小尹
 *	主界面
 */
public class MainFrame extends  JFrame implements MenuListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3026717972420281191L;

	JMenuBar menuBar;
	JMenu addMenu,queryMenu,countMenu;
	JMenuItem addMenuItem,queryMenuItem,countMenuItem;
	
	public MainFrame(String name){
		super(name);
		this.setLayout(new BorderLayout(1,1));
		//定义按钮
		menuBar = new JMenuBar();
		
		addMenu = new JMenu("新增");
		queryMenu = new JMenu("查询");	
		
		
		addMenu.addMenuListener(this);
		queryMenu.addMenuListener(this);
		
		//绑定frame
		menuBar.add(addMenu);
		menuBar.add(queryMenu);
//		menuBar.add(countMenu);
		JDesktopPane desktopPane = new JDesktopPane();
//		File file = new File(".");
//		String p = file.getParent();
		ImageIcon icon = new ImageIcon("C:\\Users\\小尹\\Desktop\\软件素材\\项目资料\\logo.jpg");
		JLabel label = new JLabel(icon);
//		label.setIcon(icon);
		label.setBounds(0, 0, icon.getIconHeight(), icon.getIconWidth());
		desktopPane.add(label,new Integer(Integer.MIN_VALUE));
		
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

        this.add(desktopPane);
		this.setJMenuBar(menuBar);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
//		new AddUIFrame(this,"新增设备维修记录");
		
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addMenu){
			
			new AddUIFrame(this,"新增设备维修记录");
		}else if(e.getSource() == queryMenu){
			new QueryUIFrame(this, "维修记录查询");
		}
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class imagePanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			ImageIcon icon = new ImageIcon("C:\\Users\\小尹\\Desktop\\软件素材\\项目资料\\图标.jpg");
			g.drawImage(icon.getImage(), 0, 0, null);
		}
	}
}
