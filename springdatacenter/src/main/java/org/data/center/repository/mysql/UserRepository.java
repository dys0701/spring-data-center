package org.data.center.repository.mysql;

import org.data.center.repository.mysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickNameContains(String nickName);

    /**
     * @return List<User>
     * @Query value is sql, nativeQuery run with t-sql
     * <p>
     * User can change another entity, but you should change sql result column
     */
    @Query(value = "select * from user ", nativeQuery = true)
    List<User> queryUsers();
}
