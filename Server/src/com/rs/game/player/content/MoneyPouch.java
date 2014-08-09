package com.rs.game.player.content;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.rs.game.player.Player;

public class MoneyPouch implements Serializable {

	private static final long serialVersionUID = -3847090682601697992L;

	private transient Player player;
	private boolean usingPouch;

	public MoneyPouch(Player player) {
		this.player = player;
	}

	public void switchPouch() {
		usingPouch = !usingPouch;
		player.getPackets().sendRunScript(5557, 1);
		player.getPackets().sendRunScript(5560, player.coinAmount);
	}

	private void refresh(boolean swap) {
		player.getPackets().sendRunScript(5557, 0);
		player.getPackets().sendRunScript(5560, player.coinAmount);
	}

	private String getFormattedNumber(int amount) {
		return new DecimalFormat("#,###,##0").format(amount).toString();
	}

	public void sendExamine() {
		player.getPackets().sendGameMessage(
				"Your money pouch current contains "
						+ getFormattedNumber(player.coinAmount) + " Coins.");
	}

	public void addMoney(int coinAmount, int addedAmount, boolean trade,
			boolean shop) {
		@SuppressWarnings("unused")
		int poo = (player.coinAmount + coinAmount);
		if (player.coinAmount >= Integer.MAX_VALUE) {
			player.getInventory().addItem(995,
					(coinAmount + player.coinAmount) - Integer.MAX_VALUE);
		} else {
			if (shop || trade) {
				player.coinAmount += coinAmount;
			} else {
				player.coinAmount += coinAmount;
				player.getInventory().deleteItem(995, addedAmount);
			}
			player.getPackets().sendGameMessage(
					(coinAmount == 1 ? "One" : NumberFormat
							.getIntegerInstance().format(coinAmount))
							+ " coin"
							+ (coinAmount == 1 ? "" : "s")
							+ " "
							+ (coinAmount == 1 ? "has" : "have")
							+ " been added to your money pouch.");
			player.getPackets().sendRunScript(5561, 1, addedAmount);
			refresh(false);
		}
	}

	public void removeMoney(int coinAmount, int removedAmount, boolean trade,
			boolean shop) {
		if (shop) {
			if (coinAmount > player.coinAmount) {
				player.sm("You don't have that many Coins in your Money Pouch.");
				return;
			}
			player.coinAmount -= coinAmount;
		} else if (trade) {
			player.coinAmount -= coinAmount;
		} else {
			if (coinAmount > player.coinAmount) {
				player.sm("You don't have that many Coins in your Money Pouch.");
				return;
			}
			player.coinAmount -= coinAmount;
			player.getInventory().addItem(995, removedAmount);
		}
		player.getPackets().sendGameMessage(
				(coinAmount == 1 ? "One" : NumberFormat.getIntegerInstance()
						.format(coinAmount))
						+ " coin"
						+ (coinAmount == 1 ? "" : "s")
						+ " "
						+ (coinAmount == 1 ? "has" : "have")
						+ " been removed from your money pouch.");
		player.getPackets().sendRunScript(5561, 0, removedAmount);
		refresh(false);
	}
}