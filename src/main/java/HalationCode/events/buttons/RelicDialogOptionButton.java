package HalationCode.events.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;

public class RelicDialogOptionButton extends LargeDialogOptionButton {
    private AbstractRelic r;
    private float x;
    private float y;
    private boolean isDisabled;

    public RelicDialogOptionButton(int slot, String msg, AbstractRelic r, boolean isDisabled) {
        super(slot, msg);
        this.r = r;
        this.y = -9999.0F * Settings.scale;
        this.pressed = false;
        this.slot = 0;
        switch(AbstractEvent.type) {
            case TEXT:
                this.x = 895.0F * Settings.scale;
                break;
            case IMAGE:
                this.x = 1260.0F * Settings.scale;
                break;
            case ROOM:
                this.x = 620.0F * Settings.scale;
        }

        this.slot = slot;
        this.isDisabled = isDisabled;
        if (isDisabled) {
            this.msg = this.stripColor(msg);
        } else {
            this.msg = msg;
        }

        this.hb = new Hitbox(892.0F * Settings.scale, 80.0F * Settings.scale);
    }

    public void renderRelicPreview(SpriteBatch sb) {
        if (this.r != null && this.hb.hovered) {
            this.r.currentX = this.x + this.hb.width / 1.75F;
            if (this.y < this.r.hb.height / 2.0F + 5.0F) {
                this.y = this.r.hb.height / 2.0F + 5.0F;
            }

            this.r.currentY = this.y;
            this.r.render(sb);
        }
    }

    private String stripColor(String input) {
        input = input.replace("#r", "");
        input = input.replace("#g", "");
        input = input.replace("#b", "");
        input = input.replace("#y", "");
        return input;
    }
}
