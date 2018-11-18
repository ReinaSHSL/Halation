package HalationCode.screens;

import HalationCode.tools.TextureLoader;
import basemod.BaseMod;
import basemod.interfaces.PostRenderSubscriber;
import basemod.interfaces.PreRoomRenderSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ElsaMariaBattleground implements PreRoomRenderSubscriber, PostRenderSubscriber {

    public ElsaMariaBattleground() {
        BaseMod.subscribe(this);
    }

    public void renderBackground(SpriteBatch sb){
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);

        sb.draw(TextureLoader.getTexture(
                "HalationImages/screens/ElsaMariaBattlegroud.png"),
                0f, 0f, 1920 * Settings.scale, 1080 * Settings.scale);
    }

    public void renderForeground(SpriteBatch sb){
        sb.setBlendFunction(770, 771);
        sb.setColor(Color.BLACK.cpy());
        sb.draw(ImageMaster.BORDER_GLOW_2,0f ,0f , 1920 * Settings.scale, 1080 * Settings.scale);
    }

    @Override
    public void receivePreRoomRender(SpriteBatch sb) {
        render(sb);
    }

    @Override
    public void receivePostRender(SpriteBatch sb) {
        sb.setBlendFunction(770, 771);
        sb.setColor(Color.BLACK.cpy());
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
    }

    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }
}
