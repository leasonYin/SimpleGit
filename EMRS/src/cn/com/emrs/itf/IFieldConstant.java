package cn.com.emrs.itf;

/**
 * 字段名称接口
 * @author 小尹
 *
 */
public interface IFieldConstant {
	public final String BILLDATE = "日期";
	public final String ENAME = "设备名称";
	public final String FIXCONTENT = "修理内容";
	public final String ERRORREASON = "故障原因分析";
	public final String PARTNAME = "零部件名称";
	public final String PARTVALUE = "备件价值";
	public final String MATRNAME = "材料名称";
	public final String MATRCOST = "材料费用";
	public final String OPUVALUE = "旧件利用价值";
	public final String ELECTNUM = "电工数量";
	public final String FITTERNUM = "钳工数量";
	public final String PLUMBERNUM = "管工数量";
	public final String CRANUM = "起重工数量";
	public final String WELDORNUM = "焊工数量";
	public final String STARTTIME = "修理工时起始时间";
	public final String COSTTIME = "工时（分钟）";
	public final String COST = "用能费用";
	public final String JOINER = "参加人员";
	public final String CHECKER = "验收人";
	public final String BCUT = "参加人员是否可减少";
	public final String BBETTER = "是否有改进措施";

	/**
	 * 控件名称，按数据库字段顺序排列，暂时未用到
	 */
	public final String[] fields = new String[] { 
			"dateTextField", // 日期
			"enameTextField", // 设备名称
			"fixcontentTextArea",//修理内容
			"errorreasonTextArea",//故障原因
			"partNameTextField", // 零部件名称
			"partValueTextField", // 备件价值
			"matrNameTextField", // 材料名称
			"matrCostTextField", // 材料费用
			"opuValueTextField", // 旧件利用价值
			"electnumText",//电工数
			"fitternumText",//钳工数
			"plumbernumText", //管工数
			"cranumText",//起重工数
			"weldornumText",//焊工数
			"starttimeText", //修理起始时间
			"costtimeText", //工时（分钟）
			"costText",//用能费用
			"joinerText", //参加人
			"checkerText",//审核人
	};
	
	public final String[] columns = new String[]{
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
}

