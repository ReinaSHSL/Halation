package HalationCode.patches;

import HalationCode.relics.monogatari.IncompleteEncyclopedia;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.TopPanel;


public class IncompleteEncyclopediaPatch {

    @SpirePatch(clz = AbstractCreature.class,
            method = "renderHealth"
    )
    public static class StopBarRender {
        public static SpireReturn Prefix(AbstractCreature __instance, SpriteBatch sb) {
            if (AbstractDungeon.player.hasRelic(IncompleteEncyclopedia.ID)) {
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(
            clz = TopPanel.class,
            method = "renderHP"
    )
    public static class StopTopPanelRender {
        public static SpireReturn Prefix(TopPanel __instance, SpriteBatch sb) {
            if (AbstractDungeon.player.hasRelic(IncompleteEncyclopedia.ID)) {
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
    }
}
