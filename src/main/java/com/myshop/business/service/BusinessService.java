package com.myshop.business.service;

import com.myshop.business.dto.BusinessResponse;
import com.myshop.business.dto.CreateBusinessRequest;
import com.myshop.business.entity.Business;
import com.myshop.business.repository.BusinessRepository;
import com.myshop.common.exception.ResourceNotFoundException;
import com.myshop.user.entity.User;
import com.myshop.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public BusinessService(
            BusinessRepository businessRepository,
            UserRepository userRepository
    ) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BusinessResponse createBusiness(CreateBusinessRequest request) {

        User owner = userRepository.findById(request.ownerUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Owner user not found")
                );

        Business business = new Business(
                owner,
                request.name(),
                request.businessType(),
                request.phone(),
                request.email(),
                request.addressLine1(),
                request.addressLine2(),
                request.city(),
                request.state(),
                request.postalCode()
        );


        Business savedBusiness = businessRepository.save(business);

        return new BusinessResponse(
                savedBusiness.getId(),
                savedBusiness.getOwner().getId(),
                savedBusiness.getName(),
                savedBusiness.getBusinessType(),
                savedBusiness.getPhone(),
                savedBusiness.getEmail(),
                savedBusiness.getAddressLine1(),
                savedBusiness.getAddressLine2(),
                savedBusiness.getCity(),
                savedBusiness.getState(),
                savedBusiness.getPostalCode(),
                savedBusiness.getCountry(),
                savedBusiness.isActive(),
                savedBusiness.getCreatedAt()
        );
    }


    @Transactional(readOnly = true)
    public BusinessResponse getBusinessById(UUID businessId) {

        Business business = businessRepository.findById(businessId)
                .orElseThrow(()-> new ResourceNotFoundException("Business not found"));

        return new BusinessResponse(
                business.getId(),
                business.getOwner().getId(),
                business.getName(),
                business.getBusinessType(),
                business.getPhone(),
                business.getEmail(),
                business.getAddressLine1(),
                business.getAddressLine2(),
                business.getCity(),
                business.getState(),
                business.getPostalCode(),
                business.getCountry(),
                business.isActive(),
                business.getCreatedAt()
        );
    }
}