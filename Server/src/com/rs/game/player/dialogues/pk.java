package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;

public class pk extends Dialogue {

		public void start() {
			sendOptionsDialogue("Player Killing Locations", "Mage Bank", 
					"Multi Area (PvP)", "Wests (PvP)", "Easts (PvP)", "Revenants" );
		}

		public void run(int interfaceId, int componentId) {
		if(stage == -1) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2538,
						4715, 0));
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3240,
						3611, 0));
				player.getControlerManager().startControler("Wilderness");
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4608,
						5061, 0));
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2984,
						3596, 0));
				player.getControlerManager().startControler("Wilderness");
			} else if (componentId == OPTION_5) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3071,
						3649, 0));
			}
		}
	}
	public void finish() {
	}
	
}