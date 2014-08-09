package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;

public class Bossing extends Dialogue {

		public void start() {
			sendOptionsDialogue("Bossing Teleports", "Kalphite Queen", "Corporeal Beast", 
					"King Black Dragon", "Queen Black Dragon");
		}

		public void run(int interfaceId, int componentId) {
		if(stage == -1) {
			if(componentId == OPTION_1) {//Kalphite Queen
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3507,9493, 0));
			} else if(componentId == OPTION_2) {//Corporeal Beast
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966, 4383, 2));
			} else if(componentId == OPTION_3) {//King Black Dragon
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3069, 10255, 0));
				player.getControlerManager().startControler("Wilderness");
			} else if (componentId == OPTION_4) {//Queen Black Dragon
					end();
					if (player.getSkills().getLevelForXp(Skills.SUMMONING) < 60) {
						player.getPackets().sendGameMessage("You need a summoning level of 60 to go through this portal.");
						return;
					}
					player.getControlerManager().startControler("QueenBlackDragonControler");
			}
		}
	}
	public void finish() {
	}
}