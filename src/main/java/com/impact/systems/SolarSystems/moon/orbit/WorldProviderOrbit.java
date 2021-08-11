package com.impact.systems.SolarSystems.moon.orbit;

import com.impact.api.IOrbitCustom;
import com.impact.register.SSBodies;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import galaxyspace.api.dimension.IAdvancedSpace;
import galaxyspace.core.world.gen.WorldProviderAdvancedSpace;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.api.world.IZeroGDimension;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.client.SkyProviderOrbit;
import micdoodle8.mods.galacticraft.core.world.gen.WorldChunkManagerOrbit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

import java.util.Random;

public class WorldProviderOrbit extends WorldProviderAdvancedSpace implements IOrbitCustom, IExitHeight, IZeroGDimension, ISolarLevel, ITeleportType, IAdvancedSpace {

    public WorldProviderOrbit() {
    }

    @Override
    public String getDimensionName() {
        return "OrbitEarth";
    }

    @Override
    public double getSolarEnergyMultiplier() {
        return 0.8D;
    }

    @Override
    public double getHorizon() {
        return 44.0D;
    }

    @Override
    public float getFallDamageModifier() {
        return 0.16F;
    }

    @Override
    public double getMeteorFrequency() {
        return 0;
    }

    @Override
    public double getFuelUsageMultiplier() {
        return 0.5D;
    }

    @Override
    public float getSoundVolReductionAmount() {
        return Float.MAX_VALUE;
    }

    @Override
    public boolean canRainOrSnow() {
        return false;
    }

    @Override
    public CelestialBody getCelestialBody() {
        return SSBodies.orbitEarth;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return ChunkProviderOrbit.class;
    }

    @Override
    public Vector3 getFogColor() {
        return new Vector3(0.0D, 0.0D, 0.0D);
    }

    @Override
    public Vector3 getSkyColor() {
        return new Vector3(0.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerOrbit.class;
    }

    @Override
    public boolean hasSunset() {
        return false;
    }

    @Override
    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public double getYCoordinateToTeleport() {
        return 1000.0D;
    }

    @Override
    public Vector3 getEntitySpawnLocation(WorldServer arg0, Entity arg1) {
        return new Vector3(0.5, 65.0, 0.5);
    }

    @Override
    public Vector3 getParaChestSpawnLocation(WorldServer arg0, EntityPlayerMP arg1, Random arg2) {
        return new Vector3(-8.5D, 90.0, -1.5D);
    }

    @Override
    public Vector3 getPlayerSpawnLocation(WorldServer world, EntityPlayerMP player) {
        return new Vector3(0.5, 65.0, 0.5);
    }

    @Override
    public void onSpaceDimensionChanged(World arg0, EntityPlayerMP player, boolean arg2) {
    }

    @Override
    public boolean useParachute() {
        return false;
    }

    @Override
    public boolean isDaytime() {
        final float a = this.worldObj.getCelestialAngle(0F);
        //TODO: adjust this according to size of planet below
        return a < 0.42F || a > 0.58F;
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        final float var2 = this.worldObj.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (var3 < 0.0F) {
            var3 = 0.0F;
        }

        if (var3 > 1.0F) {
            var3 = 1.0F;
        }

        return var3 * var3 * 0.5F + 0.3F;
    }

    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1) {
        float f1 = this.worldObj.getCelestialAngle(1.0F);
        float f2 = 1.25F - (MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F + 0.2F);
        float f3 = (float) this.worldObj.getWorldTime();
        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        f2 = 1.2F - f2;
        return f2 * 0.25F;
    }

    @Override
    public IRenderHandler getCloudRenderer() {
        return new CloudRenderer();
    }

    @Override
    public void setupAdventureSpawn(EntityPlayerMP player) {
    }

    @Override
    public double getSolarWindMultiplier() {
        return -1.0D;
    }

    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        if (super.getSkyRenderer() == null) {
            this.setSkyRenderer(new SkyProviderOrbit(
                    new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/gui/celestialbodies/earth.png"), true, true));
            ;
        }
        return super.getSkyRenderer();
    }

    @Override
    public float getGravity() {
        return 0.075F;//0.073F;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public int getRespawnDimension(EntityPlayerMP player) {
        return 0;
    }

    @Override
    public int getAverageGroundLevel() {
        return 64;
    }

    @Override
    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return true;
    }

    @Override
    public String getPlanetToOrbit() {
        return "Overworld";
    }

    @Override
    public int getYCoordToTeleportToPlanet() {
        return 30;
    }
}