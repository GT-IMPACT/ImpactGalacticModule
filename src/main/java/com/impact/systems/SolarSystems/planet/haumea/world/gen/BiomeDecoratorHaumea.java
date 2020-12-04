package com.impact.systems.SolarSystems.planet.haumea.world.gen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.world.World;

public class BiomeDecoratorHaumea extends BiomeDecoratorSpace {
    private World world;

    public BiomeDecoratorHaumea() {
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