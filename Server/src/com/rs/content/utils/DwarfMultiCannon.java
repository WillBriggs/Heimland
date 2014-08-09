package com.rs.content.utils;

import com.rs.game.Animation;
import com.rs.game.WorldObject;
import com.rs.game.player.OwnedObjectManager;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Adam
 * @since Aug,3.
 *
 */

public class DwarfMultiCannon {
	
	public static void hasItems(Player player) {
		if (player.getInventory().containsItem(6, 1) && player.getInventory().containsItem(8, 1) && player.getInventory().containsItem(10, 1)) {
			setUp(player);
		}  else {		
		player.out("You dont have the required items");
		}
	}

	public static void setUp(final Player player) {	
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					OwnedObjectManager.addOwnedObjectManager(player, new WorldObject[] { new WorldObject(7, 10, 0, player.getX(), player.getY(), player.getPlane()) }, 600000);
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(827));
				} else if (loop == 2) {
					OwnedObjectManager.addOwnedObjectManager(player, new WorldObject[] { new WorldObject(8, 10, 0, player.getX(), player.getY(), player.getPlane()) }, 600000);
				} else if (loop == 3) {
					player.setNextAnimation(new Animation(827));	
				} else if (loop == 4) {
					OwnedObjectManager.addOwnedObjectManager(player, new WorldObject[] { new WorldObject(6, 10, 0, player.getX(), player.getY(), player.getPlane()) }, 600000);
				} else if (loop == 5) {
					if (Utils.getRandom(4) == 0) {
						
				}
				loop++;
			}
			}
		}, 0, 1);
	player.setNextAnimation(new Animation(827));
OwnedObjectManager.addOwnedObjectManager(player, new WorldObject[] { new WorldObject(7, 10, 0, player.getX(), player.getY(), player.getPlane()) }, 600000);
OwnedObjectManager.removeObject(player, new WorldObject(8, 10, 0, player.getX(), player.getY(), player.getPlane()));
	player.setNextAnimation(new Animation(827));
	OwnedObjectManager.addOwnedObjectManager(player, new WorldObject[] { new WorldObject(8, 10, 0, player.getX(), player.getY(), player.getPlane()) }, 600000);
	player.unlock();
	}
	
	
}
