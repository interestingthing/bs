package personal.bs.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenjingang@gauzi.com  2019/6/9 22:28
 */
public class JXImageUrl {
    /*
    <ul class="lh" style="position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;">
                                                                                                                                                                                                <li class="img-hover"><img alt="苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm" src="//img11.360buyimg.com/n5/jfs/t14236/309/2456138516/370245/3b617ec9/5a9e3c01N6f77146e.jpg" data-url="jfs/t14236/309/2456138516/370245/3b617ec9/5a9e3c01N6f77146e.jpg" data-img="1" width="50" height="50"></li>
    <li class="">
        <img alt="苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm"
        src="//img11.360buyimg.com/n5/jfs/t16441/256/2304474124/313142/d7437ca2/5a9e3c48N44f7c3f4.jpg"
        data-url="jfs/t16441/256/2304474124/313142/d7437ca2/5a9e3c48N44f7c3f4.jpg"
        data-img="1" width="50" height="50">
    </li>
    <li class=""><img alt="苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm" src="//img11.360buyimg.com/n5/jfs/t14413/9/2431373092/348466/9106f140/5a9e3c4bN7a0261ad.jpg" data-url="jfs/t14413/9/2431373092/348466/9106f140/5a9e3c4bN7a0261ad.jpg" data-img="1" width="50" height="50"></li>
    <li class=""><img alt="苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm" src="//img11.360buyimg.com/n5/jfs/t17698/168/691981007/494751/80109f6d/5a9e3c4dN8173b6b4.jpg" data-url="jfs/t17698/168/691981007/494751/80109f6d/5a9e3c4dN8173b6b4.jpg" data-img="1" width="50" height="50"></li>
    <li><img alt="苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm" src="//img11.360buyimg.com/n5/jfs/t14359/226/2399843504/462517/e03d0330/5a9e3c57N1e32a4c7.jpg" data-url="jfs/t14359/226/2399843504/462517/e03d0330/5a9e3c57N1e32a4c7.jpg" data-img="1" width="50" height="50"></li>
    <li><img alt="苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm" src="//img11.360buyimg.com/n5/jfs/t16843/356/672819699/483760/31e869d4/5a9fa819Nc12ab240.jpg" data-url="jfs/t16843/356/672819699/483760/31e869d4/5a9fa819Nc12ab240.jpg" data-img="1" width="50" height="50"></li>
    </ul>
    */
    static String[] sku25_1 = {"<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" + "<li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm\" src=\"//img11.360buyimg.com/n5/jfs/t14236/309/2456138516/370245/3b617ec9/5a9e3c01N6f77146e.jpg\" data-url=\"jfs/t14236/309/2456138516/370245/3b617ec9/5a9e3c01N6f77146e.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "    <li class=\"\">\n" +
            "        <img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm\"\n" +
            "        src=\"//img11.360buyimg.com/n5/jfs/t16441/256/2304474124/313142/d7437ca2/5a9e3c48N44f7c3f4.jpg\"\n" +
            "        data-url=\"jfs/t16441/256/2304474124/313142/d7437ca2/5a9e3c48N44f7c3f4.jpg\"\n" +
            "        data-img=\"1\" width=\"50\" height=\"50\">\n" +
            "    </li>\n" +
            "    <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm\" src=\"//img11.360buyimg.com/n5/jfs/t14413/9/2431373092/348466/9106f140/5a9e3c4bN7a0261ad.jpg\" data-url=\"jfs/t14413/9/2431373092/348466/9106f140/5a9e3c4bN7a0261ad.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "    <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm\" src=\"//img11.360buyimg.com/n5/jfs/t17698/168/691981007/494751/80109f6d/5a9e3c4dN8173b6b4.jpg\" data-url=\"jfs/t17698/168/691981007/494751/80109f6d/5a9e3c4dN8173b6b4.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "    <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm\" src=\"//img11.360buyimg.com/n5/jfs/t14359/226/2399843504/462517/e03d0330/5a9e3c57N1e32a4c7.jpg\" data-url=\"jfs/t14359/226/2399843504/462517/e03d0330/5a9e3c57N1e32a4c7.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "    <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 桔色 240*240cm\" src=\"//img11.360buyimg.com/n5/jfs/t16843/356/672819699/483760/31e869d4/5a9fa819Nc12ab240.jpg\" data-url=\"jfs/t16843/356/672819699/483760/31e869d4/5a9fa819Nc12ab240.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "    </ul>", "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
            "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 兰色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t18196/137/646742478/404047/23dbfb0f/5a9e3ca4Ndf154e99.jpg\" data-url=\"jfs/t18196/137/646742478/404047/23dbfb0f/5a9e3ca4Ndf154e99.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 兰色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t16822/230/661622244/356758/27e32070/5a9e3c9bN0524e11a.jpg\" data-url=\"jfs/t16822/230/661622244/356758/27e32070/5a9e3c9bN0524e11a.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 兰色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t18667/129/666081503/404212/57a2e327/5aa09ddcN948577a3.jpg\" data-url=\"jfs/t18667/129/666081503/404212/57a2e327/5aa09ddcN948577a3.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 兰色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t19759/183/660629799/434471/549f9a6a/5a9e3caeN40fa8f4d.jpg\" data-url=\"jfs/t19759/183/660629799/434471/549f9a6a/5a9e3caeN40fa8f4d.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 兰色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t13024/254/2487085492/362356/a1c69ee6/5a9e3cbeNad347ad3.jpg\" data-url=\"jfs/t13024/254/2487085492/362356/a1c69ee6/5a9e3cbeNad347ad3.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 兰色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t16324/237/2211907620/369549/59ab9517/5a9e3cbfN35e1bb0f.jpg\" data-url=\"jfs/t16324/237/2211907620/369549/59ab9517/5a9e3cbfN35e1bb0f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                                            </ul>",
            "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 橙色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t16462/71/2324370637/499293/d451e79a/5a9e3ccaNc1ef102f.jpg\" data-url=\"jfs/t16462/71/2324370637/499293/d451e79a/5a9e3ccaNc1ef102f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 橙色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t14638/12/2399578938/442589/bd4e0abe/5a9e3cd0N5ba21a4f.jpg\" data-url=\"jfs/t14638/12/2399578938/442589/bd4e0abe/5a9e3cd0N5ba21a4f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 橙色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t17827/177/679745921/343764/e5770b38/5a9e3ccaN3e52a29d.jpg\" data-url=\"jfs/t17827/177/679745921/343764/e5770b38/5a9e3ccaN3e52a29d.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 橙色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t12649/266/2530006966/385752/9e254045/5a9e3cd1Nd848052d.jpg\" data-url=\"jfs/t12649/266/2530006966/385752/9e254045/5a9e3cd1Nd848052d.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 橙色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t14599/95/2409276520/449083/9996ec4d/5a9e3cd2N45fa9944.jpg\" data-url=\"jfs/t14599/95/2409276520/449083/9996ec4d/5a9e3cd2N45fa9944.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 橙色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t15715/318/2304455383/406539/98175b70/5a9fa7e6N61705e51.jpg\" data-url=\"jfs/t15715/318/2304455383/406539/98175b70/5a9fa7e6N61705e51.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>",
            "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 红色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t18859/7/1527106495/413272/2713222e/5aceaf5aN4f18a97f.jpg\" data-url=\"jfs/t18859/7/1527106495/413272/2713222e/5aceaf5aN4f18a97f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 红色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t19495/117/1547365240/438089/698b8d42/5aceaf5aNe89767cc.jpg\" data-url=\"jfs/t19495/117/1547365240/438089/698b8d42/5aceaf5aNe89767cc.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 红色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t17368/153/687037090/454782/8135ab59/5a9e3e1dN05d62ad8.jpg\" data-url=\"jfs/t17368/153/687037090/454782/8135ab59/5a9e3e1dN05d62ad8.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 红色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t18031/16/1568275469/415683/568789da/5aceaef6Nc9a42f7c.jpg\" data-url=\"jfs/t18031/16/1568275469/415683/568789da/5aceaef6Nc9a42f7c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 红色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t17947/307/699440304/383402/5325903/5aa09e00Nafbae7bc.jpg\" data-url=\"jfs/t17947/307/699440304/383402/5325903/5aa09e00Nafbae7bc.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 红色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t19504/225/1564543677/430641/4232f3e6/5aceaf5aNa569b54c.jpg\" data-url=\"jfs/t19504/225/1564543677/430641/4232f3e6/5aceaf5aNa569b54c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>"


    };

