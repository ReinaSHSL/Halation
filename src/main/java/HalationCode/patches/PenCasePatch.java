package HalationCode.patches;

import HalationCode.relics.persona5.PenCase;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;

import java.util.ArrayList;


public class PenCasePatch {
    @SpirePatch(
            clz = AbstractCampfireOption.class,
            method = SpirePatch.CLASS
    )
    public static class PenCaseField {
        public static SpireField<Boolean> isPenCase = new SpireField<>(() -> false);
    }

    @SpirePatch(
            clz = CampfireUI.class,
            method = "initializeButtons"
    )
    public static class SetRestButton {
        public static void Postfix(CampfireUI __instance) {
            ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>) ReflectionHacks.getPrivate(__instance, CampfireUI.class, "buttons");
            for(AbstractCampfireOption o : campfireButtons) {
                if(o instanceof RestOption && AbstractDungeon.player.hasRelic(PenCase.ID)) {
                    PenCaseField.isPenCase.set(o, true);
                }
            }
        }
    }

    @SpirePatch(
            clz = AbstractCampfireOption.class,
            method = "update"
    )
    public static class SetBool {
        public static boolean shouldStop = false;
        public static void Postfix(AbstractCampfireOption __instance) {
            shouldStop = PenCaseField.isPenCase.get(__instance);
        }
    }
}
