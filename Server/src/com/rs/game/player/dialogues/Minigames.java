package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.FightKiln;

public class Minigames extends Dialogue {

		public void start() {
			sendOptionsDialogue("Minigames Teleports", "Fight Pits", "Fight Caves", 
					"Fight Kiln", "Godwars", "More Options" );
		}

		public void run(int interfaceId, int componentId) {
		if(stage == -1) {
		if(componentId == OPTION_1) {//Fight Pits
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4601, 5061, 0));
		} else if(componentId == OPTION_2) {//Fight Caves
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4611, 5129, 0));
		} else if (componentId == OPTION_3) {//Fight Kiln
			Magic.sendNormalTeleportSpell(player, 0, 0, FightKiln.OUTSIDE);
		} else if(componentId == OPTION_4) {//Godwars
			sendOptionsDialogue("GodWars", "Nex", "Bandos", 
					"Armadyl", "Saradomin", "Zamorak" );
			stage = 5;
			
		} else if(componentId == OPTION_5) {//More Options
			sendOptionsDialogue("Minigames Teleports", "Clan Wars", "Dominion Tower", 
					"Warriors Guild", "Castle Wars", "More Options" );
			stage = 6;
		}
				
		} else if(stage == 5) {
			if(componentId == OPTION_1) {//Nex
				teleportPlayer(2904, 5203, 0);
			} else if(componentId == OPTION_2) {//Bandos
				teleportPlayer(2870, 5363, 0);
			} else if(componentId == OPTION_3) {//Armadyl
				teleportPlayer(2838, 5297, 0);
			} else if(componentId == OPTION_4) {//Saradomin
				teleportPlayer(2923, 5247, 0);
			} else if(componentId == OPTION_5) {//Zamorak
				teleportPlayer(2925, 5330, 0);
			} else {
				finish();
			}
		} else if(stage == 6) {
			if(componentId == OPTION_1) {//ClanWars
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2995, 9679, 0));
			} else if(componentId == OPTION_2) {//Dominion Tower
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3366, 3083, 0));
			} else if(componentId == OPTION_3) {//Warriors Guild
				player.setNextWorldTile(new WorldTile(2842, 3536, 2));
				player.getControlerManager().startControler("WGuildControler", new Object[] { });
				player.closeInterfaces();
			} else if(componentId == OPTION_4) {//Castle Wars
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2442, 3090, 0));
			} else if(componentId == OPTION_5) {//More
				sendOptionsDialogue("Minigames Teleports", "Barrows", "Crucible" );
				stage = 7;
			}
			} else if(stage == 7) {
			if(componentId == OPTION_1) {//Barrows
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3565,3289, 0));
			} else if(componentId == OPTION_2) {//Crucible
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3355, 6110, 0));
				} else {
					finish();
				}
			}
	}
	public void finish() {
	}
	
	private void teleportPlayer(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("GodWars");
	}
}