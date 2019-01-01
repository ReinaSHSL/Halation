package HalationCode.relics.ddlc;

import HalationCode.tools.TextureLoader;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class Thesaurus extends CustomRelic {
    public static final String ID = "halation:Thesaurus";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Thesaurus.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public Thesaurus() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Thesaurus();
    }

    @Override
    public void onEquip() {

    }

}
