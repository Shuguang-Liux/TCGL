package com.record.tcgl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.record.tcgl.entity.VehicleOwnerEntity;
import com.record.tcgl.service.VehicleOwnerService;
import com.record.tcgl.util.ExcelExportUtil;
import com.record.tcgl.vo.ResultVo;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.controller
 * @Description ToDo
 * @Date 2020/9/9 1:13
 **/
@RestController
@RequestMapping("/vehicleOwner")
public class VehicleOwnerController {
    @Autowired
    private VehicleOwnerService vehicleOwnerService;

    /**
     * 插入信息
     * @param param
     * @return
     */
    @RequestMapping("/insert")
    public ResultVo<String> insertVehicleOwner(@RequestBody JSONObject param){
        return vehicleOwnerService.insertVehicleOwnerAndPayment(param);
    }

    @RequestMapping("/exportVehicleOwner")
    public void exportVehicleOwer(HttpServletResponse response, @RequestBody Map<String, Object> param) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        try {
            ResultVo<Map<String, Object>> vehicleOwnerVo = vehicleOwnerService.exportVehicleOwer(param);
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
            String  fileName1 = URLEncoder.encode(sheetName, "UTF-8");
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
        return;
    }

    /**
     * 取VehicleOwner和AccessRecordHistory对应列表信息
     * @param param
     * @return
     */
    @RequestMapping("/getVehicleOwnerList")
    public ResultVo<VehicleOwnerEntity> getVehicleOwnerAndAccessRecordHistory(@RequestBody JSONObject param){
        ResultVo<VehicleOwnerEntity> resultVo = vehicleOwnerService.getVehicleOwnerAndAccessRecordHistory(param);
        return resultVo;
    }


}
