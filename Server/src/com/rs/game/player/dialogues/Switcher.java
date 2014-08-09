package com.rs.game.player.dialogues;

import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class Switcher extends Dialogue {

	public Switcher() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Select a Option",
				"I would like to switch my prayer book.",
				"I would like to switch my spells.",
				"Sorry i don't need any book switch for now.");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue("Pick a prayer book.",
						"I would like to take curse prayers.",
						"I would like to take normal prayers.");
				stage = 2;
			} else if (componentId == OPTION_2) {

				sendOptionsDialogue("Pick a spellbook",
						"I would like to use: Modern Spellbook.",
						"I would like to use: Ancient Spellbook.",
						"I would like to use: Lunar Spellbook.");
				stage = 3;
			} else if (componentId == OPTION_3) {
				player.getInterfaceManager().closeChatBoxInterface();
			}
		} else if (componentId == OPTION_4) {
			stage = 8;
			sendOptionsDialogue("What you would like to do?",
					"I would like to edit my gender & skin.",
					"I would like to edit my hairstyles.",
					"I would love to change my clothes, Aereck.",
					"I don't want to do anything.");
		} else if (stage == 8) {
			if (componentId == OPTION_1) {
				player.getInterfaceManager().closeChatBoxInterface();
				PlayerLook.openMageMakeOver(player);
			} else if (componentId == OPTION_2) {
				PlayerLook.openHairdresserSalon(player);
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				PlayerLook.openThessaliasMakeOver(player);
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				player.getInterfaceManager().closeChatBoxInterface();
			}
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.getPrayer().setPrayerBook(true);
				player.getInterfaceManager().closeChatBoxInterface();
				player.sm("You pick the power of Zaros: Curse Prayers.");
			} else if (componentId == OPTION_2) {
				player.getPrayer().setPrayerBook(false);
				player.getInterfaceManager().closeChatBoxInterface();
				player.sm("You pick the power of Normal Prayers.");
			}

		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				player.getCombatDefinitions().setSpellBook(0);
				player.getInterfaceManager().closeChatBoxInterface();
				player.sm("You have picked the modern spells.");
			} else if (componentId == OPTION_2) {
				player.getCombatDefinitions().setSpellBook(1);
				player.getInterfaceManager().closeChatBoxInterface();
				player.sm("You have picked the ancient spells.");
			} else if (componentId == OPTION_3) {
				player.getCombatDefinitions().setSpellBook(2);
				player.getInterfaceManager().closeChatBoxInterface();
				player.sm("You have picked the lunar spells.");
			}
		}
	}

	private void teleportPlayer(int x, int y, int z) {
		player.setNextGraphics(new Graphics(111));
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();

	}

	@Override
	public void finish() {
	}

}
