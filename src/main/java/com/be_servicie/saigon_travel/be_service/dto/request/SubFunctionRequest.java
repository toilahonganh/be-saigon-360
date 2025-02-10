package com.be_servicie.saigon_travel.be_service.dto.request;
import lombok.Data;

@Data
public class SubFunctionRequest {
    private String name;
    private String description;
    private String url;
    private Boolean active;
    private String m_id;
}