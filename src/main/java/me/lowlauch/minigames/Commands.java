package me.lowlauch.minigames;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.lowlauch.minigames.UsefulScripts.createwrld;

public class Commands implements CommandExecutor
{
    public Commands(Main instance) { }

    public Location snowballLobbyLoc;
    public static Location parkourSpawn;
    public static boolean parkourEnabled = true;
    public boolean onCommand(CommandSender commandSender, Command command, String commandInput, String[] args) {
        if(commandInput.equalsIgnoreCase("snowball"))
        {
            if(args.length >= 1)
            {
                if(args[0].equalsIgnoreCase("lobby"))
                {
                    Player pl = (Player) commandSender;
                    createwrld(Main.getInstance().getConfig().getString("snowball.lobby.world"));
                    snowballLobbyLoc = new Location(Main.getInstance().getServer().getWorld(Main.getInstance().getConfig().getString("snowball.lobby.world")), Main.getInstance().getConfig().getInt("snowball.lobby.x"), Main.getInstance().getConfig().getInt("snowball.lobby.y"), Main.getInstance().getConfig().getInt("snowball.lobby.z"));
                    pl.setGameMode(GameMode.SURVIVAL);
                    pl.sendMessage(Main.getPrefix() + "Willkommen in Snowball!");
                    pl.teleport(snowballLobbyLoc);
                }

                if(args[0].equalsIgnoreCase("kit"))
                {
                    Player pl = (Player) commandSender;
                    ItemStack diamondShovel = new ItemStack(Material.DIAMOND_SPADE, 1);
                    diamondShovel.addEnchantment(Enchantment.DIG_SPEED, 5);
                    ItemMeta diamondShovelMeta = diamondShovel.getItemMeta();
                    diamondShovelMeta.setDisplayName("§9§lDigger");
                    diamondShovel.setItemMeta(diamondShovelMeta);
                    pl.getInventory().setItem(8, diamondShovel);
                    commandSender.sendMessage(Main.getPrefix() + "Du hast das Kit bekommen!");
                }
            } else
            {
                commandSender.sendMessage(Main.getPrefix() + "Falsche Argumente!");
            }
            return true;
        }
        if(commandInput.equalsIgnoreCase("parkour"))
        {
            if(args.length >= 1)
            {
                if(args[0].equalsIgnoreCase("peod"))
                {
                    parkourEnabled = !parkourEnabled;
                    commandSender.sendMessage(Main.getPrefix() + "Der Parkour ist " + parkourEnabled);
                }
            } else
            {

            }
            return true;
        }

        return false;
    }
}
