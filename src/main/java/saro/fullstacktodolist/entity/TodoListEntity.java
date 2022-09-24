package saro.fullstacktodolist.entity;

import saro.fullstacktodolist.config.SchemaConfiguration;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@Setter(AccessLevel.NONE)
@Table(schema = SchemaConfiguration.SCHEMA_NAME, name = "todolist")
public class TodoListEntity {
    @Id
    private UUID todoId;
    private String todo;
    private ZonedDateTime createdDate;
    private ZonedDateTime modifiedDate;
}
