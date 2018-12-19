package HalationCode.patches;

import HalationCode.relics.aobuta.QuantumPhysicsTextbook;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;

import java.util.ArrayList;


public class QuantumPhysicsPatch {

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "setCurrMapNode"
    )
    public static class findQuantumCandidates {
        private static ArrayList<MapRoomNode> quantumCandidates;

        public static void Prefix(MapRoomNode currNode) {
            if (AbstractDungeon.player.hasRelic(QuantumPhysicsTextbook.ID)) {
                quantumCandidates = new ArrayList<>();
                ArrayList<MapRoomNode> visibleMapNodes = (ArrayList<MapRoomNode>) ReflectionHacks.getPrivate(AbstractDungeon.dungeonMapScreen, DungeonMapScreen.class, "visibleMapNodes");
                if (currNode != null) {
                    for (MapRoomNode n : visibleMapNodes) {
                        if (n.y == currNode.y && AbstractDungeon.getCurrMapNode() != n) {
                            quantumCandidates.add(n);
                        }
                    }
                    int rng = -1;
                    if (quantumCandidates.size() - 1 > 0) {
                        rng = AbstractDungeon.miscRng.random(quantumCandidates.size() - 1);
                    }
                    if (rng > -1) {
                        QuantumMapNodeField.quantumPhysicsNode.set(CardCrawlGame.dungeon, quantumCandidates.get(rng));
                        QuantumMapNodeField.quantumPhysicsNode.get(CardCrawlGame.dungeon).taken = true;
                    }
                }
            }
        }
    }

    @SpirePatch(
            clz = MapRoomNode.class,
            method = "isConnectedTo"
    )
    public static class IsConnectedTo {
        public static boolean Postfix(boolean __result, MapRoomNode __instance, MapRoomNode node) {
            MapRoomNode quantumNode = QuantumMapNodeField.quantumPhysicsNode.get(CardCrawlGame.dungeon);
            if (quantumNode != null) {
                for (MapEdge edge : quantumNode.getEdges()) {
                   if (node.x == edge.dstX && node.y == edge.dstY) {
                       return true;
                   }
                }
            }
            return __result;
        }
    }
}
