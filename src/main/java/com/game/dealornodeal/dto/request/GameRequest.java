package com.game.dealornodeal.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class GameRequest {
    private String name;
    private String description;
    private List<CopperRequest> coppers;
}
