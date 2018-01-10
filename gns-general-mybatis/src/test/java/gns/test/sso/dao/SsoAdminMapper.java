package gns.test.sso.dao;

import gns.test.sso.entity.SsoAdmin;

public interface SsoAdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(SsoAdmin record);

    int insertSelective(SsoAdmin record);

    SsoAdmin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(SsoAdmin record);

    int updateByPrimaryKey(SsoAdmin record);
}