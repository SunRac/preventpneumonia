package cn.eastlegend.control;

import cn.eastlegend.service.district.DistrictService;
import cn.eastlegend.util.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 11:30
 **/
@Controller
public class DistrictController {
    private static Logger logger = LoggerFactory.getLogger(DistrictController.class);

    @Resource
    DistrictService districtService;

    @RequestMapping(value = {"/","/home","/index"})
    public String getDistrict() {
        return "preventPneumonia";
    }

//    通过路径变量接收输入 PathVariable String cityName
//    @RequestMapping("/district/{cityName}")

    /**
     * 通过请求参数接收输入
     * @param cityName
     * @return 小区信息组成的列表
     */
    @RequestMapping("/district")
    @ResponseBody
    public Object getDistrictByCityname(@RequestParam("cityName") String cityName) {
        Map<String, Object> params = new HashMap<>();
        if(!cityName.endsWith("市")){
            cityName += "市";
        }
        params.put("cityName", cityName);
        List<Map<String, Object>> districts = districtService.getAllDistrict(params);
        //TODO 定义一个返回值模板
        return districts;
    }
}
