package com.impact.systems.SolarSystems.planet.proteus.dimension;

import com.google.common.collect.Lists;
import com.impact.register.IGM_Blocks;
import com.impact.systems.SolarSystems.planet.proteus.world.BiomeDecoratorProteus;
import com.impact.systems.SolarSystems.planet.proteus.world.MapGenCavesProteus;
import galaxyspace.core.world.gen.ChunkProviderSpaceLakes;
import galaxyspace.core.world.gen.GSBiomeGenBase;
import galaxyspace.core.world.gen.GS_GenBlocks;
import micdoodle8.mods.galacticraft.api.prefab.core.BlockMetaPair;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;

public class ChunkProviderProteus extends ChunkProviderSpaceLakes {
    private final MapGenCavesProteus caveGenerator = new MapGenCavesProteus();

    public ChunkProviderProteus(World par1World, long seed, boolean mapFeaturesEnabled) {
        super(par1World, seed, mapFeaturesEnabled);
        setBlocks(new GS_GenBlocks(this.worldObj.provider, this.getBiomesForGeneration()[0], new BlockMetaPair(IGM_Blocks.ProteusBlocks, (byte) 1), this.getDirtBlock(), this.getStoneBlock()));
        setBlocks(new GS_GenBlocks(this.worldObj.provider, this.getBiomesForGeneration()[1], new BlockMetaPair(IGM_Blocks.ProteusBlocks, (byte) 0), this.getDirtBlock(), this.getStoneBlock()));
        setBlocks(new GS_GenBlocks(this.worldObj.provider, this.getBiomesForGeneration()[2], new BlockMetaPair(IGM_Blocks.ProteusBlocks, (byte) 1), this.getDirtBlock(), this.getStoneBlock()));
        setBlocks(new GS_GenBlocks(this.worldObj.provider, this.getBiomesForGeneration()[3], new BlockMetaPair(IGM_Blocks.ProteusBlocks, (byte) 0), this.getDirtBlock(), this.getStoneBlock()));
    }

    protected List<MapGenBaseMeta> getWorldGenerators() {
        List<MapGenBaseMeta> generators = Lists.newArrayList();
        generators.add(this.caveGenerator);
        return generators;
    }

    protected BiomeDecoratorSpace getBiomeGenerator() {
        return new BiomeDecoratorProteus();
    }

    protected BiomeGenBase[] getBiomesForGeneration() {
        return new BiomeGenBase[]{
                GSBiomeGenBase.GSSpace.setBiomeName("Proteus"),
                GSBiomeGenBase.GSSpaceLowPlains.setBiomeName("ProteusLowPlains"),
                GSBiomeGenBase.GSSpaceLowHills.setBiomeName("ProteusLowHills"),
                GSBiomeGenBase.GSSpaceMidPlains.setBiomeName("ProteusMidPlains")
        };
    }

    public int getCraterProbability() {
        return 300;
    }

    protected BiomeGenBase.SpawnListEntry[] getCreatures() {
        return new BiomeGenBase.SpawnListEntry[0];
    }

    public double getHeightModifier() {
        return 20.0D;
    }

    protected BiomeGenBase.SpawnListEntry[] getMonsters() {
        return new BiomeGenBase.SpawnListEntry[0];
    }

    public double getMountainHeightModifier() {
        return 0.0D;
    }

    public int getWaterLevel() {
        return 80;
    }

    public double getSmallFeatureHeightModifier() {
        return 1.0D;
    }

    public double getValleyHeightModifier() {
        return 0.0D;
    }

    public void onChunkProvider(int cX, int cZ, Block[] blocks, byte[] metadata) {
    }

    public void onPopulate(IChunkProvider arg0, int arg1, int arg2) {
    }

    public boolean chunkExists(int x, int y) {
        return false;
    }

    protected BiomeGenBase.SpawnListEntry[] getWaterCreatures() {
        return new BiomeGenBase.SpawnListEntry[0];
    }

    protected BlockMetaPair getGrassBlock() {
        return new BlockMetaPair(IGM_Blocks.ProteusBlocks, (byte) 0);
    }

    protected BlockMetaPair getDirtBlock() {
        return new BlockMetaPair(IGM_Blocks.ProteusBlocks, (byte) 1);
    }

    protected BlockMetaPair getStoneBlock() {
        return new BlockMetaPair(IGM_Blocks.ProteusBlocks, (byte) 2);
    }

    protected boolean enableBiomeGenBaseBlock() {
        return true;
    }

    public boolean canGenerateWaterBlock() {
        return false;
    }

    public boolean canGenerateIceBlock() {
        return true;
    }

    protected BlockMetaPair getWaterBlock() {
        return null;
    }

    protected GenType getGenType() {
        return GenType.GC;
    }
}
