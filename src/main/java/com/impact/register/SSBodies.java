package com.impact.register;

import com.impact.ImpactGalacticModule;
import com.impact.systems.SolarSystems.moon.deimos.dimension.WorldProviderDeimos;
import com.impact.systems.SolarSystems.moon.phobos.dimension.WorldProviderPhobos;
import com.impact.systems.SolarSystems.planet.haumea.dimension.WorldProviderHaumea;
import com.impact.systems.SolarSystems.planet.makemake.dimension.WorldProviderMakemake;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import galaxyspace.api.BodiesHelper;
import galaxyspace.api.IBodiesHandler;
import galaxyspace.systems.SolarSystem.SolarSystemBodies;
import ic2.core.Ic2Items;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.world.IAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import net.minecraft.item.ItemStack;

public class SSBodies implements IBodiesHandler {


    /**
     *   ID Dimensions GalaxySpace: 2000 - 2050
     *   ID Dimensions Impact Galactic Module 2050 - 2100
     */

    public static int dimensionIDEmpty = -1;
    public static int dimensionIDMakeMake = -2050;
    public static int dimensionIDHaumea = -2051;
    public static int dimensionIDPhobos = -2052;
    public static int dimensionIDDeimos = -2053;
    public static int dimensionIDJupiter = -2054;

    public SSBodies() {}


    private static void registrycelestial() {
        BodiesHelper.BodiesData data;

        data = new BodiesHelper.BodiesData(null, BodiesHelper.calculateGravity(2.62F), 0, -6.0F, 0.0F, 98000L, false, true);
        data.addItemStack(new ItemStack(Ic2Items.quantumHelmet.getItem(), 1, 32767));
        data.addItemStack(new ItemStack(Ic2Items.quantumBodyarmor.getItem(), 1, 32767));
        data.addItemStack(new ItemStack(Ic2Items.quantumLeggings.getItem(), 1, 32767));
        data.addItemStack(new ItemStack(Ic2Items.quantumBoots.getItem(), 1, 32767));
        BodiesHelper.registerBody(SolarSystemBodies.planetMakemake, data, true);

        data = new BodiesHelper.BodiesData(null, BodiesHelper.calculateGravity(2.74F), 0, -5.0F, 0.0F, 98000L, false, true);
        data.addItemStack(new ItemStack(Ic2Items.quantumHelmet.getItem(), 1, 32767));
        data.addItemStack(new ItemStack(Ic2Items.quantumBodyarmor.getItem(), 1, 32767));
        data.addItemStack(new ItemStack(Ic2Items.quantumLeggings.getItem(), 1, 32767));
        data.addItemStack(new ItemStack(Ic2Items.quantumBoots.getItem(), 1, 32767));
        BodiesHelper.registerBody(SolarSystemBodies.planetHaumea, data, true);

        data = new BodiesHelper.BodiesData(null, 0.068F, 0, -2.0F, 0.0F, 12000L, false, false);
        BodiesHelper.registerBody(SolarSystemBodies.phobosMars, data, true);

        data = new BodiesHelper.BodiesData(null, 0.064F, 0, -2.0F, 0.0F, 24000L, false, false);
        BodiesHelper.registerBody(SolarSystemBodies.deimosMars, data, true);

    }

    private static void registryteleport() {
        GalacticraftRegistry.registerTeleportType(WorldProviderMakemake.class, new WorldProviderMakemake());
        GalacticraftRegistry.registerTeleportType(WorldProviderHaumea.class, new WorldProviderHaumea());
        GalacticraftRegistry.registerTeleportType(WorldProviderPhobos.class, new WorldProviderPhobos());
        GalacticraftRegistry.registerTeleportType(WorldProviderDeimos.class, new WorldProviderDeimos());
    }


    @Override
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent) {

    }

    @Override
    public void init(FMLInitializationEvent fmlInitializationEvent) {
        SolarSystem sol = GalacticraftCore.solarSystemSol;
        SolarSystemBodies.planetMakemake  = (Planet) BodiesHelper.registerPlanet(sol, "makemake", ImpactGalacticModule.TEXTURE_PATH, WorldProviderMakemake.class, dimensionIDMakeMake, 7, 11.5f, 1.2F, 3.75f, 300.0f).atmosphereComponent(IAtmosphericGas.NITROGEN).setRingColorRGB(0.1f, 0.9f, 0.6f);
        SolarSystemBodies.planetHaumea = (Planet) BodiesHelper.registerPlanet(sol, "haumea", ImpactGalacticModule.TEXTURE_PATH, WorldProviderHaumea.class, dimensionIDHaumea, 7, 21.5f, 1.2F, 3.5F, 280.0f).setRingColorRGB(0.1f, 0.9f, 0.6f);

        SolarSystemBodies.phobosMars = BodiesHelper.registerMoon(MarsModule.planetMars, "phobos", ImpactGalacticModule.TEXTURE_PATH, WorldProviderPhobos.class, dimensionIDPhobos, 2, 1.0F, 0.0017F, 8.0F, 100.0F);
        SolarSystemBodies.deimosMars = BodiesHelper.registerMoon(MarsModule.planetMars, "deimos", ImpactGalacticModule.TEXTURE_PATH, WorldProviderDeimos.class, dimensionIDDeimos, 2, 1.0F, 0.0017F, 16.0F, 300.0F);

        SSBodies.registrycelestial();
        SSBodies.registryteleport();
    }

    @Override
    public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent) {

    }
}
