//package HalationCode.patches;
//
//import HalationCode.relics.persona5.PenCase;
//import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.vfx.campfire.CampfireLiftEffect;
//
//@SpirePatch(
//        clz = CampfireLiftEffect.class,
//        method = "update"
//)
//public class FixGiryaPatch {
//    @SpireInsertPatch(
//            rloc = 20
//    )
//    public static SpireReturn FuckYouGirya(CampfireLiftEffect __instance) {
//        if (AbstractDungeon.player.hasRelic(PenCase.ID) && PenCase.secondOption) {
//            return SpireReturn.Return(null);
//        } else {
//            return SpireReturn.Continue();
//        }
//    }
//}
