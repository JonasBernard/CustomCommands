package main;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

class CustomCommand  {
	public String alias;
	public String command;
	public String description;
	
	public CustomCommand(String alias, String command, String description) {
		this.alias = alias;
		this.command = command;
		this.description = description;
	}
}

public class main extends JavaPlugin {

	private static ArrayList<CustomCommand> customCommands;
	
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		System.out.println("[CustomCommands] Starting to load Plugin...");
		loadConfig();
		
		customCommands = new ArrayList<>();
		readConfig();
		registerCommands();
		
		for (CustomCommand customCommand : customCommands) {
			System.out.println("[CustomCommands] Found custom command: Alias: " + customCommand.alias + ", Command: " + customCommand.command);
		}
		super.onEnable();
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
	
	@Override
	public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label,
			@Nonnull String[] args) {
		/*
		 * for (CustomCommand c : customCommands) { if(label.equalsIgnoreCase(c.alias))
		 * { Player p = ((sender instanceof Player)?(Player)sender:null); if (p == null)
		 * { sender.sendMessage("Custom Commands only work with players"); }
		 * p.performCommand(c.command); } }
		 */
		
		return super.onCommand(sender, command, label, args);
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	

	private void registerCommands() {
		try {
			final Field bukkitCommandMap = getServer().getClass().getDeclaredField("commandMap");
			
			bukkitCommandMap.setAccessible(true);
			CommandMap commandMap = (CommandMap) bukkitCommandMap.get(getServer());
			
			for (CustomCommand customCommand : customCommands) {
				commandMap.register("customcommands" , new Command(customCommand.alias, "Executes \"/" + customCommand.command + " \"", "/<command>", new ArrayList<>()) {
					
					@Override
					public boolean execute(CommandSender sender, String arg1, String[] arg2) {
						Player p = ((sender instanceof Player)?(Player)sender:null);
						if (p == null) {
							sender.sendMessage("Custom Commands only work with players");
							return false;
						}
						return p.performCommand(customCommand.command);
					}
				});
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readConfig() {
		System.out.println("[CustomCommands] Loading commands");
		ConfigurationSection commands = getConfig().getConfigurationSection("commands");
		for (String cmdname : commands.getKeys(false)) {
			System.out.println("[CustomCommands] Found command \"" + cmdname + "\"");
			customCommands.add(
					new CustomCommand(
							getConfig().getString("commands." + cmdname + ".alias"),
							getConfig().getString("commands." + cmdname + ".command"),
							getConfig().getString("commands." + cmdname + ".description")
							)
					);
		}
	}
}
