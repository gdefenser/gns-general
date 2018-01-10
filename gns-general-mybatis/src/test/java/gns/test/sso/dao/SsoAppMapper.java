package gns.test.sso.dao;

import gns.test.sso.entity.SsoApp;

public interface SsoAppMapper {
    int deleteByPrimaryKey(String appId);

    int insert(SsoApp record);

    int insertSelective(SsoApp record);

    SsoApp selectByPrimaryKey(String appId);

    int updateByPrimaryKeySelective(SsoApp record);

    int updateByPrimaryKey(SsoApp record);
}