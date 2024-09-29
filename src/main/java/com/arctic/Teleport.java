package com.arctic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

    public class Teleport extends JavaPlugin implements CommandExecutor {

        @Override
        public void onEnable() {
            getLogger().info(ChatColor.GREEN + "Teleport has been enabled. Type /tphelp for list of all commands.");
            getCommand("tp").setExecutor(this);
            getCommand("tphere").setExecutor(this);
            getCommand("tpto").setExecutor(this);
            getCommand("vanish").setExecutor(this);
            getCommand("unvanish").setExecutor(this);
            getCommand("tphelp").setExecutor(this);
        }

        @Override
        public void onDisable() {
            getLogger().info("Teleport has been disabled!");
        }

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            String prefix = "[TP] ";

            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(ChatColor.RED + prefix + "Hey, Console, only players are allowed to use this command!");
                return true;
            }

                Player player = (Player) sender;

                if (cmd.getName().equalsIgnoreCase("tp")) {

                    if (args.length != 1) {
                        player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Usage: /tp <player>");
                        return true;
                    }

                    Player target = Bukkit.getPlayer(args[0]);

                    if (target == null) {
                        player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Player not found or not online!");
                        return true;
                    }

                    if (target == player) {
                        player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "You can't teleport yourself!");
                        return true;
                    }

                    player.teleport(target.getLocation());
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + "Teleported to " + target.getName());
                    return true;
                }

            if (cmd.getName().equalsIgnoreCase("tpto")) {

                if (args.length != 2) {
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Usage: /tpto <targetPlayer | *> <player>");
                    return true;
                }

                Player toPlayer = Bukkit.getPlayer(args[1]);

                if (toPlayer == null) {
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Player not found or not online!");
                    return true;
                }

                if (args[0].equalsIgnoreCase("*")) {

                    if (Bukkit.getOnlinePlayers().size() <= 1) {
                        player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "No players are online to teleport!");
                        return true;
                    }

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        if (onlinePlayer != toPlayer && onlinePlayer != player) {
                            onlinePlayer.teleport(toPlayer.getLocation());
                        }
                    }
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + "All players have been teleported to " + toPlayer.getName());
                    return true;
                }

                Player targetPlayer = Bukkit.getPlayer(args[0]);

                if (targetPlayer == null) {
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Targeted player not found or not online!");
                    return true;
                }

                targetPlayer.teleport(toPlayer.getLocation());
                player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + "Teleported " + targetPlayer.getName() + " to " + toPlayer.getName());
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("tphere")) {

                if (args.length != 1) {
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Usage: /tphere <player | *>");
                    return true;
                }

                if (args[0].equalsIgnoreCase("*")) {

                    if (Bukkit.getOnlinePlayers().size() <= 1) {
                        player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "No players are online to teleport to you!");
                        return true;
                    }

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        if (onlinePlayer != player) {
                            onlinePlayer.teleport(player.getLocation());
                        }
                    }
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + "All players have been teleported to you.");
                    return true;
                }


                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Player not found or not online!");
                    return true;
                }

                if (target == player) {
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "You can't teleport yourself to yourself!");
                    return true;
                }

                target.teleport(player.getLocation());
                player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + "Teleported " + target.getName() + " to you.");
                return true;
            }


            if (cmd.getName().equalsIgnoreCase("vanish")) {

                if (args.length == 0) {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.hidePlayer(player);
                    }
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + "You are now vanished.");
                    return true;
                }

                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Player not found or not online!");
                        return true;
                    }

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.hidePlayer(target);
                    }
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + target.getName() + " has been vanished.");
                    return true;
                }

                player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Usage: /vanish | /vanish <player>");
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("unvanish")) {

                if (args.length == 0) {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.showPlayer(player);
                    }
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + "You are now visible to others.");
                    return true;
                }

                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Player not found or not online!");
                        return true;
                    }

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.showPlayer(target);
                    }
                    player.sendMessage(ChatColor.AQUA + prefix + ChatColor.GREEN + target.getName() + " is now visible to others.");
                    return true;
                }

                player.sendMessage(ChatColor.AQUA + prefix + ChatColor.RED + "Usage: /unvanish | /unvanish <player>");
                return true;
            }


            if (cmd.getName().equalsIgnoreCase("tphelp")) {
                sender.sendMessage(ChatColor.AQUA + " §lTeleport v1.2\n" +
                        ChatColor.GOLD + " /tp §f<player>\n" +
                        ChatColor.GOLD + " /tpto §f<targetPlayer | *> <player>\n" +
                        ChatColor.GOLD + " /tphere §f<player | *>\n" +
                        ChatColor.GOLD + " /vanish | /vanish §f<player>\n" +
                        ChatColor.GOLD + " /unvanish | /unvanish §f<player>");
                return true;
            }

            return false;
        }
    }
