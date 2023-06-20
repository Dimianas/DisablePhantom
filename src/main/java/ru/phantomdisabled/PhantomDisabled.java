package ru.phantomdisabled;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public final class PhantomDisabled extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aplugin enabled!"));
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aplugin disabled!"));
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (getConfig().getBoolean("prevent-phantom-spawn")) {
            return;
        }
        if (event.getEntityType() == EntityType.PHANTOM) {
            event.setCancelled(true);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("phantom") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            saveDefaultConfig();
            Permission permission = new Permission("phantom.reload");
            String message = getConfig().getString("reload-message");
            assert message != null;
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            return true;
        }
        return false;
    }
}
