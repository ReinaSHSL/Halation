package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.SuperRareRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

public class Jellyphish extends CustomRelic {
    public static final String ID = "halation:Jellyphish";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Jellyphish.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static AbstractRelic ______;
    private static boolean __ = false;
    private static boolean ___________ = false;

    //    ⠀⠀⠀⠀⣀⣤
    //            ⠀⠀⠀⠀⣿⠿⣶
    //            ⠀⠀⠀⠀⣿⣿⣀
    //            ⠀⠀⠀⣶⣶⣿⠿⠛⣶
    //            ⠤⣀⠛⣿⣿⣿⣿⣿⣿⣭⣿⣤
    //            ⠒⠀⠀⠀⠉⣿⣿⣿⣿⠀⠀⠉⣀
    //            ⠀⠤⣤⣤⣀⣿⣿⣿⣿⣀⠀⠀⣿
    //            ⠀⠀⠛⣿⣿⣿⣿⣿⣿⣿⣭⣶⠉
    //            ⠀⠀⠀⠤⣿⣿⣿⣿⣿⣿⣿
    //            ⠀⠀⠀⣭⣿⣿⣿⠀⣿⣿⣿
    //            ⠀⠀⠀⣉⣿⣿⠿⠀⠿⣿⣿
    //            ⠀⠀⠀⠀⣿⣿⠀⠀⠀⣿⣿⣤
    //            ⠀⠀⠀⣀⣿⣿⠀⠀⠀⣿⣿⣿
    //            ⠀⠀⠀⣿⣿⣿⠀⠀⠀⣿⣿⣿
    //            ⠀⠀⠀⣿⣿⠛⠀⠀⠀⠉⣿⣿
    //            ⠀⠀⠀⠉⣿⠀⠀⠀⠀⠀⠛⣿
    //            ⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⣿⣿
    //            ⠀⠀⠀⠀⣛⠀⠀⠀⠀⠀⠀⠛⠿⠿⠿
    //            ⠀⠀⠀⠛⠛



