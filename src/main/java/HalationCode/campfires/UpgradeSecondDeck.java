package HalationCode.campfires;

import HalationCode.effects.OtherCampfireSmithEffect;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

public class UpgradeSecondDeck extends AbstractCampfireOption {

    public UpgradeSecondDeck() {
        super();
        this.img = ImageMaster.CAMPFIRE_SMITH_BUTTON;
        this.usable = true;
        this.label = "Upgrade Second Deck";
        this.description = "Upgrade Second Deck";
    }

    public void useOption() {
        if (!this.usable) return;
        AbstractDungeon.effectList.add(new OtherCampfireSmithEffect());
    }
}
