package cn.com.emrs.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import cn.com.emrs.itf.IEmrsQueryAction;
import cn.com.emrs.itf.IFieldConstant;
import cn.com.emrs.util.DBTools;

import com.mysql.jdbc.StringUtils;

public class QueryActionImpl implements IEmrsQueryAction {

	Logger log = Logger.getLogger(QueryActionImpl.class);
	Connection conn;
	@Override
	public String[][] doQuery(String condition) {
		// TODO Auto-generated method stub
		String[][] datas = null;
		ResultSet rs;
		StringBuilder sql = new StringBuilder("select * from (select * from em_record ");
		if(!StringUtils.isEmptyOrWhitespaceOnly(condition)){
			sql.append(" where ");
			sql.append(condition);
		}
		sql.append(" union all ");
		sql.append("select ");
		sql.append("null as billcode, ");
		sql.append("'总计'  as billdate,");
		sql.append("ename, ");
		sql.append("null as fixcontent, ");
		sql.append("null as errorreason, ");
		sql.append("null as partname, ");
		sql.append("sum(partvalue) as partvalue, ");
		sql.append("null as matrname, ");
		sql.append("sum(matrcost) as matrcost, ");
		sql.append("sum(opuvalue) as opuvalue, ");
		sql.append("sum(electnum), ");
		sql.append("sum(fitternum), ");
		sql.append("sum(plumbernum), ");
		sql.append("sum(cranum), ");
		sql.append("sum(weldornum), ");
		sql.append("null as starttime, ");
		sql.append("sum(costtime), ");
		sql.append("sum(cost), ");
		sql.append("null as joiner, ");
		sql.append("null as checker, ");
		sql.append("null as bcut, ");
		sql.append("null as bbetter, ");
		sql.append("null as ts ");
		sql.append(" from em_record ");
		if(!StringUtils.isEmptyOrWhitespaceOnly(condition)){
			sql.append(" where ");
			sql.append(condition);
		}
		sql.append(" ) t ");
		sql.append(" order by billdate ");
		
		
		
		Connection conn = getConnection();
		Statement stmt;
		try {
			int rsLength =0;
			stmt = conn.createStatement();
			log.info("Query connect succeess !");
			rs = stmt.executeQuery(sql.toString());
			if(rs != null){
				rs.last();
				rsLength = rs.getRow();
				datas = new String[rsLength][IFieldConstant.columns.length];
				//恢复指针
				rs.beforeFirst();
			}
			int i = 0;
			while (rs.next()) {
				datas[i][0] = rs.getString("billdate");
				datas[i][1] = rs.getString("ename");
				datas[i][2] = rs.getString("partname");
				datas[i][3] = rs.getBigDecimal("partvalue").toString();
				datas[i][4] = rs.getString("matrname");
				datas[i][5] = rs.getBigDecimal("matrcost").toString();
				datas[i][6] = rs.getBigDecimal("opuvalue").toString();
				datas[i][7] = ((Integer)rs.getInt("electnum")).toString();
				datas[i][8] = ((Integer)rs.getInt("fitternum")).toString();
				datas[i][9] = ((Integer)rs.getInt("plumbernum")).toString();
				datas[i][10] = ((Integer)rs.getInt("cranum")).toString();
				datas[i][11] = ((Integer)rs.getInt("weldornum")).toString();
				datas[i][12] = rs.getString("starttime");
				datas[i][13] = ((Integer)rs.getInt("costtime")).toString();
				datas[i][14] = rs.getBigDecimal("cost").toString();
				datas[i][15] = rs.getString("joiner");
				datas[i][16] = rs.getString("checker");
				datas[i][17] = rs.getString("bcut");
				datas[i][18] = rs.getString("bbetter");
				datas[i][19] = rs.getString("fixcontent");
				datas[i][20] = rs.getString("errorreason");
				
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("Query failed : " + sql.toString());
			log.error("QueryException :",e);
		}finally{
			try {
				conn.close();
				log.info("Connection closed .");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return datas;
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
}
