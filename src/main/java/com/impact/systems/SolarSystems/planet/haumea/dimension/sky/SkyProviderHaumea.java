package com.impact.systems.SolarSystems.planet.haumea.dimension.sky;

import cpw.mods.fml.client.FMLClientHandler;
import galaxyspace.GalaxySpace;
import galaxyspace.core.client.render.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SkyProviderHaumea extends SkyProviderBase {
    private static final ResourceLocation charonTexture;

    static {
        charonTexture = new ResourceLocation(GalaxySpace.ASSET_PREFIX, "textures/gui/celestialbodies/sol/moons/charon.png");
    }

    public SkyProviderHaumea() {
    }

    protected void rendererSky(Tessellator tessellator, float f10, float ticks) {
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        f10 = 20.0F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(-180.0F, 50.0F, 1.0F, 0.0F);
        GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(charonTexture);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-f10, -100.0D, f10, 0.0D, 1.0D);
        tessellator.addVertexWithUV(f10, -100.0D, f10, 1.0D, 1.0D);
        tessellator.addVertexWithUV(f10, -100.0D, -f10, 1.0D, 0.0D);
        tessellator.addVertexWithUV(-f10, -100.0D, -f10, 0.0D, 0.0D);
        tessellator.draw();
    }

    protected boolean enableBaseImages() {
        return true;
    }

    protected float sunSize() {
        return 1.5F;
    }

    protected boolean enableStar() {
        return true;
    }

    protected ResourceLocation sunImage() {
        return null;
    }

    protected int modeLight() {
        return 0;
    }

    protected Vector3 colorSunAura() {
        return null;
    }

    protected Vector3 getAtmosphereColor() {
        return null;
    }
}