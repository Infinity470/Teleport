package com.arctic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

    public class Teleport extends JavaPlugin implements CommandExecutor {

        @Override
        public void onEnable() {
            getLogger().info("Teleport has been enabled!");
            getCommand("tp").setExecutor(this);
            getCommand("tphere").setExecutor(this);
            getCommand("vanish").setExecutor(this);
            getCommand("unvanish").setExecutor(this);
        }

        @Override
        public void onDisable() {
            getLogger().info("Teleport has been disabled!");
        }

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command.");
                return true;
            }

            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("tp")) {
                if (args.length != 1) {
                    player.sendMessage("Usage: /tp <player>");
                    return true;
                }

                if (!player.hasPermission("teleport.tp")) {
                    player.sendMessage("You do not have permission to use this command.");
                    return true;
                }


                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Player not found or not online.");
                    return true;
                }

                player.teleport(target.getLocation());
                player.sendMessage(ChatColor.GREEN + "Teleported to " + target.getName());
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("tphere")) {
                if (args.length != 1) {
                    player.sendMessage("Usage: /tphere <player>");
                    return true;
                }

                if (!player.hasPermission("teleport.tphere")) {
                    player.sendMessage("You do not have permission to use this command.");
                    return true;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Player not found or not online.");
                    return true;
                }

                target.teleport(player.getLocation());
                player.sendMessage(ChatColor.GREEN + "Teleported " + target.getName() + " to you.");
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("vanish")) {
                if (!player.hasPermission("teleport.vanish")) {
                    player.sendMessage("You do not have permission to use this command.");
                    return true;
                }

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.hidePlayer(player);
                }
                player.sendMessage(ChatColor.GREEN + "You are now vanished.");

                return true;
            }

            if (cmd.getName().equalsIgnoreCase("unvanish")) {
                if (!player.hasPermission("teleport.unvanish")) {
                    player.sendMessage("You do not have permission to use this command.");
                    return true;
                }

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.showPlayer(player);
                }
                player.sendMessage(ChatColor.GREEN + "You are now visible to others.");
                return true;
            }

            return false;
        }
    }


