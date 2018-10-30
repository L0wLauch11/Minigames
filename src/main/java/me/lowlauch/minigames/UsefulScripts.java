package me.lowlauch.minigames;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class UsefulScripts
{
    public static World createwrld(final String name)
    {
        World worl = Bukkit.getWorld(name);
        return worl == null ? Bukkit.createWorld(new WorldCreator(name)) : worl;
    }
}
