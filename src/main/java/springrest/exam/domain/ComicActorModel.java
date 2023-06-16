package springrest.exam.domain;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ComicActorModel extends RepresentationModel<ComicActor> {
    String name;
}
