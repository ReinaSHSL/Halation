package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
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
    public static final String ID = "halation:TrafficLight";
    private static final Texture     IMG = TextureLoader.getTexture("HalationImages/relics/TrafficLight.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private boolean isGreen = true;
    private static final int STR_AMT = 2;
    private static final int FRAIL_AMT = 1;
    private static final int DEX_AMT = 1;
    private static final int WEAK_AMT = 1;

    public TrafficLight() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        if (this.isGreen) {
            return CLICKABLE_DESCRIPTIONS()[0] + DESCRIPTIONS[0] + STR_AMT + DESCRIPTIONS[1] + FRAIL_AMT + DESCRIPTIONS[2]
            + DEX_AMT + DESCRIPTIONS[3] + WEAK_AMT + DESCRIPTIONS[4] + DESCRIPTIONS[5];
        } else {
            return CLICKABLE_DESCRIPTIONS()[0] + DESCRIPTIONS[0] + STR_AMT + DESCRIPTIONS[1] + FRAIL_AMT + DESCRIPTIONS[2]
                    + DEX_AMT + DESCRIPTIONS[3] + WEAK_AMT + DESCRIPTIONS[4] + DESCRIPTIONS[6];
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
                img = TextureLoader.getTexture("HalationImages/relics/TrafficLightRed.png");
                getUpdatedDescription();
            } else {
                isGreen = true;
                img = TextureLoader.getTexture("HalationImages/relics/TrafficLight.png");
                getUpdatedDescription();
            }
            this.getUpdatedDescription();
        }
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        if (isGreen) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, STR_AMT), STR_AMT));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, STR_AMT), STR_AMT));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FrailPower(p, FRAIL_AMT, false), FRAIL_AMT));
        } else {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, DEX_AMT), DEX_AMT));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseDexterityPower(p, DEX_AMT), DEX_AMT));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, WEAK_AMT, false), WEAK_AMT));
        }
    }

    @Override
    public int getPrice() {
        return 400;
    }
}
