package InterfaceTest;

import Interface.Login;
import Interface.login;
import InterfaceFrameWork.RequestUtils;
import Utils.ExcelIterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by YZQ on 2017-04-24.
 */
public class TestLogin {
    @DataProvider(name = "login")
    public Iterator<Object[]>getdata() throws IOException {
        return new ExcelIterator("tinyshop\\tinyshop_test","获取token_login");
    }
    @Test(dataProvider = "login")
    public  void login(Map<String,String>map){
        String response= Login(map.get("请求路径"));
    }
}
