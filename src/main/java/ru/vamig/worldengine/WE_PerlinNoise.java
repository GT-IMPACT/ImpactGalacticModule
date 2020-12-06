//- By Vamig Aliev.
//- https://vk.com/win_vista.

package ru.vamig.worldengine;

public class WE_PerlinNoise {
    public static double NumberNoise2D(long seed, long x, long y) {
        long n = x + y * 31L + seed * 6L;
        n = n << 13L ^ n;
        return 1.0D - (n * (15732L * n ^ 0xC0B4FL) + 1376312589L & 0x7FFFFFFFL) / 1.073741824E9D;
    }


    public static double SmoothNoise2D(long seed, long x, long y) {
        double corners = (NumberNoise2D(seed, x - 1L, y - 1L) + NumberNoise2D(seed, x + 1L, y - 1L) + NumberNoise2D(seed, x - 1L, y + 1L) + NumberNoise2D(seed, x + 1L, y + 1L)) / 16.0D;
        double sides = (NumberNoise2D(seed, x - 1L, y) + NumberNoise2D(seed, x + 1L, y) + NumberNoise2D(seed, x, y - 1L) + NumberNoise2D(seed, x, y + 1L)) / 8.0D;
        double center = NumberNoise2D(seed, x, y) / 4.0D;
        return corners + sides + center;
    }

    public static double CosineInterpolate(double a, double b, double x) {
        double f = (1.0D - Math.cos(x * Math.PI)) * 0.5D;
        return a * (1.0D - f) + b * f;
    }


    public static double CosineInterpolatedNoise2D(long seed, double x, double y) {
        long lx = (long) x;
        long ly = (long) y;

        double fx = Math.abs(x) - Math.abs(lx);
        double fy = Math.abs(y) - Math.abs(ly);


        double v1 = SmoothNoise2D(seed, lx, ly);
        double v2 = SmoothNoise2D(seed, (Math.abs(x) == x) ? (lx + 1L) : (lx - 1L), ly);
        double v3 = SmoothNoise2D(seed, lx, (Math.abs(y) == y) ? (ly + 1L) : (ly - 1L));
        double v4 = SmoothNoise2D(seed, (Math.abs(x) == x) ? (lx + 1L) : (lx - 1L), (Math.abs(y) == y) ? (ly + 1L) : (ly - 1L));


        double i1 = CosineInterpolate(v1, v2, fx);
        double i2 = CosineInterpolate(v3, v4, fx);
        return CosineInterpolate(i1, i2, fy);
    }

    public static double PerlinNoise2D(long seed, double x, double y, double persistence, int number_of_octaves) {
        double total = 0.0D;
        for (int i = 0; i <= number_of_octaves; i++) {

            double frequency = (1 << i);
            double amplitude = Math.pow(persistence, i);
            total += CosineInterpolatedNoise2D(seed, x * frequency, y * frequency) * amplitude;
        }
        return total;
    }
}