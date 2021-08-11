package com.impact.systems.SolarSystems.moon.orbit;


import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSpaceStation extends WorldGenerator {
    public WorldGenSpaceStation() {
    }

    public boolean generate(World w, Random r, int x, int y, int z) {
        camera(5, w, x, y, z, Blocks.bedrock, 0);
        // TODO: 12.08.2021 структура 
        return true;
    }

    private void single(World w, int i, int j, int k, Block b, int m) {
        w.setBlock(i, j, k, b, m, 3);
    }

    private void single(World w, int i, int j, int k, Block b) {
        w.setBlock(i, j, k, b, 0, 3);
    }

    private void camera(int range, World w, int i, int j, int k, Block b, int m) {
        for (int x = 0; x < range; x++) {
            for (int y = 0; y < range; y++) {
                single(w, i + x, j, k + y, Blocks.bedrock);
            }
        }
    }
}