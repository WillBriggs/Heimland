package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;

public class Monster extends Dialogue {

	public void start() {
		sendOptionsDialogue(
				"Dungeons", "Slayer Dungeons", "Fremennik Dungeon", "Revenant Cave", "Tolna's Rift", "Grotworms Lair");
	}

	public void run(int interfaceId, int componentId) {
	if(stage == -1) {
		if(componentId == OPTION_1) {
			sendOptionsDialogue("Dungeons", "Slayer Tower", "Ancient Cavern" );
			stage = 2;
		} else if(componentId == OPTION_2) {//Dungeon
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2808, 10002, 0));
		} else if(componentId == OPTION_3) {//Cave
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3071, 3649, 0));
		} else if(componentId == OPTION_4) {//Rift
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3297, 9824, 0));
		} else if(componentId == OPTION_5) {//Lair
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1205, 6372, 0));
		}
			
		} else if(stage == 2) {
			if(componentId == OPTION_1) {//Slayer Tower
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3429, 3534, 0));
			} else if(componentId == OPTION_2) {//Ancient Cavern
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1738, 5312, 1));
			}
		}
	}
	public void finish() {
	}
	
}