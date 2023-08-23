package com.jjuarez.gila.repository;

import com.jjuarez.gila.entity.BroadcastChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastChannelRepository extends JpaRepository<BroadcastChannel, Long> {
    BroadcastChannel findByName(String name);
}
