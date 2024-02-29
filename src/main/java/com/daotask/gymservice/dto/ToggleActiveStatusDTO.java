package com.daotask.gymservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ToggleActiveStatusDTO {
    // Data
    private String username;
    private boolean isActive;
}
