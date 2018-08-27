package com.rock.geological.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSV2JSONHelper {

    private String fileName = null;
    private static BufferedReader br = null;
    private static List<String> list;


    public CSV2JSONHelper(String fileName) throws Exception {
        this.fileName = fileName;
        list = new ArrayList<>();
        br = new BufferedReader(new FileReader(fileName));
        String stemp;
        while ((stemp = br.readLine()) != null) {
            list.add(stemp);
        }
    }

    public List getList() {
        return list;
    }

    /**
     * 获取行数
     *
     * @return
     */
    public static int getRowNum() {
        return list.size();
    }

    /**
     * 获取列数
     *
     * @return
     */
    private int getColNum() {
        if (!list.toString().equals("[]")) {
            if (list.get(0).contains(",")) {// csv为逗号分隔文件
                return list.get(0).split(",").length;
            } else if (list.get(0).trim().length() != 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 获取指定行
     *
     * @param index
     * @return
     */
    private static String getRow(int index) {
        if (list.size() != 0) {
            return (String) list.get(index);
        } else {
            return null;
        }
    }

    /**
     * 获取指定列
     *
     * @param index
     * @return
     */
    public String getCol(int index) {
        if (this.getColNum() == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        String tmp = null;
        int colnum = this.getColNum();
        if (colnum > 1) {
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                tmp = it.next().toString();
                sb = sb.append(tmp.split(",")[index] + ",");
            }
        } else {
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                tmp = it.next().toString();
                sb = sb.append(tmp + ",");
            }
        }
        String str = new String(sb.toString());
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * 获取某个单元格
     *
     * @param row
     * @param col
     * @return
     */
    public String getString(int row, int col) {
        String temp = null;
        int colnum = this.getColNum();
        if (colnum > 1) {
            temp = list.get(row).toString().split(",")[col];
        } else if (colnum == 1) {
            temp = list.get(row).toString();
        } else {
            temp = null;
        }
        return temp;
    }

    private static void CsvClose() throws Exception {
        br.close();
    }

    /**
     * 去表头
     **/
    public String removehead(String str) {
        String[] str_1 = str.split(",");
        String sb = new String();
        for (int i = 1; i < str_1.length; i++) {
            sb = sb + str_1[i] + ",";
        }
        return sb;
    }


    private static String[] getHeader() {
        //1.第一行就是Header
        String HeaderString = list.get(0);
        return HeaderString.split(",");

    }

    public static JSONArray readCSV(String path) {
        JSONArray array = new JSONArray();
        try {
            new CSV2JSONHelper(path);
            String[] Header = getHeader();
            int row = getRowNum();
            for (int i = 1; i < row; i++) {
                JSONObject jsonobject = new JSONObject(16, true);
                String RowValue = getRow(i);
                String[] cell = RowValue.split(",");
                for (int j = 0; j < cell.length; j++) {
                    jsonobject.put(Header[j], cell[j]);
                }
                array.add(jsonobject);
            }
            CsvClose();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return array;
    }

}
