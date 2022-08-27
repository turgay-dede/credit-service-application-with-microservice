package com.turgaydede.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CreditScore  implements Serializable {
    @Id
    private String id;
    private String identityNumber;
    private int creditScore;
}
