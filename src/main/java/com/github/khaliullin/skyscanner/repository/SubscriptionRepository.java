package com.github.khaliullin.skyscanner.repository;

import com.github.khaliullin.skyscanner.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByEmail(String email);
}
