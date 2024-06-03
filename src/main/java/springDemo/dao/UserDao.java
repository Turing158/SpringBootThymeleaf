package springDemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import springDemo.entity.User;

@Mapper
public interface UserDao {
    @Select("select * from springbootdata.user where username=#{username}")
    User findUserByUsername(String username);
}
