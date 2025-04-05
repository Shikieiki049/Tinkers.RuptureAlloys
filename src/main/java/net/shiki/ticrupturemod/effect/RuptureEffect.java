package net.shiki.ticrupturemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class RuptureEffect extends MobEffect {
    public RuptureEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF6347);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}
