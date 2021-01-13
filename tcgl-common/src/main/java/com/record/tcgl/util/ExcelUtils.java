package com.record.tcgl.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.util
 * @Description ToDo
 * @Date 2020/11/18 10:24
 **/
public class ExcelUtils {
    /**
     * 构建一个Excel表格
     */
    public static HSSFWorkbook createExcel(Map<String, String[]> map, String[] str, String title) {
        //创建一个HSSFWorkbook
        //这个将构建一个新的Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //在HSSFWorkbook中添加有一个HSSFSheet
        //构建一个新的工作簿
        //并命名
        HSSFSheet sheet = wb.createSheet("工作簿一");

        /**
         *构建一个标题行
         */
        //创建单元格对象
        HSSFCellStyle styleTitle = wb.createCellStyle();
        //将单元格的格式设置为居中
        styleTitle.setAlignment(HorizontalAlignment.CENTER);
        //创建一个字体格式
        HSSFFont font = wb.createFont();
        //设置字体格式
        font.setFontHeightInPoints((short) 20);
        //将这个字体放入到单元格对象中
        styleTitle.setFont(font);
        //创建工作簿的标题行
        //CellRangeAddress参数 1起始行号 2终止行号 3起始列号 4终止列号
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 11));
        //声明标题开始行
        HSSFRow row = sheet.createRow(0);
        //创建一个单元格
        HSSFCell hssfCell = row.createCell(0);
        //写入标题内容
        hssfCell.setCellValue(title);
        //将上面声明的标题单元格样式放入单元格
        hssfCell.setCellStyle(styleTitle);

        //创建正文单元格对象
        //创建单元格对象
        HSSFCellStyle style = wb.createCellStyle();
        //设置单元格样式居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //指定列宽
        sheet.setDefaultColumnWidth(20);
        //声明正文开始行，这里剔除了标题行
        HSSFRow row1 = sheet.createRow(2);
        //创建正文单元格
        //这里写入单元格标题行
        //读取传入的数组
        HSSFCell cell = null;
        for (int x = 0; x < str.length; x++) {
            cell = row1.createCell((short) x);
            cell.setCellValue(str[x]);
            cell.setCellStyle(style);
        }

        //写入实体数据,map中list数据的顺序必须和数组str中的顺序一致
        int i = 2;
        HSSFCell cel = null;
        //单元格样式对象
        HSSFCellStyle sty = wb.createCellStyle();
        //循环读取传入的集合对象
        for (String st : map.keySet()) {
            //设置行
            row1 = sheet.createRow(i + 1);
            //获取map中的list
            String[] strings = map.get(st);
//            List<String> list = map.get(st);
            //创建单元格，并设置值
            for (int j = 0; j < str.length; j++) {
                //判断，根据自己的业务逻辑调整，进行日期转换
//                if (j == 0 || j == 13 || j == 14 || j == 15) {
//                    cel = row.createCell((short) j);
//                    cel.setCellType(CellType.NUMERIC);
//                    cel.setCellValue(list.get(j));
//                    //更改excel单元格自定义日期格式
//                    HSSFDataFormat format = wb.createDataFormat();
//                    sty.setDataFormat(format.getFormat("M月d日"));
//                    cel.setCellStyle(sty);
//                } else {
                    //创建单元格
                    cel = row1.createCell((short) j);
                    //写入值
//                    cel.setCellValue(strings.get(j));
                    //写入样式
                cel.setCellValue(strings[j]);
                    cel.setCellStyle(style);
//                }
            }
            //迭代行
            i++;
        }
        return wb;
    }

    /**
     * 读取excel文件
     *
     * @param file 读取的文件对象
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> readExcel(File file) throws Exception {
        //打开需要读取的文件
        FileInputStream inputStream = new FileInputStream(file);
        //读取工作簿
        XSSFWorkbook wordBook = new XSSFWorkbook(inputStream);
        //读取工作表,从0开始
        XSSFSheet sheet = wordBook.getSheetAt(0);
        //存储数据集合
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        //存储单元格标题数据
        Map<Integer, String> stringMap = new HashMap<Integer, String>();
        //获取表格内容的最后一行的行数，获得循环次数
        int lastRowNum = sheet.getLastRowNum();
        //循环按行读取数据
        for (int i = 0; i <= lastRowNum; i++) {
            //存储单行数据集合
            Map<String, String> map = new HashMap<String, String>();
            //读取第i行
            XSSFRow row = sheet.getRow(i);
            //获取每一行的最后一列的列号，即总列数，循环次数
            int columnNum = row.getLastCellNum();
            //循环读取单行数据
            for (int j = 0; j < columnNum; j++) {
                //读取弟j个单元格对象
                XSSFCell cell = row.getCell(j);
                //设置单元格的数据类型数据类型
                cell.setCellType(CellType.STRING);
                //读取单元格的值
                String value = cell.getStringCellValue();
                //第一行是存储表头数据
                if (i == 0) {
                    //单元格值
                    stringMap.put(j, value);
                } else {
                    //否则存储表头对应单元格数据
                    map.put(stringMap.get(j), value);
                }
            }
            if (i != 0) {
                //将一行数据存入集合
                mapList.add(map);
            }
        }
        //关闭输入流
        inputStream.close();
        //关闭工作簿
        wordBook.close();
        return mapList;
    }



}
