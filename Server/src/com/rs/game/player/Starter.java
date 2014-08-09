package com.rs.game.player;

import com.rs.game.player.Player;

public class Starter {

        public static final int MAX_STARTER_COUNT = 1;

    	public static void appendStarter(Player player) {
    		String ip = player.getSession().getIP();
    		int count = StarterMap.getSingleton().getCount(ip);
                player.starter = 1;
                if (count >= MAX_STARTER_COUNT) {
                        return;
                }
                
                player.getPackets().sendWindowsPane(1028, 0);
                player.getInventory().addItem(1856, 1); //Information Book
                player.getInventory().addItem(995, 2500000); //2.5M GP
                player.getInventory().addItem(1153, 1); // Iron Helm
                player.getInventory().addItem(1115, 1); // Iron Platebody
                player.getInventory().addItem(1067, 1); // Iron Platelegs
                player.getInventory().addItem(1191, 1); // Iron Kiteshield
                player.getInventory().addItem(1323, 1); // Iron Scimitar
                player.getInventory().addItem(24315, 1); // Limited time Cape
                player.getInventory().addItem(1167, 1); //Cowl
                player.getInventory().addItem(1129, 1); //Body
                player.getInventory().addItem(1095, 1); //Chaps
                player.getInventory().addItem(841, 1); // Shortbow
                player.getInventory().addItem(884, 500); // Arrow
                player.getInventory().addItem(579, 1); //Hat
                player.getInventory().addItem(577, 1); //Top
                player.getInventory().addItem(1011, 1); //Bottom
                player.getInventory().addItem(1379, 1); //Staff
                player.getInventory().addItem(556, 250); // Air Rune
                player.getInventory().addItem(558, 250); // Mind Rune
                player.getInventory().addItem(554, 250); // Fire Rune
                player.getInventory().addItem(386, 100); // Shark
                player.getInventory().addItem(23256, 10); // Super Attack Flask
                player.getInventory().addItem(23280, 10); // Super Strength Flask
                player.getInventory().addItem(23292, 10); // Super Defence Flask
                player.setSpins(2);
                player.sendMessage("You have recieved 2 free spins! To get any more spins you must earn them.");
                player.getDialogueManager().startDialogue("HomeGuide");


                player.getHintIconsManager().removeUnsavedHintIcon();
                player.getMusicsManager().reset();
                player.getCombatDefinitions().setAutoRelatie(false);
                player.getCombatDefinitions().refreshAutoRelatie();
                StarterMap.getSingleton().addIP(ip);
        }
}