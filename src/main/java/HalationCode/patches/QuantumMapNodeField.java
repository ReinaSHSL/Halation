package HalationCode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = SpirePatch.CLASS
)
public class QuantumMapNodeField {
    public static SpireField<MapRoomNode> quantumPhysicsNode = new SpireField<>(() -> null);
}
