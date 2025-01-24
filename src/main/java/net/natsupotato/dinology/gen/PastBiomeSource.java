package net.natsupotato.dinology.gen;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.natsupotato.dinology.Dinology;

import java.util.Arrays;

public class PastBiomeSource extends BiomeSource {

    @Override
    public Biome getBiome(ChunkPos arg) {
        return super.getBiome(arg);
    }

    @Override
    public Biome getBiome(int x, int z) {
        return Dinology.PAST_BIOME;
    }

    @Override
    public double getTemperature(int x, int z) {
        return 1;
    }

    @Override
    public double[] create(double[] temperatures, int x, int z, int xSize, int zSize) {
        if (temperatures == null || temperatures.length < xSize * zSize)
            temperatures = new double[xSize * zSize];
        Arrays.fill(temperatures, 0, xSize * zSize, 1.0);
        return temperatures;
    }

    @Override
    public Biome[] getBiomesInArea(Biome[] biomes, int x, int z, int xSize, int zSize) {
        if (biomes == null || biomes.length < xSize * zSize)
            biomes = new Biome[xSize * zSize];
        if (temperatureMap == null || temperatureMap.length < xSize * zSize) {
            temperatureMap = new double[xSize * zSize];
            downfallMap = new double[xSize * zSize];
        }
        Arrays.fill(biomes, 0, xSize * zSize, Dinology.PAST_BIOME);
        return biomes;
    }
}
