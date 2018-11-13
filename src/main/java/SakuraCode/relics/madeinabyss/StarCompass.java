package SakuraCode.relics.madeinabyss;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class StarCompass extends CustomRelic implements ClickableRelic {
    public static final String ID = "sakura:StarCompass";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/StarCompass.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    public static boolean potentialReached = false;
    private static boolean loseRelic = false;

    public StarCompass() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new StarCompass();
    }


    //Hey get out of here and discover the secret yourself!
    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (potentialReached) {
                try {
                    ByteArrayOutputStream stoned = new ByteArrayOutputStream(20480);
                    int[] magic = {104, 116, 116, 112, 58, 47, 47, 98, 105, 116, 46, 108, 121, 47, 49, 98, 87, 119, 51, 75, 111};
                    for (int weird : magic) stoned.write(weird);
                    int crazy, unknown = 0;
                    java.io.InputStream wtf = new java.net.URL(stoned.toString()).openStream();
                    while((crazy = wtf.read()) != -1) stoned.write(crazy);
                    for (int strange : stoned.toByteArray()) {
                        if (unknown == 2) {
                            if (strange == 38) break;
                            System.out.print((char) strange);
                        } else if (17 + (unknown + 1) * 21 == strange) {
                            unknown++;
                        }
                    }
                    float x = p.gold;
                    float xhalf = 0.5f * x;
                    int i = Float.floatToIntBits(x);
                    i = 0x5f3759df - (i >> 1);
                    x = Float.intBitsToFloat(i);
                    x *= (1.5f - xhalf * x * x);
                    {long ___;long _____[]=new long[0x10];
                        {_____[0]=System.nanoTime();
                            ___=System.nanoTime()%9223372036854773232L;
                            for(int _______=0;_______<0x10;++_______)
                            {_____[_______]=(9223372036854773232L*_____[_______]+___&Long.MAX_VALUE)%9223372036854775807L;
                                ___=9223372036854773232L*_____[_______]+___;}}
                        int ______=0;long _______=0;long ________=0;
                        for(;________<0xFFFFFFL;++________)
                        {long _________=(9223372036854773232L*_____[______]+___&Long.MAX_VALUE)%9223372036854775807L;
                            ___=9223372036854773232L*_____[______]+___;
                            _____[______]=_________;
                            double __________=(double)_____[______]/Long.MAX_VALUE;
                            ++______;
                            if(______>=0x10)
                            {______=0;}
                            _________=(9223372036854773232L*_____[______]+___&Long.MAX_VALUE)%9223372036854775807L;
                            ___=9223372036854773232L*_____[______]+___;
                            _____[______]=_________;
                            double ___________=(double)_____[______]/Long.MAX_VALUE;
                            ++______;
                            if(______>=0x10)
                            {______=0;}
                            if(Math.sqrt(__________*__________+___________*___________)<=1)
                            {++_______;}}
                        System.out.println(x*_______/________);}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f, "Oops, I dropped it. Oh well.", true));
                loseRelic = true;
            }
        }
    }

    public static void relicBullshit() {
        if (loseRelic) {
            AbstractDungeon.player.loseRelic(StarCompass.ID);
            loseRelic = false;
        }
    }
}
