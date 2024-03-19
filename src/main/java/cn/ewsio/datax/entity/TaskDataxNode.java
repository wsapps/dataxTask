package cn.ewsio.datax.entity;

import cn.hutool.core.annotation.Alias;
import cn.hutool.json.JSONUtil;

public class TaskDataxNode {

	private Long id;

	@Alias("r_table_schema")
	private String rTableSchema;

	@Alias("r_tablename")
	private String rTablename;

	@Alias("reader_column")
	private String readerColumn;

	@Alias("split_pk")
	private String splitPk;

	private String where;

	@Alias("query_sql")
	private String querySql;

	@Alias("w_table_schema")
	private String wTableSchema;

	@Alias("w_tablename")
	private String wTablename;

	@Alias("writer_column")
	private String writerColumn;

	@Alias("pre_sql")
	private String preSql;

	@Alias("cust_transformer_paras")
	private String custTransformerParas;

	@Alias("r_jdbc")
	private String rJdbc;

	@Alias("r_username")
	private String rUsername;

	@Alias("r_pwd")
	private String rPwd;

	@Alias("r_driver")
	private String rDriver;

	@Alias("w_jdbc")
	private String wJdbc;

	@Alias("w_username")
	private String wUsername;

	@Alias("w_pwd")
	private String wPwd;

	@Alias("w_driver")
	private String wDriver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getrTableSchema() {
		return rTableSchema;
	}

	public void setrTableSchema(String rTableSchema) {
		this.rTableSchema = rTableSchema;
	}

	public String getrTablename() {
		return rTablename;
	}

	public void setrTablename(String rTablename) {
		this.rTablename = rTablename;
	}

	public String getReaderColumn() {
		return readerColumn;
	}

	public void setReaderColumn(String readerColumn) {
		this.readerColumn = readerColumn;
	}

	public String getSplitPk() {
		return splitPk;
	}

	public void setSplitPk(String splitPk) {
		this.splitPk = splitPk;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getQuerySql() {
		return querySql;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getwTableSchema() {
		return wTableSchema;
	}

	public void setwTableSchema(String wTableSchema) {
		this.wTableSchema = wTableSchema;
	}

	public String getwTablename() {
		return wTablename;
	}

	public void setwTablename(String wTablename) {
		this.wTablename = wTablename;
	}

	public String getWriterColumn() {
		return writerColumn;
	}

	public void setWriterColumn(String writerColumn) {
		this.writerColumn = writerColumn;
	}

	public String getPreSql() {
		return preSql;
	}

	public void setPreSql(String preSql) {
		this.preSql = preSql;
	}

	public String getCustTransformerParas() {
		return custTransformerParas;
	}

	public void setCustTransformerParas(String custTransformerParas) {
		this.custTransformerParas = custTransformerParas;
	}

	public String getrJdbc() {
		return rJdbc;
	}

	public void setrJdbc(String rJdbc) {
		this.rJdbc = rJdbc;
	}

	public String getrUsername() {
		return rUsername;
	}

	public void setrUsername(String rUsername) {
		this.rUsername = rUsername;
	}

	public String getrPwd() {
		return rPwd;
	}

	public void setrPwd(String rPwd) {
		this.rPwd = rPwd;
	}

	public String getrDriver() {
		return rDriver;
	}

	public void setrDriver(String rDriver) {
		this.rDriver = rDriver;
	}

	public String getwJdbc() {
		return wJdbc;
	}

	public void setwJdbc(String wJdbc) {
		this.wJdbc = wJdbc;
	}

	public String getwUsername() {
		return wUsername;
	}

	public void setwUsername(String wUsername) {
		this.wUsername = wUsername;
	}

	public String getwPwd() {
		return wPwd;
	}

	public void setwPwd(String wPwd) {
		this.wPwd = wPwd;
	}

	public String getwDriver() {
		return wDriver;
	}

	public void setwDriver(String wDriver) {
		this.wDriver = wDriver;
	}

	@Override
	public String toString() {
		return JSONUtil.toJsonPrettyStr(this);
	}

}
