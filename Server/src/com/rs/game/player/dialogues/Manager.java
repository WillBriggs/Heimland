package com.rs.game.player.dialogues;

import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.utils.ShopsHandler;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class Manager extends Dialogue {

	public Manager() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Mentios Manager Options",
				"I'd like to access to my character settings.",
				"I would like to see Mentios points shop.",
				"I want to go place of donators, could you take me there?");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue("Mentios - Account Settings",
						"I would like to edit my gender & skin.",
						"I would like to edit my hairstyles.",
						"I would love to change my clothes, Eva.");
				stage = 2;
			} else if (componentId == OPTION_2) {
				player.getInterfaceManager().closeChatBoxInterface();
				ShopsHandler.openShop(player, 34);
				stage = 3;
			} else if (componentId == OPTION_3) {
				if (player.isDonator()) {
					player.teleportPlayer(2099, 3914, 0);
				} else {
					player.getInterfaceManager().closeChatBoxInterface();
					player.sm("You are not donator, Manager won't let you go.");
				}
			} else if (stage == 2) {
				if (componentId == OPTION_1) {
					player.getInterfaceManager().closeChatBoxInterface();
					PlayerLook.openMageMakeOver(player);
				} else if (componentId == OPTION_2) {
					PlayerLook.openHairdresserSalon(player);
					player.getInterfaceManager().closeChatBoxInterface();
				} else if (componentId == OPTION_3) {
					PlayerLook.openThessaliasMakeOver(player);
					player.getInterfaceManager().closeChatBoxInterface();
				}
			}
			if (stage == 69) {
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
			}
		}

	}

	@Override
	public void finish() {
	}

}
