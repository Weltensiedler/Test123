package main;

import java.util.Map;

import commands.CommandBroadcast;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	@Override
	public void onEnable() {
		System.out.println("$b[BungeeMoney] MoneySynchronizer enabled");
		registerCommands();
	}
	@Override
	public void onDisable() {
		System.out.println("$b[BungeeMoney] MoneySynchronizer disabled");
	}
	
	public void registerCommands() {
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandBroadcast("broadcast"));
		Map<String, ServerInfo> m = ProxyServer.getInstance().getServers();
		
		System.out.println(m.size());
	}
	
}
