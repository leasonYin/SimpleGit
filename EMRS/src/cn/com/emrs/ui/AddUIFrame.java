package cn.com.emrs.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import cn.com.emrs.impl.SaveActionImpl;
import cn.com.emrs.itf.IFieldConstant;
import cn.com.emrs.util.EmrsTools;

/**
 * @author 小尹
 * 新增界面
 */
@SuppressWarnings("serial")
public class AddUIFrame extends JFrame implements WindowListener,ActionListener {

	private static final Logger log = Logger.getLogger(AddUIFrame.class);
	/**
	 * 父窗口
	 */
	private JFrame parentFrame;
	private boolean modal = true;
	
	JLabel dateLabel, // 日期
			enameLabel, // 设备名称
			partNameLabel, // 零部件名称
			partValueLabel, // 备件价值
			matrNameLabel, // 材料名称
			matrCostLabel, // 材料费用
			opuValueLabel; // 旧件利用价值

	JLabel electnumLabel, // 電工數
			fitternumLabel,// 鉗工數
			plumbernumLabel,// 管工数
			cranumLabel, // 起重工数量
			weldornumLabel,// 焊工数
			starttimeLabel,// 修理起始时间
			costtimeLabel,// 工时（分钟）
			costLabel;// 用能费用

	JLabel fixcontentLabel,// 修理内容
			errorreasonLabel,// 故障原因
			joinerLabel, // 参加人
			checkerLabel, // 审核人
			bcutLabel, // 参加人是否可减少
			bbetterLabel; // 是否有改进措施

	JTextField billdate, // 日期
			ename, // 设备名称
			partname, // 零部件名称
			partvalue, // 备件价值
			matrname, // 材料名称
			matrcost, // 材料费用
			opuvalue; // 旧件利用价值

	JTextField electnum, fitternum, plumbernum, cranum,
			weldornum, starttime, costtime, cost,joiner, checker;

	JTextArea fixcontent, errorreason;
	JComboBox bcut, bbetter;
	JButton sure,cancel;

	public AddUIFrame(JFrame frame,String title) {

		super(title);
		this.parentFrame = frame;
		if(modal){
			parentFrame.setEnabled(false);
		}
		
		this.addWindowListener(this);
		
		//初始化窗口
		log.info("Init UI ...");
		init();
		log.info("UI init successful !");
	}
	
