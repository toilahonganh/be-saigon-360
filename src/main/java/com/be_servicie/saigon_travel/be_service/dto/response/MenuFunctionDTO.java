package com.be_servicie.saigon_travel.be_service.dto.response;


import java.util.List;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MenuFunctionDTO {
    private String id;

    private String name;

    private String url;

    private Boolean active;

    private List<SubMenuFunctionDTO.ShortDTO> subFunctions;
}
