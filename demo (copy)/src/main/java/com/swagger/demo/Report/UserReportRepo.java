package com.swagger.demo.Report;

import com.swagger.demo.User.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReportRepo extends JpaRepository<UserDTO,Long> {
    @Query(nativeQuery = true,value = "select * from user_dto")
    List<UserDTO> getUserList();
}
