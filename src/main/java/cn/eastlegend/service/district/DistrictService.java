package cn.eastlegend.service.district;

import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 10:28
 **/
public interface DistrictService {
    /**
     * 查询小区列表
     * @return 小区列表
     * @param params
     */
    List<Map<String,Object>> getAllDistrict(Map<String, Object> params);
}
