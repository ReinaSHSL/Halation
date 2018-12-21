//package HalationCode.patches;
//
//import basemod.ReflectionHacks;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.relics.AbstractRelic;
//
//import java.util.ArrayList;
//
//import static com.megacrit.cardcrawl.relics.AbstractRelic.PAD_X;
//
//public class RenderClickableRelicsOnSecondRowPatch {
//    public static ArrayList<AbstractRelic> clickableRelics = new ArrayList<>();
//    public static ArrayList<AbstractRelic> normalRelics = new ArrayList<>();
//    private static boolean hasClickableRelic = false;
//
//    @SpirePatch(
//            clz = AbstractRelic.class,
//            method = "instantObtain",
//            paramtypez = {}
//    )
//    public static class AddRelicToListInstantObtain {
//        public static void Postfix(AbstractRelic __instance) {
//            if (__instance instanceof ClickableRelic) {
//                clickableRelics.add(__instance);
//                hasClickableRelic = true;
//            } else {
//                normalRelics.add(__instance);
//            }
//        }
//    }
//    @SpirePatch(
//            clz = AbstractRelic.class,
//            method = "instantObtain",
//            paramtypez = {
//                    AbstractPlayer.class,
//                    int.class,
//                    boolean.class
//            }
//    )
//    public static class AddRelicToListInstantObtainParams {
//        public static void Postfix(AbstractRelic __instance, AbstractPlayer p, int i, boolean useless) {
//            if (__instance instanceof ClickableRelic) {
//                clickableRelics.add(__instance);
//                hasClickableRelic = true;
//            } else {
//                normalRelics.add(__instance);
//            }
//        }
//    }
//    @SpirePatch(
//            clz = AbstractRelic.class,
//            method = "obtain"
//    )
//    public static class AddRelicToListObtain {
//        public static void Postfix(AbstractRelic __instance) {
//            if (__instance instanceof ClickableRelic) {
//                clickableRelics.add(__instance);
//                hasClickableRelic = true;
//            } else {
//                normalRelics.add(__instance);
//            }
//        }
//    }
//
//    @SpirePatch(
//            clz = AbstractRelic.class,
//            method = "renderInTopPanel"
//    )
//    public static class SecondRowRender {
//        public static void Prefix(AbstractRelic __instance, SpriteBatch sb) {
//            if (!Settings.hideRelics && hasClickableRelic) {
//                float startX = (float)ReflectionHacks.getPrivateStatic(AbstractRelic.class, "START_X");
//                if (__instance instanceof ClickableRelic) {
//                    int i = clickableRelics.indexOf(__instance);
//                    __instance.currentX = startX + i * PAD_X;
//                    __instance.currentY -= 64.0f;
//                } else {
//                    int i = normalRelics.indexOf(__instance);
//                    __instance.currentX = startX + i * PAD_X;
//                }
//            }
//        }
//    }
//}
