package us.flowdesigns.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.flowdesigns.fuse.Fuse;
import us.flowdesigns.fuse.Updater;

@CommandPermissions(source = SourceType.BOTH)
@CommandParameters(description = "Shows information about Fuse", usage = "/<command> [reload | debug | help | update]", aliases = "packscore,oxygen,sky,nitrogen,trident")
public class Command_fuse extends BaseCommand {
    @Override
    public boolean run(final CommandSender sender, final Player sender_p, final Command cmd, final String commandLabel, final String[] args, final boolean senderIsConsole)
    {
        String spawn_on_join = plugin.getConfig().getString("server.spawn_on_join");
        String applications_enabled = plugin.getConfig().getString("server.applications_enabled");
        String drop_items_on_death = plugin.getConfig().getString("server.drop_items_on_death");
        String clear_inventory_on_join = plugin.getConfig().getString("server.clear_inventory_on_join");
        String server_hunger = plugin.getConfig().getString("server.hunger_enabled");
        String fall_damage_enabled = plugin.getConfig().getString("server.fall_damage_enabled");
        String dev = plugin.getConfig().getString("server.dev");
        String superusers = plugin.getConfig().getString("players.superusers");
        Fuse.BuildProperties build = Fuse.build;
        if (args.length == 0)
        {
            sender.sendMessage(ChatColor.GOLD + String.format("Version "
                            + ChatColor.BLUE + "%s - %s Build %s " + ChatColor.GOLD + "("
                            + ChatColor.BLUE + "%s" + ChatColor.GOLD + ")",
                    build.codename,
                    build.version,
                    build.number,
                    build.head));
            sender.sendMessage(String.format(ChatColor.GOLD + "Compiled "
                            + ChatColor.BLUE + "%s" + ChatColor.GOLD + " by "
                            + ChatColor.BLUE + "%s",
                    build.date,
                    build.author));
        sender.sendMessage(ChatColor.RED + "Fuse is an advanced plugin designed to provide useful utilities for a Minecraft server");
        sender.sendMessage(ChatColor.GREEN + "Type /contributors to see who contributed to Fuse");
        sender.sendMessage(ChatColor.GREEN + "Type /fuse update to check for and install updates.");
        if (dev.equalsIgnoreCase("true"))
        {
        sender.sendMessage(ChatColor.DARK_AQUA + "The server is currently in development mode. "
        + "This means there may be unstable plugin builds on this server, and the server could crash more than normal!");
        }
        return true;
        }
        switch (args[0].toLowerCase()) {
            case "reload":
            {
                if (!sender.hasPermission("fuse.reload"))
                {
                    sender.sendMessage(Messages.MSG_NO_PERMS);
                    return true;
                }
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GRAY + "Configuration file has been successfully reloaded!");
                return true;
            }
            case "debug":
            {
                if (!sender.hasPermission("fuse.debug"))
                {
                    sender.sendMessage(Messages.MSG_NO_PERMS);
                    return true;
                }
                sender.sendMessage(ChatColor.GRAY + "Config Options");
                sender.sendMessage(ChatColor.GRAY + "server.spawn_on_join: " + spawn_on_join);
                sender.sendMessage(ChatColor.GRAY + "server.applications_enabled: " + applications_enabled);
                sender.sendMessage(ChatColor.GRAY + "server.dev: " + dev);
                sender.sendMessage(ChatColor.GRAY + "server.drop_items_on_death: " + drop_items_on_death);
                sender.sendMessage(ChatColor.GRAY + "server.clear_inventory_on_join: " + clear_inventory_on_join);
                sender.sendMessage(ChatColor.GRAY + "server.hunger_enabled: " + server_hunger);
                sender.sendMessage(ChatColor.GRAY + "server.fall_damage_enabled: " + fall_damage_enabled);
                sender.sendMessage(ChatColor.GRAY + "players.superusers: " + superusers);
                return true;
        } // debug end
            case "help":
            {
                sender.sendMessage(ChatColor.GRAY + "To see Fuse's help, type /help Fuse or /? Fuse. If you are the owner, for additional inforrmation, please read the help.yml file inside of the Fuse folder.");
                return true;
            }
            case "update": {
                if (!sender.hasPermission("fuse.update")) {
                    sender.sendMessage(Messages.MSG_NO_PERMS);
                    return true;
                }
                Updater updater = new Updater(Fuse.plugin);
                updater.update(sender);
                return true;
            }
            default:
            {
                return false;
            }
        } // end args
    }
}

