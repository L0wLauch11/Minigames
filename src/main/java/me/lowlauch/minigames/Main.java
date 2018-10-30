package me.lowlauch.minigames;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public static String prefix = "[§b§lMinigames§r§f] ";
    private static Main instance;

    public static Main getInstance()
    {
        return instance;
    }

    public static String getPrefix()
    {
        return prefix;
    }

    public void onEnable()
    {
        instance = this;
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info(prefix + "Enabled");
        getCommand("snowball").setExecutor(new Commands(this));
        getCommand("parkour").setExecutor(new Commands(this));

        this.saveDefaultConfig();
    }

    public void onDisable()
    {
        getLogger().info(prefix + "Plugin ist disabled!");
    }
}
