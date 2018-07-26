package com.rock.geological.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.*;


public class Excel2JSONHelper {

    //常亮，用作第一种模板类型，如下图
    private static final int HEADER_VALUE_TYPE_Z = 1;
    //第二种模板类型，如下图
    private static final int HEADER_VALUE_TYPE_S = 2;

    public static void main(String[] args) {
        File dir = new File("/Users/rock/Desktop/现阶段须二岩性图odd.xlsx");
        Excel2JSONHelper excelHelper = getExcel2JSONHelper();
        //dir文件，0代表是第一行为保存到数据库或者实体类的表头，一般为英文的字符串，2代表是第二种模板，
        JSONArray jsonArray = excelHelper.readExcel(dir, 0, 2);
        System.out.println(jsonArray.toString());
    }

    private static Excel2JSONHelper getExcel2JSONHelper() {
        return new Excel2JSONHelper();
    }



    private static boolean fileNameFileter(File file) {
        boolean endsWith = false;
        if (file != null) {
            String fileName = file.getName();
            endsWith = fileName.endsWith(".xls") || fileName.endsWith(".xlsx");
        }
        return endsWith;
    }



    private static Row getHeaderRow(Sheet sheet, int index) {
        Row headerRow = null;
        if (sheet != null) {
            headerRow = sheet.getRow(index);
        }
        return headerRow;
    }


    private static Object getCellValue(Row row, int cellIndex, FormulaEvaluator formula) {
        Cell cell = row.getCell(cellIndex);
        if (cell != null) {
            switch (cell.getCellType()) {
                //String类型
                case Cell.CELL_TYPE_STRING:
                    return cell.getRichStringCellValue().getString();


                //number类型
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().getTime();
                    } else {
                        return cell.getNumericCellValue();
                    }
                    //boolean类型
                case Cell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue();
                //公式
                case Cell.CELL_TYPE_FORMULA:
                    return formula.evaluate(cell).getNumberValue();
                default:
                    return null;
            }
        }
        return null;
    }


    private static String getHeaderCellValue(Row headerRow, int cellIndex, int type) {
        Cell cell = headerRow.getCell(cellIndex);
        String headerValue = null;
        if (cell != null) {
            //第一种模板类型
            if (type == HEADER_VALUE_TYPE_Z) {
                headerValue = cell.getRichStringCellValue().getString();
                int l_bracket = headerValue.indexOf("（");
                int r_bracket = headerValue.indexOf("）");
                if (l_bracket == -1) {
                    l_bracket = headerValue.indexOf("(");
                }
                if (r_bracket == -1) {
                    r_bracket = headerValue.indexOf(")");
                }
                headerValue = headerValue.substring(l_bracket + 1, r_bracket);
            } else if (type == HEADER_VALUE_TYPE_S) {
                //第二种模板类型
                headerValue = cell.getRichStringCellValue().getString();
            }
        }
        return headerValue;
    }


    /**
     * 读取excel并按照列顺序返回
     * @param file
     * @param headerIndex
     * @param headType
     * @return
     */
    public static JSONArray readExcel(File file, int headerIndex, int headType) {
        JSONArray jsonArray = new JSONArray();
        if (!fileNameFileter(file)) {
            return null;
        } else {
            try {
                WorkbookFactory wbFactory = new WorkbookFactory();
                Workbook wb = wbFactory.create(file);
                Sheet sheet = wb.getSheetAt(0);
                Row headerRow = getHeaderRow(sheet, headerIndex);
                //读取数据
                FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
                for (int r = headerIndex + 1; r <= sheet.getLastRowNum(); r++) {
                    //保证JSONArray有序
                    JSONObject jsonObject = new JSONObject(16,true);
                    Row dataRow = sheet.getRow(r);
                    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                    for (int h = 0; h < dataRow.getLastCellNum(); h++) {
                        //表头为key
                        String key = getHeaderCellValue(headerRow, h, headType);
                        //数据为value
                        Object value = getCellValue(dataRow, h, formula);
                        if (!key.equals("") && !key.equals("null") && key != null) {
                            map.put(key, value);
                            jsonObject.put(key,value);
                        }
                    }
                    jsonArray.add(jsonObject);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }
}
