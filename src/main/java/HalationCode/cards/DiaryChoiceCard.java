package HalationCode.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DiaryChoiceCard extends AbstractCard {
    public static final String ID = "halation:DiaryChoiceCard";
    public String s;

    public DiaryChoiceCard(String s) {
        super(ID, s, null, -2, "", CardType.SKILL, CardColor.COLORLESS,
                CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() { }

    @Override
    public AbstractCard makeCopy() {
        return new DiaryChoiceCard(s);
    }
}

