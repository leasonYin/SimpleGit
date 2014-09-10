package cn.com.emrs.itf;

import java.sql.SQLException;

public interface IEmrsSaveAction {
	
	/**
	 * 保存
	 * @param values
	 * @throws SQLException
	 */
	public void doAction(String[] values) throws SQLException;
}
