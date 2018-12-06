package HalationCode.relics.generalweebreferences;

import HalationCode.actions.RandomEtherealCardToHand;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import javax.xml.soap.Text;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class OrihimeAndHikoboshiSamaAndTears extends CustomRelic {
    public static final String ID = "halation:OrihimeAndHikoboshiSamaAndTears";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/OrihimeSama.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public OrihimeAndHikoboshiSamaAndTears() {
        super(ID, IMG, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[2];
    }

    @Override
    public void setCounter(int value) {
        super.setCounter(value);
        if (this.counter == -Integer.parseInt("DAB", 14)) {
            flash();
            img = TextureLoader.getTexture("HalationImages/relics/OrihimeAndHikoboshiSama.png");
            try {
                Field targetField = AbstractRelic.class.getDeclaredField("name");

                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(targetField, targetField.getModifiers() & ~Modifier.FINAL);

                targetField.setAccessible(true);
                targetField.set(this, DESCRIPTIONS[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            flavorText = DESCRIPTIONS[5];
            description = DESCRIPTIONS[3];
            tips.clear();
            tips.add(new PowerTip(name, description));
            initializeTips();
        }
        if (this.counter == -Integer.parseInt("HECK", 21)) {
            flash();
            img = TextureLoader.getTexture("HalationImages/relics/CelestialTears.png");
            try {
                Field targetField = AbstractRelic.class.getDeclaredField("name");

                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(targetField, targetField.getModifiers() & ~Modifier.FINAL);

                targetField.setAccessible(true);
                targetField.set(this, DESCRIPTIONS[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            flavorText = DESCRIPTIONS[6];
            description = DESCRIPTIONS[4];
            tips.clear();
            tips.add(new PowerTip(name, description));
            initializeTips();
        }
    }

    public void atTurnStartPostDraw() {
        if (this.counter == -Integer.parseInt("DAB", 14)) {
            AbstractDungeon.actionManager.addToBottom(new RandomEtherealCardToHand(true, false));
        } else if (this.counter == -Integer.parseInt("HECK", 21)) {
            AbstractDungeon.actionManager.addToBottom(new RandomEtherealCardToHand(false, true));
        } else {
            AbstractDungeon.actionManager.addToBottom(new RandomEtherealCardToHand(false, false));
        }

    }

        @Override
    public AbstractRelic makeCopy() {
        return new OrihimeAndHikoboshiSamaAndTears();
    }
}
