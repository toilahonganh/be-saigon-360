package com.be_servicie.saigon_travel.be_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubMenuFunctionDTO {
    private String id;
    private String name;
    private String url;
    private Boolean active;

    @Data
    @Builder
    public static class FullDTO {
        private String id;
        private String name;
        private String url;
        private Boolean active;
        private String menuFunctionId;
        private String menuFunctionName;
    }

    @Data
    @Builder
    public static class ShortDTO {
        private String name;
        private String url;
    }
}