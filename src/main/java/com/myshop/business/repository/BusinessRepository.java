package com.myshop.business.repository;

import com.myshop.business.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
     List<Business> findByOwnerId(UUID ownerId);
}
