package HalationCode.patches;

import HalationCode.tools.TextureLoader;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;
import com.megacrit.cardcrawl.ui.buttons.SkipCardButton;


public class RenderSmartPhonePatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderCard"
    )
    public static class RenderOverCard {
        public static void Postfix(AbstractCard __instance, SpriteBatch sb, boolean useless1, boolean useless2) {
            if (SmartPhonePatch.smartCard != null && __instance.cardID.equals(SmartPhonePatch.smartCard.cardID)) {
                sb.draw(TextureLoader.getTexture("HalationImages/relics/SmartPhone.png"),
                        __instance.current_x + 180.0f * __instance.drawScale / 3.0f * Settings.scale,
                        __instance.current_y + 380.0f * __instance.drawScale / 3.0f * Settings.scale,
                        32f, 32f, 128f, 128f,
                        __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale,
                        0.0f, 0, 0,
                        128, 128,
                        false, false);
            }
        }
    }

    @SpirePatch(
            clz = SkipCardButton.class,
            method = "renderButton"
    )
    public static class RenderOverSkip {
        public static void Postfix(SkipCardButton __instance, SpriteBatch sb) {
            if (SmartPhonePatch.smartSkip) {
                float current_x = (float)ReflectionHacks.getPrivate(__instance, SkipCardButton.class, "current_x");
                sb.draw(TextureLoader.getTexture("HalationImages/relics/SmartPhone.png"),
                        current_x - 20f,
                        25.0F * Settings.scale,
                        256.0F,
                        128.0F,
                        512.0F,
                        256.0F,
                        0.8F * Settings.scale,
                        0.8F * Settings.scale,
                        0.0F,
                        0,
                        0,
                        512,
                        256,
                        false,
                        false);
            }
        }
    }

    @SpirePatch(
            clz = SingingBowlButton.class,
            method = "renderButton"
    )
    public static class RenderOverBowl {
        public static void Postfix(SingingBowlButton __instance, SpriteBatch sb) {
            if (SmartPhonePatch.smartBowl) {
                float current_x = (float)ReflectionHacks.getPrivate(__instance, SkipCardButton.class, "current_x");
                sb.draw(TextureLoader.getTexture("HalationImages/relics/SmartPhone.png"),
                        current_x - 256.0F,
                        220.0F * Settings.scale - 128.0F,
                        256.0F,
                        128.0F,
                        512.0F,
                        256.0F,
                        Settings.scale,
                        Settings.scale,
                        0.0F,
                        0,
                        0,
                        512,
                        256,
                        false,
                        false);
            }
        }
    }
}
