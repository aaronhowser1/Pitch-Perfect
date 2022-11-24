package com.aaronhowser1.pitchperfect.enchantment;

import com.aaronhowser1.pitchperfect.item.InstrumentItem;
import com.aaronhowser1.pitchperfect.PitchPerfect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {

    public static final EnchantmentCategory INSTRUMENT = EnchantmentCategory.create("INSTRUMENT", item -> item instanceof InstrumentItem);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PitchPerfect.MOD_ID);

    public static RegistryObject<Enchantment> HEALING_BEAT =
            ENCHANTMENTS.register("healing_beat",
                    () -> new HealingBeatEnchantment(
                            Enchantment.Rarity.COMMON,
                            INSTRUMENT,
                            EquipmentSlot.MAINHAND,
                            EquipmentSlot.OFFHAND
                    ));

    public static RegistryObject<Enchantment> BWAAAP =
            ENCHANTMENTS.register("bwaaap",
                    () -> new BwaaapEnchantment(
                            Enchantment.Rarity.COMMON,
                            INSTRUMENT,
                            EquipmentSlot.MAINHAND,
                            EquipmentSlot.OFFHAND
                    ));

    public static RegistryObject<Enchantment> AND_HIS_MUSIC_WAS_ELECTRIC =
            ENCHANTMENTS.register("and_his_music_was_electric",
                    () -> new AndHisMusicWasElectricEnchantment(
                            Enchantment.Rarity.COMMON,
                            INSTRUMENT,
                            EquipmentSlot.MAINHAND,
                            EquipmentSlot.OFFHAND
                    )
            );

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);

    }
}