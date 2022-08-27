package com.turgaydede.mapper;

import com.turgaydede.entity.Credit;
import com.turgaydede.entity.dto.CreditResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditResponseMapper {
    CreditResponseDto getDto(Credit credit);
    Credit getObj(CreditResponseDto creditResponseDto);
}
