package cn.suancloud.springBoot.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.suancloud.springBoot.model.Role;

/**
 * Created by admin on 2018/4/16.
 */
@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
  @Query("FROM Role WHERE role_name=?1")
  Role getRole(String role_name);
}
