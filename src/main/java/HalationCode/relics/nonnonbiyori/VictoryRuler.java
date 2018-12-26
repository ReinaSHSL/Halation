package HalationCode.relics.nonnonbiyori;

import HalationCode.actions.TargetAction;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class VictoryRuler extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:VictoryRuler";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/VictoryRuler.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private boolean usedThisCombat = false;

    public VictoryRuler() {
        super(ID, IMG, RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return CLICKABLE_DESCRIPTIONS()[0] + DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new VictoryRuler();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.actionManager.turnHasEnded && !this.usedThisCombat) {
            new TargetAction(this);
            this.usedThisCombat = true;
        }
    }

    public void knockbackMonster (AbstractCreature m) {
        int index = AbstractDungeon.getCurrRoom().monsters.monsters.indexOf(m);
        int indexTwo = index+1;
        AbstractMonster otherM;
        if (indexTwo < AbstractDungeon.getCurrRoom().monsters.monsters.size()) {
            //comment: Kio if you read this please no bully
            otherM = AbstractDungeon.getCurrRoom().monsters.monsters.get(indexTwo);
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, m.maxHealth/4, DamageInfo.DamageType.NORMAL)));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(otherM, new DamageInfo(p, m.maxHealth/4, DamageInfo.DamageType.NORMAL)));
        } else {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, 2, DamageInfo.DamageType.NORMAL)));
        }
    }

    @Override
    public void atBattleStart() {
        this.usedThisCombat = false;
    }
}
