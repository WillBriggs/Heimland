package com.rs.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

public class NPCSpawning {

	/**
	 * Contains the custom npc spawning
	 */

	public static void spawnNPCS() {
		World.spawnNPC(0, new WorldTile(3453, 3718, 0), -1, true);
		World.spawnNPC(8629, new WorldTile(1888, 5127, 0), -1, true);
		World.spawnNPC(6971, new WorldTile(4522, 5538, 0), 1, false);
		World.spawnNPC(0, new WorldTile(3453, 3718, 0), -1, true);
		World.spawnNPC(5532, new WorldTile(4525, 5613, 0), -1, true);
		World.spawnNPC(13929, new WorldTile(4516, 5587, 0), 0, true);
		World.spawnNPC(230, new WorldTile(2223, 3796, 2), 1, true);
		World.spawnNPC(8555, new WorldTile(4506, 5590, 0), 0, true);
		World.spawnNPC(10479, new WorldTile(3161, 4278, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3161, 4275, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3161, 4273, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3161, 4270, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3161, 4267, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3161, 4265, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3161, 4263, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3161, 4262, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3161, 4261, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3153, 4251, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3156, 4249, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3172, 4261, 0), -1, true);
		World.spawnNPC(10479, new WorldTile(3173, 4268, 0), -1, true);

		// Mentios Manager
		World.spawnNPC(13768, new WorldTile(4517, 5539, 0), -1, true);
		World.spawnNPC(4247, new WorldTile(4524, 5601, 0), 2, true);
		
		World.spawnNPC(13335, new WorldTile(2182, 5669, 0), -1, true);

		World.spawnNPC(3709, new WorldTile(4514, 5595, 0), -1, true);

		World.spawnNPC(13460, new WorldTile(2704, 3642, 0), -1, true);
		World.spawnNPC(3334, new WorldTile(3135, 3739, 0), -1, true);

		World.spawnNPC(550, new WorldTile(4515, 5601, 0), 0, false);
		// Missions
		/* Spawns */
		// Khumn, mission giver
		World.spawnNPC(15867, new WorldTile(3145, 4656, 0), 0, false);
		/* More shops */
		World.spawnNPC(551, new WorldTile(4513, 5601, 0), 0, false);
		World.spawnNPC(554, new WorldTile(4509, 5607, 0), 0, false);

		/* Spell and prayer books switcher (Father Aereck) */
		World.spawnNPC(456, new WorldTile(4524, 5522, 0), 0, false);
		World.spawnNPC(15842, new WorldTile(4711, 5606, 0), -1, false);
		
		
		World.spawnNPC(15839, new WorldTile(4502, 5596, 0), 0, false);
		World.spawnNPC(15839, new WorldTile(4504, 5596, 0), 0, false);
		World.spawnNPC(15839, new WorldTile(4506, 5596, 0), 0, false);
		World.spawnNPC(15839, new WorldTile(4508, 5596, 0), 0, false);

		/* Bankers inside of the home */
		World.spawnNPC(15839, new WorldTile(4515, 5525, 0), 0, false);
		World.spawnNPC(15840, new WorldTile(4515, 5527, 0), 0, false);
		World.spawnNPC(15841, new WorldTile(4515, 5529, 0), 0, false);
		World.spawnNPC(15842, new WorldTile(4515, 5531, 0), 0, false);
		World.spawnNPC(15843, new WorldTile(4515, 5533, 0), 0, false);
		World.spawnNPC(15839, new WorldTile(4515, 5535, 0), 0, false);

		World.spawnNPC(15839, new WorldTile(4524, 5525, 0), 0, false);
		World.spawnNPC(15840, new WorldTile(4524, 5527, 0), 0, false);
		World.spawnNPC(15841, new WorldTile(4524, 5529, 0), 0, false);
		World.spawnNPC(15842, new WorldTile(4524, 5531, 0), 0, false);
		World.spawnNPC(15843, new WorldTile(4524, 5533, 0), 0, false);
		World.spawnNPC(15839, new WorldTile(4524, 5535, 0), 0, false);

		// Hunter shop
		World.spawnNPC(5112, new WorldTile(4504, 5604, 0), -1, false);

		// Mr Ex
		World.spawnNPC(3709, new WorldTile(3218, 3433, 0), -1, false);

		// Polypore Dungeon Shop
		World.spawnNPC(875, new WorldTile(4724, 5466, 0), -1, false);

		// Corporeal Shop
		World.spawnNPC(13191, new WorldTile(2959, 4382, 2), -1, false);

		/* Start of new home */
		/**************************************************************************/
		/**************************************************************************/
		World.spawnObject(new WorldObject(-1, 0, 0, 3671, 2976, 0), false);
		// Bankers
		World.spawnNPC(495, new WorldTile(3213, 3439, 0), -1, false, false);
		World.spawnNPC(494, new WorldTile(3212, 3439, 0), -1, false, false);
		World.spawnNPC(494, new WorldTile(3682, 2983, 0), -1, false, false);
		// Kurdal
		World.spawnNPC(9085, new WorldTile(4509, 5520, 0), 1, true, false);
		// Xuan
		World.spawnNPC(13727, new WorldTile(4515, 5587, 0), 2, false, false);
		// Ghost
		World.spawnNPC(457, new WorldTile(4512, 5601, 0), 2, false);
		// WiseOldMan
		World.spawnNPC(3820, new WorldTile(4511, 5601, 0), 2, false);
		// Ajjat
		World.spawnNPC(4288, new WorldTile(4516, 5601, 0), 2, false);
		// Tamayu
		World.spawnNPC(1167, new WorldTile(4509, 5605, 0), 2, false);
		// Lowe
		World.spawnNPC(550, new WorldTile(4709, 5600, 0), 2, false);
		// Zaff
		World.spawnNPC(546, new WorldTile(4509, 5606, 0), 2, false);
		// Horvik
		World.spawnNPC(549, new WorldTile(4509, 5604, 0), 2, false);
		// Hank
		World.spawnNPC(8864, new WorldTile(4509, 5603, 0), 0, false);
		// Harry
		World.spawnNPC(576, new WorldTile(4514, 5601, 0), 0, false);
		// Peska
		World.spawnNPC(538, new WorldTile(4509, 5602, 0), 0, false);
		// Bob
		World.spawnNPC(519, new WorldTile(4509, 5601, 0), 0, false);
		// Jatix
		World.spawnNPC(587, new WorldTile(4517, 5601, 0), 0, false);
		// Rommik
		World.spawnNPC(585, new WorldTile(4510, 5601, 0), 0, false);
		// GeneralStore home, ignore
		World.spawnNPC(529, new WorldTile(4522, 5539, 0), 1, false);
		// Thessalia
		World.spawnNPC(548, new WorldTile(4506, 5591, 0), 0, false);
		World.spawnNPC(598, new WorldTile(4506, 5592, 0), 0, false);
		// Object spawning
		World.spawnObject(new WorldObject(47120, 10, -1, 2651, 2653, 0), true);
		World.spawnObject(new WorldObject(409, 10, -2, 3210, 3438, 0), true);
		World.spawnObject(new WorldObject(2079, 10, 0, 3214, 3438, 0), true);
		World.spawnObject(new WorldObject(6282, 10, -2, 2661, 2649, 0), true);
		World.spawnObject(new WorldObject(66007, 10, 0, 2679, 2660, 0), true);
		// XuanTent
		World.spawnObject(new WorldObject(11448, 10, -1, 2645, 2668, 0), true);
		// Thieving stalls
		World.spawnObject(new WorldObject(4875, 10, 1, 4521, 5595, 0), true);
		World.spawnObject(new WorldObject(4876, 10, 1, 4520, 5595, 0), true);
		World.spawnObject(new WorldObject(4877, 10, 1, 4518, 5595, 0), true);
		World.spawnObject(new WorldObject(4878, 10, 1, 4519, 5595, 0), true);
		/**************************************************************************/
		/**************************************************************************/
		/* End of new Home */
		/*Banks at marketplace */
		World.spawnObject(new WorldObject(76274, 10, 0, 4510, 5595, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4510, 5596, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4510, 5597, 0), true);

		World.spawnObject(new WorldObject(76274, 10, -3, 4509, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4508, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4507, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4506, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4505, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4504, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4503, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4502, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4501, 5597, 0), true);

		World.spawnObject(new WorldObject(76274, 10, 0, 4501, 5597, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4501, 5596, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4501, 5595, 0), true);

		/* Banks at home */
		World.spawnObject(new WorldObject(76274, 10, -3, 4525, 5524, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4524, 5524, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4523, 5524, 0), true);

		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5525, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5526, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5527, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5528, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5529, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5530, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5531, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5532, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5533, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5534, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5535, 0), true);

		World.spawnObject(new WorldObject(76274, 10, 0, 4525, 5536, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4524, 5536, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4523, 5536, 0), true);

		World.spawnObject(new WorldObject(76274, 10, -3, 4516, 5524, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4515, 5524, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4514, 5524, 0), true);

		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5525, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5526, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5527, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5528, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5529, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5530, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5531, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5532, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5533, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5534, 0), true);
		World.spawnObject(new WorldObject(76274, 10, 0, 4516, 5535, 0), true);

		World.spawnObject(new WorldObject(76274, 10, -3, 4516, 5536, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4515, 5536, 0), true);
		World.spawnObject(new WorldObject(76274, 10, -3, 4514, 5536, 0), true);
		World.spawnObject(new WorldObject(45570, 10, -3, 4517, 5524, 0), true);
		World.spawnObject(new WorldObject(45570, 10, -3, 4517, 5536, 0), true);
		World.spawnObject(new WorldObject(45570, 10, -3, 4522, 5536, 0), true);
		World.spawnObject(new WorldObject(45570, 10, -3, 4522, 5524, 0), true);

		// Tzhaar
		World.spawnNPC(2625, new WorldTile(2617, 5132, 0), -1, false);
		World.spawnNPC(2614, new WorldTile(4666, 5082, 0), -1, false);

		// Donator Zone NPCS
		World.spawnNPC(6892, new WorldTile(1605, 4508, 0), -1, false); // Petshop
		World.spawnNPC(1918, new WorldTile(1605, 4506, 0), -1, false); // Mandrith
		World.spawnNPC(14854, new WorldTile(1605, 4505, 0), -1, false); // Kaqemeex
		/* Dungeoneering - Dungeon room objects */
		World.spawnObject(new WorldObject(49766, 10, 2, 89, 721, 0), true);
		World.spawnObject(new WorldObject(49768, 10, 2, 88, 721, 0), true);
		World.spawnObject(new WorldObject(49770, 10, 2, 87, 721, 0), true);
		World.spawnObject(new WorldObject(49772, 10, 2, 86, 721, 0), true);
		World.spawnObject(new WorldObject(49774, 10, 2, 85, 721, 0), true);
		// Donator Zone Objects A.k.A Banks
		World.spawnObject(new WorldObject(36786, 10, 2, 4455, 4528, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4456, 4528, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4457, 4528, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4458, 4528, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4459, 4528, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4460, 4528, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4461, 4528, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4462, 4528, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4462, 4512, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4461, 4512, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4460, 4512, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4459, 4512, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4458, 4512, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4457, 4512, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4456, 4512, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 4455, 4512, 0), true);
		World.spawnObject(new WorldObject(36786, 10, 2, 2967, 4379, 2), true);
		// runite ores
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4505, 0), true);
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4506, 0), true);
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4507, 0), true);
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4508, 0), true);
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4509, 0), true);
		World.spawnObject(new WorldObject(13704, 10, -3, 4524, 5599, 0), true);
		// magic tree
		World.spawnObject(new WorldObject(1306, 10, 0, 1595, 4503, 0), true);
		World.spawnObject(new WorldObject(1306, 10, 0, 1595, 4510, 0), true);
		// Furnace and Anvil
		World.spawnObject(new WorldObject(11010, 10, -4, 1599, 4512, 0), true);
		World.spawnObject(new WorldObject(2783, 10, -4, 1603, 4512, 0), true);
		// Thieving stall
		// World.spawnObject(new WorldObject(34385, 10, 0, 4464, 4524, 0),
		// true);

		// World.spawnNPC(4874, new WorldTile(4509, 5588, 0), -1, true, true);

		// Fishing SPOT spawnings!
		World.spawnNPC(327, new WorldTile(2604, 3419, 0), -1, true, true);
		World.spawnNPC(6267, new WorldTile(2605, 3420, 0), -1, true, true);
		World.spawnNPC(312, new WorldTile(2605, 3421, 0), -1, true, true);
		World.spawnNPC(313, new WorldTile(2604, 3422, 0), -1, true, true);
		World.spawnNPC(952, new WorldTile(2603, 3422, 0), -1, true, true);
		World.spawnNPC(327, new WorldTile(2604, 3423, 0), -1, true, true);
		World.spawnNPC(6267, new WorldTile(2605, 3424, 0), -1, true, true);
		World.spawnNPC(312, new WorldTile(2605, 3425, 0), -1, true, true);
		World.spawnNPC(327, new WorldTile(2599, 3419, 0), -1, true, true);
		World.spawnNPC(6267, new WorldTile(2598, 3422, 0), -1, true, true);
		World.spawnNPC(8864, new WorldTile(2590, 3419, 0), -1, true, true);

		
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5604, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5605, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5606, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5607, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5608, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5609, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5610, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5611, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5612, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5613, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 4520, 5614, 0), true);

		
		
		// Missions Lobby
		/* Noticeboards - Lobby */
		World.spawnObject(new WorldObject(40760, 10, 2, 3149, 4647, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 3149, 4648, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 3149, 4649, 0), true);
		World.spawnObject(new WorldObject(40760, 10, 2, 3149, 4650, 0), true);

		World.spawnObject(new WorldObject(38453, 10, -3, 4502, 5608, 0), true);// unlit
																				// beac

		// Fishing spot
		World.spawnObject(new WorldObject(36786, 10, 2, 2587, 3422, 0), true);

		// Runecrafting skill ALTARS + NPC's + Banks
		World.spawnObject(new WorldObject(2478, 10, -2, 2600, 3155, 0), true);// 1-Air
																				// altar
		World.spawnObject(new WorldObject(2479, 10, -2, 2597, 3155, 0), true);// 2-Mind
																				// altar
		World.spawnObject(new WorldObject(2480, 10, -2, 2594, 3157, 0), true);// 3-Water
																				// altar
		World.spawnObject(new WorldObject(2481, 10, -2, 2594, 3160, 0), true);// 4-Earth
																				// altar
		World.spawnObject(new WorldObject(2482, 10, -2, 2594, 3163, 0), true);// 5-Fire
																				// altar
		World.spawnObject(new WorldObject(2483, 10, -2, 2594, 3166, 0), true);// 6-Body
																				// altar
		World.spawnObject(new WorldObject(2484, 10, -2, 2603, 3157, 0), true);// 7-Cosmic
																				// altar
		World.spawnObject(new WorldObject(2487, 10, -2, 2603, 3160, 0), true);// 8-Chaos
																				// altar
		World.spawnObject(new WorldObject(17010, 10, -2, 2603, 3163, 0), true);// 9-Astral
																				// altar
		World.spawnObject(new WorldObject(2486, 10, -2, 2603, 3166, 0), true);// 10-Nature
																				// altar
		World.spawnObject(new WorldObject(2485, 10, -2, 2600, 3168, 0), true);// 11-Law
																				// altar
		World.spawnObject(new WorldObject(2488, 10, -2, 2597, 3168, 0), true);// 12-Death
																				// altar
		World.spawnObject(new WorldObject(30624, 10, -2, 2510, 3169, 0), true);// 13-Blood
																				// altar
		World.spawnObject(new WorldObject(2489, 10, -2, 2513, 3169, 0), true);// 14-Soul
																				// altar
		World.spawnObject(new WorldObject(27663, 10, -1, 2599, 3165, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -3, 2600, 3165, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -1, 2599, 3164, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -3, 2600, 3164, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -1, 2599, 3163, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -3, 2600, 3163, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -1, 2599, 3162, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -3, 2600, 3162, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -1, 2599, 3161, 0), true);// Bank
																				// chest
		World.spawnObject(new WorldObject(27663, 10, -3, 2600, 3161, 0), true);// Bank
																				// chest
		World.spawnNPC(537, new WorldTile(2598, 3162, 0), -1, true, true);// Aubury
																			// (rc
																			// shop)

		// summoning area NPC
		World.spawnNPC(6970, new WorldTile(2207, 5345, 0), -1, true, true);// Pikkupstix
																			// (summoning
																			// shops)

	}

	/**
	 * The NPC classes.
	 */
	private static final Map<Integer, Class<?>> CUSTOM_NPCS = new HashMap<Integer, Class<?>>();

	public static void npcSpawn() {
		int size = 0;
		boolean ignore = false;
		try {
			for (String string : FileUtilities
					.readFile("data/npcs/npcspawns.txt")) {
				if (string.startsWith("//") || string.equals("")) {
					continue;
				}
				if (string.contains("/*")) {
					ignore = true;
					continue;
				}
				if (ignore) {
					if (string.contains("*/")) {
						ignore = false;
					}
					continue;
				}
				String[] spawn = string.split(" ");
				@SuppressWarnings("unused")
				int id = Integer.parseInt(spawn[0]), x = Integer
						.parseInt(spawn[1]), y = Integer.parseInt(spawn[2]), z = Integer
						.parseInt(spawn[3]), faceDir = Integer
						.parseInt(spawn[4]);
				NPC npc = null;
				Class<?> npcHandler = CUSTOM_NPCS.get(id);
				if (npcHandler == null) {
					npc = new NPC(id, new WorldTile(x, y, z), -1, true, false);
				} else {
					npc = (NPC) npcHandler.getConstructor(int.class)
							.newInstance(id);
				}
				if (npc != null) {
					WorldTile spawnLoc = new WorldTile(x, y, z);
					npc.setLocation(spawnLoc);
					World.spawnNPC(npc.getId(), spawnLoc, -1, true, false);
					size++;
				}
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		System.err.println("Loaded " + size + " custom npc spawns!");
	}

}