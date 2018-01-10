package gns.test.sso.dao;

import gns.test.sso.entity.SsoConfig;

public interface SsoConfigMapper {
    int deleteByPrimaryKey(String configKey);

    int insert(SsoConfig record);

    int insertSelective(SsoConfig record);

    SsoConfig selectByPrimaryKey(String configKey);

    int updateByPrimaryKeySelective(SsoConfig record);

    int updateByPrimaryKey(SsoConfig record);
}