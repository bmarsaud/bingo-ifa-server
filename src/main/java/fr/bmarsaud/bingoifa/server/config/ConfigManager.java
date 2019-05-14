package fr.bmarsaud.bingoifa.server.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import fr.bmarsaud.bingoifa.server.BingoIFAServer;

public class ConfigManager {
    private static final Path DEFAULT_CONFIG_DIR = Paths.get("config");
    private static Path BASE_DIR;
    private Path configDirectory;

    public ConfigManager(String configDirectory) {
        this.configDirectory = ConfigManager.getBaseDir().resolve(configDirectory);
    }

    public ConfigManager() {
        this.configDirectory = ConfigManager.getBaseDir().resolve(ConfigManager.DEFAULT_CONFIG_DIR);
    }

    public Properties getProperties(String propertiesName) {
        Properties properties = new Properties();

        try {
            File userConfigFile = this.configDirectory.resolve(propertiesName + ".properties").toFile();
            if(userConfigFile.exists()) {
                properties.load(new FileInputStream(userConfigFile));
            } else {
                properties.load(BingoIFAServer.class.getResourceAsStream("/" + propertiesName + ".properties"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    private static Path getBaseDir() {
        if(BASE_DIR == null) ConfigManager.BASE_DIR = Paths.get(BingoIFAServer.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
        return ConfigManager.BASE_DIR;
    }
}
