package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;


public class Options extends Dialogue {

		public void start() {
			sendOptionsDialogue("Skilling Locations", "Achievement Capes", "Fishing", 
					"Summoning", "Woodcutting", "More Options");
		}

		public void run(int interfaceId, int componentId) {
		if(stage == -1) {
			if (componentId == OPTION_1) {//Skillcapes
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3499,
						3620, 0));
				
			} else if (componentId == OPTION_2) {//Fishing
				sendOptionsDialogue("Fishing", "Catherby", "Living Rocks Cavern", "Fishing Guild");
				stage = 3;
				
			} else if (componentId == OPTION_3) {//Summoning
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2926,
						3448, 0));
				
			} else if (componentId == OPTION_4) {//Woodcutting
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2726,
						3484, 0));
			
			} else if(componentId == OPTION_5) {//More Options
			sendOptionsDialogue("Skilling Locations", "Mining", "Agility");
			stage = 2;
			}
			
		} else if(stage == 3) {
				if (componentId == OPTION_1) {//Catherby
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2833,
					3438, 0));
			} else if(componentId == OPTION_2) {//Living Rocks
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3651, 5122, 0));
			} else if(componentId == OPTION_3) {//Guild
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2591, 3420, 0));
			} else {
				finish();
			}
				
			} else if(stage == 2) {
				if(componentId == OPTION_1) {//Mining
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3298,
							3285, 0));
				} else if(componentId == OPTION_2) {//Agility
					sendOptionsDialogue("Agility", "Gnome Agility Course", "Barbarian Outpost Course");
					stage = 4;
				}
			} else if(stage == 4) {
				if (componentId == OPTION_1) {//Gnome
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2472,
					3438, 0));
			} else if(componentId == OPTION_2) {//Barbarian Outpost
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2553, 3546, 0));
			} else {
				finish();
			}
				}
			}
	public void finish() {
	}
	
}