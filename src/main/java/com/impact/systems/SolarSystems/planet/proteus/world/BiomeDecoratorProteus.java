package com.impact.systems.SolarSystems.planet.proteus.world;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.world.World;

public class BiomeDecoratorProteus extends BiomeDecoratorSpace {
    private World world;

    public BiomeDecoratorProteus() {
    }

    protected void decorate() {
    }

    protected void setCurrentWorld(World world) {
        this.world = world;
    }

    protected World getCurrentWorld() {
        return this.world;
    }
}