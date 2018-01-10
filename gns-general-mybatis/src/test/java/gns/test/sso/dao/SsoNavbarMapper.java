package gns.test.sso.dao;

import gns.test.sso.entity.SsoNavbar;

public interface SsoNavbarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsoNavbar record);

    int insertSelective(SsoNavbar record);

    SsoNavbar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsoNavbar record);

    int updateByPrimaryKey(SsoNavbar record);
}