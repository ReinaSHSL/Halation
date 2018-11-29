package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.SuperRareRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

public class Jellyphish extends CustomRelic {
    public static final String ID = "halation:Jellyphish";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Jellyphish.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private AbstractRelic gainedRelic;

    public Jellyphish() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Jellyphish();
    }

    @Override
    public void onEnterRoom(AbstractRoom r) {
        if (r instanceof MonsterRoom) {
            int roll = AbstractDungeon.potionRng.random(99);
            RelicTier tier = RelicTier.COMMON;
            if (roll < 25) {
                tier = RelicTier.COMMON;
            } else if (roll < 50) {
                tier = RelicTier.UNCOMMON;
            } else if (roll < 75) {
                tier = RelicTier.RARE;
            } else if (roll < 100) {
                tier = RelicTier.BOSS;
            }
            this.gainedRelic = AbstractDungeon.returnRandomRelic(tier);
            this.gainedRelic.obtain();
        }
    }

    @Override
    public void onVictory() {
        AbstractDungeon.player.loseRelic(gainedRelic.relicId);
    }
}
