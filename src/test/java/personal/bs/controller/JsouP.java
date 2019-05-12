package personal.bs.controller;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenjingang@gauzi.com  2019/5/11 22:05
 */
public class JsouP {

    public static Map<String, String> headers = new HashMap<>();

    static {
        headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.put("Accept-Encoding", " gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Cache-Control", "no-cache");
        headers.put("Connection", "keep-alive");
        // TODO 需要设置
        headers.put("Cookie", "TYCID=459b7760671911e88a962752a04d49e3; undefined=459b7760671911e88a962752a04d49e3;" +
                "ssuid=8800877279; aliyungf_tc=AQAAAJaEZlt/LgoAlAFM2q76k16Acws9; " +
                "csrfToken=ebZ222cIdpZBqAQLLefohzTT; _ga=GA1.2.1043693661.1541656659; " +
                "_gid=GA1.2.248580359.1541656659; bannerFlag=true; " +
                "RTYCID=d60f1634a5e44461887b801ec5347579; CT_TYCID=0e2bded5cce44b44a8bef56c29a204a0; " +
                "cloud_token=a2cb19178fca467285af880f0aaf1d25; token=70a1ce32f1a84d3395364a9858a0e47f; " +
                "_utm=8dc98349cf3f4b6293ecee4786167b04; tyc-user-info=%257B%2522myQuestionCount%2522%253A%25220%2522%25" +
                "2C%2522integrity%2522%253A%25220%2525%2522%252C%2522state%2522%253A%25220%2522%252C%2522vipManager%2522%2" +
                "53A%25220%2522%252C%2522onum%2522%253A%25220%2522%252C%2522monitorUnreadCount%2522%253A%25220%2522%252C%2522d" +
                "iscussCommendCount%2522%253A%25220%2522%252C%2522new%2522%253A%25221%2522%252C%2522token%2522%253A%2522eyJhbGciOi" +
                "JIUzUxMiJ9.eyJzdWIiOiIxNTI3NDk2MzgyNiIsImlhdCI6MTU0MTY2NzA1NSwiZXhwIjoxNTU3MjE5MDU1fQ.U-vu1TOdC_0ThSu6ZCgGusoRz_ft6F" +
                "Y0gbzdMvX7k4J0RWl68hKhSDud-PRIcHYof0_TqgHZWAMFeU1IN632Yg%2522%252C%2522redPoint%2522%253A%25220%2522%252C%2522pleaseAns" +
                "werCount%2522%253A%25220%2522%252C%2522vnum%2522%253A%25220%2522%252C%2522bizCardUnread%2522%253A%25220%2522%252C%2522mobi" +
                "le%2522%253A%252215274963826%2522%257D; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTI3NDk2MzgyNiIsImlhdCI6MTU0MTY2NzA1NSwiZ" +
                "XhwIjoxNTU3MjE5MDU1fQ.U-vu1TOdC_0ThSu6ZCgGusoRz_ft6FY0gbzdMvX7k4J0RWl68hKhSDud-PRIcHYof0_TqgHZWAMFeU1IN632Yg; Hm_lvt_e92c8d65d" +
                "92d534b0fc290df538b4758=1541656659,1541659805,1541667039; _gat_gtag_UA_123487620_1=1; " +
                "Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1541667910");
        headers.put("DNT", "1");
        // TODO 需要设置
        headers.put("Host", "www.tianyancha.com");
        headers.put("Pragma", "no-cache");
        // TODO 需要设置
        headers.put("Referer", "https://www.tianyancha.com/search?key=%E8%85%BE%E8%AE%AF");
        // TODO 需要设置
        headers.put("X-AUTH-TOKEN", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTI3NDk2MzgyNiIsImlhdCI6MTU0MTY2NzA1NSwiZXhwIjoxN" +
                "TU3MjE5MDU1fQ.U-vu1TOdC_0ThSu6ZCgGusoRz_ft6FY0gbzdMvX7k4J0RWl68hKhSDud-PRIcHYof0_TqgHZWAMFeU1IN632Yg");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                " Chrome/70.0.3538.77 Safari/537.36");
    }

    public static String url = "https://www.tianyancha.com/search?key=%E8%85%BE%E8%AE%AF";

//    public void https() {
//        RequestBuilder requestBuilder = RequestBuilder.get(url);
//        for (Map.Entry<String, String> entries : headers.entrySet()) {
//            requestBuilder.setHeader(entries.getKey(), entries.getValue());
//        }
//        ThreeTuple<Integer, String, String> postResult = HttpClientProxyManager.getHttpClientProxy().executeStringResult(requestBuilder);
//        Document document = Jsoup.parse(postResult.getThird());
//    }

    public void http() {
        try {
            Document document = Jsoup.connect("http://www.baidu.com").get();
            System.out.println(document.title());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getProductInfo(String url) throws Exception {


        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        // 模拟浏览器浏览
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:60.0) Gecko/20100101 Firefox/60.0");
        CloseableHttpResponse response = httpclient.execute(httpGet);

        // 结果集合
        Map<String, String> reslut = null;

        //获取响应状态码
        int StatusCode = response.getStatusLine().getStatusCode();

        try {

            HttpEntity entity = response.getEntity();

            //如果状态响应码为200，则获取html实体内容或者json文件
            if(StatusCode == 200){
                String html = EntityUtils.toString(entity, Consts.UTF_8);

                // 提取HTML得到商品信息结果
                reslut = getData(html);

                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            }else {
                //否则，消耗掉实体
                EntityUtils.consume(response.getEntity());
            }
        } finally {
            response.close();
        }

        return reslut;
    }

    private static Map<String, String> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        Map<String, String> data = new HashMap<>();

        //采用Jsoup解析
        Document doc = Jsoup.parse(html);

        //获取html标签中的内容
        // 标题
        String name = doc.select("strong[class=J_34408290470]").select("i").text();
        if (name != null) {
            data.put("price", name);
        }
//
//        // sku
//        String sku = "";
//        Elements elements = doc.select("a[data-sku]");
//        for (Element ele: elements) {
//            if (ele.hasAttr("data-sku")) {
//                sku = ele.attr("data-sku");
//                break;
//            }
//        }
//
//        if (sku != null) {
//            data.put("sku", sku);
//        }
//
//        String cat = doc.select("a[clstag=shangpin|keycount|product|mbNav-1]").text();
//
//        if (cat != null) {
//            data.put("cat", cat);
//        }

        //System.out.print(sku + "---------" + cat + "---------" + name);

        //返回数据
        return data;
    }


    @Test
    public void proxy() throws Exception{
        //设置代理IP、端口、协议（请分别替换）
        HttpHost proxy = new HttpHost("127.0.0.1", 8080, "http");

        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .build();

        //实例化CloseableHttpClient对象
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

        //访问目标地址
        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        //请求返回
        CloseableHttpResponse httpResp = httpclient.execute(httpGet);
        try {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String html = EntityUtils.toString(httpResp.getEntity(), Consts.UTF_8);
                System.out.println("成功");
                System.out.println(html);
            }
        } catch (Exception e) {

        } finally {
            httpResp.close();
        }
    }

    @Test
    public void test() throws Exception{

        Map<String, String> productInfo = getProductInfo("https://search.jd.com/Search?keyword=%E7%B2%97%E5%B8%83&enc=utf-8&wq=%E7%B2%97%E5%B8%83&pvid=881627cbdf4549689af4e147527c2dbe");

        System.out.println(productInfo.get("price"));
    }
}
