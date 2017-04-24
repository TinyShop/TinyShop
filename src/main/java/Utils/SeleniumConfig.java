package Utils;

import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017-04-23.
 */
public class SeleniumConfig {
    private static ResourceBundle rb = ResourceBundle.getBundle("selenium");
    public static final int DRIVER_TYPE = Integer.valueOf(rb.getString("driver.type"));
    public static final String CHROME = rb.getString("chrome");
    public static final String FIREFOX = rb.getString("Firefox");
}
