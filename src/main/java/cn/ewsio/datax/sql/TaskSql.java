package cn.ewsio.datax.sql;

public interface TaskSql {

	/**
	 * 工具任务id查询任务节点信息
	 */
	String QUERY_TASK_NODES_BY_TASK_ID = """ 
			SELECT
				n.id,
				n.r_table_schema,n.r_tablename,n.reader_column,n.split_pk,n.`where`,n.query_sql,
				n.w_table_schema,n.w_tablename,n.writer_column,n.pre_sql,
				n.cust_transformer_paras,
				rd.jdbc r_jdbc,
				rd.username r_username,
				rd.pwd r_pwd,
				rd.driver r_driver,
				wd.jdbc w_jdbc,
				wd.username w_username,
				wd.pwd w_pwd,
				wd.driver w_driver
				FROM
					etl_datax_task_node n
					LEFT JOIN etl_datasource rd on n.r_ds_id=rd.id
					LEFT JOIN etl_datasource wd on n.w_ds_id=wd.id
				WHERE
					n.id IN (
					SELECT
						node_id 
					FROM
						etl_datax_task_node_ref 
				WHERE
					task_id = ?)
			""";
}
