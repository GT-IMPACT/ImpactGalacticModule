package com.impact.systems.SolarSystems.moon.deimos.world.gen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.world.World;

public class BiomeDecoratorDeimos extends BiomeDecoratorSpace {
    private World world;

    public BiomeDecoratorDeimos() {
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