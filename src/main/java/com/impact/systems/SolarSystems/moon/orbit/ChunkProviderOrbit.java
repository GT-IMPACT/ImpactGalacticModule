package com.impact.systems.SolarSystems.moon.orbit;

import micdoodle8.mods.galacticraft.core.world.gen.BiomeGenBaseOrbit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChunkProviderOrbit extends ChunkProviderGenerate {

    private final Random rand;

    private final World worldObj;

    public ChunkProviderOrbit(World par1World, long par2, boolean par4) {
        super(par1World, par2, par4);
        this.rand = new Random(par2);
        this.worldObj = par1World;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public boolean saveChunks(boolean var1, IProgressUpdate var2) {
        return true;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public Chunk provideChunk(int par1, int par2) {
        this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        final Block[] ids = new Block[32768];
        Arrays.fill(ids, Blocks.air);
        final byte[] meta = new byte[32768];

        final Chunk var4 = new Chunk(this.worldObj, ids, meta, par1, par2);

        final byte[] biomesArray = var4.getBiomeArray();
        Arrays.fill(biomesArray, (byte) BiomeGenBaseOrbit.space.biomeID);

        var4.generateSkylightMap();
        return var4;
    }

    @Override
    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        BlockFalling.fallInstantly = true;
        final int k = par2 * 16;
        final int l = par3 * 16;
        this.rand.setSeed(this.worldObj.getSeed());
        final long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        final long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(par2 * i1 + par3 * j1 ^ this.worldObj.getSeed());
        if (k == 0 && l == 0) {
            new WorldGenSpaceStation().generate(this.worldObj, this.rand, k, 62, l);
        }
        BlockFalling.fallInstantly = false;
    }

    @Override
    public String makeString() {
        return "OrbitLevelSource";
    }

    @Override
    public List<?> getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int i, int j, int k) {
        return null;
    }

}
