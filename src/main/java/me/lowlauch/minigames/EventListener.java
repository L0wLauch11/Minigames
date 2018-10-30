package me.lowlauch.minigames;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.lowlauch.minigames.Commands.parkourEnabled;
import static me.lowlauch.minigames.Commands.parkourSpawn;

public class EventListener implements Listener
{
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() instanceof Player)
        {
            if(event.getEntity().getWorld() == Main.getInstance().getServer().getWorld(Main.getInstance().getConfig().getString("snowball.lobby.world")))
            {
                if(event.getDamager() instanceof Snowball)
                {
                    ((Player) event.getEntity()).damage(2);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if(event.getMaterial() == Material.INK_SACK && parkourEnabled && event.getAction() == Action.RIGHT_CLICK_AIR ||event.getMaterial() == Material.ARROW && parkourEnabled && event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            parkourSpawn = new Location(Main.getInstance().getServer().getWorld(Main.getInstance().getConfig().getString("parkour.lobby.world")), Main.getInstance().getConfig().getInt("parkour.lobby.x"), Main.getInstance().getConfig().getInt("parkour.lobby.y"), Main.getInstance().getConfig().getInt("parkour.lobby.z"));
            event.getPlayer().teleport(parkourSpawn);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if(event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE && parkourEnabled)
        {
            ItemStack inc_sac = new ItemStack(Material.INK_SACK, 1, (short) 1 );
            ItemMeta inc_sac_meta = inc_sac.getItemMeta();
            inc_sac_meta.setDisplayName("§4§lParkour Spawn");
            inc_sac.setItemMeta(inc_sac_meta);
            if(!event.getPlayer().getInventory().contains(inc_sac))
            {
                event.getPlayer().getInventory().addItem(inc_sac);
                inc_sac.setItemMeta(inc_sac_meta);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        if(event.getEntity().getWorld() == Main.getInstance().getServer().getWorld(Main.getInstance().getConfig().getString("snowball.lobby.world")))
        {
            event.getEntity().setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event)
    {
        if(event.getPlayer().getWorld() == Main.getInstance().getServer().getWorld(Main.getInstance().getConfig().getString("snowball.lobby.world")))
        {
            if(event.getPlayer().getGameMode() == GameMode.SURVIVAL)
            {
                if(event.getBlock().getType() == Material.SNOW)
                {
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SNOW_BALL));
                    event.setCancelled(true);
                } else
                {
                    event.setCancelled(true);
                }
            }
        }
    }

}
