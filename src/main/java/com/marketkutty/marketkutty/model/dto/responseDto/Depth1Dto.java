package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Depth1Dto {
    private String id;
    private String name;
    private List<Depth2Dto> depth2DtoList;

    @Builder
    public Depth1Dto(String id, String name, List<Depth2Dto> depth2DtoList) {
        this.id = id;
        this.name = name;
        this.depth2DtoList = depth2DtoList;
    }
}
