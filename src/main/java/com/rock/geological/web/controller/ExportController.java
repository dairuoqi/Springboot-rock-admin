package com.rock.geological.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rock.geological.constant.ApiConstant;
import com.rock.geological.res.ResponseMgr;
import com.rock.geological.utils.CSV2JSONHelper;
import com.rock.geological.utils.Excel2JSONHelper;

import com.rock.geological.utils.FileUtil;
import com.rock.geological.utils.VisitApiUtil;
import com.rock.geological.web.pojo.ApiProcess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@RestController
@RequestMapping("/excel")
public class ExportController {

    private final String baseExcelPath = "/Users/rock/Desktop/geological/geological/excel/";

    private final String baseCsvPath = "/Users/rock/Desktop/geological/geological/csv/";

    @PostMapping("/importExcel")
    public ResponseEntity<?> importExcel(MultipartFile multipartFile) throws Exception {
        FileUtil.makeDir(baseExcelPath);
        File newFile = new File(baseExcelPath + new Date().getTime() + multipartFile.getOriginalFilename());
        multipartFile.transferTo(newFile);
        JSONArray jsonArray = Excel2JSONHelper.readExcel(newFile, 0, 2);
        return ResponseEntity.ok(ResponseMgr.successWithData(jsonArray));
    }

    @PostMapping("/importCsv")
    public ResponseEntity<?> importCsv(MultipartFile multipartFile) throws Exception {
        FileUtil.makeDir(baseCsvPath);
        String path = baseCsvPath + new Date().getTime() + multipartFile.getOriginalFilename();
        File newFile = new File(path);
        multipartFile.transferTo(newFile);
        JSONArray jsonArray = CSV2JSONHelper.readCSV(path);
        return ResponseEntity.ok(ResponseMgr.successWithData(jsonArray));
    }

    /**
     * api 读取 excel 返回前端JsonArray
     */
    @PostMapping("/readExcel")
    public ResponseEntity<?> readExcel(@RequestBody ApiProcess params) {
//        String path = "/Users/rock/Desktop/现阶段须二岩性图odd.xlsx";

        try {
            String excelPath = VisitApiUtil.visitApiPost(ApiConstant.PYTHON_URL,params);
            File excelFile = new File(excelPath);
            JSONArray jsonArray = Excel2JSONHelper.readExcel(excelFile, 0, 2);
            return ResponseEntity.ok(ResponseMgr.successWithData(jsonArray));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ResponseMgr.err());
    }





}