    public Jellyphish() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Jellyphish();
    }

    @Override
    public void atPreBattle() {
        this.flash();
        //            ⠀⠀⠀⣀⣶⣀
        //⠀⠀⠀⠒⣛⣭
        //⠀⠀⠀⣀⠿⣿⣶
        //⠀⣤⣿⠤⣭⣿⣿
        //⣤⣿⣿⣿⠛⣿⣿⠀⣀
        //⠀⣀⠤⣿⣿⣶⣤⣒⣛
        //⠉⠀⣀⣿⣿⣿⣿⣭⠉
        //⠀⠀⣭⣿⣿⠿⠿⣿
        //⠀⣶⣿⣿⠛⠀⣿⣿
        //⣤⣿⣿⠉⠤⣿⣿⠿
        //⣿⣿⠛⠀⠿⣿⣿
        //⣿⣿⣤⠀⣿⣿⠿
        //⠀⣿⣿⣶⠀⣿⣿⣶
        //⠀⠀⠛⣿⠀⠿⣿⣿
        //⠀⠀⠀⣉⣿⠀⣿⣿
        //⠀⠶⣶⠿⠛⠀⠉⣿
        //⠀⠀⠀⠀⠀⠀⣀⣿
        //⠀⠀⠀⠀⠀⣶⣿⠿
        //
        //⠀⠀⠀⠀⠀⠀⠀⠀⣤⣿⣿⠶⠀⠀⣀⣀
        //⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣶⣿⣿⣿⣿⣿⣿
        //⠀⠀⣀⣶⣤⣤⠿⠶⠿⠿⠿⣿⣿⣿⣉⣿⣿
        //⠿⣉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⣤⣿⣿⣿⣀
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⣿⣿⣿⣿⣶⣤
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣿⣿⣿⣿⠿⣛⣿
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⠛⣿⣿⣿⣿
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣿⣿⠿⠀⣿⣿⣿⠛
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⠀⠀⣿⣿⣿
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⠿⣿⠀⠀⣿⣶
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠛⠀⠀⣿⣿⣶
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⣿⣿⠤
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⣿
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣀
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣿

        int ________ = AbstractDungeon.potionRng.random(99);
        RelicTier ____ = RelicTier.COMMON;
        if (________ < 25) {
            ____ = RelicTier.COMMON;
            //⠀⠿⣿⣿⣀
            //⠀⠉⣿⣿⣀
            //⠀⠀⠛⣿⣭⣀⣀⣤
            //⠀⠀⣿⣿⣿⣿⣿⠛⠿⣶⣀
            //⠀⣿⣿⣿⣿⣿⣿⠀⠀⠀⣉⣶
            //⠀⠀⠉⣿⣿⣿⣿⣀⠀⠀⣿⠉
            //⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿
            //⠀⣀⣿⣿⣿⣿⣿⣿⣿⣿⠿
            //⠀⣿⣿⣿⠿⠉⣿⣿⣿⣿
            //⠀⣿⣿⠿⠀⠀⣿⣿⣿⣿
            //⣶⣿⣿⠀⠀⠀⠀⣿⣿⣿
            //⠛⣿⣿⣀⠀⠀⠀⣿⣿⣿⣿⣶⣀
            //⠀⣿⣿⠉⠀⠀⠀⠉⠉⠉⠛⠛⠿⣿⣶
            //⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣿
            //⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉
            //⣀⣶⣿⠛
            //
            //⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⣿⣿⣿⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣤⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠉⣿⣿⣿⣶⣿⣿⣿⣶⣶⣤⣶⣶⠶⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⣤⣿⠿⣿⣿⣿⣿⣿⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠛⣿⣤⣤⣀⣤⠿⠉⠀⠉⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠉⠉⠉⠉⠉⠀⠀⠀⠀⠉⣿⣿⣿⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣛⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠀⣶⣿⣿⠛⠿⣿⣿⣿⣶⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠀⣿⠛⠉⠀⠀⠀⠛⠿⣿⣿⣶⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⣿⣀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⣶⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠛⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣿⣿⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            //
            //⠀⠀⠀⠀⠀⠀⣤⣶⣶
            //⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣀⣀
            //⠀⠀⠀⠀⠀⣀⣶⣿⣿⣿⣿⣿⣿
            //⣤⣶⣀⠿⠶⣿⣿⣿⠿⣿⣿⣿⣿
            //⠉⠿⣿⣿⠿⠛⠉⠀⣿⣿⣿⣿⣿
            //⠀⠀⠉⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣤⣤
            //⠀⠀⠀⠀⠀⠀⠀⣤⣶⣿⣿⣿⣿⣿⣿
            //⠀⠀⠀⠀⠀⣀⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿
            //⠀⠀⠀⠀⣀⣿⣿⣿⠿⠉⠀⠀⣿⣿⣿⣿
            //⠀⠀⠀⠀⣿⣿⠿⠉⠀⠀⠀⠀⠿⣿⣿⠛
            //⠀⠀⠀⠀⠛⣿⣿⣀⠀⠀⠀⠀⠀⣿⣿⣀
            //⠀⠀⠀⠀⠀⣿⣿⣿⠀⠀⠀⠀⠀⠿⣿⣿
            //⠀⠀⠀⠀⠀⠉⣿⣿⠀⠀⠀⠀⠀⠀⠉⣿
            //⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⣀⣿
            //⠀⠀⠀⠀⠀⠀⣀⣿⣿
            //⠀⠀⠀⠀⠤⣿⠿⠿⠿

        } else if (________ < 50) {
            ____ = RelicTier.UNCOMMON;
        } else if (________ < 75) {
            ____ = RelicTier.RARE;
            //⠀⠀⣶⣿⠿⠀⠀⠀⣀⠀⣤⣤
            //⠀⣶⣿⠀⠀⠀⠀⣿⣿⣿⠛⠛⠿⣤⣀
            //⣶⣿⣤⣤⣤⣤⣤⣿⣿⣿⣀⣤⣶⣭⣿⣶⣀
            //⠉⠉⠉⠛⠛⠿⣿⣿⣿⣿⣿⣿⣿⠛⠛⠿⠿
            //⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⠿
            //⠀⠀⠀⠀⠀⠀⠀⠿⣿⣿⣿⣿
            //⠀⠀⠀⠀⠀⠀⠀⠀⣭⣿⣿⣿⣿⣿
            //⠀⠀⠀⠀⠀⠀⠀⣤⣿⣿⣿⣿⣿⣿
            //⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⠿
            //⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⠿
            //⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿
            //⠀⠀⠀⠀⠀⠀⠀⠉⣿⣿⣿⣿
            //⠀⠀⠀⠀⠀⠀⠀⠀⠉⣿⣿⣿⣿
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⣿⠛⠿⣿⣤
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣿⠀⠀⠀⣿⣿⣤
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⣶⣿⠛⠉
            //⠀⠀⠀⠀⠀⠀⠀⠀⣤⣿⣿⠀⠀⠉
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉
            //
            //⠀⠀⠀⠀⠀⠀⣶⣿⣶
            //⠀⠀⠀⣤⣤⣤⣿⣿⣿
            //⠀⠀⣶⣿⣿⣿⣿⣿⣿⣿⣶
            //⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿
            //⠀⠀⣿⣉⣿⣿⣿⣿⣉⠉⣿⣶
            //⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿
            //⠀⣤⣿⣿⣿⣿⣿⣿⣿⠿⠀⣿⣶
            //⣤⣿⠿⣿⣿⣿⣿⣿⠿⠀⠀⣿⣿⣤
            //⠉⠉⠀⣿⣿⣿⣿⣿⠀⠀⠒⠛⠿⠿⠿
            //⠀⠀⠀⠉⣿⣿⣿⠀⠀⠀⠀⠀⠀⠉
            //⠀⠀⠀⣿⣿⣿⣿⣿⣶
            //⠀⠀⠀⠀⣿⠉⠿⣿⣿
            //⠀⠀⠀⠀⣿⣤⠀⠛⣿⣿
            //⠀⠀⠀⠀⣶⣿⠀⠀⠀⣿⣶
            //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣭⣿⣿
            //⠀⠀⠀⠀⠀⠀⠀⠀⣤⣿⣿⠉
        } else {
            ____ = RelicTier.BOSS;
        }
        ______ = AbstractDungeon.returnRandomRelic(____);
        //            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣶
        //⠀⠀⠀⠀⠀⣀⣀⠀⣶⣿⣿⠶
        //⣶⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣤
        //⠀⠉⠶⣶⣀⣿⣿⣿⣿⣿⣿⣿⠿⣿⣤⣀
        //⠀⠀⠀⣿⣿⠿⠉⣿⣿⣿⣿⣭⠀⠶⠿⠿
        //⠀⠀⠛⠛⠿⠀⠀⣿⣿⣿⣉⠿⣿⠶
        //⠀⠀⠀⠀⠀⣤⣶⣿⣿⣿⣿⣿
        //⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⠒
        //⠀⠀⠀⠀⣀⣿⣿⣿⣿⣿⣿⣿
        //⠀⠀⠀⠀⠀⣿⣿⣿⠛⣭⣭⠉
        //⠀⠀⠀⠀⠀⣿⣿⣭⣤⣿⠛
        //⠀⠀⠀⠀⠀⠛⠿⣿⣿⣿⣭
        //⠀⠀⠀⠀⠀⠀⠀⣿⣿⠉⠛⠿⣶⣤
        //⠀⠀⠀⠀⠀⠀⣀⣿⠀⠀⣶⣶⠿⠿⠿
        //⠀⠀⠀⠀⠀⠀⣿⠛
        //⠀⠀⠀⠀⠀⠀⣭⣶
        //
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣤
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿
        //⠀⠀⣶⠀⠀⣀⣤⣶⣤⣉⣿⣿⣤⣀
        //⠤⣤⣿⣤⣿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣀
        //⠀⠛⠿⠀⠀⠀⠀⠉⣿⣿⣿⣿⣿⠉⠛⠿⣿⣤
        //⠀⠀⠀⠀⠀⠀⠀⠀⠿⣿⣿⣿⠛⠀⠀⠀⣶⠿
        //⠀⠀⠀⠀⠀⠀⠀⠀⣀⣿⣿⣿⣿⣤⠀⣿⠿
        //⠀⠀⠀⠀⠀⠀⠀⣶⣿⣿⣿⣿⣿⣿⣿⣿
        //⠀⠀⠀⠀⠀⠀⠀⠿⣿⣿⣿⣿⣿⠿⠉⠉
        //⠀⠀⠀⠀⠀⠀⠀⠉⣿⣿⣿⣿⠿
        //⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⠉
        //⠀⠀⠀⠀⠀⠀⠀⠀⣛⣿⣭⣶⣀
        //⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠉⠛⣿
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⣿⣿
        //⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣉⠀⣶⠿
        //⠀⠀⠀⠀⠀⠀⠀⠀⣶⣿⠿
        //⠀⠀⠀⠀⠀⠀⠀⠛⠿⠛
        //
        //⠀⠀⠀⣶⣿⣶
        //⠀⠀⠀⣿⣿⣿⣀
        //⠀⣀⣿⣿⣿⣿⣿⣿
        //⣶⣿⠛⣭⣿⣿⣿⣿
        //⠛⠛⠛⣿⣿⣿⣿⠿
        //⠀⠀⠀⠀⣿⣿⣿
        //⠀⠀⣀⣭⣿⣿⣿⣿⣀
        //⠀⠤⣿⣿⣿⣿⣿⣿⠉
        //⠀⣿⣿⣿⣿⣿⣿⠉
        //⣿⣿⣿⣿⣿⣿
        //⣿⣿⣶⣿⣿
        //⠉⠛⣿⣿⣶⣤
        //⠀⠀⠉⠿⣿⣿⣤
        //⠀⠀⣀⣤⣿⣿⣿
        //⠀⠒⠿⠛⠉⠿⣿
        //⠀⠀⠀⠀⠀⣀⣿⣿
        //⠀⠀⠀⠀⣶⠿⠿⠛
        __ = true;
    }

    @Override
    public void onVictory() {
        ___________ = true;
    }

    public static void fuckmeintheASS() {
        if (__) {
            System.out.println("IF IT CRASHES HERE BLACKLIST THIS FUCKING RELIC:　" + ______.relicId);
            ______.instantObtain();
            __ = false;
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMPLETE) {
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMBAT;
            }
        }
        if (___________) {
            AbstractDungeon.player.loseRelic(______.relicId);
            ___________ = false;
        }
    }
}