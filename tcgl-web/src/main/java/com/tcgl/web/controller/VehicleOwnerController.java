package com.tcgl.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcgl.common.util.ExcelExportUtil;
import com.tcgl.common.util.ExcelUtils;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;
import com.tcgl.web.service.VehicleOwnerService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 车主控制器
 *
 * @author Shuguang_Liux
 * @date 2021/06/28 13:51
 */
@RestController
@RequestMapping("vehicleOwner")
public class VehicleOwnerController {

    @Autowired
    private VehicleOwnerService vehicleOwnerService;


    /**
     * 插入车主
     *
     * @param param 参数
     * @return {@link ResultVo<String>}
     */
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public ResultVo<?> insertVehicleOwner(@RequestBody JSONObject param){
        return vehicleOwnerService.insertVehicleOwnerAndPayment(param);
    }


    /**
     * 出口车主
     *
     * @param response 响应
     * @param param    参数
     */
    @RequestMapping("exportVehicleOwner")
    public void exportVehicleOwner(HttpServletResponse response, @RequestBody Map<String, Object> param) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        try {
            ResultVo<Map<String, Object>> vehicleOwnerVo = vehicleOwnerService.exportVehicleOwner(param);
            if (!vehicleOwnerVo.getSuccess()){
                try {
                    resultVo.setMessage(vehicleOwnerVo.getMessage());
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter printWriter = null;
                    printWriter = response.getWriter();
                    String str = JSON.toJSONString(vehicleOwnerVo);
                    printWriter.write(str);
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            OutputStream outputStream = response.getOutputStream();
            String sheetName = "用户表";
            ExcelExportUtil excelExportUtil = new ExcelExportUtil((String[]) vehicleOwnerVo.getResult().get("titles"),(List<Object[]>)vehicleOwnerVo.getResult().get("dataList"),sheetName);
            SXSSFWorkbook sxssfWorkbook = excelExportUtil.exportExport();
            // 如果文件名有中文，必须URL编码
            String  fileName1 = URLEncoder.encode(sheetName, StandardCharsets.UTF_8);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName1+".xlsx");
            sxssfWorkbook.write(outputStream);
            sxssfWorkbook.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
//            logger.error(e.toString(), e);
            try {
                resultVo.setMessage("服务器遇到了问题...");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                String resStr = JSON.toJSONString(resultVo);
                writer.write(resStr);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @Author Shuguang_Liux
     * @Description TODO 取VehicleOwner和AccessRecordHistory对应列表信息
     * @Date 2021/4/18 23:00
     * @Param [com.alibaba.fastjson.JSONObject]
     * @return com.record.tcgl.vo.ResultVo<com.record.tcgl.entity.VehicleOwnerEntity>
     **/
    @RequestMapping("getVehicleOwnerList")
    public ResultVo<VehicleOwnerEntity> getVehicleOwnerAndAccessRecordHistory(@RequestBody JSONObject param){
        return vehicleOwnerService.getVehicleOwnerAndAccessRecordHistory(param);
    }

    /**
     * 导出车辆以及所有人信息新
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping("export")
    public void exportTestNew(HttpServletRequest request,HttpServletResponse response, @RequestBody Map<String, Object> param){
        String[] headTitles ={"车辆牌照", "车辆所有人", "创建时间", "创建人", "更新时间", "更新人", "有效状态",
                "进园时间", "出园时间","入园时长统计","价格","是否出园","次数","是否预付费用户"};
        Map<String, String[]> map = vehicleOwnerService.exportVehicleOwnerAndHistory(param,headTitles.length);
        String title = "用户表";
        //通过工具类获取到了一个文件对象
        HSSFWorkbook wb = ExcelUtils.createExcel(map, headTitles, title);
        OutputStream os = null;
        try {
            // 创建一个普通输出流
            os = response.getOutputStream();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = formatter.format(new Date());
            // 请求浏览器打开下载窗口
            response.reset();
            response.setCharacterEncoding("UTF-8");
            // Content-disposition 告诉浏览器以下载的形式打开
            String header = request.getHeader("User-Agent").toUpperCase();
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, "utf-8");
                // IE下载文件名空格变+号问题
                fileName = fileName.replace("+", "%20");
            } else {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            }
            // 要保存的文件名,保存为.xls文件，HSSFWorkbook只支持xls文件
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 直接用数组缓冲输出流输出
            wb.write(os);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
