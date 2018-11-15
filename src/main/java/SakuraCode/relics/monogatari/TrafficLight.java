package SakuraCode.relics.monogatari;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class TrafficLight extends CustomRelic implements ClickableRelic {
    public static final String ID = "sakura:TrafficLight";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/TrafficLight.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private boolean isGreen = true;

    public TrafficLight() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        if (isGreen) {
            return DESCRIPTIONS[0] + DESCRIPTIONS[1];
        } else {
            return DESCRIPTIONS[0] + DESCRIPTIONS[2];
        }

    }

    @Override
    public AbstractRelic makeCopy() {
        return new TrafficLight();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (isGreen) {
                isGreen = false;
                flash();
                img = TextureLoader.getTexture("SakuraImages/relics/TrafficLightRed.png");
                getUpdatedDescription();
            } else {
                isGreen = true;
                img = TextureLoader.getTexture("SakuraImages/relics/TrafficLight.png");
                getUpdatedDescription();

            }
        }
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        if (isGreen) {
            flash();
            am.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
            am.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 2), 2));
            am.addToBottom(new ApplyPowerAction(p, p, new FrailPower(p, 1, false)));
        } else {
            flash();
            am.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
            am.addToBottom(new ApplyPowerAction(p, p, new LoseDexterityPower(p, 2), 2));
            am.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, 1, false)));
        }
    }
}
