package SakuraCode.patches;

import SakuraCode.relics.madeinabyss.EternalFortune;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpirePatch(clz = AbstractRoom.class, method="spawnRelicAndObtain")
public class EternalFlowerPatch {
    public static SpireReturn<Object> Prefix(AbstractRoom __instance, float x, float y, AbstractRelic relic) {
        if (relic.relicId.equals(EternalFortune.ID) && AbstractDungeon.player.hasRelic(EternalFortune.ID)) {
            System.out.println("???");
            EternalFortune e = (EternalFortune) AbstractDungeon.player.getRelic(EternalFortune.ID);
            e.increment();
            e.flash();
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
