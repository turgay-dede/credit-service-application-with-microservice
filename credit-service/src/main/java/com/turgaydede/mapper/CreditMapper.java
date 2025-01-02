package com.turgaydede.mapper;


import com.turgaydede.entity.Credit;
import com.turgaydede.entity.dto.CreditDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    CreditDto getDto(Credit credit);
    Credit getObj(CreditDto creditDto);

    List<CreditDto> getDtoList(List<Credit> creditList);
    List<Credit> getObjList(List<CreditDto> creditDtoList);
}
