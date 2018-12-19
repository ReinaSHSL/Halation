package HalationCode.relics.aobuta;

import HalationCode.patches.QuantumMapNodeField;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class QuantumPhysicsTextbook extends CustomRelic {
    public static final String ID = "halation:QuantumPhysicsTextbook";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/QuantumPhysicsTextbook.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static ArrayList<MapRoomNode> quantumCandidates;

    public QuantumPhysicsTextbook() {
        super(ID, IMG, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new QuantumPhysicsTextbook();
    }

    @Override
    public void onEquip() {
       AbstractDungeon.getCurrMapNode().isConnectedTo(QuantumMapNodeField.quantumPhysicsNode.get(CardCrawlGame.dungeon));
    }
}
