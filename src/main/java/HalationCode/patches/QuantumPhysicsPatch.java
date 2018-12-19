package HalationCode.patches;

import HalationCode.relics.aobuta.QuantumPhysicsTextbook;
import HalationCode.rooms.HikoboshiSamaRoom;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;

import java.util.ArrayList;


public class QuantumPhysicsPatch {

    public static boolean isDirectlyConnectedTo(MapRoomNode start, MapRoomNode end) {
        for (MapEdge edge : start.getEdges()) {
            if (end.x == edge.dstX && end.y == edge.dstY) {
                return true;
            }
        }
        return false;
    }

    private static MapRoomNode getNode(int x, int y) {
        try {
            return CardCrawlGame.dungeon.getMap().get(y).get(x);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

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