    static String[] sku25_2 = {
            "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 桔色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t18886/201/1563809485/372089/b65405c5/5aceaf10N60413a73.jpg\" data-url=\"jfs/t18886/201/1563809485/372089/b65405c5/5aceaf10N60413a73.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 桔色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t19564/336/1530650929/408860/a12a47d4/5aceaf10N91130d63.jpg\" data-url=\"jfs/t19564/336/1530650929/408860/a12a47d4/5aceaf10N91130d63.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 桔色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t18157/184/1557755764/364578/f8487b13/5aceaf10N7d94bb11.jpg\" data-url=\"jfs/t18157/184/1557755764/364578/f8487b13/5aceaf10N7d94bb11.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 桔色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t18583/40/1524138295/504149/cb554c39/5aceaf10N5d5df53b.jpg\" data-url=\"jfs/t18583/40/1524138295/504149/cb554c39/5aceaf10N5d5df53b.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 桔色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t17695/172/1546281607/399795/db51ca7b/5aceaf0dN34a0d41c.jpg\" data-url=\"jfs/t17695/172/1546281607/399795/db51ca7b/5aceaf0dN34a0d41c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 桔色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t18997/36/1504815811/437572/7f130810/5aceaf71N828d4c81.jpg\" data-url=\"jfs/t18997/36/1504815811/437572/7f130810/5aceaf71N828d4c81.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>"
            , "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
            "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 兰色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t19498/278/1549319861/407429/b1799146/5ad000cfN4c96d025.jpg\" data-url=\"jfs/t19498/278/1549319861/407429/b1799146/5ad000cfN4c96d025.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 兰色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t17248/68/1576661714/473071/405959b6/5ad000cfN35ab212f.jpg\" data-url=\"jfs/t17248/68/1576661714/473071/405959b6/5ad000cfN35ab212f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 兰色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t19750/211/1570683501/409591/6654fccc/5ad000cfNa4fcf471.jpg\" data-url=\"jfs/t19750/211/1570683501/409591/6654fccc/5ad000cfNa4fcf471.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 兰色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t18067/161/1630155928/376350/5eeaa9ea/5ad000cfN9ef5e9f2.jpg\" data-url=\"jfs/t18067/161/1630155928/376350/5eeaa9ea/5ad000cfN9ef5e9f2.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 兰色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t18553/363/1585322216/495009/b839ee92/5ad000cfN140aad92.jpg\" data-url=\"jfs/t18553/363/1585322216/495009/b839ee92/5ad000cfN140aad92.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 兰色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t17752/169/1481087780/433410/fd8c4f4b/5aceaf97N88c412e4.jpg\" data-url=\"jfs/t17752/169/1481087780/433410/fd8c4f4b/5aceaf97N88c412e4.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                                            </ul>"
            , "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
            "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 橙色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t18301/87/1556725843/447992/e631ec22/5aceaecaNf70d198d.jpg\" data-url=\"jfs/t18301/87/1556725843/447992/e631ec22/5aceaecaNf70d198d.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 橙色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t19375/341/1586868047/465499/69167b38/5aceaecaN1917e501.jpg\" data-url=\"jfs/t19375/341/1586868047/465499/69167b38/5aceaecaN1917e501.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 橙色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t17242/113/1566137882/341200/b1c38cb0/5aceaecbN09569168.jpg\" data-url=\"jfs/t17242/113/1566137882/341200/b1c38cb0/5aceaecbN09569168.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 橙色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t17719/103/1554879187/544176/a2be2019/5aceaecbNd4f3f58e.jpg\" data-url=\"jfs/t17719/103/1554879187/544176/a2be2019/5aceaecbNd4f3f58e.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 橙色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t19360/79/1509724707/381332/760df4b6/5aceaecbNc95374d7.jpg\" data-url=\"jfs/t19360/79/1509724707/381332/760df4b6/5aceaecbNc95374d7.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 橙色 200*230cm\" src=\"//img12.360buyimg.com/n5/jfs/t19333/101/1564818049/444124/b359169f/5aceaf45Na336a4ee.jpg\" data-url=\"jfs/t19333/101/1564818049/444124/b359169f/5aceaf45Na336a4ee.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                                            </ul>"
            , "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
            "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 红色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t18382/167/675290529/378062/f721142e/5a9e3e17Ne45c0a79.jpg\" data-url=\"jfs/t18382/167/675290529/378062/f721142e/5a9e3e17Ne45c0a79.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 红色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t18823/53/660471000/364530/c64b355f/5a9e3e15Nbb26620f.jpg\" data-url=\"jfs/t18823/53/660471000/364530/c64b355f/5a9e3e15Nbb26620f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 红色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t17422/171/678149105/419707/d2cfd57/5a9e3e1dNc1121275.jpg\" data-url=\"jfs/t17422/171/678149105/419707/d2cfd57/5a9e3e1dNc1121275.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 红色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t17368/153/687037090/454782/8135ab59/5a9e3e1dN05d62ad8.jpg\" data-url=\"jfs/t17368/153/687037090/454782/8135ab59/5a9e3e1dN05d62ad8.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 红色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t14530/247/2476309926/421591/7e7be555/5a9e3e1cNa36a60a8.jpg\" data-url=\"jfs/t14530/247/2476309926/421591/7e7be555/5a9e3e1cNa36a60a8.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹四件套 红色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t17947/307/699440304/383402/5325903/5aa09e00Nafbae7bc.jpg\" data-url=\"jfs/t17947/307/699440304/383402/5325903/5aa09e00Nafbae7bc.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                                            </ul>"

    };

