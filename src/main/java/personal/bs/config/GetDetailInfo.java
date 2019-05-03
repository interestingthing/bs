package personal.bs.config;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenjingang@gauzi.com  2019/4/19 18:27
 */
public class GetDetailInfo {

    /**
     * "detailInfo": {
     * "poiId": 165669187,
     * "name": "串一火锅（五味什字店）",
     * "avgScore": 4.3,
     * "address": "碑林区南广济街79号（五味十字和粉巷西口交叉口向北100米路东）",
     * "phone": "029-87384097",
     * "openTime": "周一至周日 10:30-00:00",
     * "extraInfos": [{
     * "iconUrl": "http://p0.meituan.net/codeman/551290739062eda37e52999e2315f50c1887.png",
     * "text": "提供wifi"
     * }],
     * "hasFoodSafeInfo": true,
     * "longitude": 108.939279,
     * "latitude": 34.256869,
     * "avgPrice": 97,
     * "brandId": 0,
     * "brandName": "",
     * "showStatus": 2,
     * "isMeishi": true
     * }
     */

    public static void main(String[] args) {
        HashMap<String, String> param = new HashMap<>();
        param.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
        param.put("Remote Address", "113.200.17.241:443");
        param.put("Referrer Policy", "no-referrer-when-downgrade");
        param.put("uuid", "1555668847.2.0.0.1555668847.2.0.0");
        String s = doGet("http://www.meituan.com/meishi/165669187", param);
        String ss = doGet("http://xa.meituan.com/meishi/c17/", param);
        System.out.println(ss);
//
//        script src = "https://s0.meituan.net/mx/rohr/rohr.min.js" ></script >
//        <script src = "//s0.meituan.net/bs/knb/v1.3.19/knb.js" ></script >
//        <script src = "//map.qq.com/api/js?v=2.2exp&amp;key=B7FBZ-T2T3J-U2LF6-FGU4L-SUOCQ-WZFJ5" ></script >
//        <script ></script >
//        <script src = "//s0.meituan.net/bs/js?f=meis/meishi.web:page/common.js@331a8a1" ></script >
//        <script > window.appConfig = {"buildservice":{
//            "repo":"meis/meishi.web", "host":"s0.meituan.net/bs", "hash":"331a8a1", "folder":"static"
//        },"isDebug":false, "isOnline":true}</script >
//        <script > window._appState = {"$meta":{
//            "knbJS":"//s0.meituan.net/bs/knb/v1.3.19/knb.js", "adunionJS":
//            "//h5.dianping.com/app/adu-track/adunion-track.js", "uuid":"7891828113d84a22b7bb.1555668847.2.0.0", "userId":
//            "", "cityId":"42", "cityName":"西安", "userName":"", "pageId":"c_meishi_qcuimw0m", "valLab":
//            "{\"poi_id\":\"165669187\"}", "title":"串一火锅（五味什字店）_电话地址_营业时间-西安美团网"
//        },"pageId":"c_meishi_qcuimw0m", "valLab":{
//            "poi_id":"165669187"
//        },"loadQQMapScript":true, "title":"串一火锅（五味什字店）_电话地址_营业时间-西安美团网", "description":
//        "西安美团网提供2019最新串一火锅（五味什字店）电话,营业时间,地址,以及串一火锅（五味什字店）人均价格,优惠菜单,招牌菜怎么样.查看串一火锅（五味什字店）团购信息,了解店铺环境/服务怎么样.", "keyword":
//        "串一火锅（五味什字店）,电话,地址,营业时间",
//                "detailInfo":{
//            "poiId":165669187, "name":"串一火锅（五味什字店）", "avgScore":4.3, "address":
//            "碑林区南广济街79号（五味十字和粉巷西口交叉口向北100米路东）", "phone":"029-87384097", "openTime":"周一至周日 10:30-00:00", "extraInfos":[{
//                "iconUrl":"http://p0.meituan.net/codeman/551290739062eda37e52999e2315f50c1887.png", "text":"提供wifi"
//            }],"hasFoodSafeInfo":true, "longitude":108.939279, "latitude":34.256869, "avgPrice":97, "brandId":
//            0, "brandName":"", "showStatus":2, "isMeishi":true
//        },"photos":{
//            "frontImgUrl":
//            "https://img.meituan.net/msmerchant/9deae42899a689d2fd9cd47a79216efe585431.jpg@600w_600h_1l", "albumImgUrls":[
//            "https://img.meituan.net/msmerchant/08ff020fdabbb1fc3b08e80eac2ee9af106141.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/506216a0eadf79adfd17a779e5e279f069516.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/b1ddc0e55df316b60c93390520c5ae59191902.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/2014a84d25519007f6db80108069882f154834.jpg@600w_600h_1l", "http://p0.meituan.net/mogu/7beb62736a21f874274e1b6df76cd153242918.png@600w_600h_1l", "https://img.meituan.net/msmerchant/dcb2fbf628aba399c99cfa1182cbb23d113133.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/a63b5a4ebe60947763afca19bb88555353487.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/e78edc59f9be1bdc47372bb970366857163711.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/8e09a4184f8ceccafa1e076776b7ed2a763444.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/f6b15d98e7542f83852be2e83eabc7f4128404.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/879a1f8c33d5b486c46da46f1ea0c7ee135038.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/ba4b6ac1773eafe189d5b0adacb24ee0381407.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/02d0820b9f371f64bc804c02543ce05b523663.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/f734dc8b18c41aefc0ed5444380c7162920160.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/4973a549ce6365a67e3222a4995a9784402510.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/70a48220c88fde1ccced475e5a338cfd2088610.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/141b205c15df900c92637500d0fbd1421211818.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/1fff632aa57be892ebc41e22b6f7c0a4617645.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/9deae42899a689d2fd9cd47a79216efe585431.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/d74cc7883887e8608d63756524903e381752208.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/a86826a87cb802ee2b1ebb68aac286c74059407.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/1e86c56e4a087e38492c05511ba54539612130.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/562a015fea3bd829fdb7a7ce3d8477037675234.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/758c502c9967718c5e5abc3156dddda7316051.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/75dfb1bd5a67086f01d5c4bbb41ddc19853386.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/8fc1c2fd9b1b5b312f724feb87bc94883822324.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/562a015fea3bd829fdb7a7ce3d8477037675234.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/565090b19e4c62e53088e1124925f894656682.jpg@600w_600h_1l", "https://img.meituan.net/msmerchant/16c5363278ac06a0260e23b2667d3dc41070422.jpg@600w_600h_1l"]},
//        "recommended":[{
//            "id":"144670349", "name":"冰粉", "price":9, "frontImgUrl":
//            "http://p1.meituan.net/bbia/f286c132f2b01aeb050d63ba60376b183300459.jpg@0_0_4535_2551a@600w_600h_1l"
//        },{
//            "id":"141190449", "name":"孜然牛肉", "price":0.8, "frontImgUrl":
//            "http://p1.meituan.net/bbia/fd09da073af4613bde49b56cd9930ad03170743.jpg@0_104_5760_3240a@600w_600h_1l"
//        },{
//            "id":"141190448", "name":"玫瑰牛肉", "price":0.8, "frontImgUrl":
//            "http://p0.meituan.net/bbia/0b3b4daae3ba7adb9aa68d11d094f7832838840.jpg@0_0_5760_3240a@600w_600h_1l"
//        },{
//            "id":"141497802", "name":"黑胡椒牛肉", "price":0.8, "frontImgUrl":
//            "http://p0.meituan.net/bbia/cede25ba9935bf3e6d6fd212219219843122092.jpg@0_0_5760_3240a@600w_600h_1l"
//        },{
//            "id":"202417299", "name":"奶茶冰粉", "price":28, "frontImgUrl":
//            "http://p0.meituan.net/bbia/0b2fe152bc11e3932adca7d56b6d06ca3470052.jpg@0_0_4535_2551a@600w_600h_1l"
//        },{
//            "id":"143426152", "name":"菠萝牛肉串串", "price":0.8, "frontImgUrl":
//            "http://p0.meituan.net/bbia/6de1bc5e1b9bb996e606a30e991ebc073245632.jpg@0_0_4535_2551a@600w_600h_1l"
//        },{
//            "id":"143424477", "name":"虫花草牛肉串串", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"141073329", "name":"茴香牛肉", "price":2, "frontImgUrl":""
//        },{
//            "id":"143426031", "name":"咖喱牛肉串串", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"143423139", "name":"玫瑰牛肉串串", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"143144323", "name":"菠萝牛肉", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"144494875", "name":"蟹柳", "price":0, "frontImgUrl":""
//        },{
//            "id":"166621923", "name":"薄荷牛肉串串", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"152901761", "name":"料碗", "price":6, "frontImgUrl":""
//        },{
//            "id":"141497663", "name":"精品肥牛", "price":38, "frontImgUrl":""
//        },{
//            "id":"199689524", "name":"牛油番茄锅", "price":48, "frontImgUrl":""
//        },{
//            "id":"147787297", "name":"香菜牛肉", "price":2, "frontImgUrl":""
//        },{
//            "id":"144670348", "name":"杏仁豆腐", "price":16, "frontImgUrl":""
//        },{
//            "id":"166833609", "name":"莲藕牛肉", "price":0, "frontImgUrl":""
//        },{
//            "id":"141497610", "name":"玉米笋", "price":2, "frontImgUrl":""
//        },{
//            "id":"143144209", "name":"虫花草牛肉", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"143144204", "name":"咖喱牛肉", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"144670354", "name":"迷你莲藕", "price":0, "frontImgUrl":""
//        },{
//            "id":"143142537", "name":"串一茶", "price":16, "frontImgUrl":""
//        },{
//            "id":"143423514", "name":"黑胡椒牛肉串串", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"143033137", "name":"孜然牛肉串串", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"141497617", "name":"贡菜", "price":9, "frontImgUrl":""
//        },{
//            "id":"144670352", "name":"毛肚", "price":0, "frontImgUrl":""
//        },{
//            "id":"172460427", "name":"竹荪", "price":0, "frontImgUrl":""
//        },{
//            "id":"220833820", "name":"鲜鸭血", "price":9, "frontImgUrl":""
//        },{
//            "id":"188688807", "name":"小郡肝", "price":0.8, "frontImgUrl":""
//        },{
//            "id":"158426612", "name":"茴香小油条", "price":2, "frontImgUrl":""
//        },{
//            "id":"152901764", "name":"奶盖茶", "price":0, "frontImgUrl":""
//        },{
//            "id":"172460434", "name":"扇贝肉", "price":0, "frontImgUrl":""
//        },{
//            "id":"144670351", "name":"千层肚", "price":0, "frontImgUrl":""
//        },{
//            "id":"172460429", "name":"杏皮茶", "price":6, "frontImgUrl":""
//        },{
//            "id":"158401561", "name":"现炸小酥肉", "price":19, "frontImgUrl":""
//        },{
//            "id":"144639034", "name":"鸡脆骨", "price":0, "frontImgUrl":""
//        },{
//            "id":"147787274", "name":"秋葵", "price":2, "frontImgUrl":""
//        },{
//            "id":"144466445", "name":"大亨果汁", "price":8, "frontImgUrl":""
//        },{
//            "id":"141497639", "name":"特供午餐肉", "price":29, "frontImgUrl":""
//        },{
//            "id":"172630175", "name":"网红虾滑鸡蛋塞面筋", "price":38, "frontImgUrl":""
//        },{
//            "id":"172630252", "name":"鸭舌", "price":0, "frontImgUrl":""
//        },{
//            "id":"222436897", "name":"银耳萝卜", "price":0, "frontImgUrl":""
//        },{
//            "id":"143143266", "name":"饭后甜品", "price":10, "frontImgUrl":""
//        },{
//            "id":"141497654", "name":"竹笋尖", "price":9, "frontImgUrl":""
//        },{
//            "id":"222436898", "name":"番茄麻辣锅底", "price":0, "frontImgUrl":""
//        },{
//            "id":"172461147", "name":"DIY照片奶茶", "price":0, "frontImgUrl":""
//        },{
//            "id":"204961222", "name":"香肠", "price":0, "frontImgUrl":""
//        },{
//            "id":"172757411", "name":"麻辣牛肉", "price":0, "frontImgUrl":""
//        }],"crumbNav":[{
//            "title":"西安美团", "url":"http://xa.meituan.com/"
//        },{
//            "title":"西安美食", "url":"http://xa.meituan.com/meishi/"
//        },{
//            "title":"西安火锅", "url":"http://xa.meituan.com/meishi/c17/"
//        }],"prefer":[{
//            "itemId":"104632747", "title":"清水湾·乐汤汇", "imgUrl":
//            "https://p1.meituan.net/w.h/merchantpic/51cb019ae234d7ac65594569edd6acac5371528.jpg", "score":
//            "4.3", "consumeNum":null, "areaName":"明光路", "lowPrice":"85.0", "saleNum":null, "commentNum":
//            10398, "detailUrl":"", "firstCate":[2, 3, 20611, 20426],"avgPrice":157
//        },{
//            "itemId":"4071209", "title":"火焰山自助美食汇（盛龙广场店）", "imgUrl":
//            "http://p0.meituan.net/w.h/mogu/5f6656230816105e9e3dc1c8f283799c24504.jpg", "score":"3.2", "consumeNum":
//            null, "areaName":"未央路", "lowPrice":"50.0", "saleNum":null, "commentNum":12292, "detailUrl":"", "firstCate":[
//            2, 20632, 20426, 20557],"avgPrice":54
//        },{
//            "itemId":"1459968", "title":"比格比萨自助（吉祥村店）", "imgUrl":
//            "https://img.meituan.net/w.h/msmerchant/257f543c47f05191f3d359c19d4c9cfe53931.jpg", "score":
//            "5.0", "consumeNum":null, "areaName":"吉祥村", "lowPrice":"56.0", "saleNum":null, "commentNum":
//            16931, "detailUrl":"", "firstCate":[2, 20632, 20426, 20557],"avgPrice":54
//        },{
//            "itemId":"159138154", "title":"五叔家串串老火锅 · 冰煮羊（长安万科（总店））", "imgUrl":
//            "https://img.meituan.net/w.h/msmerchant/551aeca57f30110bef411ef664c6ac972727417.jpg", "score":
//            "5.0", "consumeNum":null, "areaName":"南大学城", "lowPrice":"69.9", "saleNum":null, "commentNum":
//            1650, "detailUrl":"", "firstCate":[2, 20632, 20426],"avgPrice":55
//        },{
//            "itemId":"95017147", "title":"黄河温泉游泳馆", "imgUrl":
//            "http://p1.meituan.net/w.h/dpmerchantalbum/74b10aec8451cb69b27ce6e5f308125e339781.jpg", "score":
//            "3.3", "consumeNum":null, "areaName":"", "lowPrice":"35.0", "saleNum":null, "commentNum":439, "detailUrl":
//            "", "firstCate":[2, 20611, 20632, 20873, 20426, 20252],"avgPrice":37
//        },{
//            "itemId":"4217424", "title":"金迈源自助涮烤王", "imgUrl":
//            "http://p0.meituan.net/w.h/deal/4e561c48e9bcfeb3b73d88a112e34852382117.jpg", "score":"4.6", "consumeNum":
//            null, "areaName":"建工路", "lowPrice":"49.0", "saleNum":null, "commentNum":12284, "detailUrl":"", "firstCate":[
//            2, 20632, 20426, 20557],"avgPrice":50
//        },{
//            "itemId":"163000095", "title":"嗨捞猪肚鸡（高新万达广场店）", "imgUrl":
//            "https://img.meituan.net/w.h/msmerchant/b56ed883cc09bd152840844c9dfe8cf1305267.jpg", "score":
//            "5.0", "consumeNum":null, "areaName":"唐延路南段", "lowPrice":"89.0", "saleNum":null, "commentNum":
//            227, "detailUrl":"", "firstCate":[2, 20632, 20426],"avgPrice":71
//        },{
//            "itemId":"159623451", "title":"爱辣屋（南稍门中贸店）", "imgUrl":
//            "https://img.meituan.net/w.h/msmerchant/23467263530292fce468a7302fc9b65558452.jpg", "score":
//            "3.9", "consumeNum":null, "areaName":"南稍门", "lowPrice":"28.0", "saleNum":null, "commentNum":
//            7031, "detailUrl":"", "firstCate":[2, 20632, 20426, 20557],"avgPrice":29
//        },{
//            "itemId":"100962453", "title":"重庆渝味晓宇火锅（曲江店）", "imgUrl":
//            "https://img.meituan.net/w.h/msmerchant/774ed3b9855f6491ea3e6117d32511b2168941.jpg", "score":
//            "3.6", "consumeNum":null, "areaName":"大雁塔", "lowPrice":"88.0", "saleNum":null, "commentNum":
//            2379, "detailUrl":"", "firstCate":[2, 20632, 20426, 20557],"avgPrice":84
//        },{
//            "itemId":"2369333", "title":"锦唐印象（纺织城店）", "imgUrl":
//            "http://p0.meituan.net/w.h/ugcpic/bd84d245817fb57dc1752ed5f5709d29", "score":"5.0", "consumeNum":
//            null, "areaName":"纺织城", "lowPrice":"69.0", "saleNum":null, "commentNum":15324, "detailUrl":"", "firstCate":[
//            2, 20632, 20426],"avgPrice":58
//        },{
//            "itemId":"6308961", "title":"赛百味（金莎店）", "imgUrl":
//            "http://p1.meituan.net/w.h/apiback/66eb71a35dce7901e5af474fcc9f52761237826.jpg", "score":"4.6", "consumeNum":
//            null, "areaName":"小寨", "lowPrice":"17.9", "saleNum":null, "commentNum":3293, "detailUrl":"", "firstCate":[
//            2, 20426, 20557],"avgPrice":25
//        },{
//            "itemId":"95496627", "title":"探鱼（cityon熙地港店）", "imgUrl":
//            "https://img.meituan.net/w.h/msmerchant/ef667ee1c36d7f19cd6eba81d33397b0988125.jpg", "score":
//            "3.7", "consumeNum":null, "areaName":"未央路", "lowPrice":"89.0", "saleNum":null, "commentNum":
//            2960, "detailUrl":"", "firstCate":[2, 20632, 20426, 20557],"avgPrice":77
//        },{
//            "itemId":"4075190", "title":"拉菲达牛排自助(赛格国际店)", "imgUrl":
//            "http://p0.meituan.net/w.h/deal/056f287ea5707f347c62a4e1c6a1877729748.jpg", "score":"3.0", "consumeNum":
//            null, "areaName":"小寨", "lowPrice":"63.0", "saleNum":null, "commentNum":16971, "detailUrl":"", "firstCate":[
//            2, 20632, 20426, 20557],"avgPrice":57
//        },{
//            "itemId":"179088551", "title":"成都钢管厂五区小郡肝串串香（半坡店）", "imgUrl":
//            "https://img.meituan.net/w.h/msmerchant/eb583f9cef1e1aef31017bd318de0a69180864.jpg", "score":
//            "3.2", "consumeNum":null, "areaName":"纺织城", "lowPrice":"85.0", "saleNum":null, "commentNum":184, "detailUrl":
//            "", "firstCate":[2, 20632, 20426],"avgPrice":81
//        },{
//            "itemId":"162333221", "title":"抚小鲜蒸汽石锅鱼（汉神店）", "imgUrl":
//            "https://img.meituan.net/w.h/msmerchant/d0d91829e3e62e04320ac8fa755b8913111237.jpg", "score":
//            "3.7", "consumeNum":null, "areaName":"明光路", "lowPrice":"50.0", "saleNum":null, "commentNum":
//            1182, "detailUrl":"", "firstCate":[2, 20632, 20426, 20557],"avgPrice":58
//        }],"dealList":{
//            "deals":[],"vouchers":[],"iconUrl":"http://p1.meituan.net/codeman/31eac2c905f5be14ce80654d9508e720832.png"
//        },"poiId":"165669187", "userId":"", "ci":"42", "uuid":"7891828113d84a22b7bb.1555668847.2.0.0", "comHeader":
//        "\u003clink rel=\"stylesheet\" type=\"text/css\" href=\"//s0.meituan.net/bs/fe-web-meituan/076a786/css/com_header.css\"/>\u003cheader class=\"com-header\">\u003cdiv class=\"header-bar\">\u003cdiv class=\"header-content clearfix\">\u003cdiv class=\"header-bar-position\">\u003cspan class=\"header-icon icon-header_location\">\u003c/span>\u003cspan class=\"current-city\">西安\u003c/span>\u003ca class=\"change-city\" href=\"https://www.meituan.com/changecity/\">切换城市\u003c/a>\u003cdiv class=\"near-citys\">[\u003ca class=\"city-guess\" href=\"https://xianyang.meituan.com\">咸阳\u003c/a>\u003ca class=\"city-guess\" href=\"https://gaoling.meituan.com\">高陵\u003c/a>\u003ca class=\"city-guess\" href=\"https://lintong.meituan.com\">临潼\u003c/a>]\u003c/div>\u003cdiv class=\"user-entry\">\u003ca class=\"growth-entry user-importent\">立即登录\u003c/a>\u003ca class=\"extra-entry\">注册\u003c/a>\u003c/div>\u003c/div>\u003cnav class=\"header-bar-nav\">\u003cul class=\"header-nav-first\">\u003cli class=\"has-child\">\u003ca rel=\"nofollow\" href=\"https://www.meituan.com/account/userinfo/\" target=\"_blank\">我的美团\u003cul class=\"header-nav-my header-nav-second\">\u003cli>\u003ca rel=\"nofollow\" href=\"https://www.meituan.com/orders/\" target=\"_blank\">我的订单\u003c/a>\u003c/li>\u003cli>\u003ca rel=\"nofollow\" href=\"https://www.meituan.com/collections/\" target=\"_blank\">我的收藏\u003c/a>\u003c/li>\u003cli>\u003ca rel=\"nofollow\" href=\"https://www.meituan.com/vouchers/\" target=\"_blank\">抵用券\u003c/a>\u003c/li>\u003cli>\u003ca rel=\"nofollow\" href=\"https://www.meituan.com/account/settings\" target=\"_blank\">账户设置\u003c/a>\u003c/li>\u003c/ul>\u003c/a>\u003c/li>\u003cli>\u003ca href=\"https://i.meituan.com/mobile/down/meituan\" target=\"_blank\">手机APP\u003c/a>\u003c/li>\u003cli class=\"has-child\">商家中心\u003cul class=\"header-nav-merchant header-nav-second\">\u003cli>\u003ca rel=\"nofollow\" href=\"https://e.meituan.com/\" target=\"_blank\">登录商家中心\u003c/a>\u003c/li>\u003cli>\u003ca rel=\"nofollow\" href=\"https://shouyin.meituan.com?utm_source=inner&amp;utm_medium=mtpcsjzx\">美团智能收银\u003c/a>\u003c/li>\u003cli>\u003ca rel=\"nofollow\" href=\"https://ecom.meituan.com/bizsettle/settle/merchantsSettle?utm_source=mt_C_my\" target=\"_blank\">我想合作\u003c/a>\u003c/li>\u003cli>\u003ca rel=\"nofollow\" href=\"https://e.meituan.com/mobile/\" target=\"_blank\">手机免费开店\u003c/a>\u003c/li>\u003cli>\u003ca rel=\"nofollow\" href=\"https://daili.meituan.com/?comeFrom=mtwebMenu\" target=\"_blank\">餐饮代理商招募\u003c/a>\u003c/li>\u003cli>\u003ca href=\"https://pc.meituan.com?activity_code=mtpcdh\" target=\"_blank\">商家申请开票\u003c/a>\u003c/li>\u003cli>\u003ca href=\"https://paidui.meituan.com/?activity_code=168_00038141\" target=\"_blank\">免费合作美团排队\u003c/a>\u003c/li>\u003c/ul>\u003c/li>\u003cli class=\"has-child\">网站导航\u003cdiv class=\"header-nav-site header-nav-second\">\u003cdl class=\"header-jiulv header-nav-third\">\u003cdt>酒店旅游\u003c/dt>\u003cdd>\u003ca href=\"https://www.meituan.com/iflight/\" target=\"_blank\">国际机票\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://www.meituan.com/train/\" target=\"_blank\">火车票\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://phoenix.meituan.com/\" target=\"_blank\">榛果民宿\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20036/\" target=\"_blank\">经济型酒店\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20037/\" target=\"_blank\">主题酒店\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20038/\" target=\"_blank\">商务酒店\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20039/\" target=\"_blank\">公寓\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20040/\" target=\"_blank\">豪华酒店\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20041/\" target=\"_blank\">客栈\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20042/\" target=\"_blank\">青年旅社\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20043/\" target=\"_blank\">度假酒店\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20044/\" target=\"_blank\">别墅\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://hotel.meituan.com/xian/c20045/\" target=\"_blank\">农家院\u003c/a>\u003c/dd>\u003c/dl>\u003cdl class=\"header-meishi header-nav-third\">\u003cdt>吃美食\u003c/dt>\u003cdd>\u003ca href=\"https://xa.meituan.com/meishi/c20004/\" target=\"_blank\">烤鱼\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://xa.meituan.com/meishi/c36/\" target=\"_blank\">特色小吃\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://xa.meituan.com/meishi/c54/\" target=\"_blank\">烧烤\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://xa.meituan.com/meishi/c40/\" target=\"_blank\">自助餐\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://xa.meituan.com/meishi/c17/\" target=\"_blank\">火锅\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://xa.meituan.com/meishi/c393/\" target=\"_blank\">代金券\u003c/a>\u003c/dd>\u003c/dl>\u003cdl class=\"header-movie header-nav-third\">\u003cdt>看电影\u003c/dt>\u003cdd>\u003ca href=\"https://maoyan.com/films\" target=\"_blank\">热映电影\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://maoyan.com/cinemas\" target=\"_blank\">热门影院\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://maoyan.com/board\" target=\"_blank\">热映电影口碑榜\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://maoyan.com/board/6\" target=\"_blank\">最受期待电影\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://maoyan.com/board/1\" target=\"_blank\">国内票房榜\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://maoyan.com/board/2\" target=\"_blank\">北美票房榜\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://maoyan.com/board/4\" target=\"_blank\">电影排行榜\u003c/a>\u003c/dd>\u003c/dl>\u003cdl class=\"header-app header-nav-third\">\u003cdt>手机应用\u003c/dt>\u003cdd>\u003ca href=\"https://i.meituan.com/mobile/down/meituan\" target=\"_blank\">\u003cimg class=\"appicon\" src=\"//s0.meituan.net/bs/fe-web-meituan/e5eeaef/img/appicons/meituan.png\" title=\"美团app\" alt=\"美团app\"/>\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://waimai.meituan.com/mobile/download/default\" target=\"_blank\">\u003cimg class=\"appicon\" src=\"//s1.meituan.net/bs/fe-web-meituan/404d350/img/appicons/waimai.png\" title=\"外卖app\" alt=\"外卖app\"/>\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://phoenix.meituan.com/app/\" target=\"_blank\">\u003cimg class=\"appicon\" src=\"//s0.meituan.net/bs/fe-web-meituan/404d350/img/appicons/zhenguo.png\" title=\"榛果app\" alt=\"榛果app\"/>\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://www.dianping.com/events/m/index.htm\" target=\"_blank\">\u003cimg class=\"appicon\" src=\"//s1.meituan.net/bs/fe-web-meituan/404d350/img/appicons/dianping.png\" title=\"点评app\" alt=\"点评app\"/>\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://maoyan.com/app\" target=\"_blank\">\u003cimg class=\"appicon\" src=\"//s1.meituan.net/bs/fe-web-meituan/404d350/img/appicons/maoyan.png\" title=\"猫眼app\" alt=\"猫眼app\"/>\u003c/a>\u003c/dd>\u003c/dl>\u003c/div>\u003c/li>\u003c/ul>\u003c/nav>\u003c/div>\u003c/div>\u003cdiv class=\"header-content clearfix\">\u003cdiv class=\"header-title-module\">\u003cdiv class=\"header-title\">\u003ca href=\"//xa.meituan.com\">\u003cimg src=\"//s0.meituan.net/bs/fe-web-meituan/e5eeaef/img/logo.png\" alt=\"美团\"/>\u003c/a>\u003c/div>\u003cspan class=\"header-categorys-cate\">· 美食\u003c/span>\u003cul class=\"header-categorys-block\">\u003cli class=\"header-categorys-all\">全部分类\u003c/li>\u003cnav class=\"header-categorys-list\">\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/meishi/\">美食\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://waimai.meituan.com\">外卖\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://hotel.meituan.com\">酒店\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://zhenguo.meituan.com/?phx_wake_up_type=mtpc_category&amp;phx_wake_up_source=nav\">榛果民宿\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://maoyan.com/films?utm_source=meituanweb\">猫眼电影\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://www.meituan.com/flight/\">机票\u003c/a>\u003ca href=\"https://www.meituan.com/train/\">火车票\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/xiuxianyule/\">休闲娱乐\u003c/a>\u003ca href=\"https://xa.meituan.com/xiuxianyule/c10/\">KTV\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/shenghuo/\">生活服务\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/jiankangliren/\">丽人\u003c/a>\u003ca href=\"https://xa.meituan.com/jiankangliren/c74/\">美发\u003c/a>\u003ca href=\"https://xa.meituan.com/jiankangliren/c20423/\">医学美容\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/jiehun/\">结婚\u003c/a>\u003ca href=\"https://xa.meituan.com/jiehun/c20198/\">婚纱摄影\u003c/a>\u003ca href=\"https://xa.meituan.com/jiehun/c20210/\">婚宴\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/qinzi/\">亲子\u003c/a>\u003ca href=\"https://xa.meituan.com/qinzi/c20108/\">儿童乐园\u003c/a>\u003ca href=\"https://xa.meituan.com/qinzi/c20045/\">幼教\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/yundongjianshen/\">运动健身\u003c/a>\u003ca href=\"https://xa.meituan.com/yundongjianshen/c20253/\">健身中心\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/jiazhuang/\">家装\u003c/a>\u003ca href=\"https://xa.meituan.com/jiazhuang/c20182/\">建材\u003c/a>\u003ca href=\"https://xa.meituan.com/jiazhuang/c20771/\">家居\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/jiaoyupeixun/\">学习培训\u003c/a>\u003ca href=\"https://xa.meituan.com/xuexipeixun/c20287/\">音乐培训\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/yiliao/\">医疗健康\u003c/a>\u003ca href=\"https://xa.meituan.com/chongwu/c20691/\">宠物\u003c/a>\u003ca href=\"https://xa.meituan.com/aiche/\">爱车\u003c/a>\u003c/li>\u003cli class=\"clearfix\">\u003ca href=\"https://xa.meituan.com/xiuxianyule/c234/\">酒吧\u003c/a>\u003ca href=\"https://xa.meituan.com/xiuxianyule/c230/\">密室逃脱\u003c/a>\u003c/li>\u003c/nav>\u003c/ul>\u003c/div>\u003cdiv class=\"header-search-module\">\u003cdiv class=\"header-search-block\">\u003cinput class=\"header-search-input\" type=\"text\" placeholder=\"搜索商家或地点\"/>\u003cbutton class=\"header-search-btn\">\u003cspan class=\"header-icon icon-search\">\u003c/span>\u003c/button>\u003c/div>\u003cdiv class=\"header-search-suggest\">\u003cdiv class=\"header-search-noinput\">\u003cdiv class=\"header-search-history\">\u003ch6>最近搜索\u003c/h6>\u003cspan class=\"header-search-clean\">删除搜索历史\u003c/span>\u003cul>\u003c/ul>\u003c/div>\u003ch6>热门搜索\u003c/h6>\u003cdiv class=\"header-search-hotword\">\u003ca href=\"https://www.meituan.com/s/%E7%A7%A6%E5%A7%8B%E7%9A%87%E5%B8%9D%E9%99%B5%E5%8D%9A%E7%89%A9%E9%99%A2\">秦始皇帝陵博物院\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E8%A5%BF%E5%AE%89%E5%A4%B1%E6%81%8B%E5%8D%9A%E7%89%A9%E9%A6%86\">西安失恋博物馆\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E8%A5%BF%E5%AE%89%E7%A7%A6%E5%B2%AD%E9%87%8E%E7%94%9F%E5%8A%A8%E7%89%A9%E5%9B%AD\">西安秦岭野生动物园\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E8%A5%BF%E5%AE%89%E6%9B%B2%E6%B1%9F%E6%B5%B7%E6%B4%8B%E6%9E%81%E5%9C%B0%E5%85%AC%E5%9B%AD\">西安曲江海洋极地公园\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E5%A4%A7%E5%94%90%E8%8A%99%E8%93%89%E5%9B%AD\">大唐芙蓉园\u003c/a>\u003ca href=\"https://www.meituan.com/s/Double%20One%E8%B9%A6%E5%BA%8A%E4%B8%BB%E9%A2%98%E5%85%AC%E5%9B%AD\">Double One蹦床主题公园\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E8%A5%BF%E5%AE%89%E6%A4%8D%E7%89%A9%E5%9B%AD\">西安植物园\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E7%BF%A0%E5%8D%8E%E5%B1%B1%E9%A3%8E%E6%99%AF%E5%8C%BA\">翠华山风景区\u003c/a>\u003c/div>\u003c/div>\u003cdiv class=\"header-search-hasinput\">\u003cul>\u003c/ul>\u003c/div>\u003c/div>\u003cdiv class=\"header-search-hotword\">\u003ca href=\"https://www.meituan.com/s/%E7%A7%A6%E5%A7%8B%E7%9A%87%E5%B8%9D%E9%99%B5%E5%8D%9A%E7%89%A9%E9%99%A2\">秦始皇帝陵博物院\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E8%A5%BF%E5%AE%89%E5%A4%B1%E6%81%8B%E5%8D%9A%E7%89%A9%E9%A6%86\">西安失恋博物馆\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E8%A5%BF%E5%AE%89%E7%A7%A6%E5%B2%AD%E9%87%8E%E7%94%9F%E5%8A%A8%E7%89%A9%E5%9B%AD\">西安秦岭野生动物园\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E8%A5%BF%E5%AE%89%E6%9B%B2%E6%B1%9F%E6%B5%B7%E6%B4%8B%E6%9E%81%E5%9C%B0%E5%85%AC%E5%9B%AD\">西安曲江海洋极地公园\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E5%A4%A7%E5%94%90%E8%8A%99%E8%93%89%E5%9B%AD\">大唐芙蓉园\u003c/a>\u003ca href=\"https://www.meituan.com/s/Double%20One%E8%B9%A6%E5%BA%8A%E4%B8%BB%E9%A2%98%E5%85%AC%E5%9B%AD\">Double One蹦床主题公园\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E8%A5%BF%E5%AE%89%E6%A4%8D%E7%89%A9%E5%9B%AD\">西安植物园\u003c/a>\u003ca href=\"https://www.meituan.com/s/%E7%BF%A0%E5%8D%8E%E5%B1%B1%E9%A3%8E%E6%99%AF%E5%8C%BA\">翠华山风景区\u003c/a>\u003c/div>\u003c/div>\u003c/div>\u003c/header>\u003cscript>window.comPtData = window.comPtData || {};\nwindow.comPtData['header'] = {\"currentCity\":{\"id\":42,\"name\":\"西安\",\"pinyin\":\"xian\",\"acronym\":\"xa\",\"onlineTime\":\"1280073600\",\"open\":true,\"rank\":\"A\",\"locationId\":\"610100\",\"position\":{\"lat\":34.2778015,\"lng\":108.9530945},\"divisionStr\":\"\",\"originGeotagID\":0,\"realLocationID\":\"610100\",\"provinceLocationID\":\"610000\",\"gisID\":\"610100\",\"chineseFullName\":\"西安市\",\"isHot\":2,\"firstChar\":\"X\",\"originCityID\":0,\"recommend\":2,\"newIndex\":2,\"endTime\":\"2000000000\"}}\u003c/script>\u003cscript src=\"//s1.meituan.net/bs/fe-web-meituan/7845e34/js/ie-shim.js\">\u003c/script>\u003cscript src=\"//s1.meituan.net/bs/fe-web-meituan/e9d9492/js/fetch.js\">\u003c/script>\u003cscript src=\"//s0.meituan.net/bs/fe-web-meituan/34aeb91/js/common.js\">\u003c/script>\u003cscript src=\"//s0.meituan.net/bs/fe-web-meituan/f2c6837/js/com_header.js\" defer=\"defer\">\u003c/script>", "comFooter":
//        "\u003clink rel=\"stylesheet\" type=\"text/css\" href=\"//s0.meituan.net/bs/fe-web-meituan/cd77df7/css/com_footer.css\"/>\u003cfooter class=\"com-footer\">\u003cdiv class=\"footer-content\">\u003cdiv class=\"footer-link clearfix\">\u003cdiv class=\"footer-column\">\u003cdl>\u003cdt>用户帮助\u003c/dt>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://www.meituan.com/help/selfservice\" target=\"_blank\">申请退款\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://www.meituan.com/help/selfservice?tab=2\" target=\"_blank\">查看美团券密码\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://www.meituan.com/help/faq\" target=\"_blank\">常见问题\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://portal-portm.meituan.com/webpc/protocolmanage/useragree\" target=\"_blank\">用户协议\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://portal-portm.meituan.com/webpc/protocolmanage/privacy\" target=\"_blank\">隐私政策\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://www.meituan.com/about/anticheat\" target=\"_blank\">反诈骗公告\u003c/a>\u003c/dd>\u003c/dl>\u003cdl>\u003cdt>美团服务\u003c/dt>\u003cdd>\u003ca href=\"http://waimai.meituan.com/\" target=\"_blank\">美团外卖\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"http://hotel.meituan.com/\" target=\"_blank\">美团酒店\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"http://maoyan.com/\" target=\"_blank\">猫眼电影\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://peisong.meituan.com/\" target=\"_blank\">美团配送\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://www.mtyun.com/\" target=\"_blank\">美团云\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"http://www.dianping.com/\" target=\"_blank\">大众点评\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://phoenix.meituan.com/\" target=\"_blank\">榛果民宿\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://mad.meituan.com\" target=\"_blank\">无人配送\u003c/a>\u003c/dd>\u003c/dl>\u003c/div>\u003cdiv class=\"footer-column\">\u003cdl>\u003cdt>商家合作\u003c/dt>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://ecom.meituan.com/bizsettle/settle?utm_source=mt_C_my\" target=\"_blank\">美食商家入驻(非外卖)\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://kd.meituan.com/\" target=\"_blank\">美团外卖开店申请\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"http://shouyin.meituan.com?utm_source=inner&amp;utm_medium=mtpc\" target=\"_blank\">美团收银官网\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://page.peisong.meituan.com/apply/join\" target=\"_blank\">外卖配送加盟申请\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"https://xue.meituan.com/?from=mtpc\" target=\"_blank\">美团点评餐饮学院\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://ruzhu.meituan.com/ruzhu/index.html\" target=\"_blank\">酒店商家入驻\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://i.meituan.com/awp/h5/trip/business/cooperation/index.html?client=pc\" target=\"_blank\">境内度假商家入驻\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://e.dianping.com/claimcpc/page/index?source=mt\" target=\"_blank\">综合商家入驻\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://phoenix.meituan.com/about/\" target=\"_blank\">榛果民宿房东商家入驻\u003c/a>\u003c/dd>\u003cdd>\u003ca href=\"http://pc.meituan.com/?activity_code=mtpcdb\" target=\"_blank\">商家开票申请\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://h5.youzan.com/v2/feature/nALm22bkFF?dc_ps=2039811416638097413.200001\" target=\"_blank\">美团点评智能收银机\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://developer.meituan.com/\" target=\"_blank\">聚宝盆餐饮开放平台\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://recommend-zc.meituan.com/opportunity?channel=1\" target=\"_blank\">美团点评收单\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://paidui.meituan.com/?activity_code=167_00038050\" target=\"_blank\">免费使用美团排队\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://i.meituan.com/awp/hfe/block/6c4bec785dce/11188/index.html\" target=\"_blank\">快驴进货商家合作\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://shangou.meituan.com/joinin\" target=\"_blank\">美团闪购商家入驻\u003c/a>\u003c/dd>\u003c/dl>\u003c/div>\u003cdiv class=\"footer-column\">\u003cdl>\u003cdt>代理商加盟\u003c/dt>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://daili.meituan.com/?comeFrom=mtwebBusinesscoopd\" target=\"_blank\">到店餐饮代理商招募\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://www.dianping.com/apollo/agent/index?source=mtpcd\" target=\"_blank\">非餐饮代理商招募\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://union.meituan.com/\" target=\"_blank\">美团联盟\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://store.meituan.com/distribution/broadcast\" target=\"_blank\">美团收银招募线上分销商\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://agent.meituan.com/zhaoshang?entry=5\" target=\"_blank\">美团点评5S服务商招募\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://i.meituan.com/awp/hfe/block/index.html?cube_h=62493758737055c4373c&amp;cube_i=37498\" target=\"_blank\">美团收单渠道代理商招募\u003c/a>\u003c/dd>\u003c/dl>\u003cdl>\u003cdt>行业规范\u003c/dt>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://portal-portm.meituan.com/webpc/protocolmanage/foodsafe\" target=\"_blank\">美团点评餐饮安全管理办法\u003c/a>\u003c/dd>\u003c/dl>\u003cdl>\u003cdt>廉正举报\u003c/dt>\u003cdd>\u003ca rel=\"nofollow\" href=\"mailto:lianzheng@meituan.com\" target=\"_self\">廉政邮箱\u003c/a>\u003c/dd>\u003c/dl>\u003c/div>\u003cdiv class=\"footer-column\">\u003cdl>\u003cdt>关注美团\u003c/dt>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://weibo.com/meituan\" target=\"_blank\">美团新浪微博\u003c/a>\u003c/dd>\u003c/dl>\u003cdl>\u003cdt>公司信息\u003c/dt>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://about.meituan.com/\" target=\"_blank\">关于我们\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://about.meituan.com/investor.html\" target=\"_blank\">投资者关系\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"http://zhaopin.meituan.com/\" target=\"_blank\">加入我们\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://portal-portm.meituan.com/webpc/protocolmanage/law\" target=\"_blank\">法律声明\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://portal-portm.meituan.com/webpc/protocolmanage/comment/price\" target=\"_blank\">价格说明\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://portal-portm.meituan.com/webpc/protocolmanage/comment/judge\" target=\"_blank\">美团网平台点评规则\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://portal-portm.meituan.com/webpc/protocolmanage/comment\" target=\"_blank\">美团点评团购用户服务条款\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://dpapp-appeal.meituan.com/#/shopCreditRegulationPC\" target=\"_blank\">商户诚信公约及管理办法\u003c/a>\u003c/dd>\u003cdd>\u003ca rel=\"nofollow\" href=\"https://i.meituan.com/awp/ffe/insurance-website/index.html#/\" target=\"_blank\">保险经纪资质\u003c/a>\u003c/dd>\u003c/dl>\u003c/div>\u003cdiv class=\"footer-column\">\u003cdl>\u003cdt>消费者服务热线\u003c/dt>\u003cdd>外卖消费者：\u003ca rel=\"nofollow\" href=\"tel:10109777\" target=\"_blank\">10109777\u003c/a>\u003c/dd>\u003cdd>猫眼消费者：\u003ca rel=\"nofollow\" href=\"tel:10105335\" target=\"_blank\">10105335\u003c/a>\u003c/dd>\u003cdd>其他消费者：\u003ca rel=\"nofollow\" href=\"tel:10107888\" target=\"_blank\">10107888\u003c/a>\u003c/dd>\u003c/dl>\u003cdl>\u003cdt>商家服务热线\u003c/dt>\u003cdd>外卖&amp;餐饮商家：\u003ca rel=\"nofollow\" href=\"tel:10105557\" target=\"_blank\">10105557\u003c/a>\u003c/dd>\u003cdd>休闲娱乐、丽人、ktv、教育、结婚、亲子、家装等商家：\u003ca rel=\"nofollow\" href=\"tel:10100107\" target=\"_blank\">10100107\u003c/a>\u003c/dd>\u003c/dl>\u003cdl>\u003cdt>投诉举报热线\u003c/dt>\u003cdd>违法和不良信息举报电话：\u003ca rel=\"nofollow\" href=\"tel:4006018900\" target=\"_blank\">4006018900\u003c/a>\u003c/dd>\u003cdd>举报邮箱：\u003ca rel=\"nofollow\" href=\"mailto:tousujubao@meituan.com\" target=\"_self\">tousujubao@meituan.com\u003c/a>\u003c/dd>\u003c/dl>\u003cdl>\u003cdt>\u003ca rel=\"nofollow\" href=\"https://ecom.meituan.com/bizsettle/settle/merchantsSettle\" target=\"_blank\">商家自助入驻美团入口\u003c/a>\u003c/dt>\u003c/dl>\u003c/div>\u003c/div>\u003cdiv class=\"footer-copyright clearfix\">\u003cdiv class=\"footer-copyright-left\">\u003cp>©美团网团购 meituan.com\u003ca href=\"http://www.beianbeian.com/beianxinxi/283f39a9-4c00-427a-97ef-3c7a9e1e0af1.html\" style=\"margin-left: 20px;\">京ICP证070791号\u003c/a>\u003ca href=\"http://www.miitbeian.gov.cn/\">京ICP备10211739号\u003c/a>\u003c/p>\u003cp>\u003ca href=\"https://portal-portm.meituan.com/webpc/protocolmanage/comment/license/tv\" target=\"_blank\">广播电视节目制作经营许可证（京）字第03889号\u003c/a>\u003c/p>\u003cp>\u003ca href=\"https://portal-portm.meituan.com/webpc/protocolmanage/foodlicense\" target=\"_blank\">食品经营许可证\u003c/a>\u003ca href=\"https://portal-portm.meituan.com/webpc/protocolmanage/medicalcertificate\" target=\"_blank\">互联网药品信息服务资格证\u003c/a>\u003c/p>\u003cp> \u003ca href=\"http://portal-portm.meituan.com/webpc/protocolmanage/medicalapparatus\" target=\"_blank\">医疗器械网络交易服务第三方平台备案：（京）网械平台备字[2018]第00004号\u003c/a>\u003c/p>\u003c/div>\u003cdiv class=\"footer-copyright-right\">\u003ca href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010502025545\" target=\"_blank\">京公网安备11010502025545号\u003c/a>\u003cdiv class=\"footer-copyright-cert\">\u003ca class=\"sp-ft sp-ft--record\" href=\"https://portal-portm.meituan.com/webpc/protocolmanage/comment/license/sankuai\" title=\"备案信息\" target=\"_blank\">备案信息\u003c/a>\u003ca class=\"sp-ft sp-ft--knet\" href=\"http://t.knet.cn/index_new.jsp\" title=\"可信网站认证\" target=\"_blank\">可信网站\u003c/a>\u003ca class=\"sp-ft sp-ft--12315\" href=\"http://www.bj315.org/xfwq/lstd/201209/t20120910_3344.shtml?dnrpluojqxbceiqq\" title=\"12315消费争议\" target=\"_blank\">12315消费争议\u003c/a>\u003c/div>\u003c/div>\u003c/div>\u003c/div>\u003c/footer>"}
//        ;</script >
//        <script src = "//s0.meituan.net/bs/js?f=meis/meishi.web:page/poi/detail/index.js@331a8a1" ></script ><
//        script > if (!window._turboClasses) {
//            console.error("turbo class not found");
//        } else {
//            var PageClass = window._turboClasses['client/page/poi/detail1'];
//            if (PageClass) {
//                window._turboRoot = (window.ReactDOM || window.React).render(React.createElement(PageClass), document.getElementById('app'));
//            }
//        }</script >
    }

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            System.out.println("__________________-");
            System.out.println("__________________-");
            System.out.println("__________________-");
            System.out.println("__________________-");
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
