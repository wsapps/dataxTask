package cn.ewsio.datax.conf;

import org.noear.solon.annotation.Configuration;

import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;

@Configuration
public class HutoolDBConfiguration {

	public void initDB() {
		Setting setting = new Setting("db.setting");
		DSFactory dsFactory = DSFactory.create(setting);
		DSFactory.setCurrentDSFactory(dsFactory);
	}
}
