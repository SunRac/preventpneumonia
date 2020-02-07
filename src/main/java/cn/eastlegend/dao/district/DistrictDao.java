package cn.eastlegend.dao.district;

import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 10:30
 **/
public interface DistrictDao {
    /**
     * 查询小区列表
     * @return 小区列表
     */
    List<Map<String, Object>> getAllDistrict(Map<String,Object> params);
}
