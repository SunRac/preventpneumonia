package cn.eastlegend.dao.district;

import cn.eastlegend.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 10:55
 **/
@Repository
public class DistrictDaoImpl extends BaseDaoImpl implements DistrictDao {

    /**
     * 查询小区列表
     * @return 小区列表
     */
    @Override
    public List<Map<String, Object>> getAllDistrict(Map<String,Object> params) {
        return queryForList("getAllDistrict", params);
    }
}
