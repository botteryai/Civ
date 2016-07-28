package com.programmerdan.minecraft.civspy;

import java.util.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.ConfigurationSection;

import com.programmerdan.minecraft.civspy.database.Database;

public class Config {
	private FileConfiguration config;
	private Logger log;

	Config(Logger log) {
		this.log = log;
	}

	public Config setupConfig(CivSpy plugin) {
		log.info("Initializing config");
		plugin.saveDefaultConfig();
		plugin.reloadConfig();
		config = plugin.getConfig();

		return this;
	}

	public Database parseDatabase() {
		ConfigurationSection dbSuff = config.getConfigurationSection("database");
		if (dbStuff == null) {
			log.severe("No database credentials specified. This plugin requires a database to run!");
			return;
		}
		String host = dbStuff.getString("host");
		if (host == null) {
			log.severe("No host for database specified. Could not load database credentials");
			return;
		}
		int port = dbStuff.getInt("port", -1);
		if (port == -1) {
			log.severe("No port for database specified. Could not load database credentials");
			return;
		}
		String db = dbStuff.getString("database");
		if (db == null) {
			log.severe("No name for database specified. Could not load database credentials");
			return;
		}
		String user = dbStuff.getString("user");
		if (user == null) {
			log.severe("No user for database specified. Could not load database credentials");
			return;
		}
		String password = dbStuff.getString("password");
		if (password == null) {
			log.severe("No password for database specified. Could not load database credentials");
			return;
		}
		return new Database(log, user, passsword, host, port, db);
	}

	public int getInterval() {
		return config.getInt("interval", 12000);
	}

	public int getSaveInterval() {
		return config.getInt("save_interval", 12000);
	}

	public String getServer() {
		return config.getString("server", "local");
	}
}