	private void init(){
		
		dateLabel = new JLabel(IFieldConstant.BILLDATE, SwingConstants.CENTER);
		billdate = new JTextField(EmrsTools.getCurrentTime());

		enameLabel = new JLabel(IFieldConstant.ENAME, SwingConstants.CENTER);
		ename = new JTextField();

		partNameLabel = new JLabel(IFieldConstant.PARTNAME,
				SwingConstants.CENTER);
		partname = new JTextField();

		partValueLabel = new JLabel(IFieldConstant.PARTVALUE,
				SwingConstants.CENTER);
		partvalue = new JTextField();

		matrNameLabel = new JLabel(IFieldConstant.MATRNAME,
				SwingConstants.CENTER);
		matrname = new JTextField();

		matrCostLabel = new JLabel(IFieldConstant.MATRCOST,
				SwingConstants.CENTER);
		matrcost = new JTextField();

		opuValueLabel = new JLabel(IFieldConstant.OPUVALUE,
				SwingConstants.CENTER);
		opuvalue = new JTextField();

		//
		
		electnumLabel = new JLabel(IFieldConstant.ELECTNUM, SwingConstants.CENTER);
		electnum = new JTextField();
		
		fitternumLabel = new JLabel(IFieldConstant.FITTERNUM,SwingConstants.CENTER);
		fitternum = new JTextField();
		
		plumbernumLabel = new JLabel(IFieldConstant.PLUMBERNUM,SwingConstants.CENTER);
		plumbernum = new JTextField();
		
		cranumLabel = new JLabel(IFieldConstant.CRANUM,SwingConstants.CENTER);
		cranum = new JTextField();
		
		weldornumLabel = new JLabel(IFieldConstant.WELDORNUM,SwingConstants.CENTER);
		weldornum = new JTextField();
		
		starttimeLabel = new JLabel(IFieldConstant.STARTTIME,SwingConstants.CENTER);
		starttime = new JTextField();
		
		costtimeLabel = new JLabel(IFieldConstant.COSTTIME,SwingConstants.CENTER);
		costtime = new JTextField();
		
		costLabel = new JLabel(IFieldConstant.COST,SwingConstants.CENTER);
		cost = new JTextField();
		
		//
		joinerLabel = new JLabel(IFieldConstant.JOINER,SwingConstants.CENTER);
		joiner = new JTextField();
		
		checkerLabel = new JLabel(IFieldConstant.CHECKER,SwingConstants.CENTER);
		checker = new JTextField();
		
		bcutLabel = new JLabel(IFieldConstant.BCUT,SwingConstants.CENTER);
		bcut = new JComboBox(new String[]{"是","否"});
		
		bbetterLabel = new JLabel(IFieldConstant.BBETTER,SwingConstants.CENTER);
		bbetter = new JComboBox(new String[]{"是","否"});
		
		fixcontentLabel = new JLabel(IFieldConstant.FIXCONTENT,SwingConstants.CENTER);
		fixcontent = new JTextArea(8,6);
		
		errorreasonLabel = new JLabel(IFieldConstant.ERRORREASON,SwingConstants.CENTER);
		errorreason = new JTextArea(8,6);
		
		JPanel btnPanel = new JPanel();
		JPanel upPanel = new JPanel(new GridLayout(7, 6));
		JPanel downPanel = new JPanel();
		downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.Y_AXIS));
		
		sure = new JButton("保存");
		cancel = new JButton("取消");
		sure.addActionListener(this);
		cancel.addActionListener(this);
		
		btnPanel.add(sure);
		btnPanel.add(cancel);
		
		upPanel.add(dateLabel);
		upPanel.add(billdate);
		upPanel.add(new JLabel());
		upPanel.add(new JLabel());
		upPanel.add(new JLabel());
		upPanel.add(new JLabel());
		upPanel.add(enameLabel);
		upPanel.add(ename);
		upPanel.add(partNameLabel);
		upPanel.add(partname);
		upPanel.add(partValueLabel);
		upPanel.add(partvalue);
		upPanel.add(matrNameLabel);
		upPanel.add(matrname);
		upPanel.add(matrCostLabel);
		upPanel.add(matrcost);
		upPanel.add(opuValueLabel);
		upPanel.add(opuvalue);
		//
		upPanel.add(electnumLabel);
		upPanel.add(electnum);
		upPanel.add(fitternumLabel);
		upPanel.add(fitternum);
		upPanel.add(plumbernumLabel);
		upPanel.add(plumbernum);
		upPanel.add(cranumLabel);
		upPanel.add(cranum);
		upPanel.add(weldornumLabel);
		upPanel.add(weldornum);
		upPanel.add(starttimeLabel);
		upPanel.add(starttime);
		upPanel.add(costtimeLabel);
		upPanel.add(costtime);
		upPanel.add(costLabel);
		upPanel.add(cost);
		//
		upPanel.add(joinerLabel);
		upPanel.add(new JScrollPane(joiner));
		upPanel.add(checkerLabel);
		upPanel.add(checker);
		upPanel.add(bcutLabel);
		upPanel.add(bcut);
		upPanel.add(bbetterLabel);
		upPanel.add(bbetter);
		
		JPanel p1 = new JPanel(new GridLayout(1, 6));
		JPanel p2 = new JPanel(new GridLayout(1, 6));
		
		p1.add(fixcontentLabel);
		p2.add(errorreasonLabel);
		//补位
		for(int i = 0; i < 5 ; i++){
			p1.add(new JLabel());
			p2.add(new JLabel());
		}
		
		downPanel.add(p1);
		downPanel.add(new JScrollPane(fixcontent));
		downPanel.add(p2);
		downPanel.add(new JScrollPane(errorreason));
		downPanel.add(btnPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(upPanel);
		panel.add(downPanel);
		
		this.add(panel);
		this.setLocation(100, 100);
		this.pack();
		this.setVisible(true);
	}

	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(modal){
			parentFrame.setEnabled(true);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		if(modal){
			this.requestFocus();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == sure){
			//保存数据
			saveDatas();
			//关闭窗口
			WindowEvent event=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
			this.dispatchEvent(event);
		}else if(e.getSource() == cancel){
			  WindowEvent event=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
			    this.dispatchEvent(event);

		}
	}
	
	private void saveDatas() {
		log.info("Saving datas...");
		//1.校验
		
		//2.保存
		String[] values = getValues();
		SaveActionImpl saveActionImpl = new SaveActionImpl();
		saveActionImpl.doAction(values);
		
	}

	/**
	 * 返回界面值，按数据库字段顺序
	 * @return
	 */
	private String[] getValues(){
		return new String[]{
			this.billdate.getText(),
			this.ename.getText(),
			this.fixcontent.getText(),
			this.errorreason.getText(),
			this.partname.getText(),
			this.partvalue.getText().equals("") ? "0" : this.partvalue.getText(),
			this.matrname.getText(),
			this.matrcost.getText().equals("") ? "0" : this.matrcost.getText(),
			this.opuvalue.getText().equals("") ? "0" : this.opuvalue.getText(),
			this.electnum.getText().equals("") ? "0" : this.electnum.getText(),
			this.fitternum.getText().equals("") ? "0" : this.fitternum.getText(),
			this.plumbernum.getText().equals("") ? "0" : this.plumbernum.getText(),
			this.cranum.getText().equals("") ? "0" : this.cranum.getText(),
			this.weldornum.getText().equals("") ? "0" : this.weldornum.getText(),
			this.starttime.getText(),
			this.costtime.getText().equals("") ? "0" : this.costtime.getText(),
			this.cost.getText().equals("") ? "0" : this.cost.getText(),
			this.joiner.getText(),
			this.checker.getText(),
			(String) this.bcut.getSelectedItem(),
			(String) this.bbetter.getSelectedItem()
				
		};
	}
}
