package com.swagger.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<SystemSetting,Long> {
    SystemSetting findByKeyAndIsDeletedIsFalse(String key);
}
