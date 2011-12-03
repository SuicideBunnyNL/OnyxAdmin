package me.maveronyx.onyxadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Config {

	OnyxAdmin plugin;

	File dataFolder;
	File mainConfig;
	File messageConfig;

	public Config(OnyxAdmin instance) {
		plugin = instance;
		dataFolder = instance.getDataFolder();
	}

	private void setup() throws Exception {
		mainConfig = new File(dataFolder, "config.yml");
		messageConfig = new File(dataFolder, "messages.yml");

		if (!mainConfig.exists()) {
			mainConfig.getParentFile().mkdirs();
			copyFile(plugin.getResource("config.yml"), mainConfig);
		}

		if (!messageConfig.exists()) {
			messageConfig.getParentFile().mkdirs();
			copyFile(plugin.getResource("messages.yml"), messageConfig);
		}

	}

	/**
	 * Copy's a file
	 * 
	 * @param in
	 * @param file
	 */
	private void copyFile(InputStream in, File file) {
		try {
			// Try to copy the file
			OutputStream out = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			out.close();
			in.close();

		} catch (Exception e) {
			// If something goes wrong, output it to console
			e.printStackTrace();
		}
	}

	private void saveConfig() {
		try {
			plugin.config.save(mainConfig);
			plugin.config.save(messageConfig);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadConfig() {
		try {
			plugin.config.load(mainConfig);
			plugin.config.load(messageConfig);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
