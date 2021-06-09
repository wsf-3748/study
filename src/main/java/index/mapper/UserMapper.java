package index.mapper;

import index.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<User> selectUserInfo(@Param("user") Map<String, Object> user);

    List<User> selectUserInfo2(@Param("user") User user);

    List<User> selectUserInfo3(@Param("user") User user);
}
