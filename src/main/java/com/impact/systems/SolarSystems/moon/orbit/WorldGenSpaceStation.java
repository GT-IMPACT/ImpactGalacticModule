package com.impact.systems.SolarSystems.moon.orbit;


import com.impactarchitecture.loader.MainLoader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSpaceStation extends WorldGenerator {
    Block yellowZoneBlock;
    int yellowZoneMeta;
    Block redZoneBlock;
    int redZoneBlockMeta;
    Block redZoneGlass;
    int redZoneGlassMeta;
    
    public WorldGenSpaceStation() {
        this.yellowZoneBlock  = MainLoader.Concrete;
        this.yellowZoneMeta   = 7;
        this.redZoneBlock     = MainLoader.Concrete;
        this.redZoneBlockMeta = 7;
        this.redZoneGlass     = MainLoader.IGlass;
        this.redZoneGlassMeta = 7;
    }
    
    public boolean generate(World w, Random r, int x, int y, int z) {
        this.set(w, x, y, z, this.yellowZoneBlock, this.yellowZoneMeta);
        this.yellowZone(w, x, y, z);
        this.redZone(w, x, y, z);
        return true;
    }
    
    private void set(World w, int x, int y, int z, Block b, int m) {
        w.setBlock(x, y, z, b, m, 3);
    }
    
    private void yellowZone(World w, int x, int y, int z) {
        int row = 5;
        
        int Y;
        int Z;
        for (Y = -row; Y <= row; ++Y) {
            for (Z = -row; Z <= row; ++Z) {
                if ((Y != 0 || Z != 0) && (Y != row && Y != -row || Z != row && Z != -row)) {
                    this.set(w, x + Y, y, z + Z, this.yellowZoneBlock, this.yellowZoneMeta);
                }
            }
        }
        
        for (Y = -row; Y <= row; ++Y) {
            for (Z = -row; Z <= row; ++Z) {
                if ((Y < -1 || Y > 1 || Z < -1 || Z > 1) && (Y != row && Y != -row || Z != row && Z != -row)) {
                    this.set(w, x + Y, y + 6, z + Z, this.yellowZoneBlock, this.yellowZoneMeta);
                }
            }
        }
        
        for (Y = 1; Y <= 6; ++Y) {
            this.set(w, x - 5, y + Y, z + 3, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 5, y + Y, z - 3, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 5, y + Y, z + 3, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 5, y + Y, z - 3, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 3, y + Y, z + 5, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 3, y + Y, z - 5, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 3, y + Y, z + 5, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 3, y + Y, z - 5, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 4, y + Y, z + 4, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 4, y + Y, z - 4, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 4, y + Y, z + 4, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 4, y + Y, z - 4, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 4, y + Y, z + 3, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 4, y + Y, z - 3, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 4, y + Y, z + 3, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 4, y + Y, z - 3, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 3, y + Y, z + 4, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x - 3, y + Y, z - 4, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 3, y + Y, z + 4, this.yellowZoneBlock, this.yellowZoneMeta);
            this.set(w, x + 3, y + Y, z - 4, this.yellowZoneBlock, this.yellowZoneMeta);
        }
        
        for (Y = 0; Y <= 6; ++Y) {
            if (Y == 0 || Y == 6) {
                this.set(w, x + 4, y + Y, z + 5, Blocks.air, 4);
                this.set(w, x + 4, y + Y, z - 5, Blocks.air, 4);
                this.set(w, x - 4, y + Y, z + 5, Blocks.air, 4);
                this.set(w, x - 4, y + Y, z - 5, Blocks.air, 4);
                this.set(w, x - 5, y + Y, z + 4, Blocks.air, 4);
                this.set(w, x - 5, y + Y, z - 4, Blocks.air, 4);
                this.set(w, x + 5, y + Y, z + 4, Blocks.air, 4);
                this.set(w, x + 5, y + Y, z - 4, Blocks.air, 4);
                this.set(w, x + 4, y + Y, z + 5, Blocks.air, 4);
                this.set(w, x + 4, y + Y, z - 5, Blocks.air, 4);
                this.set(w, x - 4, y + Y, z + 5, Blocks.air, 4);
                this.set(w, x - 4, y + Y, z - 5, Blocks.air, 4);
                this.set(w, x - 5, y + Y, z + 4, Blocks.air, 4);
                this.set(w, x - 5, y + Y, z - 4, Blocks.air, 4);
                this.set(w, x + 5, y + Y, z + 4, Blocks.air, 4);
                this.set(w, x + 5, y + Y, z - 4, Blocks.air, 4);
                this.set(w, x - 5, y + Y, z + 3, Blocks.air, 4);
                this.set(w, x - 5, y + Y, z - 3, Blocks.air, 4);
                this.set(w, x + 5, y + Y, z + 3, Blocks.air, 4);
                this.set(w, x + 5, y + Y, z - 3, Blocks.air, 4);
                this.set(w, x - 3, y + Y, z + 5, Blocks.air, 4);
                this.set(w, x - 3, y + Y, z - 5, Blocks.air, 4);
                this.set(w, x + 3, y + Y, z + 5, Blocks.air, 4);
                this.set(w, x + 3, y + Y, z - 5, Blocks.air, 4);
                this.set(w, x - 4, y + Y, z + 4, Blocks.air, 4);
                this.set(w, x - 4, y + Y, z - 4, Blocks.air, 4);
                this.set(w, x + 4, y + Y, z + 4, Blocks.air, 4);
                this.set(w, x + 4, y + Y, z - 4, Blocks.air, 4);
            }
        }
        
    }
    
    private void redZoneCorridor(World w, int x, int y, int z, boolean rotate, boolean minusCoords) {
        int O = 0;
        int X;
        int Z;
        if (rotate) {
            if (minusCoords) {
                for (X = 0; X <= 4; ++X) {
                    for (Z = -3; Z <= 3; ++Z) {
                        if (Z == -2 || Z == 2) {
                            this.set(w, x - X, y + 1, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                            this.set(w, x - X, y + 5, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                        }
                        
                        if (X != 0 && X != 4) {
                            if (Z >= -1 && Z <= 1) {
                                this.set(w, x - X, y + O, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                                this.set(w, x - X, y + 6, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                            }
                            
                            if (Z == -3 || Z == 3) {
                                this.set(w, x - X, y + 4, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                                this.set(w, x - X, y + 2, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                                this.set(w, x - X, y + 3, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                            }
                        } else {
                            if (Z >= -1 && Z <= 1) {
                                this.set(w, x - X, y + O, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                                this.set(w, x - X, y + 6, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                            }
                            
                            if (Z == -3 || Z == 3) {
                                this.set(w, x - X, y + 4, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                                this.set(w, x - X, y + 2, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                                this.set(w, x - X, y + 3, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                            }
                        }
                    }
                }
            } else {
                for (X = -4; X <= 0; ++X) {
                    for (Z = -3; Z <= 3; ++Z) {
                        if (Z == -2 || Z == 2) {
                            this.set(w, x - X, y + 1, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                            this.set(w, x - X, y + 5, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                        }
                        
                        if (X != 0 && X != -4) {
                            if (Z >= -1 && Z <= 1) {
                                this.set(w, x - X, y + O, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                                this.set(w, x - X, y + 6, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                            }
                            
                            if (Z == -3 || Z == 3) {
                                this.set(w, x - X, y + 4, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                                this.set(w, x - X, y + 2, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                                this.set(w, x - X, y + 3, z + Z, this.redZoneGlass, this.redZoneGlassMeta);
                            }
                        } else {
                            if (Z >= -1 && Z <= 1) {
                                this.set(w, x - X, y + O, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                                this.set(w, x - X, y + 6, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                            }
                            
                            if (Z == -3 || Z == 3) {
                                this.set(w, x - X, y + 4, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                                this.set(w, x - X, y + 2, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                                this.set(w, x - X, y + 3, z + Z, this.redZoneBlock, this.redZoneBlockMeta);
                            }
                        }
                    }
                }
            }
        } else if (minusCoords) {
            for (X = 0; X <= 4; ++X) {
                for (Z = -3; Z <= 3; ++Z) {
                    if (Z == -2 || Z == 2) {
                        this.set(w, x - Z, y + 1, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                        this.set(w, x - Z, y + 5, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                    }
                    
                    if (X != 0 && X != 4) {
                        if (Z >= -1 && Z <= 1) {
                            this.set(w, x - Z, y + O, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                            this.set(w, x - Z, y + 6, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                        }
                        
                        if (Z == -3 || Z == 3) {
                            this.set(w, x - Z, y + 4, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                            this.set(w, x - Z, y + 2, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                            this.set(w, x - Z, y + 3, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                        }
                    } else {
                        if (Z >= -1 && Z <= 1) {
                            this.set(w, x - Z, y + O, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                            this.set(w, x - Z, y + 6, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                        }
                        
                        if (Z == -3 || Z == 3) {
                            this.set(w, x - Z, y + 2, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                            this.set(w, x - Z, y + 3, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                            this.set(w, x - Z, y + 4, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                        }
                    }
                }
            }
        } else {
            for (X = -4; X <= 0; ++X) {
                for (Z = -3; Z <= 3; ++Z) {
                    if (Z == -2 || Z == 2) {
                        this.set(w, x - Z, y + 1, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                        this.set(w, x - Z, y + 5, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                    }
                    
                    if (X != 0 && X != -4) {
                        if (Z >= -1 && Z <= 1) {
                            this.set(w, x - Z, y + O, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                            this.set(w, x - Z, y + 6, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                        }
                        
                        if (Z == -3 || Z == 3) {
                            this.set(w, x - Z, y + 2, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                            this.set(w, x - Z, y + 3, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                            this.set(w, x - Z, y + 4, z + X, this.redZoneGlass, this.redZoneGlassMeta);
                        }
                    } else {
                        if (Z >= -1 && Z <= 1) {
                            this.set(w, x - Z, y + O, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                            this.set(w, x - Z, y + 6, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                        }
                        
                        if (Z == -3 || Z == 3) {
                            this.set(w, x - Z, y + 2, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                            this.set(w, x - Z, y + 3, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                            this.set(w, x - Z, y + 4, z + X, this.redZoneBlock, this.redZoneBlockMeta);
                        }
                    }
                }
            }
        }
        
    }
    
    private void redZone(World w, int x, int y, int z) {
        int startRedZone = 6;
        int corridorRange = 4;
        
        for (int rowCorridor = 0; rowCorridor <= 4; ++rowCorridor) {
            this.redZoneCorridor(w, x + startRedZone + corridorRange * rowCorridor, y, z, true, false);
            this.redZoneCorridor(w, x - startRedZone - corridorRange * rowCorridor, y, z, true, true);
            this.redZoneCorridor(w, x, y, z - startRedZone - corridorRange * rowCorridor, false, false);
            this.redZoneCorridor(w, x, y, z + startRedZone + corridorRange * rowCorridor, false, true);
        }
        
    }
}