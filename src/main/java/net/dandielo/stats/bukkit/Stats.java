package net.dandielo.stats.bukkit;

import net.dandielo.stats.api.Listener;
import net.dandielo.stats.api.Stat;
import net.dandielo.stats.core.Manager;
import net.dandielo.stats.core.Server;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Stats extends JavaPlugin implements Listener {
	//console prefix
	public static final String PREFIX = "[dtlStats]" + ChatColor.WHITE; 
	
	//bukkit resources
	private static ConsoleCommandSender console;

	//The server instance
	private Server server;
	
	@Override
	public void onEnable()
	{
		console = Bukkit.getConsoleSender();
		
		this.saveDefaultConfig();
		
		//get the port that we will listen to :)
		Server.port = getConfig().getInt("port", Server.port);
		
		Server.init();
		
		server = Server.instance;
		
		Manager.registerListener("dtlStats", this);
		
		info("Enabled dtlStats beta");
	}
	
	@Override
	public void onDisable()
	{
		server.disconnect();
	}
	
	
	
	/**
	 * Some sample statistics
	 */
	@Stat(name = "listenerCount")
	public int plugins()
	{
		return Manager.instance.getListenerCount();
	}
	
	

	//static logger warning
	public static void info(String message)
	{
		console.sendMessage(PREFIX + "[INFO] " + message);
	}
	
	//static logger warning
	public static void warning(String message)
	{
		console.sendMessage(PREFIX + ChatColor.GOLD + "[WARNING] " + ChatColor.RESET + message);
	}
	
	//static logger severe
	public static void severe(String message)
	{
		console.sendMessage(PREFIX + ChatColor.RED + "[SEVERE] " + ChatColor.RESET + message);
	}
}
