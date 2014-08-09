package com.rs.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.content.ItemConstants;


public final class EconomyPrices {
	public static String UNPACKED_PATH = "data/items/prices.txt";
	public static final String PACKED_PATH = "data/items/prices.p";
	public static final HashMap<Integer, Integer> handledPrices = new HashMap<Integer, Integer>();
	
	public static void init() {
		File f1 = new File("data/items/prices.p");
		f1.delete();
		if (new File(PACKED_PATH).exists()) {
			loadPackedPrices();
		} else {
			loadUnpackedPrices();
		}
	}
	public static void loadUnpackedPrices() {
	try {
		BufferedReader in = new BufferedReader(new FileReader(UNPACKED_PATH));
		DataOutputStream out = new DataOutputStream(new FileOutputStream(PACKED_PATH));
		while (true) {
			String line = in.readLine();
			if (line == null)
				break;
			if (line.startsWith("//"))
				continue;
			String[] splitedLine = line.split(" - ", 2);
			int itemId = Integer.valueOf(splitedLine[0]);
			int price = Integer.valueOf(splitedLine[1]);
			out.writeShort(itemId);
			out.writeShort(price);
			ItemDefinitions itemDef = ItemDefinitions.getItemDefinitions(itemId);
			addPrice(itemId, itemDef.value = price);
		}
		in.close();
		out.close();
	} catch (Throwable e) {
		Logger.handle(e);
	}
}

public static void loadPackedPrices() {
	try {
		RandomAccessFile in = new RandomAccessFile(PACKED_PATH, "r");
		FileChannel channel = in.getChannel();
		ByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
		while (buffer.hasRemaining()) {
			int itemId = buffer.getShort() & 0xffff;
			int price = buffer.getShort() & 0xffff;
			ItemDefinitions itemDef = ItemDefinitions.getItemDefinitions(itemId);
			addPrice(itemId, itemDef.value = price);
		}
		channel.close();
		in.close();
	} catch (Throwable e) {
		Logger.handle(e);
	}
}

public static void addPrice(int itemId, int price) {
	handledPrices.put(itemId, price);
}

	public static int getPrice(int itemId) {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		if (defs.isNoted())
			itemId = defs.getCertId();
		else if (defs.isLended())
			itemId = defs.getLendId();
		if (!ItemConstants.isTradeable(new Item(itemId, 1)))
			return 0;
		if (itemId == 995) 
			return 1;
		return defs.getValue(); 
								
	}

	private EconomyPrices() {

	}
}