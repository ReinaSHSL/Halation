package HalationCode.monsters;

import HalationCode.powers.PrayingPower;
import HalationCode.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import com.megacrit.cardcrawl.vfx.CollectorStakeEffect;

public class ElsaMaria extends AbstractMonster {
    public static final String ID = "halation:ElsaMaria";
    private static final String NAME = "Elsa Maria";
    public static final int HP = 350;
    private boolean firstMove;
    private static final int SNAKE_BARRAGE = 3;
    private static final int PRAYER_FRAIL = 2;
    private static final int BLOCK_AMT = 30;
    private static final int BIG_HEAL_AMT = 40;
    private static final int HEAL_AMT = 20;
    private static final int KILLING_BLOW = 40;
    private static int SNAKE_BARRAGE_HITS = (AbstractDungeon.player.currentHealth/3)+2;

    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    private static final String[] MOVES = monsterStrings.MOVES;

    public ElsaMaria() {
        super(NAME, ID, HP, 0.0F, 0.0F, 300F, 300F, null);
        this.img = TextureLoader.getTexture("HalationImages/monsters/ElsaMaria.png");
    }

    @Override
    public void usePreBattleAction() {
       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new PrayingPower(this, HEAL_AMT)));
    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 2.0f));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this, AbstractDungeon.player.currentHealth-1, DamageInfo.DamageType.HP_LOSS)));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, PRAYER_FRAIL, true)));
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, PRAYER_FRAIL, true)));
                break;
            case 2:
                for (int i = 0; i < SNAKE_BARRAGE_HITS; i++) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this, SNAKE_BARRAGE, DamageInfo.DamageType.NORMAL)));
                }
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, this, BIG_HEAL_AMT));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, BLOCK_AMT));
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new HealAction(m, this, BIG_HEAL_AMT));
                }
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this, KILLING_BLOW, DamageInfo.DamageType.NORMAL)));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int i) {
        if (AbstractDungeon.player.currentHealth > 1 && this.firstMove) {
            this.setMove((byte)0, Intent.STRONG_DEBUFF);
            this.firstMove = false;
            return;
        }
        if (AbstractDungeon.player.currentHealth < 20) {
            this.setMove((byte)4, Intent.ATTACK);
            return;
        }
        if (!AbstractDungeon.player.hasPower(FrailPower.POWER_ID)) {
            this.setMove((byte)1, Intent.DEBUFF);
            return;
        }
        if (i < 49) {
            this.setMove((byte)2, Intent.ATTACK, SNAKE_BARRAGE, SNAKE_BARRAGE_HITS, false);
            return;
        }
        if (i >= 49 && !this.lastMove((byte)3)) {
            this.setMove((byte)3, Intent.DEFEND_BUFF);
            return;
        }

    }
}
