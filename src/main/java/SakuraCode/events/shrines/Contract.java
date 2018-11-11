package SakuraCode.events.shrines;

import SakuraCode.relics.madoka.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.Circlet;

public class Contract extends AbstractImageEvent {
    public static final String ID = "sakura:Contract";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    public Contract() {
        super(NAME, DESCRIPTIONS[0], "SakuraImages/events/Contract.png");

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
        imageEventText.setDialogOption(OPTIONS[2]);
        imageEventText.setDialogOption(OPTIONS[3]);
        imageEventText.setDialogOption(OPTIONS[4]);
        imageEventText.setDialogOption(OPTIONS[5]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        if (AbstractDungeon.player.hasRelic(PurpleSoulGem.ID)) {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(Circlet.ID).makeCopy());
                        } else {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(PurpleSoulGem.ID).makeCopy());
                        }
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.updateDialogOption(0, OPTIONS[5]);
                        imageEventText.clearRemainingOptions();
                        break;
                    case 1:
                        if (AbstractDungeon.player.hasRelic(BlueSoulGem.ID)) {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(Circlet.ID).makeCopy());
                        } else {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(BlueSoulGem.ID).makeCopy());
                        }
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.updateDialogOption(0, OPTIONS[5]);
                        imageEventText.clearRemainingOptions();
                        break;
                    case 2:
                        if (AbstractDungeon.player.hasRelic(RedSoulGem.ID)) {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(Circlet.ID).makeCopy());
                        } else {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(RedSoulGem.ID).makeCopy());
                        }
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.updateDialogOption(0, OPTIONS[5]);
                        imageEventText.clearRemainingOptions();
                        break;
                    case 3:
                        if (AbstractDungeon.player.hasRelic(YellowSoulGem.ID)) {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(Circlet.ID).makeCopy());
                        } else {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(YellowSoulGem.ID).makeCopy());
                        }
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.updateDialogOption(0, OPTIONS[5]);
                        imageEventText.clearRemainingOptions();
                        break;
                    case 4:
                        if (AbstractDungeon.player.hasRelic(PinkSoulGem.ID)) {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(Circlet.ID).makeCopy());
                        } else {
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2,
                                    RelicLibrary.getRelic(PinkSoulGem.ID).makeCopy());
                        }
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.updateDialogOption(0, OPTIONS[5]);
                        imageEventText.clearRemainingOptions();
                        break;
                    case 5:
                        screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        imageEventText.updateDialogOption(0, OPTIONS[5]);
                        imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case 1:
                switch (buttonPressed) {
                    default:
                        openMap();
                        break;
                }
                break;
        }
    }
}
