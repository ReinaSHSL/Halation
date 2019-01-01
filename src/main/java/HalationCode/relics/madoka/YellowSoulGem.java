package HalationCode.relics.madoka;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class YellowSoulGem extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:YellowSoulGem";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/YellowSoulGem.png");
    private boolean usedThisTurn;
    private static final int DAMAGE_AMT = 15;
    private static final int USAGE_LIMIT = 8;
    private static final int VULN_AMT = 99;

    public YellowSoulGem() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return CLICKABLE_DESCRIPTIONS()[0] + DESCRIPTIONS[0] + DAMAGE_AMT + DESCRIPTIONS[1] + VULN_AMT + DESCRIPTIONS[2] + USAGE_LIMIT + DESCRIPTIONS[3];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new YellowSoulGem();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !this.usedUp && !this.usedThisTurn) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new MindblastEffect(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, AbstractDungeon.player.flipHorizontal), 0.1f));
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player, DAMAGE_AMT,
                        DamageInfo.DamageType.NORMAL)));
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new VulnerablePower(AbstractDungeon.player, VULN_AMT, false), VULN_AMT));
            this.counter++;
            counterCheck();
            this.usedThisTurn = true;
        }
    }

    public void onPlayerEndTurn() {
        this.usedThisTurn = false;
    }

    public void counterCheck() {
        if (this.counter >= USAGE_LIMIT) {
            this.usedUp = true;
        }
    }
}
