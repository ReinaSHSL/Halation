package SakuraCode.relics.monogatari;

import SakuraCode.tools.TextureLoader;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;

import java.util.ArrayList;

public class LostSnail extends CustomRelic {
    public static final String ID = "sakura:LostSnail";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/LostSnail.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public LostSnail() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LostSnail();
    }

    public void onEnterRoom(final AbstractRoom room) {
        if (room instanceof EventRoom) {
            int roll = AbstractDungeon.mapRng.random(99);
            if (roll < 39) {
                ArrayList<MapRoomNode> visibleMapNodes = (ArrayList<MapRoomNode>) ReflectionHacks.getPrivate(AbstractDungeon.dungeonMapScreen, DungeonMapScreen.class, "visibleMapNodes");
                for (final MapRoomNode n : visibleMapNodes) {
                    if (n.y == AbstractDungeon.getCurrMapNode().y+1 && !(n.room instanceof MonsterRoomBoss)) {
                        n.setRoom(new EventRoom());
                    }
                }
            }
        }
    }
}
