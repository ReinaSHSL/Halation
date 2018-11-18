package HalationCode.monsters;

import HalationCode.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ElsaMaria extends AbstractMonster {
    public static final String ID = "halation:ElsaMaria";
    private static final String NAME = "Elsa Maria";
    public static final int HP = 500;

    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    private static final String[] MOVES = monsterStrings.MOVES;

    public ElsaMaria() {
        super(NAME, ID, HP, 0.0F, 0.0F, 300F, 300F, null);
        this.img = TextureLoader.getTexture("HalationImages/monsters/ElsaMaria.png");
    }

    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }
}
