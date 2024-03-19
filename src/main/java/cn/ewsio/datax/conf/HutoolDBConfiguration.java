package cn.ewsio.datax.conf;

import java.sql.SQLException;
import java.util.List;

import org.noear.solon.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;

@Configuration
public class HutoolDBConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(HutoolDBConfiguration.class);
	
	public void initDB() {
		Setting setting = new Setting("db.setting");
		DSFactory dsFactory = DSFactory.create(setting);
		DSFactory.setCurrentDSFactory(dsFactory);
		
		try {
			List<Entity> es = Db.use().query("select id,jdbc,driver,username,pwd from etl_datasource");
			for (Entity e : es) {
				String id = e.getStr("id");
				String jdbc = e.getStr("jdbc");
				String driver = e.getStr("driver");
				String username = e.getStr("username");
				String pwd = e.getStr("pwd");
				String groupId = "dynamic_" + id;
				
				setting.putByGroup("url", groupId, jdbc);
				setting.putByGroup("username", groupId, username);
				setting.putByGroup("pwd", groupId, pwd);
				setting.putByGroup("driver", groupId, driver);
				
			}
			dsFactory = DSFactory.create(setting);
			DSFactory.setCurrentDSFactory(dsFactory);
		} catch (SQLException e) {
			LOG.error("init dynamic datasource error",e);
		}
	}
}
