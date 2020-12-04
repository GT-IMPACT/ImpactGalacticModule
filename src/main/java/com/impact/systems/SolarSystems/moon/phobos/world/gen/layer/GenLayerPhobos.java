package com.impact.systems.SolarSystems.moon.phobos.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerPhobos extends GenLayer {

    public GenLayerPhobos(long seed) {
        super(seed);
    }

    public static GenLayer[] makeTheWorld(long seed) {
        GenLayer biomes = new GenLayerPhobosBiomes(1L);
        GenLayerZoom genLayerZoom = new GenLayerZoom(1000L, biomes);
        genLayerZoom = new GenLayerZoom(1001L, genLayerZoom);
        genLayerZoom = new GenLayerZoom(1002L, genLayerZoom);
        genLayerZoom = new GenLayerZoom(1003L, genLayerZoom);
        genLayerZoom = new GenLayerZoom(1004L, genLayerZoom);
        genLayerZoom = new GenLayerZoom(1005L, genLayerZoom);
        GenLayerVoronoiZoom genLayerVoronoiZoom = new GenLayerVoronoiZoom(10L, genLayerZoom);
        GenLayerRiver genlayerriver = new GenLayerRiver(1000L, genLayerZoom);
        genLayerZoom.initWorldGenSeed(seed);
        genLayerVoronoiZoom.initWorldGenSeed(seed);
        return new GenLayer[]{
                genLayerZoom, genLayerVoronoiZoom, genlayerriver
        };
    }

    public int[] getInts(int x, int z, int width, int depth) {
        return null;
    }

}
