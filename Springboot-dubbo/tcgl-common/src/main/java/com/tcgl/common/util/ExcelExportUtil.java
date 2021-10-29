package com.tcgl.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.util
 * @Description ToDo
 * @Date 2020/9/19 21:08
 **/
public class ExcelExportUtil {
    private SXSSFWorkbook workbook = null;
    //表头
    private String[] heardList;
    //需要填充的数据信息
    private List<Object[]> data = new ArrayList<>();

    private Sheet sheet = null;
    //字体大小
    private int fontSize = 14;
    //行高
    private int rowHeight = 30;
    //列宽
    private int columWidth = 200;
    //工作表
    private String sheetName;

    public ExcelExportUtil(SXSSFWorkbook workbook, Sheet sheet) {
        this.workbook = workbook;
        this.sheet = sheet;
    }

    public ExcelExportUtil(String[] heardList, List<Object[]> data, String sheetName) {
        this.heardList = heardList;
        this.data = data;
        this.sheetName = sheetName;
    }

    /**
     * 开始导出数据信息
     *
     */
    public SXSSFWorkbook exportExport() throws IOException {
        Integer rowaccess = 1000;// 内存中缓存记录行数，以免内存溢出

        SXSSFWorkbook workbook = new SXSSFWorkbook(rowaccess);

        Sheet sheet = workbook.createSheet(sheetName);

        // 产生表格标题行
        Row titleRow = sheet.createRow(0);
        Cell cellTiltle = titleRow.createCell(0);
        CellStyle columnTopStyle = this.getColumnTopStyle(workbook);// 获取列头样式对象
        sheet.setDefaultColumnWidth(100);
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (heardList.length - 1)));
        cellTiltle.setCellStyle(columnTopStyle);
        cellTiltle.setCellValue(sheetName);

        Cell cell = null;

        CellStyle headStyle = this.getHeadStyle(workbook);

        // 定义所需列数
        int columnNum = heardList.length;
        Row headRow = sheet.createRow(2); // 在索引2的位置创建行(最顶端的行开始的第二行)

        //表头
        for (int n = 0; n < columnNum; n++) {
            Cell cellRowName = headRow.createCell(n); // 创建列头对应个数的单元格
            cellRowName.setCellType(CellType.STRING); // 设置列头单元格的数据类型
            HSSFRichTextString text = new HSSFRichTextString(heardList[n]);
            cellRowName.setCellValue(heardList[n]); // 设置列头单元格的值
            cellRowName.setCellStyle(headStyle); // 设置列头单元格样式
            sheet.setColumnWidth(n,256*20);
        }

        CellStyle bodyStyle = this.getBodyStyle(workbook);

        // 表体数据
        for (int i = 0; i < data.size(); i++) {

            Object[] obj = data.get(i);
            Row row = sheet.createRow(i + 3);// 创建所需的行数

            for (int j = 0; j < obj.length; j++) {

                cell = row.createCell(j);
                String str = String.valueOf(StringUtils.isEmpty(obj[j]) ? "" : obj[j]);
                cell.setCellValue(str); // 单元格的值
                cell.setCellStyle(bodyStyle); // 单元格的样式
            }
        }

        //导出数据
//        try {
//            //设置Http响应头告诉浏览器下载这个附件
//            response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".xls");
//            OutputStream outputStream = response.getOutputStream();
//            workbook.write(outputStream);
//            workbook.close();
//            outputStream.flush();
//            outputStream.close();
//            return workbook;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new IOException("导出Excel出现严重异常，异常信息：" + ex.getMessage());
//        }

        return workbook;

    }
    /*
     * 列头单元格样式
     */
    public CellStyle getColumnTopStyle(SXSSFWorkbook workbook) {

        // 设置字体
        Font font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 18);
        // 字体加粗
        font.setBold(true);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        CellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        // 设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }

    // 设置表头的单元格样式
    public CellStyle getHeadStyle(SXSSFWorkbook workbook) {
        // 创建单元格样式
        CellStyle cellStyle = workbook.createCellStyle();

        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.index);
        // 设置填充字体的样式
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // cellStyle.setShrinkToFit(true);
        // 创建单元格内容显示不下时自动换行
        //cellStyle.setWrapText(true);

        // 设置单元格字体样式
        XSSFFont font = (XSSFFont) workbook.createFont();
        //  font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 这是字体加粗
        font.setFontName("宋体");// 设置字体的样式
        font.setFontHeight(12);// 设置字体的大小
        cellStyle.setFont(font);// 将字体填充到表格中去

        // 设置单元格边框为细线条（上下左右）
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        return cellStyle;

    }

    // 设置表体的单元格样式
    public CellStyle getBodyStyle(SXSSFWorkbook workbook) {
        // 创建单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建单元格内容不显示自动换行
        //cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = (XSSFFont) workbook.createFont();
        //   font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 这是字体加粗
        font.setFontName("宋体");// 设置字体
        font.setFontHeight(10);// 设置字体的大小
        cellStyle.setFont(font);// 将字体添加到表格中去

        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        return cellStyle;

    }


    /**
     * 检查数据配置问题
     *
     * @throws IOException 抛出数据异常类
     */
    protected void checkConfig() throws IOException {
//        if (heardKey == null || heardList.length == 0) {
//            throw new IOException("列名数组不能为空或者为NULL");
//        }

        if (fontSize < 0 || rowHeight < 0 || columWidth < 0) {
            throw new IOException("字体、宽度或者高度不能为负值");
        }

//        if (Strings.isNullOrEmpty(sheetName)) {
//            throw new IOException("工作表表名不能为NULL");
//        }
    }
}
