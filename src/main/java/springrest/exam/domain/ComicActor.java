package springrest.exam.domain;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ComicActor extends RepresentationModel<ComicActor> {
    private String name;
    private String addr;
    private String image;
}
