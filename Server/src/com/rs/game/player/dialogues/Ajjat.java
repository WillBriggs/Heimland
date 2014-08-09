package com.rs.game.player.dialogues;

import com.rs.game.player.Skills;

public class Ajjat extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "What do you want?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
					"I would like to purchase a Attack Skillcape.", "Nevermind.");
			break;
		case 0:
			if (componentId == OPTION_2) {
				stage = 1;
				sendPlayerDialogue(9827, "Nevermind.");
			} else {
				stage = 2;
				sendPlayerDialogue(9827, "I would like to purchase a Attack Skillcape.");
			}
			break;
		case 1:
			stage = -2;
			sendNPCDialogue(npcId, 9827,
					"Well, please return if you change your mind.");
			break;
		case 2:
			stage = 3;
			sendNPCDialogue(
					npcId,
					9827,
					"To purchase an Attack Skillcape, you must have 99 in the Attack Skill and 99,000 GP.");
			break;
		case 3:
			stage = 4;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
					"I would like to purchase it.",
					"Nevermind, I do not have those requirements.");
			break;
		case 4:
			if (componentId == OPTION_2) {
				end();
			} else {
				stage = 5;
				if (player.getSkills().getLevelForXp(Skills.ATTACK) == 99
						&& player.getInventory().containsItem(995, 99000)) {
						player.getInventory().deleteItem(995, 99000);
						player.getInventory().addItem(9749, 1);
						player.getInventory().addItem(9748, 1);
			}
			end();
			break;
			}
		case 5:
			stage = 6;
			end();
			break;
		case 6:
			end();
		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
