package gns.test.sso.dao;

import gns.test.sso.entity.SsoUser;

public interface SsoUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SsoUser record);

    int insertSelective(SsoUser record);

    SsoUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SsoUser record);

    int updateByPrimaryKey(SsoUser record);
}