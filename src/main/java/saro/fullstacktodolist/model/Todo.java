package saro.fullstacktodolist.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Todo {

    private UUID todoID;
    private String todo;

}
