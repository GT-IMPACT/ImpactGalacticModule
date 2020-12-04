package com.impact.systems.SolarSystems.moon.phobos.dimension.sky;

import com.impact.ImpactGalacticModule;
import cpw.mods.fml.client.FMLClientHandler;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class SkyProviderPhobos extends IRenderHandler {

    private static final ResourceLocation sunTexture = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation marsTexture = new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/gui/celestialbodies/mars.png");
    private static final ResourceLocation overworldTexture = new ResourceLocation(GalacticraftCore.ASSET_PREFIX, "textures/gui/celestialbodies/earth.png");
    private static final ResourceLocation lmcTexture = new ResourceLocation(ImpactGalacticModule.TEXTURE_PATH, "textures/environment/background/LMC.png");
    private static final ResourceLocation smcTexture = new ResourceLocation(ImpactGalacticModule.TEXTURE_PATH, "textures/environment/background/SMC.png");
    private static final ResourceLocation andromedaTexture = new ResourceLocation(ImpactGalacticModule.TEXTURE_PATH, "textures/environment/background/Andromeda.png");
    private static final ResourceLocation barnardaloopTexture = new ResourceLocation(ImpactGalacticModule.TEXTURE_PATH, "textures/environment/background/BarnardaLoop.png");
    public int starGLCallList = GLAllocation.generateDisplayLists(3);
    public int glSkyList;
    public int glSkyList2;
    public int starList;

    public SkyProviderPhobos() {
        int displayLists = GLAllocation.generateDisplayLists(3);
        this.starList = displayLists;
        this.glSkyList = displayLists + 1;
        this.glSkyList2 = displayLists + 2;
        GL11.glPushMatrix();
        GL11.glNewList(this.starList, 4864);
        renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();
        Tessellator tessellator = Tessellator.instance;
        GL11.glNewList(this.glSkyList, 4864);
        float f = 16.0F;
        for (int j = -384; j <= 384; j += 64) {
            for (int l = -384; l <= 384; l += 64) {
                tessellator.startDrawingQuads();
                tessellator.addVertex((j), f, (l));
                tessellator.addVertex((j + 64), f, (l));
                tessellator.addVertex((j + 64), f, (l + 64));
                tessellator.addVertex((j), f, (l + 64));
                tessellator.draw();
            }
        }
        GL11.glEndList();
        GL11.glNewList(this.glSkyList2, 4864);
        f = -16.0F;
        tessellator.startDrawingQuads();
        for (int k = -384; k <= 384; k += 64) {
            for (int i2 = -384; i2 <= 384; i2 += 64) {
                tessellator.addVertex((k + 64), f, (i2));
                tessellator.addVertex((k), f, (i2));
                tessellator.addVertex((k), f, (i2 + 64));
                tessellator.addVertex((k + 64), f, (i2 + 64));
            }
        }
        tessellator.draw();
        GL11.glEndList();
    }

    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        GL11.glDisable(3553);
        Vec3 vec3 = world.getSkyColor(mc.renderViewEntity, partialTicks);
        float f1 = (float) vec3.xCoord;
        float f2 = (float) vec3.yCoord;
        float f3 = (float) vec3.zCoord;
        if (mc.gameSettings.anaglyph) {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f12 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f12;
        }
        GL11.glColor3f(f1, f2, f3);
        Tessellator tessellator1 = Tessellator.instance;
        GL11.glDepthMask(false);
        GL11.glEnable(2912);
        GL11.glColor3f(f1, f2, f3);
        GL11.glCallList(this.glSkyList);
        GL11.glDisable(2912);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        RenderHelper.disableStandardItemLighting();
        float f7 = world.getStarBrightness(partialTicks);
        if (f7 > 0.0F) {
            GL11.glPushMatrix();
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-19.0F, 0.0F, 1.0F, 0.0F);
            GL11.glColor4f(f7, f7, f7, f7);
            GL11.glCallList(this.starList);
            GL11.glPopMatrix();
        }
        float[] afloat = new float[4];
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        afloat[0] = 1.0F;
        afloat[1] = 0.7607843F;
        afloat[2] = 0.7058824F;
        afloat[3] = 0.3F;
        float f6 = afloat[0];
        float f8 = afloat[1];
        float f9 = afloat[2];
        if (mc.gameSettings.anaglyph) {
            float f4 = (f6 * 30.0F + f8 * 59.0F + f9 * 11.0F) / 100.0F;
            float f5 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
            float f12 = (f6 * 30.0F + f9 * 70.0F) / 100.0F;
            f6 = f4;
            f8 = f5;
            f9 = f12;
        }
        f7 = 1.0F - f7;
        tessellator1.startDrawing(6);
        tessellator1.setColorRGBA_F(f6 * f7, f8 * f7, f9 * f7, afloat[3] * 2.0F / f7);
        tessellator1.addVertex(0.0D, 100.0D, 0.0D);
        tessellator1.setColorRGBA_F(afloat[0] * f7, afloat[1] * f7, afloat[2] * f7, 0.0F);
        float f11 = 20.0F;
        tessellator1.addVertex(-f11, 100.0D, -f11);
        tessellator1.addVertex(0.0D, 100.0D, -f11 * 1.5D);
        tessellator1.addVertex(f11, 100.0D, -f11);
        tessellator1.addVertex(f11 * 1.5D, 100.0D, 0.0D);
        tessellator1.addVertex(f11, 100.0D, f11);
        tessellator1.addVertex(0.0D, 100.0D, f11 * 1.5D);
        tessellator1.addVertex(-f11, 100.0D, f11);
        tessellator1.addVertex(-f11 * 1.5D, 100.0D, 0.0D);
        tessellator1.addVertex(-f11, 100.0D, -f11);
        tessellator1.draw();
        tessellator1.startDrawing(6);
        tessellator1.setColorRGBA_F(f6 * f7, f8 * f7, f9 * f7, afloat[3] * f7);
        tessellator1.addVertex(0.0D, 100.0D, 0.0D);
        tessellator1.setColorRGBA_F(afloat[0] * f7, afloat[1] * f7, afloat[2] * f7, 0.0F);
        f11 = 40.0F;
        tessellator1.addVertex(-f11, 100.0D, -f11);
        tessellator1.addVertex(0.0D, 100.0D, -f11 * 1.5D);
        tessellator1.addVertex(f11, 100.0D, -f11);
        tessellator1.addVertex(f11 * 1.5D, 100.0D, 0.0D);
        tessellator1.addVertex(f11, 100.0D, f11);
        tessellator1.addVertex(0.0D, 100.0D, f11 * 1.5D);
        tessellator1.addVertex(-f11, 100.0D, f11);
        tessellator1.addVertex(-f11 * 1.5D, 100.0D, 0.0D);
        tessellator1.addVertex(-f11, 100.0D, -f11);
        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glShadeModel(7424);
        GL11.glEnable(3553);
        OpenGlHelper.glBlendFunc(770, 1, 1, 0);
        GL11.glPushMatrix();
        f8 = 0.0F;
        f9 = 0.0F;
        float f10 = 0.0F;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(f8, f9, f10);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        f11 = 10.5F;

        (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(sunTexture);

        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f11, 100.0D, -f11, 0.0D, 0.0D);
        tessellator1.addVertexWithUV(f11, 100.0D, -f11, 1.0D, 0.0D);
        tessellator1.addVertexWithUV(f11, 100.0D, f11, 1.0D, 1.0D);
        tessellator1.addVertexWithUV(-f11, 100.0D, f11, 0.0D, 1.0D);
        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        f11 = 15.0F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(lmcTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f11, -100.0D, f11, 0.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, f11, 1.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, -f11, 1.0D, 0.0D);
        tessellator1.addVertexWithUV(-f11, -100.0D, -f11, 0.0D, 0.0D);
        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        f11 = 5.0F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(smcTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f11, -100.0D, f11, 0.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, f11, 1.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, -f11, 1.0D, 0.0D);
        tessellator1.addVertexWithUV(-f11, -100.0D, -f11, 0.0D, 0.0D);
        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        f11 = 4.0F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(145.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(andromedaTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f11, -100.0D, f11, 0.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, f11, 1.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, -f11, 1.0D, 0.0D);
        tessellator1.addVertexWithUV(-f11, -100.0D, -f11, 0.0D, 0.0D);
        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        f11 = 40.0F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(barnardaloopTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f11, -100.0D, f11, 0.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, f11, 1.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, -f11, 1.0D, 0.0D);
        tessellator1.addVertexWithUV(-f11, -100.0D, -f11, 0.0D, 0.0D);
        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glDisable(3042);
        f11 = 95.0F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(-180.0F, 100.0F, 1.0F, 0.0F);
        GL11.glRotatef(35.0F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(marsTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f11, -100.0D, f11, 0.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, f11, 1.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, -f11, 1.0D, 0.0D);
        tessellator1.addVertexWithUV(-f11, -100.0D, -f11, 0.0D, 0.0D);
        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        f11 = 0.5F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(-180.0F, 100.0F, 50.0F, 0.0F);
        GL11.glRotatef(360.0F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(overworldTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV(-f11, -100.0D, f11, 0.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, f11, 1.0D, 1.0D);
        tessellator1.addVertexWithUV(f11, -100.0D, -f11, 1.0D, 0.0D);
        tessellator1.addVertexWithUV(-f11, -100.0D, -f11, 0.0D, 0.0D);
        tessellator1.draw();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(3008);
        GL11.glEnable(2912);
        GL11.glPopMatrix();
        GL11.glDisable(3553);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        double d0 = (mc.thePlayer.getPosition(partialTicks)).yCoord - world.getHorizon();
        if (d0 < 0.0D) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 12.0F, 0.0F);
            GL11.glCallList(this.glSkyList2);
            GL11.glPopMatrix();
            f9 = 1.0F;
            f10 = -((float) (d0 + 65.0D));
            f11 = -f9;
            tessellator1.startDrawingQuads();
            tessellator1.setColorRGBA_I(0, 255);
            tessellator1.addVertex(-f9, f10, f9);
            tessellator1.addVertex(f9, f10, f9);
            tessellator1.addVertex(f9, f11, f9);
            tessellator1.addVertex(-f9, f11, f9);
            tessellator1.addVertex(-f9, f11, -f9);
            tessellator1.addVertex(f9, f11, -f9);
            tessellator1.addVertex(f9, f10, -f9);
            tessellator1.addVertex(-f9, f10, -f9);
            tessellator1.addVertex(f9, f11, -f9);
            tessellator1.addVertex(f9, f11, f9);
            tessellator1.addVertex(f9, f10, f9);
            tessellator1.addVertex(f9, f10, -f9);
            tessellator1.addVertex(-f9, f10, -f9);
            tessellator1.addVertex(-f9, f10, f9);
            tessellator1.addVertex(-f9, f11, f9);
            tessellator1.addVertex(-f9, f11, -f9);
            tessellator1.addVertex(-f9, f11, -f9);
            tessellator1.addVertex(-f9, f11, f9);
            tessellator1.addVertex(f9, f11, f9);
            tessellator1.addVertex(f9, f11, -f9);
            tessellator1.draw();
        }
        if (world.provider.isSkyColored()) {
            GL11.glColor3f(f1 * 0.2F + 0.04F, f2 * 0.2F + 0.04F, f3 * 0.6F + 0.1F);
        } else {
            GL11.glColor3f(f1, f2, f3);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -((float) (d0 - 16.0D)), 0.0F);
        GL11.glCallList(this.glSkyList2);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDepthMask(true);
    }

    private void renderStars() {
        Random rand = new Random(10842L);
        Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();
        for (int starIndex = 0; starIndex < (ConfigManagerCore.moreStars ? 35000 : 6000); starIndex++) {
            double var3 = (rand.nextFloat() * 2.0F - 1.0F);
            double var4 = (rand.nextFloat() * 2.0F - 1.0F);
            double var5 = (rand.nextFloat() * 2.0F - 1.0F);
            double var6 = (0.15F + rand.nextFloat() * 0.1F);
            double var7 = var3 * var3 + var4 * var4 + var5 * var5;
            if (var7 < 1.0D && var7 > 0.01D) {
                var7 = 1.0D / Math.sqrt(var7);
                var3 *= var7;
                var4 *= var7;
                var5 *= var7;
                double var8 = var3 * (ConfigManagerCore.moreStars ? (rand.nextDouble() * 150.0D + 130.0D) : 100.0D);
                double var9 = var4 * (ConfigManagerCore.moreStars ? (rand.nextDouble() * 150.0D + 130.0D) : 100.0D);
                double var10 = var5 * (ConfigManagerCore.moreStars ? (rand.nextDouble() * 150.0D + 130.0D) : 100.0D);
                double var11 = Math.atan2(var3, var5);
                double var12 = Math.sin(var11);
                double var13 = Math.cos(var11);
                double var14 = Math.atan2(Math.sqrt(var3 * var3 + var5 * var5), var4);
                double var15 = Math.sin(var14);
                double var16 = Math.cos(var14);
                double var17 = rand.nextDouble() * Math.PI * 2.0D;
                double var18 = Math.sin(var17);
                double var19 = Math.cos(var17);
                for (int var20 = 0; var20 < 4; var20++) {
                    double var22 = ((var20 & 0x2) - 1) * var6;
                    double var23 = ((var20 + 1 & 0x2) - 1) * var6;
                    double var24 = var22 * var19 - var23 * var18;
                    double var25 = var23 * var19 + var22 * var18;
                    double var26 = var24 * var15 + 0.0D * var16;
                    double var27 = 0.0D * var15 - var24 * var16;
                    double var28 = var27 * var12 - var25 * var13;
                    double var29 = var25 * var12 + var27 * var13;
                    var2.addVertex(var8 + var28, var9 + var26, var10 + var29);
                }
            }
        }
        var2.draw();
    }

    private Vec3 getCustomSkyColor() {
        return Vec3.createVectorHelper(0.26796875D, 0.1796875D, 0.0D);
    }

    public float getSkyBrightness(float par1) {
        float var2 = (FMLClientHandler.instance().getClient()).theWorld.getCelestialAngle(par1);
        float var3 = 1.0F - MathHelper.sin(var2 * 6.2831855F) * 2.0F + 0.25F;
        if (var3 < 0.0F)
            var3 = 0.0F;
        if (var3 > 1.0F)
            var3 = 1.0F;
        return var3 * var3 * 1.0F;
    }
}