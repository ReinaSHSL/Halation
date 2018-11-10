package relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractSakuraRelic extends AbstractRelic {

    public AbstractSakuraRelic(String setId, String imgName, RelicTier tier, LandingSound sfx) {
        super(setId, "", tier, sfx);
    }

}
