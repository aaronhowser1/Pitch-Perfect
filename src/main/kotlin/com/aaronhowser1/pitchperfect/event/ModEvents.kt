package com.aaronhowser1.pitchperfect.event

import com.aaronhowser1.pitchperfect.PitchPerfect
import com.aaronhowser1.pitchperfect.enchantment.AndHisMusicWasElectricEnchantment
import com.aaronhowser1.pitchperfect.item.InstrumentItem
import net.minecraft.world.entity.LivingEntity
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.TickEvent.ServerTickEvent
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Suppress("unused")
@Mod.EventBusSubscriber(modid = PitchPerfect.MOD_ID)
object ModEvents {

    @SubscribeEvent
    fun onLivingHurt(event: LivingHurtEvent) {
        val target = event.entity
        val attacker: LivingEntity = event.source.entity as? LivingEntity ?: return

        AndHisMusicWasElectricEnchantment.handleElectric(event)
        attacker.mainHandItem.item.apply {
            if (this is InstrumentItem) this.attack(target)
        }

    }


    @SubscribeEvent
    fun serverTick(event: ServerTickEvent) {
        if (event.phase == TickEvent.Phase.END) {
            ModScheduler.tick()
        }
    }
}
