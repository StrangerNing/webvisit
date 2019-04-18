package com.webvisit.utils;

import com.webvisit.common.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/18
 */

public class MultipartFileUtil {
    public static String getExtFilename(MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        if (filename != null) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        }
        throw new BusinessException("获取文件扩展名失败");
    }
}
