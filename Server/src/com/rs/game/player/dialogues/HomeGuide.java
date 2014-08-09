package com.rs.game.player.dialogues;


public class HomeGuide extends Dialogue {


	public void start() {
		sendDialogue("Welcome great warrior to Citellum RSPS!",
					"Here, you can obtain opportunities that will some day give you the",
				 	"chance of becoming a true warrior...a real warrior!",
				 	"Citellum gives you that chance from Bossing and Player Killing",
				 	"to Challenging Tasks and adventurous Minigames.",
				 	"Give us your all and become that warrior!");
	}

	public void run(int interfaceId, int componentId) {
		end();
	}
	public void finish() {

	}

}
