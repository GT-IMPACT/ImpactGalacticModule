package com.impact.systems.SolarSystems.makemake.dimension;

import com.impact.systems.SolarSystems.makemake.dimension.sky.SkyProviderMakemake;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import galaxyspace.api.dimension.IAdvancedSpace;
import galaxyspace.core.world.gen.WorldProviderAdvancedSpace;
import galaxyspace.systems.SolarSystem.SolarSystemBodies;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.entities.EntityLander;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

import java.util.Random;

public class WorldProviderMakemake extends WorldProviderAdvancedSpace implements IExitHeight, ISolarLevel, ITeleportType, IAdvancedSpace {

    public WorldProviderMakemake() {
    }

    public double getSolarEnergyMultiplier() {
        double s = this.getSolarSize();
        return s * s * s * ConfigManagerCore.spaceStationEnergyScalar;
    }

    @Override
    public long getDayLength() {
        return 98000L;
    }

    public double getHorizon() {
        return 44.0D;
    }

    public float getFallDamageModifier() {
        return 0.16F;
    }

    public double getFuelUsageMultiplier() {
        return 0.8D;
    }

    public double getMeteorFrequency() {
        return 3.0D;
    }

    public float getSoundVolReductionAmount() {
        return Float.MAX_VALUE;
    }

    public boolean canRainOrSnow() {
        return false;
    }

    public CelestialBody getCelestialBody() {
        return SolarSystemBodies.planetMakemake;
    }

    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return ChunkProviderMakemake.class;
    }

    public Vector3 getFogColor() {
        return new Vector3(0.0D, 0.0D, 0.0D);
    }

    public Vector3 getSkyColor() {
        return new Vector3(0.0D, 0.0D, 0.0D);
    }

    public boolean isSkyColored() {
        return true;
    }

    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerMakemake.class;
    }

    public boolean hasSunset() {
        return false;
    }

    public boolean shouldForceRespawn() {
        return !ConfigManagerCore.forceOverworldRespawn;
    }

    public double getYCoordinateToTeleport() {
        return 1000.0D;
    }

    public Vector3 getEntitySpawnLocation(WorldServer arg0, Entity arg1) {
        return new Vector3(arg1.posX, ConfigManagerCore.disableLander ? 250.0D : 900.0D, arg1.posZ);
    }

    public Vector3 getParaChestSpawnLocation(WorldServer arg0, EntityPlayerMP arg1, Random arg2) {
        if (ConfigManagerCore.disableLander) {
            double x = (arg2.nextDouble() * 2.0D - 1.0D) * 5.0D;
            double z = (arg2.nextDouble() * 2.0D - 1.0D) * 5.0D;
            return new Vector3(x, 220.0D, z);
        } else {
            return null;
        }
    }

    public Vector3 getPlayerSpawnLocation(WorldServer world, EntityPlayerMP player) {
        if (player != null) {
            GCPlayerStats stats = GCPlayerStats.get(player);
            double x = stats.coordsTeleportedFromX;
            double z = stats.coordsTeleportedFromZ;
            int limit = ConfigManagerCore.otherPlanetWorldBorders - 2;
            if (limit > 20) {
                if (x > (double) limit) {
                    z *= (double) limit / x;
                    x = limit;
                } else if (x < (double) (-limit)) {
                    z *= (double) (-limit) / x;
                    x = -limit;
                }

                if (z > (double) limit) {
                    x *= (double) limit / z;
                    z = limit;
                } else if (z < (double) (-limit)) {
                    x *= (double) (-limit) / z;
                    z = -limit;
                }
            }

            return new Vector3(x, ConfigManagerCore.disableLander ? 250.0D : 900.0D, z);
        } else {
            return null;
        }
    }

    public void onSpaceDimensionChanged(World arg0, EntityPlayerMP player, boolean arg2) {
        if (player != null && GCPlayerStats.get(player).teleportCooldown <= 0) {
            if (player.capabilities.isFlying) {
                player.capabilities.isFlying = false;
            }

            EntityLander lander = new EntityLander(player);
            if (!arg0.isRemote) {
                arg0.spawnEntityInWorld(lander);
            }

            GCPlayerStats.get(player).teleportCooldown = 10;
        }
    }

    public boolean useParachute() {
        return ConfigManagerCore.disableLander;
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        float var2 = this.worldObj.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.25F);
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

    public IRenderHandler getCloudRenderer() {
        return new CloudRenderer();
    }

    public void setupAdventureSpawn(EntityPlayerMP player) {
    }

    public double getSolarWindMultiplier() {
        return -1.0D;
    }

    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        if (super.getSkyRenderer() == null) this.setSkyRenderer(new SkyProviderMakemake());
        return super.getSkyRenderer();
    }

}
