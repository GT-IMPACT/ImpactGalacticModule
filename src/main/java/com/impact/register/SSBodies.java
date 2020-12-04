package com.impact.register;

import com.impact.ImpactGalacticModule;
import com.impact.core.Config;
import com.impact.systems.SolarSystems.haumea.dimension.WorldProviderHaumea;
import com.impact.systems.SolarSystems.makemake.dimension.WorldProviderMakemake;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import galaxyspace.GalaxySpace;
import galaxyspace.api.BodiesHelper;
import galaxyspace.api.IBodiesHandler;
import galaxyspace.core.configs.GSConfigCore;
import galaxyspace.core.configs.GSConfigDimensions;
import galaxyspace.core.registers.items.GSItems;
import galaxyspace.systems.SolarSystem.SolarSystemBodies;
import ic2.api.item.IC2Items;
import ic2.core.Ic2Items;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.world.IAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import net.minecraft.item.ItemStack;

public class SSBodies implements IBodiesHandler {

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

    }

    private static void registryteleport() {
        GalacticraftRegistry.registerTeleportType(WorldProviderMakemake.class, new WorldProviderMakemake());
        GalacticraftRegistry.registerTeleportType(WorldProviderHaumea.class, new WorldProviderHaumea());
    }


    @Override
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent) {

    }

    @Override
    public void init(FMLInitializationEvent fmlInitializationEvent) {
        SolarSystem sol = GalacticraftCore.solarSystemSol;
        SolarSystemBodies.planetMakemake  = (Planet) BodiesHelper.registerPlanet(sol, "makemake", ImpactGalacticModule.TEXTURE_PATH, WorldProviderMakemake.class, Config.dimensionIDMakeMake, 7, 11.5f, 1.2F, 3.75f, 300.0f).atmosphereComponent(IAtmosphericGas.NITROGEN).setRingColorRGB(0.1f, 0.9f, 0.6f);
        SolarSystemBodies.planetHaumea = (Planet) BodiesHelper.registerPlanet(sol, "haumea", ImpactGalacticModule.TEXTURE_PATH, WorldProviderHaumea.class, Config.dimensionIDHaumea, 7, 21.5f, 1.2F, 3.5F, 280.0f).setRingColorRGB(0.1f, 0.9f, 0.6f);
        
        SSBodies.registrycelestial();
        SSBodies.registryteleport();
    }

    @Override
    public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent) {

    }
}
