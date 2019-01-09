package HalationCode.patches;

import HalationCode.actions.PickDeckAction;
import HalationCode.relics.ddlc.SimulatedSpire;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.megacrit.cardcrawl.ui.buttons.CancelButton;

@SpirePatch(
        clz = CancelButton.class,
        method = "update"
)
public class CancelButtonPatch {
    @SpireInsertPatch(
            rloc = 14
    )
    public static SpireReturn Insert(CancelButton __instance) {
        if (PickDeckAction.isPickingDeck) {
            ReflectionHacks.setPrivate(AbstractDungeon.gridSelectScreen, GridCardSelectScreen.class, "targetGroup", PickDeckAction.tmp);
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
