package cn.ewsio.datax.controller;

import java.util.List;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Put;
import org.noear.solon.validation.annotation.NotNull;

import cn.ewsio.datax.component.ProcessComponent;
import cn.ewsio.datax.component.TaskXMLComponent;
import cn.ewsio.datax.entity.TaskNode;
import cn.ewsio.datax.sql.TaskSql;
import cn.hutool.db.Db;

@Controller
@Mapping("/task")
public class TaskController {

//    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @Inject
    private TaskXMLComponent taskXMLComponent;
    
    @Inject
    private ProcessComponent processComponent;
    
    @Inject("${solon.app.dataxPath}")
    String dataxPath;
    
    @Inject("${server.port}")
    Integer serverPort;

    @Put
    @Mapping("/{id}")
    public String runTask(@NotNull Integer id) throws Exception {

        List<TaskNode> es = Db.use().query(TaskSql.QUERY_TASK_NODES_BY_TASK_ID, TaskNode.class, id);

        for (TaskNode taskNode : es) {
			String cmd = "python " + dataxPath + " http://localhost:" + serverPort + "/task/" + taskNode.getId();
        	processComponent.runProcessWaitFor(cmd);
        }


		return es.size() + "";
    }


    @Get
    @Mapping("/{id}")
    public String task(@NotNull Integer id) throws Exception {
        String json = null;
        List<TaskNode> es = Db.use().query(TaskSql.QUERY_TASK_NODES_BY_NODE_ID, TaskNode.class, id);

        if (null != es && es.size() == 1) {
            json = taskXMLComponent.createXML(es.get(0));
        }


        return json;
    }

}
