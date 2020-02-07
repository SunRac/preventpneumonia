package cn.eastlegend.service.district;

import cn.eastlegend.dao.district.DistrictDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc
 * @createTime 2020/1/17 10:56
 **/
@Service
public class DistrictServiceImpl implements DistrictService {

    @Resource
    public DistrictDao districtDao;
    /**
     * 查询用户
     *
     * @return 用户列表
     * @param params
     */
    @Override
    public List<Map<String, Object>> getAllDistrict(Map<String, Object> params) {
        return districtDao.getAllDistrict(params);
    }
}
