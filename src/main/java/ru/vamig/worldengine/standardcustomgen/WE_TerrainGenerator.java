//- By Vamig Aliev.
//- https://vk.com/win_vista.

package ru.vamig.worldengine.standardcustomgen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import ru.vamig.worldengine.WE_Biome;
import ru.vamig.worldengine.WE_PerlinNoise;
import ru.vamig.worldengine.additions.WE_CreateChunkGen;
import ru.vamig.worldengine.additions.WE_GeneratorData;
import ru.vamig.worldengine.perlinnoise.PerlinNoise;

public class WE_TerrainGenerator extends WE_CreateChunkGen {
    public Block worldStoneBlock = Blocks.stone;
    public byte worldStoneBlockMeta = 0;

    public boolean worldSeaGen = true;
    public int worldSeaGenMaxY = 63;
    public Block worldSeaGenBlock = Blocks.water;
    public byte worldSeaGenBlockMeta = 0;


    public void gen(WE_GeneratorData data) {
        int q = WE_Biome.getBiggestInterpolationQuality(data.chunkProvider);
        WE_Biome[][] bl = new WE_Biome[16 + q * 2][16 + q * 2];
        WE_Biome sb = null;
        boolean ni = false;
        for (int xm = 0; xm < 16 + q * 2; xm++) {
            for (int zm = 0; zm < 16 + q * 2; zm++) {
                if (xm > q - 1 && zm > q - 1 && xm < 16 + q && zm < 16 + q) {
                    bl[xm][zm] = getBiome(data, xm - q, zm - q);
                } else {
                    bl[xm][zm] = WE_Biome.getBiomeAt(data.chunkProvider, data.chunk_X - q + xm, data.chunk_Z - q + zm);
                }
                if (!ni)
                    if (sb == null) {
                        sb = bl[xm][zm];
                    } else if (sb.id != (bl[xm][zm]).id) {
                        ni = true;
                    }
            }
        }
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int n = MathHelper.floor_double(interpolatedBlock(data.chunkProvider.worldObj.getSeed(), data.chunk_X, data.chunk_Z, x, z, bl, ni, q));
                for (int y = 0; y < 256; y++) {

                    if (y <= n) {
                        setBlock(data, this.worldStoneBlock, this.worldStoneBlockMeta, x, y, z);
                    } else if (this.worldSeaGen && y <= this.worldSeaGenMaxY) {
                        setBlock(data, this.worldSeaGenBlock, this.worldSeaGenBlockMeta, x, y, z);
                    }
                }
            }
        }
    }

    public double interpolatedBlock(long seed, long chunk_X, long chunk_Z, int bcx, int bcz, WE_Biome[][] biomeListOfChunk, boolean doInterpolate, int iQuality) {
        int bx = iQuality + bcx, bz = iQuality + bcz;

        if (doInterpolate) {
            double genPersistence = 0.0D, genScaleX = 0.0D, genScaleY = 0.0D;
            int genNumberOfOctaves = 0, genSurfaceHeight = 0;
            int c = 0;
            for (int i = 0; i <= 360; i++) {
                for (int i2 = 0; i2 <= (biomeListOfChunk[bx][bz]).biomeInterpolateQuality; i2++) {
                    int cbx = MathHelper.floor_float(bx + MathHelper.cos(i * 3.1415927F / 180.0F) * i2);
                    int cbz = MathHelper.floor_float(bz + MathHelper.sin(i * 3.1415927F / 180.0F) * i2);

                    c++;
                    genPersistence += (biomeListOfChunk[cbx][cbz]).biomePersistence;
                    genNumberOfOctaves += (biomeListOfChunk[cbx][cbz]).biomeNumberOfOctaves;
                    genScaleX += (biomeListOfChunk[cbx][cbz]).biomeScaleX;
                    genScaleY += (biomeListOfChunk[cbx][cbz]).biomeScaleY;
                    genSurfaceHeight += (biomeListOfChunk[cbx][cbz]).biomeSurfaceHeight;
                }
            }
            genPersistence /= c;
            genScaleX /= c;
            genScaleY /= c;

            genNumberOfOctaves = MathHelper.floor_float((float)(genNumberOfOctaves / c));
            genSurfaceHeight = MathHelper.floor_float((float)(genSurfaceHeight / c));

            PerlinNoise perlinNoise = new PerlinNoise(seed, genPersistence, genNumberOfOctaves, genScaleX, genScaleY, genSurfaceHeight);
            return perlinNoise.genNoise2d((chunk_X + bcx), (chunk_Z + bcz));
        }


        PerlinNoise noise = new PerlinNoise(seed, (biomeListOfChunk[bx][bz]).biomePersistence, (biomeListOfChunk[bx][bz]).biomeNumberOfOctaves, (biomeListOfChunk[bx][bz]).biomeScaleX, (biomeListOfChunk[bx][bz]).biomeScaleY, (biomeListOfChunk[bx][bz]).biomeSurfaceHeight);

        return noise.genNoise2d((chunk_X + bcx), (chunk_Z + bcz));
    }
}