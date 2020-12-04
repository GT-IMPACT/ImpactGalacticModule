package com.impact.register;

import com.impact.common.blocks.MakemakeBlocks;
import com.impact.common.blocks.itemBlocks.ItemBlocksMakemake;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class IGM_Blocks {

    public static Block MakeMakeBlocks = new MakemakeBlocks();

    public static void regiserBlocks(){
        GameRegistry.registerBlock(MakeMakeBlocks, ItemBlocksMakemake.class, "makemakegrunt");
    }

}
