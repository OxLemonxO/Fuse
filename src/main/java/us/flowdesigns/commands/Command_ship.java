package us.flowdesigns.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(source = SourceType.BOTH)
@CommandParameters(description = "Ships a player with another player", usage = "/<command> <player1> <player2>")
public class Command_ship extends BaseCommand {
    @Override
    public boolean run(final CommandSender sender, final Player sender_p, final Command cmd, final String commandLabel, final String[] args, final boolean senderIsConsole)
    {
        if (!sender.hasPermission("fuse.ship"))
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);
            return true;
        }
        if (args.length != 2)
        {
            sender.sendMessage(ChatColor.RED + "Invalid Usage: /ship <player1> <player2>");
            return true;
        }

        Player GodzillaxNero = sender_p;
        String notPacks = args[0];
        String definitelyNotLemonHesForeverAlone = args[1];

        if (notPacks.equals(definitelyNotLemonHesForeverAlone))
        {
            sender.sendMessage(ChatColor.RED + "You can't ship the same person with the same person.");
            return true;
        }

        Bukkit.broadcastMessage(ChatColor.GREEN + "" + GodzillaxNero.getName() + " ships " + notPacks + " x " + definitelyNotLemonHesForeverAlone + "." + ChatColor.LIGHT_PURPLE + " <3");
        return true;
    }
}
