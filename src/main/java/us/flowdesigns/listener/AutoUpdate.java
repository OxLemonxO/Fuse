package us.flowdesigns.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.Bukkit.getServer;

public class AutoUpdate implements Listener {
    PluginManager pm = getServer().getPluginManager();
    Plugin p = pm.getPlugin("Fuse");
    PluginDescriptionFile pdf = p.getDescription();
    int version = this.getVersionFromString(pdf.getVersion());
    final String versionLink = "https://flowdesigns.us/version.txt";
    private Plugin plugin;
    @EventHandler
    public boolean onPlayerJoin(PlayerJoinEvent event) throws IOException {
        if (event.getPlayer().hasPermission("fuse.update") || event.getPlayer().isOp()) {
            URL url = new URL(versionLink);
            URLConnection con = url.openConnection();
            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            reader.ready();
            int newVersion = this.getVersionFromString(reader.readLine());
            if (newVersion > version) {
                event.getPlayer().sendMessage(ChatColor.RED + "There is an update available for Fuse. To update Fuse, type /fuse update");
            }
        }
        return true;
    }

    public int getVersionFromString(String from)
    {
        String result = "";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(from);

        while(matcher.find())
        {
            result += matcher.group();
        }

        return result.isEmpty() ? 0 : Integer.parseInt(result);
    }
}