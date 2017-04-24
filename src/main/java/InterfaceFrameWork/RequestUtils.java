package InterfaceFrameWork;

import Utils.ReportUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;


/**
 * 接口测试请求工具，包含post、get、delete、put请求方法
 */
public class RequestUtils {
 //声明httpClient自动重定向对象
private static CloseableHttpClient httpClient = HttpClients.custom()
        .disableAutomaticRetries()
        .setRedirectStrategy(new LaxRedirectStrategy())
        .build();
//申明报告对象
private static ReportUtils report = new ReportUtils();

    /**
     *
     * @param url 请求地址
     * @param lists 跟随url一起发送的参数  key=value结构的数据
     * @return     String类型的请求头--uri
     */
    public static String getUri(String url, List<NameValuePair>... lists) {
        if (lists.length != 0) {
            url = url + "?";
            //迭代器遍历
            Iterator<NameValuePair> iterator = lists[0].iterator();
            while (iterator.hasNext()) {
                //组装url，将key=value转换成key=value&key1=value1
                url = url + iterator.next().toString() + "&";
            }
        }
        return url;
    }

    /**
     * @param uriRequest 请求方式(HttpGet、HttpPost....)
     * @return  响应实体
     */
    private static String response(HttpUriRequest uriRequest) {
        String strResponse;
        try {
            report.log("开始发送请求"+uriRequest.getURI());
            //发送请求、接受响应
            CloseableHttpResponse response = httpClient.execute(uriRequest);
            //判断响应是否为空
            if (response != null) {
                //获取响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    //获取响应实体
                    HttpEntity entity = response.getEntity();
                    if (entity.getContentLength() != 0) {
                        //将实体转换成字符串串类型返回
                        strResponse = EntityUtils.toString(entity, "utf-8");
                        return strResponse;
                    } else {
                       report.warn("响应实体为空");
                        return null;
                    }
                } else {
                    report.error("响应错误，响应状态码为：" + statusCode);
                }
            } else {
                report.error("请求失败！");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送get请求获取响应实体
     * @param url  请求地址 ---uri
     * @param lists 请求参数
     * @return      响应实体
     */
    public static String get(String url,List<NameValuePair>... lists) {
        String uri = getUri(url, lists);
        HttpGet get = new HttpGet(uri);
        return response(get);
    }

    /**
     * 发送delete请求，获取响应实体
     * @param url
     * @param lists
     * @return
     */
    public static String delete(String url,List<NameValuePair>... lists) {
        String uri = getUri(url, lists);
        HttpDelete delete = new HttpDelete(uri);
        return response(delete);
    }

    /**
     *
     * @param url
     * @param lists
     * @return
     */
    public static String post(String url,List<NameValuePair>... lists) {
        HttpPost post = new HttpPost(url);
        if (lists.length != 0) {
            try {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(lists[0]);
                post.setEntity(formEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return response(post);
    }

    /**
     * post请求方式
     * @param url       请求地址
     * @param jsonObject json数据参数
     * @return
     */
    public static String post(String url,JSONObject jsonObject) {
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        entity.setContentType("*/*");
        entity.setContentEncoding("utf-8");
        post.setEntity(entity);
        return response(post);
    }

    /**
     * post请求方式 上传文件
     * @param url       请求地址
     * @param fileName  文件名称参数
     * @param filePath  文件路径+文件名
     * @return
     */
    public static String post(String url,String fileName,String filePath) {
        File file = new File(filePath);
        HttpPost post = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        HttpEntity entity = builder.addBinaryBody(fileName, file).build();
        post.setEntity(entity);
        return response(post);
    }

    public static String put(String url,String filePath) {
        File file = new File(filePath);
        HttpPut put = new HttpPut(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        HttpEntity entity = builder.addBinaryBody("file", file).build();
        put.setEntity(entity);
        return response(put);
    }

    public static String put(String url,List<NameValuePair>... lists) {
        HttpPut put = new HttpPut(url);
        if (lists.length != 0) {
            try {
                UrlEncodedFormEntity formEntity;
                formEntity = new UrlEncodedFormEntity(lists[0]);
                put.setEntity(formEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return response(put);
    }

    public static String put(String url,JSONObject jsonObject) {
        HttpPut put = new HttpPut(url);
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        entity.setContentType(".*/*.");
        put.setEntity(entity);
        return response(put);
    }

}
