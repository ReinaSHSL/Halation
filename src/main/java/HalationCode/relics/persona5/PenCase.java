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

    public PenCase() {
        super(ID, IMG, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
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
        if(PenCasePatch.SetBool.shouldContinue && secondOption) {
            ((RestRoom)AbstractDungeon.getCurrRoom()).campfireUI.reopen();
            ((RestRoom)AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.INCOMPLETE;
            ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>) ReflectionHacks.getPrivate(((RestRoom)AbstractDungeon.getCurrRoom()).campfireUI, CampfireUI.class, "buttons");
            for (AbstractCampfireOption o : campfireButtons) {
                if (o instanceof RestOption) {
                    o.usable = false;
                }
            }
            PenCasePatch.SetBool.shouldContinue = false;
            secondOption = false;
            return false;
        }
       return true;
    }
}
