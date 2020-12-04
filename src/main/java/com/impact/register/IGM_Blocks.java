package com.impact.register;

import com.impact.common.blocks.HaumeaBlocks;
import com.impact.common.blocks.MakemakeBlocks;
import com.impact.common.blocks.itemBlocks.ItemBlocksHaumea;
import com.impact.common.blocks.itemBlocks.ItemBlocksMakemake;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class IGM_Blocks {

    public static Block MakeMakeBlocks = new MakemakeBlocks();
    public static Block HaumeaBlocks = new HaumeaBlocks();

    public static void regiserBlocks(){
        GameRegistry.registerBlock(MakeMakeBlocks, ItemBlocksMakemake.class, "makemakegrunt");
        GameRegistry.registerBlock(HaumeaBlocks, ItemBlocksHaumea.class, "haumeablocks");
    }

}
