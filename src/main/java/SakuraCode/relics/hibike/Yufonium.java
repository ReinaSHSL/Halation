package SakuraCode.relics.hibike;

import SakuraCode.powers.HalveDamagePower;
import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Yufonium extends CustomRelic {
    public static final String ID = "sakura:Yufonium";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/Yufonium.png");

    public Yufonium() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Yufonium();
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new HalveDamagePower(AbstractDungeon.player)));
    }

    public static void onDraw(AbstractCard c) {
        if (c.type == AbstractCard.CardType.ATTACK && c.cost < 0) {
            c.modifyCostForCombat(-1);
        }
    }
}
