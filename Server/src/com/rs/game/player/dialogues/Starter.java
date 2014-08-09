package com.rs.game.player.dialogues;

import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.DonatorActions;
import com.rs.game.player.actions.ShipTravelAction;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.cities.tzhaar.LavaMine;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.NomadsRequiem;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class Starter extends Dialogue {

	public Starter() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Pick a Starter Type", "I would like to become Fighter.",
				"I would like to become Archer.", "I would like to become Magician");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				player.getInventory().addItem(995, 5000000);
				player.getInventory().addItem(4587, 1);
				player.getInventory().addItem(20671, 1);
				player.getInventory().addItem(11037, 1);
				player.getInventory().addItem(1323, 1);
				player.getInventory().addItem(10551, 1);
				player.getInventory().addItem(8845, 1);
				player.getInventory().addItem(8850, 1);
				player.getInventory().addItem(405, 2);
				player.getInventory().addItem(22340, 1);
				player.getInventory().addItem(1333, 1);
				player.getInventory().addItem(25028, 1);
				player.getInventory().addItem(1725, 1);
				player.getInventory().addItem(6528, 1);
				player.getInventory().addItem(11118, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(22412, 1);
				player.getInventory().refresh();
				player.unlock();
				player.sm("Your current combat class is: Fighter.");
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				player.getInventory().addItem(995, 5000000);
				player.getInventory().addItem(863, 1200);
				player.getInventory().addItem(868, 1500);
				player.getInventory().addItem(892, 1200);
				player.getInventory().addItem(9185, 1);
				player.getInventory().addItem(8734, 1);
				player.getInventory().addItem(24379, 1);
				player.getInventory().addItem(24382, 1);
				player.getInventory().addItem(861, 1);
				player.getInventory().addItem(6177, 1);
				player.getInventory().addItem(25034, 1);
				player.getInventory().addItem(2445, 1000);
				player.getInventory().addItem(15272, 1000);
				player.getInventory().addItem(405, 2);
				player.getInventory().addItem(22340, 1);
				player.getInventory().addItem(9244, 850);
				player.sm("Your current combat class is: Archer.");
				player.getInventory().refresh();
				player.unlock();
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				player.getInventory().addItem(995, 5000000);
				player.getInventory().addItem(554, 5000);
				player.getInventory().addItem(555, 5000);
				player.getInventory().addItem(556, 5000);
				player.getInventory().addItem(557, 5000);
				player.getInventory().addItem(558, 5000);
				player.getInventory().addItem(559, 5000);
				player.getInventory().addItem(560, 5000);
				player.getInventory().addItem(561, 5000);
				player.getInventory().addItem(562, 5000);
				player.getInventory().addItem(563, 5000);
				player.getInventory().addItem(564, 5000);
				player.getInventory().addItem(565, 5000);
				player.getInventory().addItem(566, 5000);
				player.getInventory().addItem(24094, 1);
				player.getInventory().addItem(6889, 1);
				player.getInventory().addItem(2414, 1);
				player.getInventory().addItem(405, 2);
				player.getInventory().addItem(22340, 1);
				player.getInventory().addItem(25031, 1);
				player.getInventory().addItem(6107, 1);
				player.getInventory().addItem(6108, 1);
				player.getInventory().addItem(6109, 1);
				player.getInventory().refresh();
				player.sm("Your current combat class is: Magician.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.unlock();
			} 
		}
	}


	@Override
	public void finish() {
	}

}
