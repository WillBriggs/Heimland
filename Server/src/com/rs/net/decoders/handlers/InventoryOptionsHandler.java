package com.rs.net.decoders.handlers;
 
import java.util.List;
 
import com.rs.Settings;
import com.rs.cores.WorldThread;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar.SpecialAttack;
import com.rs.game.npc.pet.Pet;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.Equipment;
import com.rs.game.player.Inventory;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.BoxAction;
import com.rs.game.player.actions.BoxAction.HunterEquipment;
import com.rs.game.player.actions.Firemaking;
import com.rs.game.player.actions.Fletching;
import com.rs.game.player.actions.Fletching.Fletch;
import com.rs.game.player.actions.GemCutting;
import com.rs.game.player.actions.GemCutting.Gem;
import com.rs.game.player.actions.HerbCleaning;
import com.rs.game.player.actions.Herblore;
import com.rs.game.player.actions.LeatherCrafting;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.actions.Summoning.Pouches;
import com.rs.game.player.content.AncientEffigies;
import com.rs.game.player.content.ArmourSets;
import com.rs.game.player.content.ArmourSets.Sets;
import com.rs.game.player.content.Burying.Bone;
import com.rs.game.player.content.Dicing;
import com.rs.game.player.content.Foods;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.Pots;
import com.rs.game.player.content.Runecrafting;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.controlers.Barrows;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import java.text.DecimalFormat;
import com.rs.io.InputStream;
import com.rs.utils.Logger;
import com.rs.utils.Utils;
 
public class InventoryOptionsHandler {
 
    public static void handleItemOption2(final Player player, final int slotId,
            final int itemId, Item item) {
        if (Firemaking.isFiremaking(player, itemId))
            return;
        if (itemId >= 5509 && itemId <= 5514) {
            int pouch = -1;
            if (itemId == 5509)
                pouch = 0;
            if (itemId == 5510)
                pouch = 1;
            if (itemId == 5512)
                pouch = 2;
            if (itemId == 5514)
                pouch = 3;
            Runecrafting.emptyPouch(player, pouch);
            player.stopAll(false);
        } else if (itemId >= 15086 && itemId <= 15100) {
            Dicing.handleRoll(player, itemId, true); 
            return;
        } else {
            if (player.isEquipDisabled())
                return;
            long passedTime = Utils.currentTimeMillis()
                    - WorldThread.LAST_CYCLE_CTM;
            WorldTasksManager.schedule(new WorldTask() {
 
                @Override
                public void run() {
                    List<Integer> slots = player.getSwitchItemCache();
                    int[] slot = new int[slots.size()];
                    for (int i = 0; i < slot.length; i++)
                        slot[i] = slots.get(i);
                    player.getSwitchItemCache().clear();
                    ButtonHandler.sendWear(player, slot);
                    player.stopAll(false, true, false);
                }
            }, passedTime >= 600 ? 0 : passedTime > 330 ? 1 : 0);
            if (player.getSwitchItemCache().contains(slotId))
                return;
            player.getSwitchItemCache().add(slotId);
        }
    }
     
    public static void dig(final Player player) {
        player.resetWalkSteps();
        player.setNextAnimation(new Animation(830));
        player.lock();
        WorldTasksManager.schedule(new WorldTask() {
 
            @Override
            public void run() {
                player.unlock();
                if (Barrows.digIntoGrave(player))
                    return;
                if(player.getX() == 3005 && player.getY() == 3376
                        || player.getX() == 2999 && player.getY() == 3375
                        || player.getX() == 2996 && player.getY() == 3377
                        || player.getX() == 2989 && player.getY() == 3378
                        || player.getX() == 2987 && player.getY() == 3387
                        || player.getX() == 2984 && player.getY() == 3387) {
                    //mole
                    player.setNextWorldTile(new WorldTile(1752, 5137, 0));
                    player.getPackets().sendGameMessage("You seem to have dropped down into a network of mole tunnels.");
                    return;
                }
                player.getPackets().sendGameMessage("You find nothing.");
            }
             
        });
    }
 
