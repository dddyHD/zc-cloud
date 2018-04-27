package cn.suancloud.springBoot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.suancloud.springBoot.model.User;

/**
 * Created by admin on 2018/4/16.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {
  User findByUsername(String username);
}

