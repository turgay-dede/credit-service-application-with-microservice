package com.turgaydede.mapper;


import com.turgaydede.entity.Credit;
import com.turgaydede.entity.dto.CreditDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    CreditDto getDto(Credit credit);
    Credit getObj(CreditDto creditDto);
}
