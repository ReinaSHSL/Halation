package HalationCode.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SecondDeck extends CustomCard {
    public static final String ID = "halation:SecondDeck";

    public SecondDeck() {
        super(ID, "Second Deck", null, 0, "", CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }

    @Override
    public void upgrade() { }

    @Override
    public AbstractCard makeCopy() {
        return new MainDeck();
    }
}
