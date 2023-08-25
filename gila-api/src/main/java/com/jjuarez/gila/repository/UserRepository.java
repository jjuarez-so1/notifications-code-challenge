package com.jjuarez.gila.repository;

import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBySubscribedTopicsContaining(Topic topic);
}
