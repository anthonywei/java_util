
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private final Properties props;

    public PropertyReader(String propFile) {
        this.props = new Properties();
        try {
            InputStream is = PropertyReader.class.getClassLoader()
                    .getResourceAsStream(propFile);
            props.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PropertyReader(Properties props) {
        this.props = props;
    }

    public int getValueAsInt(String key, int defaultValue) {
        int value = defaultValue;
        String v = props.getProperty(key);
        if (v != null) {
            try {
                value = Integer.parseInt(v.trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public boolean getValueAsBool(String key, boolean defaultValue) {
        boolean value = defaultValue;
        String v = props.getProperty(key);
        if (v != null) {
            try {
                value = Boolean.parseBoolean(v.trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public String getValueAsString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public long getValueAsLong(String key, long defaultValue) {
        long value = defaultValue;
        String v = props.getProperty(key);
        if (v != null) {
            try {
                value = Long.parseLong(v.trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public int getValueAsInt(String key) {
        return getValueAsInt(key, 0);
    }

    public boolean getValueAsBool(String key) {
        return getValueAsBool(key, false);
    }

    public String getValueAsString(String key) {
        return props.getProperty(key);
    }

    public long getValueAsLong(String key) {
        return getValueAsLong(key, 0L);
    }

}
