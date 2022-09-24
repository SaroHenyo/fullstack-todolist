package saro.fullstacktodolist.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class TodoDTO {

    private UUID todoID;
    private String todo;
    private ZonedDateTime createdDate;
    private ZonedDateTime modifiedDate;
}
