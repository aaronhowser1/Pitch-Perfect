package dev.aaronhowser.mods.pitchperfect.entity

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.monster.Skeleton
import net.minecraft.world.level.Level

class SansEntity(
    pEntityType: EntityType<out Skeleton>,
    pLevel: Level
) : Skeleton(pEntityType, pLevel) {

    // One in 93 skeletons spawns as a Sans, because that's how many monsters have to die to fight sans

}