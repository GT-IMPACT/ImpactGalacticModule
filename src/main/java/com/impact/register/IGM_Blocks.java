package com.impact.register;

import com.impact.common.blocks.HaumeaBlocks;
import com.impact.common.blocks.MakemakeBlocks;
import com.impact.common.blocks.PhobosBlocks;
import com.impact.common.blocks.itemBlocks.ItemBlocksHaumea;
import com.impact.common.blocks.itemBlocks.ItemBlocksMakemake;
import com.impact.common.blocks.itemBlocks.ItemBlocksPhobos;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class IGM_Blocks {

    public static Block MakeMakeBlocks = new MakemakeBlocks();
    public static Block HaumeaBlocks = new HaumeaBlocks();
    public static Block PhobosBlocks = new PhobosBlocks();

    public static void regiserBlocks(){
        GameRegistry.registerBlock(MakeMakeBlocks, ItemBlocksMakemake.class, "makemakeblocks");
        GameRegistry.registerBlock(HaumeaBlocks, ItemBlocksHaumea.class, "haumeablocks");
        GameRegistry.registerBlock(PhobosBlocks, ItemBlocksPhobos.class, "phobosblocks");
    }

}
