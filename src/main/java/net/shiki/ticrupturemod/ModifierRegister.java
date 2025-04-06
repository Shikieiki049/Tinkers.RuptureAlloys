package net.shiki.ticrupturemod;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.shiki.ticrupturemod.modifiers.newmodifierx;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class ModifierRegister {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(ticrupture.MOD_ID);

    public static final StaticModifier<newmodifierx> new_modifier = MODIFIERS.register("new_modifier", newmodifierx::new);
    /*public ModifierRegister() {
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }*/
}
