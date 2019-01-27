package stao.BMS.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Slf4j
public class UploadUtil {

    public static final String prefix = "/../img/";

    public static String upload(MultipartFile file, HttpServletRequest request) {
        String path = null;// 文件路径
        if (file != null) {// 判断上传的文件是否为空
            String type;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            if (fileName == null || "".equals(fileName)) return path;
            type = fileName.lastIndexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            if (type != null) {// 判断文件类型是否为空
                // 项目在容器中实际发布运行的根路径
                String realPath = request.getSession().getServletContext().getRealPath("/");
                log.info("realPath:{}", realPath);
                // 自定义的文件名称
                String trueFileName =prefix + System.currentTimeMillis() + fileName;
                // 设置存放图片文件的路径
                path = realPath + trueFileName;
                log.info("存放图片文件的路径:{}", path);
                try {
                    file.transferTo(new File(path));
                } catch (Exception e) {
                    log.error("stao.BMS.utils.UploadUtil.upload():", e);
                    return null;
                }
            }
            log.info("上传成功");
        }
        return path;
    }
}
