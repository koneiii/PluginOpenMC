package fr.communaywen.core.tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandTpdeny implements CommandExecutor {

    TPAQueue tpQueue = TPAQueue.INSTANCE;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Player tpaplayer = tpQueue.TPA_REQUESTS.get(player);
            if (tpaplayer == null) {
                player.sendMessage(ChatColor.RED + "Vous n'avez pas de demande de téléporation");
                return true;
            }
            tpQueue.TPA_REQUESTS.remove(player);
            tpQueue.TPA_REQUESTS2.remove(tpaplayer);
            tpaplayer.sendMessage(ChatColor.RED + player.getName() + " a refusé votre demande de téléportation");
            player.sendMessage("Vous avez refusé la demande de téléporation de "+tpaplayer.getName());
            return true;
        }

        return false;
    }
}
