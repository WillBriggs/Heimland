package com.rs.utils;
 
import java.util.Iterator;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
 
public class Text {
 
     
            public static void init() {
                WorldTasksManager.schedule(new WorldTask() {
                    @Override
                    public void run() {
                   for(Iterator<?> iterator = World.getPlayers().iterator(); iterator.hasNext();)
                         {
                             Player players = (Player)iterator.next();
                             players.getPackets().sendIComponentText(550, 18, "Players Online: <col=ff0000>" + World.getPlayers().size());
                         }
                    }
                }, 0, 30);
                   
                 }
}