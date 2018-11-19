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

    public VictoryRuler() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.CLINK);
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
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && am.turnHasEnded) {
            new TargetAction(this);
        }
    }

    public void knockbackMonster (AbstractCreature m) {
        int index = AbstractDungeon.getCurrRoom().monsters.monsters.indexOf(m);
        int indexTwo = index+1;
        AbstractMonster otherM = null;
        try {
            //comment: Kio if you read this please no bully
            otherM = AbstractDungeon.getCurrRoom().monsters.monsters.get(indexTwo);
        } catch (ArrayIndexOutOfBoundsException exception) {
            am.addToBottom(new DamageAction(m, new DamageInfo(p, 2, DamageInfo.DamageType.NORMAL)));
        }
        if (otherM != null) {
            am.addToBottom(new DamageAction(m, new DamageInfo(p, 5, DamageInfo.DamageType.NORMAL)));
            am.addToBottom(new DamageAction(m, new DamageInfo(p, 5, DamageInfo.DamageType.NORMAL)));
        }
    }
}
