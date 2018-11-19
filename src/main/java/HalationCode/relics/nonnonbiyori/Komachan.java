package HalationCode.relics.nonnonbiyori;

import HalationCode.tools.TextureLoader;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;

import java.util.ArrayList;

public class Komachan extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:Komachan";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Komachan.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public Komachan() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return CLICKABLE_DESCRIPTIONS()[0] + DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Komachan();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT && !this.usedUp) {
            ArrayList<MapRoomNode> visibleMapNodes = (ArrayList<MapRoomNode>) ReflectionHacks.getPrivate(AbstractDungeon.dungeonMapScreen, DungeonMapScreen.class, "visibleMapNodes");
            for (final MapRoomNode n : visibleMapNodes) {
                if (n.y == AbstractDungeon.getCurrMapNode().y+1 && !(n.room instanceof MonsterRoomBoss)) {
                    n.setRoom(new RestRoom());
                }
            }
            this.usedUp = true;
        }
    }
}
