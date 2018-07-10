package com.rock.geological.web.controller;

import com.rock.geological.utils.ExcelUtils;
import com.rock.geological.web.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExportController {
    @GetMapping("export")
    public void export(HttpServletResponse response){

        //模拟从数据库获取需要导出的数据
        List<User> personList = new ArrayList<>();
        User person1 = new User();
        person1.setUsername("dai");
        personList.add(person1);
        //导出操作
        ExcelUtils.exportExcel(personList,"花名册","草帽一伙",User.class,"海贼王.xls",response);
    }

    @PutMapping("importExcel")
    public void importExcel(){
        String filePath = "/Users/rock/Desktop/现阶段须二岩性图odd.xls";
        //解析excel，
        List<User> personList = ExcelUtils.importExcel(filePath,1,1,User.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");
        //TODO 保存数据库
    }


}
