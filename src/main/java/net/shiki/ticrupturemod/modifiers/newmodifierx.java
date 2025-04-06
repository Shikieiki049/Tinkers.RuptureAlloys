package net.shiki.ticrupturemod.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.shiki.ticrupturemod.init.ModEffects;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static net.shiki.ticrupturemod.init.ModEffects.RUPTURE_EFFECT;

public class newmodifierx extends Modifier implements MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,  ModifierHooks.MELEE_HIT);
    }
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        //System.out.println("1111111111111111111111111111111111111111111111111test");
        if (target != null && context.isFullyCharged() && target.isAlive() && RANDOM.nextFloat() < 0.2F && target.getEffect(ModEffects.RUPTURE_EFFECT.get()) == null) {
            //System.out.println("222222222222222222222222222222222222222222222222222222222222test");
            target.setLastHurtMob(context.getAttacker());
            target.addEffect(new MobEffectInstance(
                    RUPTURE_EFFECT.get(),
                    400,
                    0,
                    true, true, true
            ));
        }
        if (target != null && context.isFullyCharged() && target.isAlive() && target.getEffect(ModEffects.RUPTURE_EFFECT.get()) != null) {
            System.out.println("33333333333333333333333333test");
            target.setLastHurtMob(context.getAttacker());
            int newtime = (int) (target.getEffect(ModEffects.RUPTURE_EFFECT.get()).getDuration() + (modifier.getLevel()*(1-(target.getEffect(ModEffects.RUPTURE_EFFECT.get()).getAmplifier()+1)*0.15))*20 - 40);
            int amp=target.getEffect(ModEffects.RUPTURE_EFFECT.get()).getAmplifier();
            //System.out.println(target.getEffect(ModEffects.RUPTURE_EFFECT.get()).getDuration());
            //System.out.println("--------------");
            target.removeEffect(RUPTURE_EFFECT.get());
            //System.out.println(newtime);
            //System.out.println(amp);
            if (newtime > 0){
                target.addEffect(new MobEffectInstance(
                        RUPTURE_EFFECT.get(),
                        newtime,
                        amp,
                        true, true, true
                ));
            }

        }
    }

    }

