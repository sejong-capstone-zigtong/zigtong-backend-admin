package capstone.zigtong.adminserver.domain.businessType.service;

import capstone.zigtong.adminserver.domain.businessType.BusinessType;
import capstone.zigtong.adminserver.domain.businessType.dto.BusinessTypeDto;
import capstone.zigtong.adminserver.domain.businessType.repository.BusinessTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessTypeService {
    private final BusinessTypeRepository businessTypeRepository;
    public List<BusinessTypeDto> getBusinessType() {
        List<BusinessType> businessTypeList = businessTypeRepository.findAll();

        return businessTypeList.stream()
                .map(this::mapToBusinessTypeDto)
                .collect(Collectors.toList());
    }

    private BusinessTypeDto mapToBusinessTypeDto(BusinessType businessType) {
        return new BusinessTypeDto(businessType.getIndustryCode(), businessType.getIndustryName());
    }
}
