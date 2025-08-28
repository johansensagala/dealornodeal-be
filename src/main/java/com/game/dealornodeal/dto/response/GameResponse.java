package com.game.dealornodeal.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class GameResponse {
    private Integer id;
    private String name;
    private String description;
    private List<CopperResponse> coppers;
}
