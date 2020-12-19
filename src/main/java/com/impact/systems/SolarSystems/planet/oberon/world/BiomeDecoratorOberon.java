package com.impact.systems.SolarSystems.planet.oberon.world;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.world.World;

public class BiomeDecoratorOberon extends BiomeDecoratorSpace {
    private World world;

    public BiomeDecoratorOberon() {
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