package cn.com.emrs.itf;

import java.sql.SQLException;

public interface IEmrsQueryAction {
	
	/**
	 * 按条件查询
	 * @param where
	 * @return
	 * @throws SQLException
	 */
	public String[][] doQuery(String where) throws SQLException;

}
