package com.rs.game.player.dialogues;

import com.rs.utils.ShopsHandler;

public class ArmourShop extends Dialogue {

	private int npcId;
	private int shopId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		shopId = (Integer) parameters[1];
		sendNPCDialogue(npcId, 549, "Can I help you at all?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
					"Yes, please. What are you selling?",
					"How should I use your shop?", "No, thanks.");
			break;
		case 0:
			if (componentId == OPTION_1) {
				ShopsHandler.openShop(player, shopId);
				end();
			} else if (componentId == OPTION_2) {
				stage = 1;
				sendNPCDialogue(
						npcId,
						549,
						"I'm glad you ask! Here you can purchase Armoury",
						"from me which you can use to better yourself ",
						"in combat.");
			} else if (componentId == OPTION_3)
				end();
			break;
		case 1:
			stage = -2;
			sendNPCDialogue(npcId, 549,
					"You can also sell that you do not need ",
					"to me. ");
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
