package cn.ewsio.datax.entity;

import cn.hutool.core.annotation.Alias;

public class TaskSqlNode {

	private Long id;
	@Alias("sql_script")
	private String sqlScript;
	private String driver;
	private String jdbc;
	private String username;
	private String pwd;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSqlScript() {
		return sqlScript;
	}

	public void setSqlScript(String sqlScript) {
		this.sqlScript = sqlScript;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getJdbc() {
		return jdbc;
	}

	public void setJdbc(String jdbc) {
		this.jdbc = jdbc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