    static String[] sku25_3 = {
            "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 桔色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t18367/212/1517540580/495516/5c7c9f27/5aceaf71Na5e0fdc3.jpg\" data-url=\"jfs/t18367/212/1517540580/495516/5c7c9f27/5aceaf71Na5e0fdc3.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 桔色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t18433/178/1561183139/345279/f6de251d/5aceaf71Nd4d54936.jpg\" data-url=\"jfs/t18433/178/1561183139/345279/f6de251d/5aceaf71Nd4d54936.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 桔色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t14413/9/2431373092/348466/9106f140/5a9e3c4bN7a0261ad.jpg\" data-url=\"jfs/t14413/9/2431373092/348466/9106f140/5a9e3c4bN7a0261ad.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 桔色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t17698/168/691981007/494751/80109f6d/5a9e3c4dN8173b6b4.jpg\" data-url=\"jfs/t17698/168/691981007/494751/80109f6d/5a9e3c4dN8173b6b4.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 桔色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t14359/226/2399843504/462517/e03d0330/5a9e3c57N1e32a4c7.jpg\" data-url=\"jfs/t14359/226/2399843504/462517/e03d0330/5a9e3c57N1e32a4c7.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 桔色 200*230cm\" src=\"//img10.360buyimg.com/n5/jfs/t19501/258/1612430792/499181/effcf651/5aceaf71N83c041a2.jpg\" data-url=\"jfs/t19501/258/1612430792/499181/effcf651/5aceaf71N83c041a2.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>",
            "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 兰色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t17845/172/1575349348/363758/3dcf8f/5aceaf96N6a3d7d1f.jpg\" data-url=\"jfs/t17845/172/1575349348/363758/3dcf8f/5aceaf96N6a3d7d1f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 兰色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t19369/72/1556844232/410147/1e6bac9a/5aceaf96N362bc14b.jpg\" data-url=\"jfs/t19369/72/1556844232/410147/1e6bac9a/5aceaf96N362bc14b.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 兰色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t13024/254/2487085492/362356/a1c69ee6/5a9e3cbeNad347ad3.jpg\" data-url=\"jfs/t13024/254/2487085492/362356/a1c69ee6/5a9e3cbeNad347ad3.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 兰色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t19030/81/1514017027/478137/8e6842e6/5aceae89N641aae56.jpg\" data-url=\"jfs/t19030/81/1514017027/478137/8e6842e6/5aceae89N641aae56.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 兰色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t16324/237/2211907620/369549/59ab9517/5a9e3cbfN35e1bb0f.jpg\" data-url=\"jfs/t16324/237/2211907620/369549/59ab9517/5a9e3cbfN35e1bb0f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 兰色 200*230cm\" src=\"//img14.360buyimg.com/n5/jfs/t18229/245/1539451314/549406/78087248/5aceaf97Nbdea6054.jpg\" data-url=\"jfs/t18229/245/1539451314/549406/78087248/5aceaf97Nbdea6054.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>",
            "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 橙色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t17746/48/1570769433/395043/6cdbbb35/5aceaf45N215974c7.jpg\" data-url=\"jfs/t17746/48/1570769433/395043/6cdbbb35/5aceaf45N215974c7.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 橙色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t19093/39/1501802532/406236/9a537409/5aceaf45N036c2905.jpg\" data-url=\"jfs/t19093/39/1501802532/406236/9a537409/5aceaf45N036c2905.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 橙色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t17827/177/679745921/343764/e5770b38/5a9e3ccaN3e52a29d.jpg\" data-url=\"jfs/t17827/177/679745921/343764/e5770b38/5a9e3ccaN3e52a29d.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 橙色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t12649/266/2530006966/385752/9e254045/5a9e3cd1Nd848052d.jpg\" data-url=\"jfs/t12649/266/2530006966/385752/9e254045/5a9e3cd1Nd848052d.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 橙色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t18979/362/707240632/406539/98175b70/5aa09e2aNc07636d4.jpg\" data-url=\"jfs/t18979/362/707240632/406539/98175b70/5aa09e2aNc07636d4.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹床单 橙色 200*230cm\" src=\"//img13.360buyimg.com/n5/jfs/t16954/68/1495027784/479253/9da18c32/5aceaf46N3978259f.jpg\" data-url=\"jfs/t16954/68/1495027784/479253/9da18c32/5aceaf46N3978259f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>",
            "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 红色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t18031/16/1568275469/415683/568789da/5aceaef6Nc9a42f7c.jpg\" data-url=\"jfs/t18031/16/1568275469/415683/568789da/5aceaef6Nc9a42f7c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 红色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t18739/336/1519822569/359493/60ec7af4/5aceaef6Nfe58527c.jpg\" data-url=\"jfs/t18739/336/1519822569/359493/60ec7af4/5aceaef6Nfe58527c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 红色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t18757/337/1563754255/457438/8aa308e5/5aceaef6N270c4217.jpg\" data-url=\"jfs/t18757/337/1563754255/457438/8aa308e5/5aceaef6N270c4217.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 红色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t19315/365/1527897279/612722/ad6e7ce3/5aceaef7N0c41c5e6.jpg\" data-url=\"jfs/t19315/365/1527897279/612722/ad6e7ce3/5aceaef7N0c41c5e6.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 红色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t19153/357/1579796097/449442/f0e0d20b/5aceaef6N5b36e1f2.jpg\" data-url=\"jfs/t19153/357/1579796097/449442/f0e0d20b/5aceaef6N5b36e1f2.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）家纺 纯棉四件套床单 全棉床上用品床品套件床单单件被套1.8米床 平纹三件套 红色 200*230cm\" src=\"//img11.360buyimg.com/n5/jfs/t19492/13/1554871428/474498/88da409f/5aceaf5aN6af158e2.jpg\" data-url=\"jfs/t19492/13/1554871428/474498/88da409f/5aceaf5aN6af158e2.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>"
    };

