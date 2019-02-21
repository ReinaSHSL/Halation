package HalationCode.patches;

import HalationCode.relics.aobuta.SmartPhone;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.SingingBowl;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;


public class SmartPhonePatch{
    public static boolean smartSkip = false;
    public static boolean smartBowl = false;
    public static AbstractCard smartCard;
    private static int rng;
	
	@SpirePatch(
	        clz = RewardItem.class,
	        method = SpirePatch.CONSTRUCTOR,
	        paramtypez = {}
	)
	public static class SmartPhonePatchMain {
	
	    public static void Postfix(RewardItem __instance) {
	        if (AbstractDungeon.player.hasRelic(SmartPhone.ID)) {
	            if (AbstractDungeon.player.hasRelic(SingingBowl.ID)) {
	                rng = AbstractDungeon.cardRng.random(__instance.cards.size()) + 1;
	            }
	            rng = AbstractDungeon.cardRng.random(__instance.cards.size());
	            if (rng == __instance.cards.size()) {
	                smartSkip = true;
	            } else if (rng == __instance.cards.size() + 1) {
	                smartBowl = true;
	            } else {
	                smartCard = __instance.cards.get(rng);
	            }
	        }
	    }
	}
	    
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "nextRoomTransition",
            paramtypez = {SaveFile.class}
    )
    public static class SmartPhonePatchSetInBattle {
    	
    	public static void Prefix(AbstractDungeon __instance, SaveFile savefile) {
    		AbstractRelic relic = AbstractDungeon.player.getRelic(SmartPhone.ID);
    		if (relic != null){
    			relic.onEnterRoom(AbstractDungeon.nextRoom.getRoom());
    		}
    	}
    }
}
