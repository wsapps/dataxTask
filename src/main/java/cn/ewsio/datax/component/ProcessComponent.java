package cn.ewsio.datax.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.noear.solon.annotation.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.system.SystemUtil;

@Component
public class ProcessComponent {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessComponent.class);
	
	public void runProcessWaitFor(String cmd) {

		ProcessBuilder processBuilder = null;

		if (SystemUtil.getOsInfo().isWindows()) {
			processBuilder = new ProcessBuilder("cmd.exe", "/c", cmd);
		} else {
			processBuilder = new ProcessBuilder("bash", "-c", cmd);
		}
		
		try {
			Process process = processBuilder.start();
			
			
			BufferedReader stdInfo = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			String line;
			while ((line = stdInfo.readLine()) != null) {
				LOG.info(line);
//				System.out.println(line);
			}
			
			while ((line = stdError.readLine()) != null) {
				LOG.error(line);
//				System.out.println(line);
            }
			
			
			int exitCode = process.waitFor();
			if (exitCode == 0) {
				LOG.info("executed successfully, cmd:" + cmd);
//				System.out.println("executed successfully, cmd:" + cmd);
				
	            
			} else {
				LOG.error("executed successfully, cmd:" + cmd);
//				System.out.println("executed successfully, cmd:" + cmd);
			}
			
			
		} catch (IOException e) {
			LOG.error("process start error, cmd: " + cmd, e);
//			e.printStackTrace();
		} catch (InterruptedException e2) {
			LOG.error("process waitFor error, cmd: " + cmd, e2);
//			e2.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ProcessComponent component = new ProcessComponent();
		String dataxPath = "D:\\datax\\bin\\datax.py";
		String jsonFile = "http://localhost:8080/task/98";
		String cmd = "python " + dataxPath + " " + jsonFile;
		component.runProcessWaitFor(cmd);
	}
}
