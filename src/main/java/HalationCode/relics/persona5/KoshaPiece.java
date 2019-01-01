package HalationCode.relics.persona5;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

public class KoshaPiece extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:KoshaPiece";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/KoshaPiece.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static CardType[] CODE = {CardType.SKILL, CardType.SKILL, CardType.SKILL, CardType.ATTACK, CardType.ATTACK, CardType.ATTACK, CardType.SKILL, CardType.POWER};
    //SSSAAASP
    private static final int SKILL_1 = 3;
    private static final int ATTACK_1 = 3;
    private static final int SKILL_2 = 1;
    private static final int POWER_1 = 1;

    public KoshaPiece() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return CLICKABLE_DESCRIPTIONS()[0] + DESCRIPTIONS[0] + SKILL_1 + DESCRIPTIONS[1] + ATTACK_1 + DESCRIPTIONS[2] + SKILL_2 + DESCRIPTIONS[3] + POWER_1 + DESCRIPTIONS[4];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new KoshaPiece();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && this.counter == CODE.length) {
            AbstractDungeon.getCurrRoom().smoked = true;
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmokeBombEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
            AbstractDungeon.player.hideHealthBar();
            AbstractDungeon.player.isEscaping = true;
            AbstractDungeon.overlayMenu.endTurnButton.disable();
            AbstractDungeon.player.escapeTimer = 2.5f;
            this.counter = 0;
        }
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        if (this.counter >= 0 && this.counter < CODE.length && c.type == CODE[this.counter]) {
            ++this.counter;
        } else {
            this.counter = 0;
            if (this.pulse) {
                this.stopPulse();
            }
        }
        if (this.counter == CODE.length) {
            this.beginLongPulse();
        }
    }
}
