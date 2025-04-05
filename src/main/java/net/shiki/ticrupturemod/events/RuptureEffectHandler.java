package net.shiki.ticrupturemod.events;

import net.minecraft.client.Minecraft;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.shiki.ticrupturemod.init.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import static net.shiki.ticrupturemod.init.ModEffects.RUPTURE_EFFECT;

@Mod.EventBusSubscriber(modid = "ticrupture", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RuptureEffectHandler {

    @SubscribeEvent public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();

        // 调试输出
        System.out.println("[DEBUG] 收到伤害事件，来源: " + event.getSource().getMsgId());
        System.out.println("实体: " + entity.getName().getString() + " 伤害值: " + event.getAmount());

        if (entity.level().isClientSide) {
            System.out.println("[DEBUG] 客户端跳过");
            return;
        }

        MobEffectInstance effect = entity.getEffect(ModEffects.RUPTURE_EFFECT.get());
        if (effect == null) {
            System.out.println("[DEBUG] 无破裂效果");
            return;
        }
        if (event.getSource() == entity.damageSources().genericKill()) {
            System.out.println("[DEBUG] nono");
            return;
        }

        int level = effect.getAmplifier() + 1;
        float baseDamage = event.getAmount();

        System.out.println("[DEBUG] 当前效果等级: " + level + " 需要伤害 > " + (level + 3));
        if (baseDamage > (level + 3)) {
            System.out.println("[DEBUG] 满足条件，开始调度延迟伤害");
            float extraDamage = (level - 1) * 2 + 1;
            entity.invulnerableTime = 0;
            entity.hurtTime = 0;
            entity.hurt(entity.damageSources().genericKill(), extraDamage);
            applyDelayedDamage(entity, effect, event.getSource());
        } else {
            System.out.println("[DEBUG] 未满足伤害阈值");
        }
    }

    private static void applyDelayedDamage(LivingEntity entity, MobEffectInstance effect, DamageSource originalSource) {
        System.out.println("[DEBUG] 执行延迟伤害，实体状态 - 存活: " + entity.isAlive() + " 未移除: " + !entity.isRemoved());

        if (entity.isAlive() && !entity.isRemoved()) {

            int newDuration = effect.getDuration() - 40;
            System.out.println("[DEBUG] 原持续时间: " + effect.getDuration() + " 新持续时间: " + newDuration);

            entity.removeEffect(RUPTURE_EFFECT.get());
            if (newDuration > 0) {
                System.out.println("[DEBUG] 重新应用效果");
                entity.addEffect(new MobEffectInstance(
                        RUPTURE_EFFECT.get(),
                        newDuration,
                        effect.getAmplifier(),
                        true, true, true
                ));
            }
        }
    }


}
