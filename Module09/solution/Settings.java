package solution;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.UIManager;


public final class Settings {
    private static Settings instance;
    public final String CAPTION;
    public final String FONT;
    public final int FONT_SIZE;
    public final String NUMBERS;
    public final String CLEAR_BUTTON;
    public final String EQUAL_BUTTON;

    private Settings() {
        Properties properties = loadProperties();
        CAPTION = properties.getProperty("frame_caption");
        FONT = properties.getProperty("font");
        FONT_SIZE = Integer.parseInt(properties.getProperty("font_size"));
        NUMBERS = properties.getProperty("calc_numbers");
        CLEAR_BUTTON = properties.getProperty("calc_clear");
        EQUAL_BUTTON = properties.getProperty("calc_equals");
        setUIManager();
    }

    private static void setUIManager() {
        // this sets it to match the operating system (sort of - light mode only, no dark mode
        // unless using a third party library)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream fis = Settings.class.getResourceAsStream("/config.xml");
            properties.loadFromXML(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

}
