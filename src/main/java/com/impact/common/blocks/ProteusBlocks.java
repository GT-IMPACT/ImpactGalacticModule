package com.impact.common.blocks;

import com.impact.ImpactGalacticModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import galaxyspace.core.util.GSCreativeTabs;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ProteusBlocks
        extends Block
        implements ITerraformableBlock {
    public static String[] metadata = new String[]{"ProteusGrunt", "ProteusSubGrunt", "ProteusStone"};
    protected IIcon[] textures;


    public ProteusBlocks() {
        super(Material.rock);
        this.textures = new IIcon[metadata.length];
        setBlockName("ProteusBlocks");
        setHardness(3.0F);
        setStepSound(Block.soundTypeStone);
        setHarvestLevel("pickaxe", 1);
    }

    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn() {
        return GSCreativeTabs.GSBlocksTab;
    }

    public boolean isTerraformable(World world, int x, int y, int z) {
        return true;
    }

    public int damageDropped(int metadata) {
        return metadata;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        world.setBlockMetadataWithNotify(x, y, z, is.getItemDamage(), 3);
    }

    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        this.textures[0] = iconRegister.registerIcon(ImpactGalacticModule.TEXTURE_PATH + ":solarsystem/proteus/proteusgrunt");
        this.textures[1] = iconRegister.registerIcon(ImpactGalacticModule.TEXTURE_PATH + ":solarsystem/proteus/proteussubgrunt");
        this.textures[2] = iconRegister.registerIcon(ImpactGalacticModule.TEXTURE_PATH + ":solarsystem/proteus/proteusstone");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta > this.textures.length) {
            return this.textures[0];
        }
        return this.textures[meta];
    }

    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.textures.length; i++) {
            list.add(new ItemStack(block, 1, i));
        }
    }
}


