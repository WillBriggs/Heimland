package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: JazzyYaYaYa
 * Date: 20.1.2013
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class Subscribe {
   public static int BOARD =  1067;

    public static void SubBoard(Player player) {
        if (player.isDonator()) {
        	
        } else {
    	player.getInterfaceManager().sendInterface(BOARD);
    }
        }

    public static void detectPlayerStatus(Player player) {
    	if (player.starter == 0) {
			player.getDialogueManager().startDialogue("Starter");
			player.	lock();
			player.chooseChar = 1;
			player.starter = 1;
		}
    }
    public static void handleButtons(Player player, int componentId) {
        if (componentId == 0) {
        	
            player.getPackets().sendOpenURL("http://www.mentiosrsps.zzl.org/donate.html");
            player.getInventory().refresh();
            player.closeInterfaces();
            detectPlayerStatus(player);
        }
        if (componentId == 24) {
            player.getPackets().sendOpenURL("http://www.mentiosrsps.zzl.org/donate.html");
            player.getInventory().refresh();
            player.closeInterfaces();
            detectPlayerStatus(player);
        }
        if (componentId == 12) {	
                player.getInventory().refresh();
            player.closeInterfaces();
            detectPlayerStatus(player);
        }
    }
}
