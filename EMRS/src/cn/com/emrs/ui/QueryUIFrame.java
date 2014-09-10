package cn.com.emrs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import cn.com.emrs.impl.QueryActionImpl;
import cn.com.emrs.itf.IFieldConstant;
import cn.com.emrs.util.EmrsTools;
import cn.com.emrs.util.PagingTool;

/**
 * 查询窗口
 * @author 小尹
 *
 */
public class QueryUIFrame extends JFrame implements WindowListener,ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7877492369028060925L;

	private static final Logger log = Logger.getLogger(QueryUIFrame.class);
	JButton queryBtn,firstPageBtn,nextPageBtn,prePageBtn,endPageBtn;
	private JFrame parentFrame;
	private JTable jtable;
	private boolean modal = true;
	private DefaultTableModel model;
	
	JTextField enameTextField;
	JTextField startTimeField;
	JTextField endTimeField;
	JLabel pageJLabel;
	PagingTool pt = null;
	
	public QueryUIFrame(JFrame parentFrame,String title){
		super(title);
		this.parentFrame = parentFrame;
		if(modal){
			this.parentFrame.setEnabled(false);
		}
		this.addWindowListener(this);
		
		log.info("Init queryUI...");
		
		this.init();
		
		log.info("QueryUI init successful !");
	}
	
	/**
	 * 初始化查询窗口
	 */
	private void init(){
		//查询条件行
		JPanel headPanel = new JPanel();
		queryBtn = new JButton("查询");
		queryBtn.addActionListener(this);
		
		JLabel enameLabel = new JLabel("设备名称",SwingConstants.CENTER);
		JLabel startTimeLabel = new JLabel("起始时间",SwingConstants.CENTER);
		JLabel endTimeLabel = new JLabel("结束时间",SwingConstants.CENTER);
		
		enameTextField = new JTextField(10);
		startTimeField = new JTextField(EmrsTools.getCurrentTime().length());
		endTimeField = new JTextField(EmrsTools.getCurrentTime().length());
		
		headPanel.add(enameLabel);
		headPanel.add(enameTextField);
		headPanel.add(startTimeLabel);
		headPanel.add(startTimeField);
		headPanel.add(endTimeLabel);
		headPanel.add(endTimeField);
		headPanel.add(queryBtn);
		
		//表格
		String[] columnNames = IFieldConstant.columns;
		model = new DefaultTableModel(columnNames,30){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		model.setColumnIdentifiers(columnNames);
		jtable = new JTable();
		jtable.setModel(model);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//分页按钮
		pageJLabel = new JLabel("共0条记录1页|当前1页");
		firstPageBtn = new JButton("首页");
		firstPageBtn.addActionListener(this);
		
		prePageBtn = new JButton("上一页");
		prePageBtn.addActionListener(this);
		
		nextPageBtn = new JButton("下一页");
		nextPageBtn.addActionListener(this);
		
		endPageBtn = new JButton("尾页");
		endPageBtn.addActionListener(this);
		
		JPanel pagePanel = new JPanel();
		pagePanel.add(pageJLabel);
		pagePanel.add(new JLabel());
		pagePanel.add(new JLabel());
		pagePanel.add(new JLabel());
		pagePanel.add(new JLabel());
		pagePanel.add(new JLabel());
		pagePanel.add(new JLabel());
		pagePanel.add(firstPageBtn);
		pagePanel.add(prePageBtn);
		pagePanel.add(nextPageBtn);
		pagePanel.add(endPageBtn);
		
		this.add("North",headPanel);
		this.add("Center",(new JScrollPane(jtable)));
		this.add("South",pagePanel);
		this.pack();
		
		this.setVisible(true);
	}
	
	/*private String[] getColumns(){
		return new String[]{
				"日期",
				"设备名称",
				"零部件名称",
				"备件价值",
				"材料名称",
				"材料费用",
				"旧件利用价值",
				"电工数量",
				"钳工数量",
				"管工数量",
				"起重工数量",
				"焊工数量",
				"修理工时起始时间",
				"工时（分钟）",
				"用能费用",
				"参加人员",
				"验收人",
				"参加人员是否可减少",
				"是否有改进措施",
				"修理内容",
				"故障原因分析"
		};
	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == queryBtn){
			//查询处理
			StringBuilder where = new StringBuilder();
			if(!StringUtils.isEmptyOrWhitespaceOnly(enameTextField.getText())){
				if(!StringUtils.isEmptyOrWhitespaceOnly(where.toString())){
					where.append(" and ");
				}
				where.append(" ename = '");
				where.append(enameTextField.getText());
				where.append("' ");
			}
			if(!StringUtils.isEmptyOrWhitespaceOnly(startTimeField.getText())){
				if(!StringUtils.isEmptyOrWhitespaceOnly(where.toString())){
					where.append(" and ");
				}
				where.append(" billdate >= '");
				where.append(startTimeField.getText());
				where.append("' ");
			}
			if(!StringUtils.isEmptyOrWhitespaceOnly(endTimeField.getText())){
				if(!StringUtils.isEmptyOrWhitespaceOnly(where.toString())){
					where.append(" and ");
				}
				where.append(" billdate <= '");
				where.append(endTimeField.getText());
				where.append("' ");
			}
			String[][] datas = (new QueryActionImpl()).doQuery(where.toString());
			pt = new PagingTool(jtable, datas, 5);
			int curPage = pt.firstPage();
			pageJLabel.setText("共" + datas.length + "条记录" + (datas.length%5 == 0 ? datas.length/5 : datas.length/5 + 1) + "页|当前第" + curPage + "页");
		}
		//首页
		if(e.getSource() == firstPageBtn){
			if(pt != null){
				int curPage = pt.firstPage();
				pageJLabel.setText(pageJLabel.getText().substring(0, pageJLabel.getText().indexOf("第")+1) + curPage + "页");
			}
		}
		//尾页
		if(e.getSource() == endPageBtn){
			if(pt != null){
				int curPage = pt.endPage();
				pageJLabel.setText(pageJLabel.getText().substring(0, pageJLabel.getText().indexOf("第")+1) + curPage + "页");
			}
		}
		//上一页
		if(e.getSource() == prePageBtn){
			if(pt != null){
				int curPage = pt.prePage();
				pageJLabel.setText(pageJLabel.getText().substring(0, pageJLabel.getText().indexOf("第")+1) + curPage + "页");
			}
		}
		//下一页
		if(e.getSource() == nextPageBtn){
			int curPage = pt.nextPage();
			pageJLabel.setText(pageJLabel.getText().substring(0, pageJLabel.getText().indexOf("第")+1) + curPage + "页");
		}
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
		System.out.println("");
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

}
