package HalationCode.events.shrines;

import HalationCode.events.buttons.RelicDialogOptionButton;
import HalationCode.relics.generalweebreferences.OrihimeAndHikoboshiSamaAndTears;
import HalationCode.relics.madoka.*;
import HalationCode.relics.persona3.PapillonHeart;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.CursedKey;

import java.util.ArrayList;

public class HikoboshiSamaEvent extends AbstractImageEvent {
    public static final String ID = "halation:HikoboshiSama";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static AbstractRelic isHikoboshi = new OrihimeAndHikoboshiSamaAndTears();
    private static AbstractRelic isTear = new OrihimeAndHikoboshiSamaAndTears();
    private int hpLoss = 0;

    public HikoboshiSamaEvent() {
        super(NAME, DESCRIPTIONS[0], "HalationImages/events/HikoboshiSama.png");
        isHikoboshi.setCounter(-Integer.parseInt("DAB", 14));
        isTear.setCounter(-Integer.parseInt("HECK", 21));
        this.hpLoss = AbstractDungeon.player.maxHealth/2;
        imageEventText.optionList.add(new RelicDialogOptionButton(0, OPTIONS[0] + this.hpLoss + OPTIONS[1], isHikoboshi, false));
        imageEventText.optionList.add(new RelicDialogOptionButton(1, OPTIONS[2], isTear, false));
        imageEventText.setDialogOption(OPTIONS[3]);
    }

    @Override
    protected void buttonEffect(int i) {
        switch (screenNum) {
            case 0:
                switch (i) {
                    case 0:
                        AbstractRelic r = AbstractDungeon.player.getRelic(OrihimeAndHikoboshiSamaAndTears.ID);
                        r.setCounter(-Integer.parseInt("DAB", 14));
                        AbstractDungeon.player.decreaseMaxHealth(this.hpLoss);
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.updateDialogOption(0, OPTIONS[4]);
                        imageEventText.clearRemainingOptions();
                        break;
                    case 1:
                        AbstractRelic re = AbstractDungeon.player.getRelic(OrihimeAndHikoboshiSamaAndTears.ID);
                        re.setCounter(-Integer.parseInt("HECK", 21));
                        ArrayList<AbstractRelic> energyRelics = getEnergyRelics();
                        AbstractRelic relicToObtain = energyRelics.get(AbstractDungeon.miscRng.random(energyRelics.size()-1));
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, relicToObtain.makeCopy());
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        imageEventText.updateDialogOption(0, OPTIONS[4]);
                        imageEventText.clearRemainingOptions();
                        break;
                    case 2:
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        imageEventText.updateDialogOption(0, OPTIONS[4]);
                        imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case 1:
                switch (i) {
                    default:
                        openMap();
                        break;
                }
                break;
        }
    }

    public ArrayList<AbstractRelic> getEnergyRelics() {
        ArrayList<AbstractRelic> retVal = new ArrayList<>();
        String ckd = ((new CursedKey()).DESCRIPTIONS[1]) + ((new CursedKey()).DESCRIPTIONS[0]);
        String akd = "Gain [E] at the start of each turn.";
        String energyDesc = (ckd.substring(0, ckd.indexOf("[")));
        String energyDesc2 = (ckd.substring(ckd.indexOf("]") + 2, ckd.indexOf(".")));
        String energyDescAlt = (akd.substring(0, akd.indexOf("[")));
        String energyDescAlt2 = (akd.substring(akd.indexOf("]") + 2, akd.indexOf(".")));
        for (AbstractRelic r : RelicLibrary.bossList) {
            boolean g1 = false;
            boolean g2 = false;
            for (String d : r.DESCRIPTIONS) {
                if (d.contains(energyDesc) || d.contains(energyDescAlt)) {
                    g1 = true;
                }
                if (d.contains(energyDesc2) || d.contains(energyDescAlt2)) {
                    g2 = true;
                }
            }
            if (g1) {
                if (g2) {
                    retVal.add(r);
                } else {
                    if (r.relicId.equals("RNG:RNG")) {
                        retVal.add(r);
                    }
                }
            }
        }
        for (AbstractRelic r : retVal) {
            if (AbstractDungeon.player.hasRelic(r.relicId)) {
                retVal.remove(r);
            }
        }
        return retVal;
    }
}
