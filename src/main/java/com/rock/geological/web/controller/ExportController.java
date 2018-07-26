package com.rock.geological.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.rock.geological.res.ResponseMgr;
import com.rock.geological.utils.Excel2JSONHelper;

import com.rock.geological.utils.FileUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@RestController
@RequestMapping("/excel")
public class ExportController {

    private final String basePath = "/Users/rock/Desktop/geological/geological/excel/";

    @PostMapping("/importExcel")
    public ResponseEntity<?> importExcel(MultipartFile multipartFile) throws Exception {
        FileUtil.makeDir(basePath);
        File newFile = new File(basePath + new Date().getTime() + multipartFile.getOriginalFilename());
        multipartFile.transferTo(newFile);
        JSONArray jsonArray = Excel2JSONHelper.readExcel(newFile, 0, 2);
        return ResponseEntity.ok(ResponseMgr.successWithData(jsonArray));
    }


}
