package personal.bs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import personal.bs.domain.vo.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${SKU_IMG_PATH}")
    private String SKU_IMG_PATH;

    @RequestMapping("/upload")
    public Result upload(List<MultipartFile> file) {
        ArrayList<String> urls = new ArrayList<>();
        for (MultipartFile file1 : file) {
            String originalFilename = file1.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String id = UUID.randomUUID().toString().replace("-", "");
            //图片完整路径
            String url = "/img/skuImg/" + id + suffix;
            System.out.println("上传路径：" + url);
            //将图片保存到static文件夹里
            try {
                file1.transferTo(new File(SKU_IMG_PATH + id + suffix));
                urls.add(url);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, "上传失败");
            }
        }
        return new Result(true, Arrays.toString(urls.toArray()));
    }
}
