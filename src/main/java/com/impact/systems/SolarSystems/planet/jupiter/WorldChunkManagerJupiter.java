package com.impact.systems.SolarSystems.planet.jupiter;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldChunkManagerSpace;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldChunkManagerJupiter extends WorldChunkManagerSpace {

    @Override
    public BiomeGenBase getBiome() {
        return BiomeGenBaseJupiter.jupiter;
    }
}
