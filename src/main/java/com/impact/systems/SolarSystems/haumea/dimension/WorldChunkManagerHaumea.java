package com.impact.systems.SolarSystems.haumea.dimension;

import com.impact.systems.SolarSystems.haumea.world.gen.layer.GenLayerHaumea;
import galaxyspace.core.world.gen.GSBiomeGenBase;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldChunkManagerSpace;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerHaumea extends WorldChunkManagerSpace {

    private GenLayer unzoomedBiomes;
    private GenLayer zoomedBiomes;
    private BiomeCache myBiomeCache;
    private List<BiomeGenBase> myBiomesToSpawnIn;

    protected WorldChunkManagerHaumea() {
        this.myBiomeCache = new BiomeCache(this);
        this.myBiomesToSpawnIn = new ArrayList();
        this.myBiomesToSpawnIn.add(this.getBiome());
        this.myBiomesToSpawnIn.add(GSBiomeGenBase.GSSpace.setBiomeName("Haumea"));
        this.myBiomesToSpawnIn.add(GSBiomeGenBase.GSSpaceLowPlains.setBiomeName("HaumeaLowPlains"));
        this.myBiomesToSpawnIn.add(GSBiomeGenBase.GSSpaceLowHills.setBiomeName("HaumeaLowHills"));
        this.myBiomesToSpawnIn.add(GSBiomeGenBase.GSSpaceMidPlains.setBiomeName("HaumeaMidPlains"));
    }

    public WorldChunkManagerHaumea(long seed) {
        this();
        GenLayer[] agenlayer = GenLayerHaumea.makeTheWorld(seed);
        this.unzoomedBiomes = agenlayer[0];
        this.zoomedBiomes = agenlayer[1];
    }

    public WorldChunkManagerHaumea(World world) {
        this(world.getSeed());
    }

    public BiomeGenBase getBiome() {
        return GSBiomeGenBase.GSSpace.setBiomeName("Haumea");
    }

    public List getBiomesToSpawnIn() {
        return this.myBiomesToSpawnIn;
    }

    public BiomeGenBase getBiomeGenAt(int par1, int par2) {
        BiomeGenBase biome = this.myBiomeCache.getBiomeGenAt(par1, par2);
        return biome == null ? this.getBiome() : biome;
    }

    public float[] getRainfall(float[] par1, int x, int z, int width, int depth) {
        IntCache.resetIntCache();
        int[] aint = this.zoomedBiomes.getInts(x, z, width, depth);
        if (par1 == null || par1.length < width * depth) {
            par1 = new float[width * depth];
        }

        for(int i1 = 0; i1 < width * depth; ++i1) {
            float f = (float)BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;
            if (f > 1.0F) {
                f = 1.0F;
            }

            par1[i1] = f;
        }

        return par1;
    }

    public float getTemperatureAtHeight(float par1, int par2) {
        return par1;
    }

    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int x, int z, int length, int width) {
        int[] arrayOfInts = this.unzoomedBiomes.getInts(x, z, length, width);
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < length * width) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[length * width];
        }

        for(int i = 0; i < length * width; ++i) {
            if (arrayOfInts[i] >= 0) {
                par1ArrayOfBiomeGenBase[i] = BiomeGenBase.getBiome(arrayOfInts[i]);
            } else {
                par1ArrayOfBiomeGenBase[i] = this.getBiome();
            }
        }

        return par1ArrayOfBiomeGenBase;
    }

    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int x, int y, int width, int length, boolean cacheFlag) {
        int[] ai = this.zoomedBiomes.getInts(x, y, width, length);
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < width * length) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (y & 15) == 0) {
            BiomeGenBase[] abiomegenbase = this.myBiomeCache.getCachedBiomes(x, y);
            System.arraycopy(abiomegenbase, 0, par1ArrayOfBiomeGenBase, 0, width * length);
            return par1ArrayOfBiomeGenBase;
        } else {
            for(int i = 0; i < width * length; ++i) {
                if (ai[i] >= 0) {
                    par1ArrayOfBiomeGenBase[i] = BiomeGenBase.getBiome(ai[i]);
                } else {
                    par1ArrayOfBiomeGenBase[i] = this.getBiome();
                }
            }

            return par1ArrayOfBiomeGenBase;
        }
    }

    public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
        int i = par1 - par3 >> 2;
        int j = par2 - par3 >> 2;
        int k = par1 + par3 >> 2;
        int l = par2 + par3 >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] ai = this.unzoomedBiomes.getInts(i, j, i1, j1);

        for(int k1 = 0; k1 < i1 * j1; ++k1) {
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ai[k1]);
            if (!par4List.contains(biomegenbase)) {
                return false;
            }
        }

        return true;
    }

    public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random) {
        int i = par1 - par3 >> 2;
        int j = par2 - par3 >> 2;
        int k = par1 + par3 >> 2;
        int l = par2 + par3 >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] ai = this.unzoomedBiomes.getInts(i, j, i1, j1);
        ChunkPosition chunkposition = null;
        int k1 = 0;

        for(int l1 = 0; l1 < ai.length; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ai[l1]);
            if (par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(k1 + 1) == 0)) {
                chunkposition = new ChunkPosition(i2, 0, j2);
                ++k1;
            }
        }

        return chunkposition;
    }

    public void cleanupCache() {
        this.myBiomeCache.cleanupCache();
    }

}
