package cn.ewsio.datax.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.noear.solon.annotation.Component;

import cn.ewsio.datax.entity.TaskDataxNode;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@Component
public class TaskXMLComponent {

//    private static final String SETTING_SPEED_CHANNEL = "job.setting.speed.channel";
    private static final String READER = "job.content[0].reader";
    private static final String READER_NAME = READER + ".name";
    private static final String READER_PARAMETER = READER + ".parameter";
    private static final String READER_USERNAME = READER_PARAMETER + ".username";
    private static final String READER_PASSWORD = READER_PARAMETER + ".password";
    private static final String READER_SPLITPK = READER_PARAMETER + ".splitPk";
    private static final String READER_WHERE = READER_PARAMETER + ".where";
    private static final String READER_COLUMN = READER_PARAMETER + ".column";
    private static final String READER_CONNECTION = READER_PARAMETER + ".connection[0]";
    private static final String READER_TABLE = READER_CONNECTION + ".table[0]";
    private static final String READER_JDBCURL = READER_CONNECTION + ".jdbcUrl[0]";
    private static final String READER_QUERYSQL = READER_CONNECTION + ".querySql[0]";

    private static final String WRITER = "job.content[0].writer";
    private static final String WRITER_NAME = WRITER + ".name";
    private static final String WRITER_PARAMETER = WRITER + ".parameter";
    private static final String WRITER_USERNAME = WRITER_PARAMETER + ".username";
    private static final String WRITER_PASSWORD = WRITER_PARAMETER + ".password";
    private static final String WRITER_COLUMN = WRITER_PARAMETER + ".column";
    private static final String WRITER_PRESQL = WRITER_PARAMETER + ".preSql[0]";
    private static final String WRITER_CONNECTION = WRITER_PARAMETER + ".connection[0]";
    private static final String WRITER_TABLE = WRITER_CONNECTION + ".table[0]";
    private static final String WRITER_JDBCURL = WRITER_CONNECTION + ".jdbcUrl";

    private static final String TRANSFORMER = "job.content[0].transformer[0]";
    private static final String TRANSFORMER_NAME = TRANSFORMER + ".name";
    private static final String TRANSFORMER_PARAMETER = TRANSFORMER + ".parameter";
    private static final String TRANSFORMER_COLUMNINDEX = TRANSFORMER_PARAMETER + ".columnIndex";
    private static final String TRANSFORMER_PARAS = TRANSFORMER_PARAMETER + ".paras[0]";

    private static final Map<String, String> READER_NAME_MAP = new HashMap<>();
    private static final Map<String, String> WRITER_NAME_MAP = new HashMap<>();

    static {
        READER_NAME_MAP.put("com.mysql.cj.jdbc.Driver", "mysqlreader");
        READER_NAME_MAP.put("com.clickhouse.jdbc.ClickHouseDriver", "clickhousereader");

        WRITER_NAME_MAP.put("com.mysql.cj.jdbc.Driver", "mysqlwriter");
        WRITER_NAME_MAP.put("com.clickhouse.jdbc.ClickHouseDriver", "clickhousewriter");
    }

    public String createXML(TaskDataxNode taskNode) {
        String readerName = READER_NAME_MAP.get(taskNode.getrDriver());
        String writerName = WRITER_NAME_MAP.get(taskNode.getwDriver());

        if (null == readerName || null == writerName) {
            throw new RuntimeException("driver not found");
        }

        String tempPath = TaskXMLComponent.class.getResource("/datax_script_template.json").getPath();
        FileReader fileReader = new FileReader(tempPath);
        String txt = fileReader.readString();
        JSONObject js = JSONUtil.parseObj(txt);

        // js.putByPath(SETTING_SPEED_CHANNEL, 8);

        js.putByPath(READER_NAME, readerName);
        js.putByPath(READER_USERNAME, taskNode.getrUsername());
        js.putByPath(READER_PASSWORD, taskNode.getrPwd());
        js.putByPath(READER_JDBCURL, taskNode.getrJdbc());

        if (null != taskNode.getQuerySql() && !taskNode.getQuerySql().isEmpty()) {
            js.putByPath(READER_QUERYSQL, taskNode.getQuerySql());
        } else {
            js.putByPath(READER_COLUMN, column(taskNode.getReaderColumn()));
            js.putByPath(READER_SPLITPK, taskNode.getSplitPk());
            js.putByPath(READER_WHERE, taskNode.getWhere());
            js.putByPath(READER_TABLE, taskNode.getrTableSchema() + "." + taskNode.getrTablename());
        }

        js.putByPath(WRITER_NAME, writerName);
        js.putByPath(WRITER_USERNAME, taskNode.getwUsername());
        js.putByPath(WRITER_PASSWORD, taskNode.getwPwd());
        js.putByPath(WRITER_JDBCURL, taskNode.getwJdbc());

        js.putByPath(WRITER_TABLE, taskNode.getwTableSchema() + "." + taskNode.getwTablename());
        js.putByPath(WRITER_COLUMN, column(taskNode.getWriterColumn()));

        if (null != taskNode.getPreSql() && !taskNode.getPreSql().isEmpty()) {
            js.putByPath(WRITER_PRESQL, taskNode.getPreSql());
        }

        if (null != taskNode.getCustTransformerParas() && !taskNode.getCustTransformerParas().isEmpty()) {
            js.putByPath(TRANSFORMER_NAME, "cust_transformer");
            js.putByPath(TRANSFORMER_COLUMNINDEX, 1);
            js.putByPath(TRANSFORMER_PARAS, taskNode.getCustTransformerParas());

        }

        return js.toStringPretty();
    }

    private List<String> column(String col) {
        List<String> list = new ArrayList<>();
        if (null == col || col.isBlank()) {
            list.add("*");
        } else {
            String[] arr = col.split(",");
            for (String str : arr) {
                list.add(str.trim());
            }
        }

        return list;
    }
}
