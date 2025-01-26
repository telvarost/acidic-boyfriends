package net.natsupotato.acidboy;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class Config {
    public static class SpawnConfig {
        
        @ConfigEntry(name = "Spawning Enabled")
        public Boolean spawningEnabled = true;
        
        @ConfigEntry(name = "Spawn Rarity", minLength = 1, maxLength = 100, comment = "Larger Value = Less Rare")
        public Integer spawnRarity = 8;
    }
}
