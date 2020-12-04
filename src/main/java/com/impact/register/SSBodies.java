package com.impact.register;

import com.impact.ImpactGalacticModule;
import com.impact.core.Config;
import com.impact.systems.SolarSystems.makemake.dimension.WorldProviderMakemake;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import galaxyspace.api.BodiesHelper;
import galaxyspace.api.IBodiesHandler;
import galaxyspace.core.registers.items.GSItems;
import galaxyspace.systems.SolarSystem.SolarSystemBodies;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.world.IAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.item.ItemStack;

public class SSBodies implements IBodiesHandler {

    public SSBodies() {}


    private static void registrycelestial() {
        BodiesHelper.BodiesData data;

        data = new BodiesHelper.BodiesData(null, BodiesHelper.calculateGravity(2.62F), 0, -5.0F, 0.0F, 98000L, false, true);
        data.addItemStack(new ItemStack(GSItems.SpacesuitHelmet, 1, 32767));
        data.addItemStack(new ItemStack(GSItems.SpacesuitPlate, 1, 32767));
        data.addItemStack(new ItemStack(GSItems.SpacesuitLeg, 1, 32767));
        data.addItemStack(new ItemStack(GSItems.SpacesuitBoots, 1, 32767));
        BodiesHelper.registerBody(SolarSystemBodies.planetMakemake, data, true);

    }

    private static void registryteleport() {
        GalacticraftRegistry.registerTeleportType(WorldProviderMakemake.class, new WorldProviderMakemake());
    }


    @Override
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent) {

    }

    @Override
    public void init(FMLInitializationEvent fmlInitializationEvent) {
        SolarSystem sol = GalacticraftCore.solarSystemSol;
        SolarSystemBodies.planetMakemake  = (Planet) BodiesHelper.registerPlanet(sol, "makemake", ImpactGalacticModule.TEXTURE_PATH, WorldProviderMakemake.class, Config.dimensionIDMakeMake, 7, 11.5f, 1.2F, 3.75f, 300.0f).atmosphereComponent(IAtmosphericGas.NITROGEN).setRingColorRGB(0.1f, 0.9f, 0.6f);
        SolarSystemBodies.planetHaumea = (Planet) BodiesHelper.registerPlanet(sol, "haumea", ImpactGalacticModule.TEXTURE_PATH, null, Config.dimensionIDEmpty, 7, 21.5f, 1.2F, 3.5F, 280.0f).setRingColorRGB(0.1f, 0.9f, 0.6f);

        SSBodies.registrycelestial();
        SSBodies.registryteleport();
    }

    @Override
    public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent) {

    }
}