    static List<String[]> spu25 = new ArrayList<>();

    static {
        spu25.add(sku25_1);
        spu25.add(sku25_2);
        spu25.add(sku25_3);
    }

    static String[] sku27 = {"<ul class=\"lh\">\n" +
            "                                                                                                                                 <li class=\"\"><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 红色 1.2米(160*220cm)\" src=\"//img11.360buyimg.com/n5/jfs/t21148/164/33296998/419987/d2a828d3/5af6a86fN37facfe8.jpg\" data-url=\"jfs/t21148/164/33296998/419987/d2a828d3/5af6a86fN37facfe8.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            " <li><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 红色 1.2米(160*220cm)\" src=\"//img11.360buyimg.com/n5/jfs/t20992/140/38220467/450467/9366a842/5af6a86fN04e66347.jpg\" data-url=\"jfs/t20992/140/38220467/450467/9366a842/5af6a86fN04e66347.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            " <li class=\"\"><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 红色 1.2米(160*220cm)\" src=\"//img11.360buyimg.com/n5/jfs/t20689/146/39344128/435533/9530673c/5af6a86fN96a03a93.jpg\" data-url=\"jfs/t20689/146/39344128/435533/9530673c/5af6a86fN96a03a93.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            " <li class=\"\"><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 红色 1.2米(160*220cm)\" src=\"//img11.360buyimg.com/n5/jfs/t20656/304/37887018/476318/e8fbbe35/5af6a86fNa94fa304.jpg\" data-url=\"jfs/t20656/304/37887018/476318/e8fbbe35/5af6a86fNa94fa304.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            " <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 红色 1.2米(160*220cm)\" src=\"//img11.360buyimg.com/n5/jfs/t21052/158/32587195/428445/42c570a7/5af6a86fNa491d083.jpg\" data-url=\"jfs/t21052/158/32587195/428445/42c570a7/5af6a86fNa491d083.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                             </ul>", "<ul class=\"lh\">\n" +
            "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 橙色 1.2米(160*220cm)\" src=\"//img12.360buyimg.com/n5/jfs/t19303/281/2488140707/394520/8dcc6230/5af6a883N6f94a4a0.jpg\" data-url=\"jfs/t19303/281/2488140707/394520/8dcc6230/5af6a883N6f94a4a0.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 橙色 1.2米(160*220cm)\" src=\"//img12.360buyimg.com/n5/jfs/t16660/265/2442258950/394734/def4a5e9/5af6a883N6aaf059c.jpg\" data-url=\"jfs/t16660/265/2442258950/394734/def4a5e9/5af6a883N6aaf059c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 橙色 1.2米(160*220cm)\" src=\"//img12.360buyimg.com/n5/jfs/t16804/161/2480507577/620450/aafafb0b/5af6a883N9358836a.jpg\" data-url=\"jfs/t16804/161/2480507577/620450/aafafb0b/5af6a883N9358836a.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 橙色 1.2米(160*220cm)\" src=\"//img12.360buyimg.com/n5/jfs/t18817/353/2437493396/481491/4f0a161d/5af6a883N9e5d6d0c.jpg\" data-url=\"jfs/t18817/353/2437493396/481491/4f0a161d/5af6a883N9e5d6d0c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）床品家纺  纯棉床单单件 单人双人床加大全棉床上用品老粗布床单 1.5米/1.8米 床单 橙色 1.2米(160*220cm)\" src=\"//img12.360buyimg.com/n5/jfs/t21766/126/40658916/362146/9745015b/5af6a87fN62003adb.jpg\" data-url=\"jfs/t21766/126/40658916/362146/9745015b/5af6a87fN62003adb.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                                            </ul>"};
    static List<String[]> spu27 = new ArrayList<>();

