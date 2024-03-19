package cn.ewsio.datax.controller;

import java.util.List;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Put;
import org.noear.solon.validation.annotation.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ewsio.datax.entity.TaskSqlNode;
import cn.ewsio.datax.sql.TaskSql;
import cn.hutool.db.Db;

@Controller
@Mapping("/task/sql")
public class TaskSqlController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskSqlController.class);

    @Put
    @Mapping("/{id}")
    public String runTask(@NotNull Integer id) throws Exception {

        List<TaskSqlNode> es = Db.use().query(TaskSql.QUERY_TASK_SQL_NODES_BY_TASK_ID, TaskSqlNode.class, id);

        for (TaskSqlNode taskNode : es) {
        	String groupId = "dynamic_" + taskNode.getId();
			LOG.info("execute [" + taskNode.getName() + "] start.");
        	Db.use(groupId).execute(taskNode.getSqlScript());
        	LOG.info("execute [" + taskNode.getName() + "] end.");
        }

		return es.size() + "";
    }



}
