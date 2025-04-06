package net.shiki.ticrupturemod.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static net.shiki.ticrupturemod.init.ModEffects.RUPTURE_EFFECT;

public class newmodifier extends Modifier implements MeleeHitModifierHook {

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        System.out.println("1111111111111111111111111111111111111111111111111test");
        if (target != null && context.isFullyCharged() && target.isAlive() && RANDOM.nextFloat() < 0.2F) {
            target.setLastHurtMob(context.getAttacker());
            target.addEffect(new MobEffectInstance(
                    RUPTURE_EFFECT.get(),
                    4000,
                    0,
                    true, true, true
            ));
        }
    }

}
