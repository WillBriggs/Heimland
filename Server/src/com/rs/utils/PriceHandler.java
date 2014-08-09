/*package com.rs.utils;

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

/**
 * Handles custom prices
 * @author Displee
 */

/*public final class PriceHandler {

	private static String UNPACKED_PATH = "data/items/prices.txt";
	private static final String PACKED_PATH = "data/items/prices.p";
	private static final HashMap<Integer, Integer> handledPrices = new HashMap<Integer, Integer>();
	
	public static void init() {
		if (new File(PACKED_PATH).exists()) {
			loadPackedPrices();
		} else {
			loadUnpackedPrices();
		}
	}
	
	private static void loadUnpackedPrices() {
		Logger.log("PriceHandler", "Loading prices shops...");
		try {
			BufferedReader in = new BufferedReader(new FileReader(UNPACKED_PATH));
			DataOutputStream out = new DataOutputStream(new FileOutputStream(PACKED_PATH));
			while (true) {
				String line = in.readLine();
				if (line == null)
					break;
				if (line.startsWith("//"))
					continue;
				String[] splitedLine = line.split(" ", 2);
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
	
	private static void loadPackedPrices() {
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

}*/