    public static void handleItemOption1(Player player, final int slotId,
            final int itemId, Item item) {
        long time = Utils.currentTimeMillis();
        if (player.getLockDelay() >= time
                || player.getEmotesManager().getNextEmoteEnd() >= time)
            return;
        player.stopAll(false);
        if (Foods.eat(player, item, slotId))
            return;
        if (itemId >= 15086 && itemId <= 15100) {
            Dicing.handleRoll(player, itemId, false); 
            return;
        }
        if (Pots.pot(player, item, slotId))
            return;
        if (itemId >= 5509 && itemId <= 5514) {
            int pouch = -1;
            if (itemId == 5509)
                pouch = 0;
            if (itemId == 5510)
                pouch = 1;
            if (itemId == 5512)
                pouch = 2;
            if (itemId == 5514)
                pouch = 3;
            Runecrafting.fillPouch(player, pouch);
            return;
        }
        if (itemId == 22370) {
            Summoning.openDreadnipInterface(player);
        }
        if (itemId == 952) {// spade
            dig(player);
            return;
        }
        if (HerbCleaning.clean(player, item, slotId))
            return;
        Bone bone = Bone.forId(itemId);
        if (bone != null) {
            Bone.bury(player, slotId);
            return;
        }
        if (Magic.useTabTeleport(player, itemId))
            return;
        if (itemId == AncientEffigies.SATED_ANCIENT_EFFIGY
                || itemId == AncientEffigies.GORGED_ANCIENT_EFFIGY
                || itemId == AncientEffigies.NOURISHED_ANCIENT_EFFIGY
                || itemId == AncientEffigies.STARVED_ANCIENT_EFFIGY)
            player.getDialogueManager().startDialogue("AncientEffigiesD",
                    itemId);
        else if (itemId == 4155)
            player.getDialogueManager().startDialogue("EnchantedGemDialouge");
        else if (itemId >= 23653 && itemId <= 23658)
            FightKiln.useCrystal(player, itemId);
        if(itemId == 405) {
            int[] reward = { 2000000, 3000000, 4000000, 5000000, 7500000 };
            int won = reward[Utils.random(reward.length-1)];
            player.getInventory().deleteItem(405, 1);
            player.getInventory().addItem(995, won);
            player.getPackets().sendGameMessage("The casket slowly opens... You receive "+ won + " coins!");
        } else if (itemId == 24155) { // Double Spin ticket
            player.getPackets().sendGameMessage("You opened your spin ticket and recieved two spins.");
            player.setSpins(player.getSpins() + 2);
            player.getPackets().sendIComponentText(1139, 10, " "+ player.getSpins() +" ");
            player.getInventory().deleteItem(24155, 1);
        } else if (itemId == 23714) {
    			player.getInterfaceManager().sendInterface(1263);
        } else if (itemId == 24154) { // Spin ticket
            player.getPackets().sendGameMessage("You opened your spin ticket and recieved a spin.");
            player.setSpins(player.getSpins() + 1);
            player.getPackets().sendIComponentText(1139, 10, " "+ player.getSpins() +" ");
            player.getInventory().deleteItem(24154, 1);
        } else if (itemId == 23717) {
            player.getSkills().addXp(Skills.ATTACK, 2500);
            player.getInventory().deleteItem(23717, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Attack Experience.");
        } else if (itemId == 23721) {
            player.getSkills().addXp(Skills.STRENGTH, 2500);
            player.getInventory().deleteItem(23721, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Strength Experience.");
        } else if (itemId == 23725) {
            player.getSkills().addXp(Skills.DEFENCE, 2500);
            player.getInventory().deleteItem(23725, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Defence Experience.");
        } else if (itemId == 23729) {
            player.getSkills().addXp(Skills.RANGE, 2500);
            player.getInventory().deleteItem(23729, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Range Experience.");
        } else if (itemId == 23733) {
            player.getSkills().addXp(Skills.MAGIC, 2500);
            player.getInventory().deleteItem(23733, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Magic Experience.");
        } else if (itemId == 23737) {
            player.getSkills().addXp(Skills.PRAYER, 2500);
            player.getInventory().deleteItem(23737, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Prayer Experience.");
        } else if (itemId == 23741) {
            player.getSkills().addXp(Skills.RUNECRAFTING, 2500);
            player.getInventory().deleteItem(23741, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Runecrafting Experience.");
        } else if (itemId == 23745) {
            player.getSkills().addXp(Skills.CONSTRUCTION, 2500);
            player.getInventory().deleteItem(23745, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Construction Experience.");
        } else if (itemId == 23749) {
            player.getSkills().addXp(Skills.DUNGEONEERING, 2500);
            player.getInventory().deleteItem(23749, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Dungeoneering Experience.");
        } else if (itemId == 23753) {
            player.getSkills().addXp(Skills.HITPOINTS, 2500);
            player.getInventory().deleteItem(23753, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Constitution Experience.");
        } else if (itemId == 23757) {
            player.getSkills().addXp(Skills.AGILITY, 2500);
            player.getInventory().deleteItem(23757, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Agility Experience.");
        } else if (itemId == 23761) {
            player.getSkills().addXp(Skills.HERBLORE, 2500);
            player.getInventory().deleteItem(23761, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Herblore Experience.");
        } else if (itemId == 23765) {
            player.getSkills().addXp(Skills.THIEVING, 2500);
            player.getInventory().deleteItem(23765, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Thieving Experience.");
        } else if (itemId == 23769) {
            player.getSkills().addXp(Skills.CRAFTING, 2500);
            player.getInventory().deleteItem(23769, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Crafting Experience.");
        } else if (itemId == 23774) {
            player.getSkills().addXp(Skills.FLETCHING, 2500);
            player.getInventory().deleteItem(23774, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Fletching Experience.");
        } else if (itemId == 23778) {
            player.getSkills().addXp(Skills.SLAYER, 2500);
            player.getInventory().deleteItem(23778, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Slayer Experience.");
        } else if (itemId == 23782) {
            player.getSkills().addXp(Skills.HUNTER, 2500);
            player.getInventory().deleteItem(23782, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Hunter Experience.");
        } else if (itemId == 23786) {
            player.getSkills().addXp(Skills.MINING, 2500);
            player.getInventory().deleteItem(23786, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Mining Experience.");
        } else if (itemId == 23790) {
            player.getSkills().addXp(Skills.SMITHING, 2500);
            player.getInventory().deleteItem(23790, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Smithing Experience.");
        } else if (itemId == 23794) {
            player.getSkills().addXp(Skills.FISHING, 2500);
            player.getInventory().deleteItem(23794, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Fishing Experience.");
        } else if (itemId == 23798) {
            player.getSkills().addXp(Skills.COOKING, 2500);
            player.getInventory().deleteItem(23798, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Cooking Experience.");
        } else if (itemId == 23802) {
            player.getSkills().addXp(Skills.FIREMAKING, 2500);
            player.getInventory().deleteItem(23802, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Firemaking Experience.");
        } else if (itemId == 23806) {
            player.getSkills().addXp(Skills.WOODCUTTING, 2500);
            player.getInventory().deleteItem(23806, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Woodcutting Experience.");
        } else if (itemId == 23810) {
            player.getSkills().addXp(Skills.FARMING, 2500);
            player.getInventory().deleteItem(23810, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Farming Experience.");
        } else if (itemId == 23814) {
            player.getSkills().addXp(Skills.SUMMONING, 2500);
            player.getInventory().deleteItem(23814, 1);
            player.getPackets().sendGameMessage("You have earned yourself some Summoning Experience.");
        } else if (itemId == 23718) {
            player.getSkills().addXp(Skills.ATTACK, 5000);
            player.getInventory().deleteItem(23718, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Attack.");
        } else if (itemId == 23722) {
            player.getSkills().addXp(Skills.STRENGTH, 5000);
            player.getInventory().deleteItem(23722, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Strength.");
        } else if (itemId == 23726) {
            player.getSkills().addXp(Skills.DEFENCE, 5000);
            player.getInventory().deleteItem(23726, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Defence.");
        } else if (itemId == 23730) {
            player.getSkills().addXp(Skills.RANGE, 5000);
            player.getInventory().deleteItem(23730, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Range.");
        } else if (itemId == 23734) {
            player.getSkills().addXp(Skills.MAGIC, 5000);
            player.getInventory().deleteItem(23734, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Magic.");
        } else if (itemId == 23738) {
            player.getSkills().addXp(Skills.PRAYER, 5000);
            player.getInventory().deleteItem(23738, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Prayer.");
        } else if (itemId == 23742) {
            player.getSkills().addXp(Skills.RUNECRAFTING, 5000);
            player.getInventory().deleteItem(23742, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Runecrafting.");
        } else if (itemId == 23746) {
            player.getSkills().addXp(Skills.CONSTRUCTION, 5000);
            player.getInventory().deleteItem(23746, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Construction.");
        } else if (itemId == 23750) {
            player.getSkills().addXp(Skills.DUNGEONEERING, 5000);
            player.getInventory().deleteItem(23750, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Dungeoneering.");
        } else if (itemId == 23754) {
            player.getSkills().addXp(Skills.HITPOINTS, 5000);
            player.getInventory().deleteItem(23754, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Constitution.");
        } else if (itemId == 23758) {
            player.getSkills().addXp(Skills.AGILITY, 5000);
            player.getInventory().deleteItem(23758, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Agility.");
        } else if (itemId == 23762) {
            player.getSkills().addXp(Skills.HERBLORE, 5000);
            player.getInventory().deleteItem(23762, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Herblore.");
        } else if (itemId == 23766) {
            player.getSkills().addXp(Skills.THIEVING, 5000);
            player.getInventory().deleteItem(23766, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Thieving.");
        } else if (itemId == 23770) {
            player.getSkills().addXp(Skills.CRAFTING, 5000);
            player.getInventory().deleteItem(23770, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Crafting.");
        } else if (itemId == 23775) {
            player.getSkills().addXp(Skills.FLETCHING, 5000);
            player.getInventory().deleteItem(23775, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Fletching.");
        } else if (itemId == 23779) {
            player.getSkills().addXp(Skills.SLAYER, 5000);
            player.getInventory().deleteItem(23779, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Slayer.");
        } else if (itemId == 23783) {
            player.getSkills().addXp(Skills.HUNTER, 5000);
            player.getInventory().deleteItem(23783, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Hunter.");
        } else if (itemId == 23787) {
            player.getSkills().addXp(Skills.MINING, 5000);
            player.getInventory().deleteItem(23787, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Mining.");
        } else if (itemId == 23791) {
            player.getSkills().addXp(Skills.SMITHING, 5000);
            player.getInventory().deleteItem(23791, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Smithing.");
        } else if (itemId == 23795) {
            player.getSkills().addXp(Skills.FISHING, 5000);
            player.getInventory().deleteItem(23795, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Fishing.");
        } else if (itemId == 23799) {
            player.getSkills().addXp(Skills.COOKING, 5000);
            player.getInventory().deleteItem(23799, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Cooking.");
        } else if (itemId == 23803) {
            player.getSkills().addXp(Skills.FIREMAKING, 5000);
            player.getInventory().deleteItem(23803, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Firemaking.");
        } else if (itemId == 23807) {
            player.getSkills().addXp(Skills.WOODCUTTING, 5000);
            player.getInventory().deleteItem(23807, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Woodcutting.");
        } else if (itemId == 23811) {
            player.getSkills().addXp(Skills.FARMING, 5000);
            player.getInventory().deleteItem(23811, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Farming.");
        } else if (itemId == 23815) {
            player.getSkills().addXp(Skills.SUMMONING, 5000);
            player.getInventory().deleteItem(23815, 1);
            player.getPackets().sendGameMessage("You feel more experienced in Summoning.");
        } else if (itemId == 23719) {
            player.getSkills().addXp(Skills.ATTACK, 7500);
            player.getInventory().deleteItem(23719, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Attack.");
        } else if (itemId == 23723) {
            player.getSkills().addXp(Skills.STRENGTH, 7500);
            player.getInventory().deleteItem(23723, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Strength.");
        } else if (itemId == 23727) {
            player.getSkills().addXp(Skills.DEFENCE, 7500);
            player.getInventory().deleteItem(23727, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Defence.");
        } else if (itemId == 23731) {
            player.getSkills().addXp(Skills.RANGE, 7500);
            player.getInventory().deleteItem(23731, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Range.");
        } else if (itemId == 23735) {
            player.getSkills().addXp(Skills.MAGIC, 7500);
            player.getInventory().deleteItem(23735, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Magic.");
        } else if (itemId == 23739) {
            player.getSkills().addXp(Skills.PRAYER, 7500);
            player.getInventory().deleteItem(23739, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Prayer.");
        } else if (itemId == 23743) {
            player.getSkills().addXp(Skills.RUNECRAFTING, 7500);
            player.getInventory().deleteItem(23743, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Runecrafting.");
        } else if (itemId == 23747) {
            player.getSkills().addXp(Skills.CONSTRUCTION, 7500);
            player.getInventory().deleteItem(23747, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Construction.");
        } else if (itemId == 23751) {
            player.getSkills().addXp(Skills.DUNGEONEERING, 7500);
            player.getInventory().deleteItem(23751, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Dungeoneering.");
        } else if (itemId == 23755) {
            player.getSkills().addXp(Skills.HITPOINTS, 7500);
            player.getInventory().deleteItem(23755, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Constitution.");
        } else if (itemId == 23759) {
            player.getSkills().addXp(Skills.AGILITY, 7500);
            player.getInventory().deleteItem(23759, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Agility.");
        } else if (itemId == 23763) {
            player.getSkills().addXp(Skills.HERBLORE, 7500);
            player.getInventory().deleteItem(23763, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Herblore.");
        } else if (itemId == 23767) {
            player.getSkills().addXp(Skills.THIEVING, 7500);
            player.getInventory().deleteItem(23767, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Thieving.");
        } else if (itemId == 23771) {
            player.getSkills().addXp(Skills.CRAFTING, 7500);
            player.getInventory().deleteItem(23771, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Crafting.");
        } else if (itemId == 23776) {
            player.getSkills().addXp(Skills.FLETCHING, 7500);
            player.getInventory().deleteItem(23776, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Fletching.");
        } else if (itemId == 23780) {
            player.getSkills().addXp(Skills.SLAYER, 7500);
            player.getInventory().deleteItem(23780, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Slayer.");
        } else if (itemId == 23784) {
            player.getSkills().addXp(Skills.HUNTER, 7500);
            player.getInventory().deleteItem(23784, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Hunter.");
        } else if (itemId == 23788) {
            player.getSkills().addXp(Skills.MINING, 7500);
            player.getInventory().deleteItem(23788, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Mining.");
        } else if (itemId == 23792) {
            player.getSkills().addXp(Skills.SMITHING, 7500);
            player.getInventory().deleteItem(23792, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Smithing.");
        } else if (itemId == 23796) {
            player.getSkills().addXp(Skills.FISHING, 7500);
            player.getInventory().deleteItem(23796, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Fishing.");
        } else if (itemId == 23800) {
            player.getSkills().addXp(Skills.COOKING, 7500);
            player.getInventory().deleteItem(23800, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Cooking.");
        } else if (itemId == 23804) {
            player.getSkills().addXp(Skills.FIREMAKING, 7500);
            player.getInventory().deleteItem(23804, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Firemaking.");
        } else if (itemId == 23808) {
            player.getSkills().addXp(Skills.WOODCUTTING, 7500);
            player.getInventory().deleteItem(23808, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Woodcutting.");
        } else if (itemId == 23812) {
            player.getSkills().addXp(Skills.FARMING, 7500);
            player.getInventory().deleteItem(23812, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Farming.");
        } else if (itemId == 23816) {
            player.getSkills().addXp(Skills.SUMMONING, 7500);
            player.getInventory().deleteItem(23816, 1);
            player.getPackets().sendGameMessage("You feel a magical force give you great knowledge in Summoning.");
        } else if (itemId == 23720) {
            player.getSkills().addXp(Skills.ATTACK, 10000);
            player.getInventory().deleteItem(23720, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Attack.");
        } else if (itemId == 23724) {
            player.getSkills().addXp(Skills.STRENGTH, 10000);
            player.getInventory().deleteItem(23724, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Strength.");
        } else if (itemId == 23728) {
            player.getSkills().addXp(Skills.DEFENCE, 10000);
            player.getInventory().deleteItem(23728, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Defence.");
        } else if (itemId == 23732) {
            player.getSkills().addXp(Skills.RANGE, 10000);
            player.getInventory().deleteItem(23732, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Range.");
        } else if (itemId == 23736) {
            player.getSkills().addXp(Skills.MAGIC, 10000);
            player.getInventory().deleteItem(23736, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Magic.");
        } else if (itemId == 23740) {
            player.getSkills().addXp(Skills.PRAYER, 10000);
            player.getInventory().deleteItem(23740, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Prayer.");
        } else if (itemId == 23744) {
            player.getSkills().addXp(Skills.RUNECRAFTING, 10000);
            player.getInventory().deleteItem(23744, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Runecrafting.");
        } else if (itemId == 23748) {
            player.getSkills().addXp(Skills.CONSTRUCTION, 10000);
            player.getInventory().deleteItem(23748, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Construction.");
        } else if (itemId == 23752) {
            player.getSkills().addXp(Skills.DUNGEONEERING, 10000);
            player.getInventory().deleteItem(23752, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Dungeoneering.");
        } else if (itemId == 23756) {
            player.getSkills().addXp(Skills.HITPOINTS, 10000);
            player.getInventory().deleteItem(23756, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Constitution.");
        } else if (itemId == 23760) {
            player.getSkills().addXp(Skills.AGILITY, 10000);
            player.getInventory().deleteItem(23760, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Agility.");
        } else if (itemId == 23764) {
            player.getSkills().addXp(Skills.HERBLORE, 10000);
            player.getInventory().deleteItem(23764, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Herblore.");
        } else if (itemId == 23768) {
            player.getSkills().addXp(Skills.THIEVING, 10000);
            player.getInventory().deleteItem(23768, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Thieving.");
        } else if (itemId == 23772) {
            player.getSkills().addXp(Skills.CRAFTING, 10000);
            player.getInventory().deleteItem(23772, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Crafting.");
        } else if (itemId == 23777) {
            player.getSkills().addXp(Skills.FLETCHING, 10000);
            player.getInventory().deleteItem(23777, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Fletching.");
        } else if (itemId == 23781) {
            player.getSkills().addXp(Skills.SLAYER, 10000);
            player.getInventory().deleteItem(23781, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Slayer.");
        } else if (itemId == 23785) {
            player.getSkills().addXp(Skills.HUNTER, 10000);
            player.getInventory().deleteItem(23785, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Hunter.");
        } else if (itemId == 23789) {
            player.getSkills().addXp(Skills.MINING, 10000);
            player.getInventory().deleteItem(23789, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Mining.");
        } else if (itemId == 23793) {
            player.getSkills().addXp(Skills.SMITHING, 10000);
            player.getInventory().deleteItem(23793, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Smithing.");
        } else if (itemId == 23797) {
            player.getSkills().addXp(Skills.FISHING, 10000);
            player.getInventory().deleteItem(23797, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Fishing.");
        } else if (itemId == 23801) {
            player.getSkills().addXp(Skills.COOKING, 10000);
            player.getInventory().deleteItem(23801, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Cooking.");
        } else if (itemId == 23805) {
            player.getSkills().addXp(Skills.FIREMAKING, 10000);
            player.getInventory().deleteItem(23805, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Firemaking.");
        } else if (itemId == 23809) {
            player.getSkills().addXp(Skills.WOODCUTTING, 10000);
            player.getInventory().deleteItem(23809, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Woodcutting.");
        } else if (itemId == 23813) {
            player.getSkills().addXp(Skills.FARMING, 10000);
            player.getInventory().deleteItem(23813, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Farming.");
        } else if (itemId == 23817) {
            player.getSkills().addXp(Skills.SUMMONING, 10000);
            player.getInventory().deleteItem(23817, 1);
            player.getPackets().sendGameMessage("You have been granted great experience in Summoning.");
        } else if (itemId == 24300) {
            player.getSkills().addXp(Skills.ATTACK, 2000000);
            player.getSkills().addXp(Skills.STRENGTH, 2000000);
            player.getSkills().addXp(Skills.DEFENCE, 2000000);
            player.getSkills().addXp(Skills.RANGE, 2000000);
            player.getSkills().addXp(Skills.MAGIC, 2000000);
            player.getSkills().addXp(Skills.PRAYER, 2000000);
            player.getSkills().addXp(Skills.RUNECRAFTING, 2000000);
            player.getSkills().addXp(Skills.CONSTRUCTION, 2000000);
            player.getSkills().addXp(Skills.DUNGEONEERING, 2000000);
            player.getSkills().addXp(Skills.HITPOINTS, 2000000);
            player.getSkills().addXp(Skills.AGILITY, 2000000);
            player.getSkills().addXp(Skills.HERBLORE, 2000000);
            player.getSkills().addXp(Skills.THIEVING, 2000000);
            player.getSkills().addXp(Skills.CRAFTING, 2000000);
            player.getSkills().addXp(Skills.FLETCHING, 2000000);
            player.getSkills().addXp(Skills.SLAYER, 2000000);
            player.getSkills().addXp(Skills.HUNTER, 2000000);
            player.getSkills().addXp(Skills.MINING, 2000000);
            player.getSkills().addXp(Skills.SMITHING, 2000000);
            player.getSkills().addXp(Skills.FISHING, 2000000);
            player.getSkills().addXp(Skills.COOKING, 2000000);
            player.getSkills().addXp(Skills.FIREMAKING, 2000000);
            player.getSkills().addXp(Skills.WOODCUTTING, 2000000);
            player.getSkills().addXp(Skills.FARMING, 2000000);
            player.getSkills().addXp(Skills.SUMMONING, 2000000);
            player.getPackets().sendGameMessage("The gods have heard your plea, and grant you 200,000,000 Experience!");
            player.getInventory().deleteItem(24300, 1);
        } else if (itemId == 1856) {// Information Book
            player.getInterfaceManager().sendInterface(275);
            player.getPackets().sendIComponentText(275, 1,
                    "Welcome to " + Settings.SERVER_NAME + ".");
            player.getPackets().sendIComponentText(275, 11, "");
            player.getPackets().sendIComponentText(275, 10,
                    "Welcome to " + Settings.SERVER_NAME + " RSPS.");
            player.getPackets().sendIComponentText(275, 12,
                    "Citellum RSPS is a wonderful economy server.");
            player.getPackets().sendIComponentText(275, 13,
                    "Everywhere You Turn, There Are Many");
            player.getPackets().sendIComponentText(275, 14,
                    "opprotunities Such As Skilling, Bossing, ");
            player.getPackets().sendIComponentText(275, 15, 
            		"Player Killing, and Minigames!");
            player.getPackets().sendIComponentText(275, 16,
                    "We are Home of Amazing Content!");
            player.getPackets().sendIComponentText(275, 17,
                    "");
            player.getPackets().sendIComponentText(275, 18,
                    "If You Need Any Help, Do ::ticket. (Don't abuse it)");
            player.getPackets().sendIComponentText(275, 19, "");
            player.getPackets().sendIComponentText(275, 20,
                    "Also, You Can Compare Stats With Friends.");
            player.getPackets().sendIComponentText(275, 21, "");
            player.getPackets().sendIComponentText(275, 22,
                    "Oh and Yeah, Don't Forget To ::Vote and Follow Rules.");
            player.getPackets().sendIComponentText(275, 23, "");
            player.getPackets().sendIComponentText(275, 24,
                    "Visit The Website: " + Settings.WEBSITE_LINK);
            player.getPackets().sendIComponentText(275, 25, "");
            player.getPackets().sendIComponentText(275, 26,
                    "Enjoy your time on " + Settings.SERVER_NAME + ".");
            player.getPackets().sendIComponentText(275, 27,
                    "<img=1> Citellum Staff Team");
            player.getPackets().sendIComponentText(275, 28, "");
            player.getPackets().sendIComponentText(275, 29, "");
            player.getPackets().sendIComponentText(275, 30, "");
            player.getPackets().sendIComponentText(275, 31, "");
            player.getPackets().sendIComponentText(275, 32, "");
            player.getPackets().sendIComponentText(275, 33, "");
            player.getPackets().sendIComponentText(275, 34, "");
            player.getPackets().sendIComponentText(275, 35, "");
            player.getPackets().sendIComponentText(275, 36, "");
            player.getPackets().sendIComponentText(275, 37, "");
            player.getPackets().sendIComponentText(275, 38, "");
            player.getPackets().sendIComponentText(275, 39, "");
            player.getPackets().sendIComponentText(275, 40, "");
            for (int i = 31; i < 300; i++)
                player.getPackets().sendIComponentText(275, i, "");
        } else if (itemId == HunterEquipment.BOX.getId()) // almost done
            player.getActionManager().setAction(new BoxAction(HunterEquipment.BOX));
        else if (itemId == HunterEquipment.BRID_SNARE.getId())
            player.getActionManager().setAction(
                    new BoxAction(HunterEquipment.BRID_SNARE));
        else if (item.getDefinitions().getName().startsWith("Burnt")) 
            player.getDialogueManager().startDialogue("SimplePlayerMessage", "Ugh, this is inedible.");
         
		if (player.getRights() == 2)
			player.sendMessage("Item Select:" + itemId + ", Slot Id:"
                    + slotId);
    }
 
    /*
     * returns the other
     */
    public static Item contains(int id1, Item item1, Item item2) {
        if (item1.getId() == id1)
            return item2;
        if (item2.getId() == id1)
            return item1;
        return null;
    }
 
    public static boolean contains(int id1, int id2, Item... items) {
        boolean containsId1 = false;
        boolean containsId2 = false;
        for (Item item : items) {
            if (item.getId() == id1)
                containsId1 = true;
            else if (item.getId() == id2)
                containsId2 = true;
        }
        return containsId1 && containsId2;
    }
 
    public static void handleItemOnItem(final Player player, InputStream stream) {
        int itemUsedWithId = stream.readShort();
        int toSlot = stream.readShortLE128();
        int interfaceId = stream.readInt() >> 16;
        int interfaceId2 = stream.readInt() >> 16;
        int fromSlot = stream.readShort();
        int itemUsedId = stream.readShortLE128();
        if ((interfaceId2 == 747 || interfaceId2 == 662)
                && interfaceId == Inventory.INVENTORY_INTERFACE) {
            if (player.getFamiliar() != null) {
                player.getFamiliar().setSpecial(true);
                if (player.getFamiliar().getSpecialAttack() == SpecialAttack.ITEM) {
                    if (player.getFamiliar().hasSpecialOn())
                        player.getFamiliar().submitSpecial(toSlot);
                }
            }
            return;
        }
        if (interfaceId == Inventory.INVENTORY_INTERFACE
                && interfaceId == interfaceId2
                && !player.getInterfaceManager().containsInventoryInter()) {
            if (toSlot >= 28 || fromSlot >= 28)
                return;
            Item usedWith = player.getInventory().getItem(toSlot);
            Item itemUsed = player.getInventory().getItem(fromSlot);
            if (itemUsed == null || usedWith == null
                    || itemUsed.getId() != itemUsedId
                    || usedWith.getId() != itemUsedWithId)
                return;
            player.stopAll();
            if (!player.getControlerManager().canUseItemOnItem(itemUsed,
                    usedWith))
                return;
            Fletch fletch = Fletching.isFletching(usedWith, itemUsed);
            if (fletch != null) {
                player.getDialogueManager().startDialogue("FletchingD", fletch);
                return;
            }
            if (itemUsed.getId() == 11710 || usedWith.getId() == 11712 || usedWith.getId() == 11714) {
                    if (player.getInventory().containsItem(11710, 1)
                                    && player.getInventory().containsItem(11712, 1)
                                    && player.getInventory().containsItem(11714, 1)) {
                            player.getInventory().deleteItem(11710, 1);
                            player.getInventory().deleteItem(11712, 1);
                            player.getInventory().deleteItem(11714, 1);
                            player.getInventory().addItem(11690, 1);
                            player.getPackets().sendGameMessage("You attached the shards and made a Godsword Blade.");
                    }
                }
                if (itemUsed.getId() == 11690 || usedWith.getId() == 11702) {
                    if (player.getInventory().containsItem(11690, 1)
                                    && player.getInventory().containsItem(11702, 1)) {
                            player.getInventory().deleteItem(11690, 1);
                            player.getInventory().deleteItem(11702, 1);
                            player.getInventory().addItem(11694, 1);
                            player.getPackets().sendGameMessage("You attach the hilt to the blade and made a Armadyl Godsword.");
                    }
                }
                if (itemUsed.getId() == 11690 || usedWith.getId() == 11704) {
                    if (player.getInventory().containsItem(11690, 1)
                                    && player.getInventory().containsItem(11704, 1)) {
                            player.getInventory().deleteItem(11690, 1);
                            player.getInventory().deleteItem(11704, 1);
                            player.getInventory().addItem(11696, 1);
                            player.getPackets().sendGameMessage("You attach the hilt to the blade and made a Bandos Godsword.");
                    }
                }
                if (itemUsed.getId() == 11690 || usedWith.getId() == 11706) {
                    if (player.getInventory().containsItem(11690, 1)
                                    && player.getInventory().containsItem(11706, 1)) {
                            player.getInventory().deleteItem(11690, 1);
                            player.getInventory().deleteItem(11706, 1);
                            player.getInventory().addItem(11698, 1);
                            player.getPackets().sendGameMessage("You attach the hilt to the blade and made a Saradomin Godsword.");
                    }
                 }
                if (itemUsed.getId() == 11690 || usedWith.getId() == 11708) {
                    if (player.getInventory().containsItem(11690, 1)
                                    && player.getInventory().containsItem(11708, 1)) {
                            player.getInventory().deleteItem(11690, 1);
                            player.getInventory().deleteItem(11708, 1);
                            player.getInventory().addItem(11700, 1);
                            player.getPackets().sendGameMessage("You attach the hilt to the blade and made a Zamorak Godsword.");
                    }
                }
            int herblore = Herblore.isHerbloreSkill(itemUsed, usedWith);
            if (herblore > -1) {
                player.getDialogueManager().startDialogue("HerbloreD",
                        herblore, itemUsed, usedWith);
                return;
            }
            if (itemUsed.getId() == LeatherCrafting.NEEDLE.getId()
                    || usedWith.getId() == LeatherCrafting.NEEDLE.getId()) {
                if (LeatherCrafting
                        .handleItemOnItem(player, itemUsed, usedWith)) {
                    return;
                }
            }
            Sets set = ArmourSets.getArmourSet(itemUsedId, itemUsedWithId);
            if (set != null) {
                ArmourSets.exchangeSets(player, set);
                return;
            }
            if (Firemaking.isFiremaking(player, itemUsed, usedWith))
                return;
            else if (contains(1755, Gem.OPAL.getUncut(), itemUsed, usedWith))
                GemCutting.cut(player, Gem.OPAL);
            else if (contains(1755, Gem.JADE.getUncut(), itemUsed, usedWith))
                GemCutting.cut(player, Gem.JADE);
            else if (contains(1755, Gem.RED_TOPAZ.getUncut(), itemUsed,
                    usedWith))
                GemCutting.cut(player, Gem.RED_TOPAZ);
            else if (contains(1755, Gem.SAPPHIRE.getUncut(), itemUsed, usedWith))
                GemCutting.cut(player, Gem.SAPPHIRE);
            else if (contains(1755, Gem.EMERALD.getUncut(), itemUsed, usedWith))
                GemCutting.cut(player, Gem.EMERALD);
            else if (contains(1755, Gem.RUBY.getUncut(), itemUsed, usedWith))
                GemCutting.cut(player, Gem.RUBY);
            else if (contains(1755, Gem.DIAMOND.getUncut(), itemUsed, usedWith))
                GemCutting.cut(player, Gem.DIAMOND);
            else if (contains(1755, Gem.DRAGONSTONE.getUncut(), itemUsed,
                    usedWith))
                GemCutting.cut(player, Gem.DRAGONSTONE);
                else if (itemUsed.getId() == 21369 && usedWith.getId() == 4151){
                player.getInventory().deleteItem(21369, 1);
                player.getInventory().deleteItem(4151, 1);
                player.getInventory().addItem(21371, 1);
                player.getPackets().sendGameMessage("Good job, you have succesfully combined a whip and vine into a vine whip.");
                }
                else if (itemUsed.getId() == 4151 && usedWith.getId() == 21369){
                player.getInventory().deleteItem(21369, 1);
                player.getInventory().deleteItem(4151, 1);
                player.getInventory().addItem(21371, 1);
                player.getPackets().sendGameMessage("Good job, you have succesfully combined a whip and vine into a vine whip.");
                }
                else if (itemUsed.getId() == 13734 && usedWith.getId() == 13754){
                player.getInventory().deleteItem(13734, 1);
                player.getInventory().deleteItem(13754, 1);
                player.getInventory().addItem(13736, 1);
                player.getPackets().sendGameMessage("You have poured the holy elixir on a spirit shield making it unleash Blessed powers.");
                }
                else if (itemUsed.getId() == 13754 && usedWith.getId() == 13734){
                player.getInventory().deleteItem(13734, 1);
                player.getInventory().deleteItem(13754, 1);
                player.getInventory().addItem(13736, 1);
                player.getPackets().sendGameMessage("You have poured the holy elixir on a spirit shield making it unleash Blessed powers.");
                }
                else if (itemUsed.getId() == 13736 && usedWith.getId() == 13748){
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().deleteItem(13748, 1);
                player.getInventory().addItem(13740, 1);
                player.getPackets().sendGameMessage("You force the sigil upon the blessed spirit shield making it unleash Divine Powers.");
                }
                else if (itemUsed.getId() == 13736 && usedWith.getId() == 13750){
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().deleteItem(13750, 1);
                player.getInventory().addItem(13742, 1);
                player.getPackets().sendGameMessage("You force the sigil upon the blessed spirit shield making it unleash Elysian Powers.");
                }
                else if (itemUsed.getId() == 13736 && usedWith.getId() == 13746){
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().deleteItem(13746, 1);
                player.getInventory().addItem(13738, 1);
                player.getPackets().sendGameMessage("You force the sigil upon the blessed spirit shield making it unleash Arcane Powers.");
                }
                else if (itemUsed.getId() == 13746 && usedWith.getId() == 13736){
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().deleteItem(13746, 1);
                player.getInventory().addItem(13738, 1);
                player.getPackets().sendGameMessage("You force the sigil upon the blessed spirit shield making it unleash Arcane Powers.");
                }
                else if (itemUsed.getId() == 13736 && usedWith.getId() == 13752){
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().deleteItem(13752, 1);
                player.getInventory().addItem(13744, 1);
                player.getPackets().sendGameMessage("You force the sigil upon the blessed spirit shield making it unleash Spectral Powers.");
                }
                else if (itemUsed.getId() == 13752 && usedWith.getId() == 13736){
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().deleteItem(13752, 1);
                player.getInventory().addItem(13744, 1);
                player.getPackets().sendGameMessage("You force the sigil upon the blessed spirit shield making it unleash Spectral Powers.");
                }
                else if (itemUsed.getId() == 1755 && usedWith.getId() == 11864){
                player.getInventory().deleteItem(11864, 1);
                player.getInventory().addItem(1135, 1);
                player.getInventory().addItem(1099, 1);
                player.getInventory().addItem(1065, 1);
                player.getPackets().sendGameMessage("You used the chisel on the Dragon Hide Set.");
                }
                else if (itemUsed.getId() == 1755 && usedWith.getId() == 11868){
                player.getInventory().deleteItem(11868, 1);
                player.getInventory().addItem(2501, 1);
                player.getInventory().addItem(2495, 1);
                player.getInventory().addItem(2489, 1);
                player.getPackets().sendGameMessage("You used the chisel on the Dragon Hide Set.");
                }
                else if (itemUsed.getId() == 1755 && usedWith.getId() == 11870){
                player.getInventory().deleteItem(11870, 1);
                player.getInventory().addItem(2503, 1);
                player.getInventory().addItem(2497, 1);
                player.getInventory().addItem(2491, 1);
                player.getPackets().sendGameMessage("You used the chisel on the Dragon Hide Set.");
                }
            else if (contains(1755, Gem.ONYX.getUncut(), itemUsed, usedWith))
                GemCutting.cut(player, Gem.ONYX);
            else
                player.getPackets().sendGameMessage(
                        "Nothing interesting happens.");
            if (Settings.DEBUG)
                Logger.log("ItemHandler", "Used:" + itemUsed.getId()
                        + ", With:" + usedWith.getId());
        }
    }
 
    public static void handleItemOption3(Player player, int slotId, int itemId,
            Item item) {
        long time = Utils.currentTimeMillis();
        if (player.getLockDelay() >= time
                || player.getEmotesManager().getNextEmoteEnd() >= time)
            return;
        player.stopAll(false);
        if (itemId == 20767 || itemId == 20769 || itemId == 20771)
            SkillCapeCustomizer.startCustomizing(player, itemId);
        else if(itemId >= 15084 && itemId <= 15100)
            player.getDialogueManager().startDialogue("DiceBag", itemId);
        else if(itemId == 24437 || itemId == 24439 || itemId == 24440 || itemId == 24441) 
            player.getDialogueManager().startDialogue("FlamingSkull", item, slotId);
        else if (Equipment.getItemSlot(itemId) == Equipment.SLOT_AURA)
            player.getAuraManager().sendTimeRemaining(itemId);
    }
 
    public static void handleItemOption4(Player player, int slotId, int itemId,
            Item item) {
        System.out.println("Option 4");
    }
 
    public static void handleItemOption5(Player player, int slotId, int itemId,
            Item item) {
        System.out.println("Option 5");
    }
     
    @SuppressWarnings("unused")
	private static String getFormattedNumber(int amount) {
        return new DecimalFormat("#,###,##0").format(amount).toString();
    }
 
    public static void handleItemOption6(Player player, int slotId, int itemId,
            Item item) {
        long time = Utils.currentTimeMillis();
        if (player.getLockDelay() >= time
                || player.getEmotesManager().getNextEmoteEnd() >= time)
            return;
        player.stopAll(false);
        Pouches pouches = Pouches.forId(itemId);
        if (pouches != null)
            Summoning.spawnFamiliar(player, pouches);
        else if (itemId == 1438)
            Runecrafting.locate(player, 3127, 3405);
        else if (itemId == 1440)
            Runecrafting.locate(player, 3306, 3474);
        else if (itemId == 1442)
            Runecrafting.locate(player, 3313, 3255);
        else if (itemId == 1444)
            Runecrafting.locate(player, 3185, 3165);
        else if (itemId == 1446)
            Runecrafting.locate(player, 3053, 3445);
        else if (itemId == 1448)
            Runecrafting.locate(player, 2982, 3514);
        else if (itemId <= 1712 && itemId >= 1706 || itemId >= 10354
                && itemId <= 10362)
            player.getDialogueManager().startDialogue("Transportation",
                    "Edgeville", new WorldTile(3087, 3496, 0), "Karamja",
                    new WorldTile(2918, 3176, 0), "Draynor Village",
                    new WorldTile(3105, 3251, 0), "Al Kharid",
                    new WorldTile(3293, 3163, 0), itemId);
		else if (itemId == 995) {
			int amount = player.getInventory().getItems().getNumberOf(995);
			if (player.money + amount > 0) {
				player.getInventory().deleteItem(995, amount);
				player.getPackets().sendRunScript(5561, 1, amount);
				player.money += amount;
				player.refreshMoneyPouch();
			} else {
				player.getPackets().sendGameMessage("You can't have more then 2147483647 coins in your pouch.");
		}
		
	}
        else if (itemId == 1704 || itemId == 10352)
            player.getPackets()
                    .sendGameMessage(
                            "The amulet has ran out of charges. You need to recharge it if you wish it use it once more.");
        else if (itemId >= 3853 && itemId <= 3867)
            player.getDialogueManager().startDialogue("Transportation",
                    "Burthrope Games Room", new WorldTile(2880, 3559, 0),
                    "Barbarian Outpost", new WorldTile(2519, 3571, 0),
                    "Gamers' Grotto", new WorldTile(2970, 9679, 0),
                    "Corporeal Beast", new WorldTile(2886, 4377, 0), itemId);
    }
 
    public static void handleItemOption7(Player player, int slotId, int itemId,
            Item item) {
        long time = Utils.currentTimeMillis();
        if (player.getLockDelay() >= time
                || player.getEmotesManager().getNextEmoteEnd() >= time)
            return;
        if (!player.getControlerManager().canDropItem(item))
            return;
        player.stopAll(false);
        if (item.getDefinitions().isOverSized()) {
            player.getPackets().sendGameMessage("The item appears to be oversized.");
            player.getInventory().deleteItem(item);
            return;
        }
		if (item.getDefinitions().isDestroyItem()) {
			player.getDialogueManager().startDialogue("DestroyItemOption",
					slotId, item);
			return;
		}
		if (player.getPetManager().spawnPet(itemId, true)) {
			return;
		}
		player.getInventory().deleteItem(slotId, item);
		//if (player.getCharges().degradeCompletly(item))
			//return;
		World.addGroundItem(item, new WorldTile(player), player, false, 180,
				true);
		player.getPackets().sendSound(2739, 0, 1);
	}
     
	public static void handleItemOption8(Player player, int slotId, int itemId,
			Item item) {
		player.getInventory().sendExamine(slotId);
	}

	public static void handleItemOnNPC(final Player player, final NPC npc, final Item item) {
		if (item == null) {
			return;
		}
		player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
			@Override
			public void run() {
				if (!player.getInventory().containsItem(item.getId(), item.getAmount())) {
					return;
				}
				if (npc instanceof Pet) {
					player.faceEntity(npc);
					player.getPetManager().eat(item.getId(), (Pet) npc);
					return;
				}
			}
		}, npc.getSize()));
	}
}