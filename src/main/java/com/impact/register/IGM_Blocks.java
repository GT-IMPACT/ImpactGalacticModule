package com.impact.register;

import com.impact.common.blocks.*;
import com.impact.common.blocks.itemBlocks.ItemBlockBase;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class IGM_Blocks {

    public static Block MakeMakeBlocks = new MakemakeBlocks();
    public static Block HaumeaBlocks = new HaumeaBlocks();
    public static Block PhobosBlocks = new PhobosBlocks();
    public static Block DeimosBlocks = new DeimosBlocks();
    public static Block OberonBlocks = new OberonBlocks();
    public static Block ProteusBlocks = new ProteusBlocks();

    public static void regiserBlocks(){
        GameRegistry.registerBlock(MakeMakeBlocks, ItemBlockBase.class, "makemakeblocks");
        GameRegistry.registerBlock(HaumeaBlocks, ItemBlockBase.class, "haumeablocks");
        GameRegistry.registerBlock(PhobosBlocks, ItemBlockBase.class, "phobosblocks");
        GameRegistry.registerBlock(DeimosBlocks, ItemBlockBase.class, "deimosblocks");
        GameRegistry.registerBlock(OberonBlocks, ItemBlockBase.class, "oberonblocks");
        GameRegistry.registerBlock(ProteusBlocks, ItemBlockBase.class, "proteusblocks");
    }

}
