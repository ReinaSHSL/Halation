package HalationCode.relics.katawashoujo;

import HalationCode.powers.NoSkillsPower;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Risk extends CustomRelic {
    public static final String ID = "halation:Risk";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Risk.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public Risk() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Risk();
    }

    @Override
    public void atTurnStart() {
        this.counter = 2;
    }

    @Override
    public void onEquip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        --energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        ++energy.energyMaster;
    }

    public void onVictory() {
        this.counter = 2;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if (this.counter > 0) {
                if (!card.purgeOnUse) {
                    this.flash();
                    AbstractMonster m = null;
                    if (action.target != null) {
                        m = (AbstractMonster) action.target;
                    }
                    final AbstractCard tmp = card.makeStatEquivalentCopy();
                    AbstractDungeon.player.limbo.addToBottom(tmp);
                    tmp.current_x = card.current_x;
                    tmp.current_y = card.current_y;
                    tmp.target_x = Settings.WIDTH / 2.0f - 300.0f * Settings.scale;
                    tmp.target_y = Settings.HEIGHT / 2.0f;
                    tmp.freeToPlayOnce = true;
                    if (m != null) {
                        tmp.calculateCardDamage(m);
                    }
                    tmp.purgeOnUse = true;
                    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse));
                    if (tmp.cardID.equals("Rampage")) {
                        AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(card.uuid, tmp.magicNumber));
                    }
                    this.counter--;
                }
                if (!AbstractDungeon.player.hasPower(NoSkillsPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NoSkillsPower(p, true)));
                }
                if (card.type == AbstractCard.CardType.SKILL) {
                    if (!card.purgeOnUse) {
                        this.flash();
                        AbstractMonster m = null;
                        if (action.target != null) {
                            m = (AbstractMonster)action.target;
                        }
                        final AbstractCard tmp = card.makeStatEquivalentCopy();
                        AbstractDungeon.player.limbo.addToBottom(tmp);
                        tmp.current_x = card.current_x;
                        tmp.current_y = card.current_y;
                        tmp.target_x = Settings.WIDTH / 2.0f - 300.0f * Settings.scale;
                        tmp.target_y = Settings.HEIGHT / 2.0f;
                        tmp.freeToPlayOnce = true;
                        if (m != null) {
                            tmp.calculateCardDamage(m);
                        }
                        tmp.purgeOnUse = true;
                        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse));
                        if (tmp.cardID.equals("Genetic Algorithm")) {
                            AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(tmp.uuid, tmp.misc + tmp.magicNumber, tmp.magicNumber));
                        }
                        this.counter--;
                    }
                    if (!AbstractDungeon.player.hasPower(EntanglePower.POWER_ID)) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EntanglePower(p)));
                    }
                }
            }
        }
    }
}