    static {
        spu27.add(sku27);
    }

    static String[] sku30 = {"<ul class=\"lh\" style=\"position: absolute; width: 406px; height: 54px; top: 0px; left: 0px;\">\n" +
            "                                                                                                                                                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条五 四件套\" src=\"//img14.360buyimg.com/n5/jfs/t1/56987/9/200/500594/5cd375dcE3104b3c8/0d96735289b709e5.jpg\" data-url=\"jfs/t1/56987/9/200/500594/5cd375dcE3104b3c8/0d96735289b709e5.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条五 四件套\" src=\"//img14.360buyimg.com/n5/jfs/t1/45362/19/185/376196/5cd375dcEc6cf41b0/934b7c06cff53d96.jpg\" data-url=\"jfs/t1/45362/19/185/376196/5cd375dcEc6cf41b0/934b7c06cff53d96.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条五 四件套\" src=\"//img14.360buyimg.com/n5/jfs/t1/47152/37/186/441754/5cd375dcEa5184be7/81f9e76194242bf2.jpg\" data-url=\"jfs/t1/47152/37/186/441754/5cd375dcEa5184be7/81f9e76194242bf2.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条五 四件套\" src=\"//img14.360buyimg.com/n5/jfs/t1/48225/18/183/411485/5cd375ddE1bafb5a8/ac81737e88070075.jpg\" data-url=\"jfs/t1/48225/18/183/411485/5cd375ddE1bafb5a8/ac81737e88070075.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条五 四件套\" src=\"//img14.360buyimg.com/n5/jfs/t1/47814/24/188/386393/5cd375ddEe31381c3/4745d4c2ea4419a7.jpg\" data-url=\"jfs/t1/47814/24/188/386393/5cd375ddEe31381c3/4745d4c2ea4419a7.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条五 四件套\" src=\"//img14.360buyimg.com/n5/jfs/t1/52923/8/184/460389/5cd375ddE10cdf14e/67cf293ee5423e1a.jpg\" data-url=\"jfs/t1/52923/8/184/460389/5cd375ddE10cdf14e/67cf293ee5423e1a.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条五 四件套\" src=\"//img14.360buyimg.com/n5/jfs/t1/42760/30/4394/505856/5cd375deE295e7121/dbc319b2a30607ff.png\" data-url=\"jfs/t1/42760/30/4394/505856/5cd375deE295e7121/dbc319b2a30607ff.png\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            " </ul>",
            "<ul class=\"lh\" style=\"position: absolute; width: 406px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条二 四件套\" src=\"//img11.360buyimg.com/n5/jfs/t1/39690/9/6702/487552/5cd3754aEa2fb7168/eb8267439b35f4ab.jpg\" data-url=\"jfs/t1/39690/9/6702/487552/5cd3754aEa2fb7168/eb8267439b35f4ab.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条二 四件套\" src=\"//img11.360buyimg.com/n5/jfs/t1/53132/15/198/382303/5cd3754aEfe30ee20/fa81e93b1ea3f509.jpg\" data-url=\"jfs/t1/53132/15/198/382303/5cd3754aEfe30ee20/fa81e93b1ea3f509.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条二 四件套\" src=\"//img11.360buyimg.com/n5/jfs/t1/51260/17/189/420231/5cd3754aE4014ce6f/f47e24dafa63c744.jpg\" data-url=\"jfs/t1/51260/17/189/420231/5cd3754aE4014ce6f/f47e24dafa63c744.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条二 四件套\" src=\"//img11.360buyimg.com/n5/jfs/t1/54381/34/182/413012/5cd3754aEda37374e/06ff52cee4046f03.jpg\" data-url=\"jfs/t1/54381/34/182/413012/5cd3754aEda37374e/06ff52cee4046f03.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条二 四件套\" src=\"//img11.360buyimg.com/n5/jfs/t1/41872/20/4422/369030/5cd3754aE9ff12753/9d319ba7f7b46f94.jpg\" data-url=\"jfs/t1/41872/20/4422/369030/5cd3754aE9ff12753/9d319ba7f7b46f94.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条二 四件套\" src=\"//img11.360buyimg.com/n5/jfs/t1/54088/31/178/435870/5cd3754bE44a2ebce/a61f820bf329e6c0.jpg\" data-url=\"jfs/t1/54088/31/178/435870/5cd3754bE44a2ebce/a61f820bf329e6c0.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条二 四件套\" src=\"//img11.360buyimg.com/n5/jfs/t1/48076/13/167/515683/5cd3754bE6b2403d8/e8c66dfd89f90304.png\" data-url=\"jfs/t1/48076/13/167/515683/5cd3754bE6b2403d8/e8c66dfd89f90304.png\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>",
            "<ul class=\"lh\" style=\"position: absolute; width: 406px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条四 四件套\" src=\"//img13.360buyimg.com/n5/jfs/t1/58077/15/178/481720/5cd375b7Ec273683f/88690ddb279339ba.jpg\" data-url=\"jfs/t1/58077/15/178/481720/5cd375b7Ec273683f/88690ddb279339ba.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条四 四件套\" src=\"//img13.360buyimg.com/n5/jfs/t1/36410/20/10137/380217/5cd375b7Eb2c61979/71375d5d81ef47b6.jpg\" data-url=\"jfs/t1/36410/20/10137/380217/5cd375b7Eb2c61979/71375d5d81ef47b6.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条四 四件套\" src=\"//img13.360buyimg.com/n5/jfs/t1/49343/20/208/497501/5cd375b7E74249c95/ce7abcef8faa3384.jpg\" data-url=\"jfs/t1/49343/20/208/497501/5cd375b7E74249c95/ce7abcef8faa3384.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条四 四件套\" src=\"//img13.360buyimg.com/n5/jfs/t1/46841/3/199/408024/5cd375b8E5eb0bdad/7f3dd284f6d51c0c.jpg\" data-url=\"jfs/t1/46841/3/199/408024/5cd375b8E5eb0bdad/7f3dd284f6d51c0c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条四 四件套\" src=\"//img13.360buyimg.com/n5/jfs/t1/40516/29/4454/394463/5cd375b8Eb45875e8/1418c9f1da2efc37.jpg\" data-url=\"jfs/t1/40516/29/4454/394463/5cd375b8Eb45875e8/1418c9f1da2efc37.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条四 四件套\" src=\"//img13.360buyimg.com/n5/jfs/t1/43057/29/4416/377508/5cd375b8E3c75938c/5f3ba10ad8f5c7b1.jpg\" data-url=\"jfs/t1/43057/29/4416/377508/5cd375b8E3c75938c/5f3ba10ad8f5c7b1.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘（SUHUI）四件套纯棉家纺 床上用品床单枕套双人全棉手工织布套件1.8/2.0米床 彩条四 四件套\" src=\"//img13.360buyimg.com/n5/jfs/t1/38512/19/6668/512511/5cd375b8E4b83a598/4661470c834868d5.png\" data-url=\"jfs/t1/38512/19/6668/512511/5cd375b8E4b83a598/4661470c834868d5.png\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>"
    };

