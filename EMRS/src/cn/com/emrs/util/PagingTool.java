package cn.com.emrs.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.emrs.itf.IFieldConstant;

/**
 * 分页处理工具类
 * @author 小尹
 *
 */
public class PagingTool {
	private JTable jTable;
	private Object[][] datas;
	private int perPageNum;
	private static int curPage = 1;
	private int totalPages;
	
	
	
	public PagingTool(JTable jtable,Object[][] datas,int perPageNum){
		this.jTable = jtable;
		this.datas = datas;
		this.perPageNum = perPageNum;
		if(datas.length%perPageNum == 0){
			this.totalPages = datas.length/perPageNum;
		}else{
			this.totalPages = datas.length/perPageNum + 1;
		}
	}
	
	//分页显示
	
	public void showPage(int n){
		Object[][] newDatas = new Object[perPageNum][IFieldConstant.columns.length];
		if(n < totalPages){
			for(int i = (n-1)*perPageNum , m = 0 ; i < n*perPageNum && m < perPageNum ; i++,m++){
				for(int j = 0 ; j < datas[0].length ; j++){
					newDatas[m][j] = datas[i][j];
				}
			}
		}
		if(n == totalPages){
			for(int i = (n-1)*perPageNum , m = 0 ; i < datas.length && m < perPageNum ; i++,m++){
				for(int j = 0 ; j < datas[0].length ; j++){
					newDatas[m][j] = datas[i][j];
				}
			}
			
		}
		
		//刷新数据
		((DefaultTableModel)jTable.getModel()).setDataVector(newDatas, IFieldConstant.columns);
		
		jTable.updateUI();
		
	}
	
	/**
	 * 下一页，返回当前页
	 * @return
	 */
	public int nextPage(){
		
		if(curPage + 1 <= totalPages){
			showPage(curPage + 1);
			curPage++;
		}
		
		return curPage;
	}
	
	/**
	 * 上一页，返回当前页
	 * @return
	 */
	public int prePage(){
		if(curPage - 1 >= 1){
			showPage(curPage - 1);
			curPage--;
		}
		
		return curPage;
	}
	
	/**
	 * 首页
	 * @return
	 */
	public int firstPage(){
		showPage(1);
		curPage = 1;
		return curPage;
	}
	
	/**
	 * 尾页
	 * @return
	 */
	public int endPage(){
		showPage(getTotalPage());
		curPage = getTotalPage();
		return curPage;
	}
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPage(){
		if(datas.length%perPageNum == 0){
			return datas.length/perPageNum;
		}else{
			return datas.length/perPageNum + 1;
		}
	}
}
