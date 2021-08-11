package com.impact.proxy;

import com.impact.api.IOrbitCustom;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;

public class WorldTick {

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            final WorldServer world = (WorldServer) event.world;

            if (world.provider instanceof IOrbitCustom) {
                final Object[] entityList = world.loadedEntityList.toArray();

                for (final Object o : entityList) {
                    if (o instanceof Entity) {
                        final Entity e = (Entity) o;

                        if (e.worldObj.provider instanceof IOrbitCustom) {
                            final IOrbitCustom dimension = (IOrbitCustom) e.worldObj.provider;

                            if (e.posY <= dimension.getYCoordToTeleportToPlanet()) {
                                int dim = 0;
                                try {
                                    dim = WorldUtil.getProviderForNameServer(dimension.getPlanetToOrbit()).dimensionId;
                                } catch (Exception ignored) {
                                }

                                WorldUtil.transferEntityToDimension(e, dim, world, false, null);
                            }
                        }
                    }
                }
            }
        }
    }

}
