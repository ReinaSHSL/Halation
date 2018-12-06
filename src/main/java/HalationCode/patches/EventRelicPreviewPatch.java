package HalationCode.patches;

import HalationCode.events.buttons.RelicDialogOptionButton;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;

@SpirePatch(clz = GenericEventDialog.class, method = "render")
public class EventRelicPreviewPatch {
    @SpireInsertPatch(
            rloc = 70
    )
    private static void Insert(GenericEventDialog __instance, SpriteBatch sb) {
        for (LargeDialogOptionButton b : __instance.optionList) {
            if (b instanceof RelicDialogOptionButton) {
                RelicDialogOptionButton rb = (RelicDialogOptionButton) b;
                rb.renderRelicPreview(sb);
            }
        }
    }
}
