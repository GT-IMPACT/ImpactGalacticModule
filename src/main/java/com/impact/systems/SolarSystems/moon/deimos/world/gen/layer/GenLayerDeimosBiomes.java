package com.impact.systems.SolarSystems.moon.deimos.world.gen.layer;

import galaxyspace.core.world.gen.GSBiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDeimosBiomes extends GenLayer {
    protected BiomeGenBase[] commonBiomes;
    protected BiomeGenBase[] rareBiomes;

    public GenLayerDeimosBiomes(long seed, GenLayer genlayer) {
        super(seed);
        this.commonBiomes = new BiomeGenBase[]{
              GSBiomeGenBase.GSSpace.setBiomeName("Deimos"),
              GSBiomeGenBase.GSSpaceLowPlains.setBiomeName("DeimosLowPlains"),
              GSBiomeGenBase.GSSpaceLowHills.setBiomeName("DeimosLowHills"),
              GSBiomeGenBase.GSSpaceMidPlains.setBiomeName("DeimosMidPlains")
        };
        this.rareBiomes = new BiomeGenBase[]{
                GSBiomeGenBase.GSSpace.setBiomeName("Deimos"),
        };
        this.parent = genlayer;
    }

    public GenLayerDeimosBiomes(long seed) {
        super(seed);
        this.commonBiomes = new BiomeGenBase[]{
                GSBiomeGenBase.GSSpace.setBiomeName("Deimos"),
                GSBiomeGenBase.GSSpaceLowPlains.setBiomeName("DeimosLowPlains"),
                GSBiomeGenBase.GSSpaceLowHills.setBiomeName("DeimosLowHills"),
                GSBiomeGenBase.GSSpaceMidPlains.setBiomeName("DeimosMidPlains")
        };
        this.rareBiomes = new BiomeGenBase[]{
                GSBiomeGenBase.GSSpace.setBiomeName("Deimos"),
        };
    }

    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);

        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.initChunkSeed(dx + x, dz + z);
                if (this.nextInt(15) == 0) {
                    dest[dx + dz * width] = this.rareBiomes[this.nextInt(this.rareBiomes.length)].biomeID;
                } else {
                    dest[dx + dz * width] = this.commonBiomes[this.nextInt(this.commonBiomes.length)].biomeID;
                }
            }
        }
        return dest;
    }
}
