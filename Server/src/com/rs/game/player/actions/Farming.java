package com.rs.game.player.actions;

import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

/**
 * 
 * @author JazzyYaYaYa | Nexon | Fuzen Seth
 *
 */
public class Farming {
public static int RAKE = 5341;
public static final Animation USERAKE = new Animation(2273);

	public static void startRake(Player player) {
		if (player.getInventory().containsItem(RAKE, 1)) {
			player.lock(4);
			player.getSkills().addXp(Skills.FARMING, 75);
			player.sm("You rake the weed.");
			player.getInventory().addItem(6055, 1);
			player.setNextAnimation(USERAKE);
			player.getInventory().refresh();
		}
		else {
			player.sm("You must have rake, buy one at general store.");
		}
	}
}
