package HalationCode.relics.discord;

import HalationCode.events.TetrisEvent;
import HalationCode.tools.TextureLoader;
import HalationCode.tools.tetris.TetrisGame;
import basemod.CustomEventRoom;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;

import java.util.ArrayList;

public class LBlock extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:LBlock";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/LBlock.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public LBlock() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return CLICKABLE_DESCRIPTIONS()[1] + DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LBlock();
    }

    @Override
    public void onRightClick() {
        AbstractDungeon.eventList.add(0, TetrisEvent.ID);
        final MapRoomNode cur = AbstractDungeon.currMapNode;
        final MapRoomNode node = new MapRoomNode(cur.x, cur.y);
        node.room = (AbstractRoom)new CustomEventRoom();
        final ArrayList<MapEdge> curEdges = (ArrayList<MapEdge>)cur.getEdges();
        for (final MapEdge edge : curEdges) {
            node.addEdge(edge);
        }
        AbstractDungeon.previousScreen = null;
        AbstractDungeon.dynamicBanner.hide();
        AbstractDungeon.dungeonMapScreen.closeInstantly();
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.topPanel.unhoverHitboxes();
        AbstractDungeon.fadeIn();
        //AbstractDungeon.effectList.clear();
        //AbstractDungeon.topLevelEffects.clear();
        //AbstractDungeon.topLevelEffectsQueue.clear();
        //AbstractDungeon.effectsQueue.clear();
        AbstractDungeon.dungeonMapScreen.dismissable = true;
        AbstractDungeon.setCurrMapNode(AbstractDungeon.nextRoom = node);
        AbstractDungeon.getCurrRoom().onPlayerEntry();
        AbstractDungeon.scene.nextRoom(node.room);
        AbstractDungeon.rs = AbstractDungeon.RenderScene.EVENT;
    }
}
