package HalationCode.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

public class TetrisEvent extends AbstractImageEvent {
    public static final String ID = "halation:TetrisEvent";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private CUR_SCREEN screen;

    private enum CUR_SCREEN
    {
        INTRO,
        RULE_EXPLANATION,
        PLAY,
        COMPLETE,
        CLEAN_UP;
    }

    public TetrisEvent() {
        super(NAME, DESCRIPTIONS[0], "HalationImages/events/Tetris.png");
        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
        imageEventText.setDialogOption(OPTIONS[2]);
        imageEventText.setDialogOption(OPTIONS[3]);
    }

    @Override
    public void update() {
        super.update();
        if (this.screen == CUR_SCREEN.PLAY) {
            this.updateTetrisLogic();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (this.screen == CUR_SCREEN.PLAY) {

        }
    }

    public void updateTetrisLogic() {
        if (this.screen == CUR_SCREEN.PLAY) {

        }
    }

    @Override
    protected void buttonEffect(int i) {
        switch (screenNum) {
            case 0:
                switch (i) {
                    case 0:
                        this.screen = CUR_SCREEN.PLAY;
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

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
}
