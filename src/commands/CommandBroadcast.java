package commands;

import java.util.Map;
import java.util.Set;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandBroadcast extends Command {

	public CommandBroadcast(String name) {
		super(name);
	}

	  public static String join( Iterable<?> iterable )
	  {
	    StringBuilder result = new StringBuilder();
	    for ( Object o : iterable )
	    {
	      if ( result.length() != 0 )
	        result.append( ", " );
	      result.append( o.toString() );
	    }
	    return result.toString();
	  }
	  
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {

		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			String message = "";

			for (String string : args) {
				message += string.concat(" ");
			}

			if (!message.equals("")) {
				ProxyServer.getInstance().broadcast(message);
			} else {
				Map<String, ServerInfo> serverMap = ProxyServer.getInstance().getServers();
				Set<String> setKey= serverMap.keySet();
				
				player.sendMessage("Serverkeys: " + join(setKey));

			
			}
		}
		if (args.length > 0) {

		}

	}

}
