package com.cp.repository;

import com.cp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author ChengPeng
 * @create 2019-10-28 10:54
 */
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    /**
     * 根据时间查询上传的图片
     */
    @Query(value = "select * ,ABS(NOW() - upload_date)as diffTime from t_user order by diffTime asc limit 0,1",nativeQuery=true)
    public User findByUserImage();

}

