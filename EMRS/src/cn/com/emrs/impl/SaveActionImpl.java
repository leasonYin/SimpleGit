package cn.com.emrs.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.apache.log4j.Logger;

import cn.com.emrs.itf.IEmrsSaveAction;
import cn.com.emrs.util.DBTools;
import cn.com.emrs.util.EmrsTools;

public class SaveActionImpl implements IEmrsSaveAction {

	private static final Logger logger = Logger.getLogger(SaveActionImpl.class);
	Connection conn = null;
	@Override
	public void doAction(String[] values) {
		// TODO Auto-generated method stub
		String sql = null;
		Connection con = getConnection();
		Statement statement;
		logger.info("Insert action starting ...");
		try {
			statement = (Statement) con.createStatement();
			sql = getInsertSql(values);
			int n = statement.executeUpdate(sql);
			logger.info("Save action success ! save " + n + " rows datas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("Insert failed : " + sql);
			logger.error("Save exception: ", e);
		}finally{
			try {
				con.close();
				logger.info("Connection closed successful !");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Connection close exception : ", e);
			}
		}
		
	}

	/**
	 * 获取数据库连接
	 * @return
	 */
	private Connection getConnection(){
		if(null == conn){
			conn = DBTools.getConnection();
		}
		return conn;
	}
	
	/**
	 * 构造insert语句
	 * @param values
	 * @return
	 */
	private String getInsertSql(String[] values){
		StringBuffer sb = new StringBuffer("insert into em_record values(");
		if(null == values){
			return null;
		}
		//自动生成billcode
		sb.append("'" + UUID.randomUUID() + "'");
		for(int i = 0 ; i < values.length ; i++){
			
			String value = values[i];
			//整数类型不加引号,i是整数类型的字段所在的顺序
			if(i == 5 
					|| (i >= 7 && i <= 13)
					|| (i >= 15 && i <= 16)){
				sb.append(",");
				sb.append(value);
			}else{
				sb.append(",'");
				sb.append(value);
				sb.append("'");
			}
		}
		
		//拼接ts
		sb.append(",'");
		sb.append(EmrsTools.getCurrentTime());
		sb.append("'");
		sb.append(")");
		
		return sb.toString();
	}
}
