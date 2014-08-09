package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;

public class Training extends Dialogue {

		public void start() {
			sendOptionsDialogue(
					"Monster Teleports", "Rats",
					"Rock Crabs", "River Lum Cows", "More Options" );
		}

		public void run(int interfaceId, int componentId) {
		if(stage == -1) {
			if(componentId == OPTION_1) {//Rats
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3104, 9513, 0));
			} else if(componentId == OPTION_2) {//Rock Crabs
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2710, 3710, 0));
			} else if(componentId == OPTION_3) {//Cows
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3176, 3317, 0));
			} else if(componentId == OPTION_4) {//More Options
				sendOptionsDialogue(
						"Monster Teleports", "Tormented Demons", "Frost Dragons");
				stage = 2;
			}
				
				
			} else if(stage == 2) {
				if (componentId == OPTION_1) {//Tormented Demons
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2571, 5734, 0));
				} else if (componentId == OPTION_2) {//Frost Dragons
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1298, 4509, 0));
				} else if(componentId == OPTION_3) {//Catherby
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2809, 3436, 0));
				} else {
					finish();
				}
			}
		}
	public void finish() {
	}
}