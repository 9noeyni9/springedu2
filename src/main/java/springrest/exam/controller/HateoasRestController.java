package springrest.exam.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springrest.exam.domain.ComicActor;
import springrest.exam.domain.ComicActorModel;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HateoasRestController {

    List<ComicActorModel> actorModelList = new ArrayList<>();

    @GetMapping("/all")
    public CollectionModel<ComicActorModel> allActor(){

        ComicActorModel model = new ComicActorModel();
        model.setName("둘리");
        model.add(Link.of("http://localhost:8088/dooly"));
        actorModelList.add(model);

        model = new ComicActorModel();
        model.setName("또치");
        model.add(Link.of("http://localhost:8088/ddochi"));
        actorModelList.add(model);

        model = new ComicActorModel();
        model.setName("도우너");
        model.add(Link.of("http://localhost:8088/dauner"));
        actorModelList.add(model);

        CollectionModel<ComicActorModel> result = CollectionModel.of(actorModelList);

        return result;
    }

    @GetMapping("/dooly")
    public ResponseEntity<ComicActor> getDooly(){
        ComicActor dooly = new ComicActor("둘리","쌍문동","dooly.jpg");

        return ResponseEntity.ok().body(dooly);
    }

    @GetMapping("/ddochi")
    public ResponseEntity<ComicActor> getDdochi(){
        ComicActor ddochi = new ComicActor("또치","아프리카","ddochi.jpg");

        return ResponseEntity.ok().body(ddochi);
    }

    @GetMapping("/dauner")
    public ResponseEntity<ComicActor> getDauner(){
        ComicActor dauner = new ComicActor("도우너","깐따삐아","dauner.png");

        return ResponseEntity.ok().body(dauner);
    }
}