    static List<String[]> spu30 = new ArrayList<>();

    static {
        spu30.add(sku30);
    }


    static String[] sku31 = {"<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
            "                                                                                                                                                                                                <li class=\"img-hover\"><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 蓝紫色 喜上喜上眉梢三件套\" src=\"//img10.360buyimg.com/n5/jfs/t19138/1/857413821/373117/3c942d07/5aaa1a8cNf19ee0fb.jpg\" data-url=\"jfs/t19138/1/857413821/373117/3c942d07/5aaa1a8cNf19ee0fb.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 蓝紫色 喜上喜上眉梢三件套\" src=\"//img10.360buyimg.com/n5/jfs/t16837/89/855683353/439003/d244d85f/5aaa1aa1Na90e217f.jpg\" data-url=\"jfs/t16837/89/855683353/439003/d244d85f/5aaa1aa1Na90e217f.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 蓝紫色 喜上喜上眉梢三件套\" src=\"//img10.360buyimg.com/n5/jfs/t16774/98/782930252/454579/68169c44/5aaa1ab2Na1824736.jpg\" data-url=\"jfs/t16774/98/782930252/454579/68169c44/5aaa1ab2Na1824736.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 蓝紫色 喜上喜上眉梢三件套\" src=\"//img10.360buyimg.com/n5/jfs/t17719/18/847399762/371100/f26d0192/5aaa1aa4N07943273.jpg\" data-url=\"jfs/t17719/18/847399762/371100/f26d0192/5aaa1aa4N07943273.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 蓝紫色 喜上喜上眉梢三件套\" src=\"//img10.360buyimg.com/n5/jfs/t15067/158/2595522050/452946/8829f831/5aaa1ab9Na4b33273.jpg\" data-url=\"jfs/t15067/158/2595522050/452946/8829f831/5aaa1ab9Na4b33273.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 蓝紫色 喜上喜上眉梢三件套\" src=\"//img10.360buyimg.com/n5/jfs/t15154/317/2615551821/388889/298597f3/5aaa1ab9Nd42b4200.jpg\" data-url=\"jfs/t15154/317/2615551821/388889/298597f3/5aaa1ab9Nd42b4200.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                                            </ul>",
            "<ul class=\"lh\" style=\"position: absolute; width: 348px; height: 54px; top: 0px; left: 0px;\">\n" +
                    "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 桃红 喜上喜上眉梢三件套\" src=\"//img12.360buyimg.com/n5/jfs/t16042/334/2435751957/380712/9c018b27/5aaa1b16Na8d3c4e3.jpg\" data-url=\"jfs/t16042/334/2435751957/380712/9c018b27/5aaa1b16Na8d3c4e3.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"\"><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 桃红 喜上喜上眉梢三件套\" src=\"//img12.360buyimg.com/n5/jfs/t12820/263/1480455975/358460/6d6439c2/5a20bb28Nb9445062.jpg\" data-url=\"jfs/t12820/263/1480455975/358460/6d6439c2/5a20bb28Nb9445062.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li class=\"img-hover\"><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 桃红 喜上喜上眉梢三件套\" src=\"//img12.360buyimg.com/n5/jfs/t12952/300/1432326037/374098/14eeff21/5a20bb33N16bba267.jpg\" data-url=\"jfs/t12952/300/1432326037/374098/14eeff21/5a20bb33N16bba267.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 桃红 喜上喜上眉梢三件套\" src=\"//img12.360buyimg.com/n5/jfs/t12841/287/1468022022/354446/4e97b900/5a20bb38Ndad8253b.jpg\" data-url=\"jfs/t12841/287/1468022022/354446/4e97b900/5a20bb38Ndad8253b.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 桃红 喜上喜上眉梢三件套\" src=\"//img12.360buyimg.com/n5/jfs/t12457/345/1444150021/393086/fcd0e07/5a20bb3dNed08bde4.jpg\" data-url=\"jfs/t12457/345/1444150021/393086/fcd0e07/5a20bb3dNed08bde4.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                <li><img alt=\"苏绘 床品家纺 双人床单单件纯棉 全棉土布床上用品老粗布套件被单 桃红 喜上喜上眉梢三件套\" src=\"//img12.360buyimg.com/n5/jfs/t13234/343/1472909863/376420/35c2a023/5a20bb33Nb87b29e6.jpg\" data-url=\"jfs/t13234/343/1472909863/376420/35c2a023/5a20bb33Nb87b29e6.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
                    "                                                                                            </ul>"
    };

