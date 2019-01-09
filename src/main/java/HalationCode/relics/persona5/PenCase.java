package HalationCode.relics.persona5;

import HalationCode.patches.PenCasePatch;
import HalationCode.tools.TextureLoader;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.CoffeeDripper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;

import java.util.ArrayList;

public class PenCase extends CustomRelic {
    public static final String ID = "halation:PenCase";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/PenCase.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    public static boolean secondOption = false;
    private static final int REST_AMT = 2;
    private static AbstractCampfireOption rest = null;
    private static boolean removeRest = false;
    private static ArrayList<AbstractCampfireOption> campfireButtons;

    public PenCase() {
        super(ID, IMG, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + REST_AMT + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PenCase();
    }

    @Override
    public void onEnterRestRoom() {
        secondOption = true;
    }

    public static boolean ContinueCampfire() {
        if (AbstractDungeon.player.hasRelic(CoffeeDripper.ID) && secondOption) {
            secondOption = false;
            return false;
        }
        if(PenCasePatch.SetBool.shouldStop && secondOption) {
            ((RestRoom)AbstractDungeon.getCurrRoom()).campfireUI.reopen();
            ((RestRoom)AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.INCOMPLETE;
            campfireButtons = (ArrayList<AbstractCampfireOption>) ReflectionHacks.getPrivate(((RestRoom)AbstractDungeon.getCurrRoom()).campfireUI, CampfireUI.class, "buttons");
            for (AbstractCampfireOption o : campfireButtons) {
                if (o instanceof RestOption) {
                    rest = o;
                }
            }
            if (rest != null) {
                removeRest = true;
            }
            PenCasePatch.SetBool.shouldStop = false;
            secondOption = false;
            boolean softLocked = true;
            for (AbstractCampfireOption o : campfireButtons) {
                if (o.usable) {
                    softLocked = false;
                }
            }
            if (softLocked) {
                AbstractDungeon.overlayMenu.proceedButton.show();
                ((RestRoom)AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                return true;
            }
            return false;
        }
       return true;
    }

    public static void postUpdateBS() {
        if (removeRest) {
            campfireButtons.remove(rest);
            removeRest = false;
        }
    }
}
