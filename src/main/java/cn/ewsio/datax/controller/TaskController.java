package cn.ewsio.datax.controller;

import java.util.List;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Put;
import org.noear.solon.validation.annotation.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ewsio.datax.entity.TaskNode;
import cn.ewsio.datax.sql.TaskSql;
import cn.hutool.db.Db;

@Controller
@Mapping("/task")
public class TaskController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);
	
	@Put
	@Mapping("/{id}")
	public String runTask(@NotNull Integer id) throws Exception {
		
		List<TaskNode> es = Db.use().query(TaskSql.QUERY_TASK_NODES_BY_TASK_ID, TaskNode.class, id);
		
		for (TaskNode taskNode : es) {
			LOG.debug(taskNode.toString());
			
			
			
		}
		return "ok, " + es.size();
	}

}
