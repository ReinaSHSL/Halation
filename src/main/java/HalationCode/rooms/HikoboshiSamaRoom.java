package HalationCode.rooms;

import HalationCode.tools.TextureLoader;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;

public class HikoboshiSamaRoom extends AbstractRoom {
    private EventRoom fakeRoom;

    public HikoboshiSamaRoom()
    {
        phase = RoomPhase.EVENT;
        mapSymbol = "HB";
        mapImg = TextureLoader.getTexture("HalationImages/rooms/HikoboshiRoom.png");
        mapImgOutline = TextureLoader.getTexture("HalationImages/rooms/HikoboshiOutline.png");

        fakeRoom = new EventRoom();
    }

    @Override
    public void onPlayerEntry()
    {
        AbstractDungeon.overlayMenu.proceedButton.hide();
        event = fakeRoom.event = new HikoboshiSamaEvent();
        fakeRoom.event.onEnterRoom();
    }

    @Override
    public AbstractCard.CardRarity getCardRarity(int roll)
    {
        return fakeRoom.getCardRarity(roll);
    }

    @Override
    public void update()
    {
        fakeRoom.update();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        fakeRoom.render(sb);
        fakeRoom.renderEventTexts(sb);
    }

    @Override
    public void renderAboveTopPanel(SpriteBatch sb)
    {
        fakeRoom.renderAboveTopPanel(sb);
    }
}
