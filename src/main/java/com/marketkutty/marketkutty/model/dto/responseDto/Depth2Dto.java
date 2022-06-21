package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Depth2Dto {

    private String id;
    private String name;

    @Builder
    public Depth2Dto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
