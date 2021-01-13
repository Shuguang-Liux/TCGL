package com.record.tcgl.util;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.*;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Shuguang_Liux
 */
public class ExportExcel<T> {
    public void exportExcel(String[] headers, Collection<T> dadaset, String filename, HttpServletResponse response) {
        // 声明一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为30个字节
        sheet.setDefaultColumnWidth((short) 30);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        try {
            // 遍历集合产生数据行
            Iterator<T> it = dadaset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                // 利用反射根据java bean熟悉的先后顺序，动态调用getXXX()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (int i = 0; i < headers.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class tClass = t.getClass();
                    Method getMethod = tClass.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    // 判断的值的类型后进行强制转换
                    String textValue = null;
                    // 其他数据类型都当做字符串简单处理
                    if (value != null && value != "") {
                        textValue = value.toString();
                    }
                    if (textValue != null) {
                        XSSFRichTextString richTextString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richTextString);
                    }
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        getExportFile(workbook, filename, response);
    }

    // 产生输出
    public void getExportFile(XSSFWorkbook workbook, String name, HttpServletResponse response) {
        BufferedOutputStream bof = null;
        try {
            String fileName = name + ".xlsx";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            bof = new BufferedOutputStream(response.getOutputStream());
            workbook.write(bof);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bof != null) {
                try {
                    bof.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

