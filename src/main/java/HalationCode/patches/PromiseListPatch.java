package HalationCode.patches;

import HalationCode.relics.persona5.PromiseList;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch(
        cls="infinitespire.quests.QuestLog",
        method="receivePostUpdate",
        optional=true
)
public class PromiseListPatch {
    public static ExprEditor Instrument()
    {
        return new ExprEditor() {

            @Override
            public void edit(MethodCall m) throws CannotCompileException
            {
                if (m.getMethodName().equals("giveReward")) {
                    m.replace("{" +
                            "$_ = $proceed($$);" +
                            "HalationCode.patches.PromiseListPatch.Nested.Do();" +
                            "}");
                }
            }
        };
    }

    public static class Nested
    {
        public static void Do()
        {
            AbstractRelic relic = AbstractDungeon.player.getRelic(PromiseList.ID);
            if (relic != null) {
                relic.onTrigger();
            }
        }
    }
}
