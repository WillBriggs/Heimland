package com.rs.game.player.content;

import com.rs.game.player.Player;

public class SolomonsStore {

	private static final int INTERFACE_ID = 0;
	private static final int SolomonStore = 0;

	public void OpenSolomon(Player player) {
		player.getPackets().sendWindowsPane(SolomonStore, 0);
	}
}