    static List<String[]> spu31 = new ArrayList<>();

    static {
        spu31.add(sku31);
    }

    static String[] sku33 = {"<ul class=\"lh\">\n" +
            "                                                                                                                                                                                                <li class=\"\"><img alt=\"苏绘（suhui)家纺 纯棉八件套双人床上用品全棉多件套手工织布被套床单1.8米/2.0米床撞色拼接 图片色 1.8米/2.0米\" src=\"//img14.360buyimg.com/n5/jfs/t1/72367/5/1607/229747/5cfefc5aE048b221c/788ad47eb6be0ce3.jpg\" data-url=\"jfs/t1/72367/5/1607/229747/5cfefc5aE048b221c/788ad47eb6be0ce3.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（suhui)家纺 纯棉八件套双人床上用品全棉多件套手工织布被套床单1.8米/2.0米床撞色拼接 图片色 1.8米/2.0米\" src=\"//img14.360buyimg.com/n5/jfs/t1/64013/30/1618/146172/5cfefc61E5a26af6a/35fd43616a52392c.jpg\" data-url=\"jfs/t1/64013/30/1618/146172/5cfefc61E5a26af6a/35fd43616a52392c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（suhui)家纺 纯棉八件套双人床上用品全棉多件套手工织布被套床单1.8米/2.0米床撞色拼接 图片色 1.8米/2.0米\" src=\"//img14.360buyimg.com/n5/jfs/t1/46769/11/1987/137493/5cfefc67E34caa0d6/7ad62d8ae8ea6fff.jpg\" data-url=\"jfs/t1/46769/11/1987/137493/5cfefc67E34caa0d6/7ad62d8ae8ea6fff.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"img-hover\"><img alt=\"苏绘（suhui)家纺 纯棉八件套双人床上用品全棉多件套手工织布被套床单1.8米/2.0米床撞色拼接 图片色 1.8米/2.0米\" src=\"//img14.360buyimg.com/n5/jfs/t1/44921/15/2105/170621/5cfefc6dEd2c0a96e/d0f94adb570b7d2c.jpg\" data-url=\"jfs/t1/44921/15/2105/170621/5cfefc6dEd2c0a96e/d0f94adb570b7d2c.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                <li class=\"\"><img alt=\"苏绘（suhui)家纺 纯棉八件套双人床上用品全棉多件套手工织布被套床单1.8米/2.0米床撞色拼接 图片色 1.8米/2.0米\" src=\"//img14.360buyimg.com/n5/jfs/t1/66852/10/1605/97120/5cfefc74E32892431/378562b809d523ea.jpg\" data-url=\"jfs/t1/66852/10/1605/97120/5cfefc74E32892431/378562b809d523ea.jpg\" data-img=\"1\" width=\"50\" height=\"50\"></li>\n" +
            "                                                                                            </ul>"
    };

