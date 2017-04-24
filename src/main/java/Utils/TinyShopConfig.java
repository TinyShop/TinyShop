package Utils;

import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017-04-24.
 */
public class TinyShopConfig {
    private static ResourceBundle rb = ResourceBundle.getBundle("tinyshop");
    public static final String HOST = rb.getString("host");
}
