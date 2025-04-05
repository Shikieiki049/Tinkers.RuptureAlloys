package net.shiki.ticrupturemod.init;

import net.shiki.ticrupturemod.effect.RuptureEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "ticrupture");

    public static final RegistryObject<MobEffect> RUPTURE_EFFECT =
            EFFECTS.register("rupture", RuptureEffect::new);
}