    static List<String[]> spu33 = new ArrayList<>();

    static {
        spu33.add(sku33);
    }


    @Test
    public void jiexiImage() {
        for (int i = 0; i < spu33.size(); i++) {
            String[] skus = spu33.get(i);
            for (int j = 0; j < skus.length; j++) {
                jiexi(33, i, j, skus[j]);
            }
        }
    }

    private ArrayList<String> jiexi(Integer skuId, Integer specId, Integer id, String htm) {
        String serverString = htm;//网络请求数据，没有使用jsoup连接网络获取数据,感觉自己封装的网络请求时间更短
        Document document = Jsoup.parse(serverString);//可以使用Jsoup自带的网络请求方式：Jsoup.connect(url).timeout(5000).get();
        //String string = document.toString();
        //System.out.println("document:" + string);
        Elements imgsElements = document.select("img");//得到img标签
//		String divString = divsElements.toString();
//		System.out.println("divString:"+divString);
        //Elements lis = lisElements.select("li");//获得table中的所有的td标签
        ArrayList<String> urls = new ArrayList<>();
        int len = imgsElements.size();
        System.out.println("td的长度：" + len);
        for (int i = 0; i < imgsElements.size(); i++) {
            //将解析到的数据依次存入数据库，下标为偶数的为问题，奇数的为答案
            urls.add("http:" + imgsElements.get(i).attr("src").replace("n5", "n1"));//select("td").text()即可获得到标签中的内容
        }
        urls.forEach(s -> System.out.println(s));
        downloadPicture(urls, "/Users/chenjingang/Desktop/image/", skuId, specId, id);
        return urls;
    }

    private static void downloadPicture(List<String> urlList, String path, int skuId, int specId, int id) {
        URL url = null;
        try {
            for (int i = 0; i < urlList.size(); i++) {
                url = new URL(urlList.get(i));
                DataInputStream is = new DataInputStream(url.openStream());
                FileOutputStream os = new FileOutputStream(new File(path + skuId + "_" + specId + "-" + id + "-" + i + ".jpg"));

                byte[] bs = new byte[1024 * 1024];
                int len;
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }

                os.close();
                is.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    Elements pngs = doc.select("img[src$=.png]");// 所有引用 png 图片的元素
//  		 for(Element png:pngs){
//        String pngText=png.text();
//        String src=png.attr("src");//根据属性名获取src的路径
//        System.out.println(src+pngText);
//    }
//    //在id为tel的td标签里面添加一个value等于121212121的值 如:<td id="tel" value="121212121"></td>
//  		 doc.getElementById("tel").val("121212121");//
//    //在id为tel的td标签添加一个文本值 如:<td id="tel">121212121</td>
//

}
