package springrest.exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import springrest.exam.entity.Friend;
import springrest.exam.repository.FriendRepository;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    FriendRepository friendRepository;
    List<Friend> friendList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Friend>> list() {
        log.info("list");

        friendList = friendRepository.findAll();
        ResponseEntity<List<Friend>> entity = new ResponseEntity<>(friendList, HttpStatus.OK);

        return entity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable int id) {
        log.info("read");

        try {
            Friend friend = friendRepository.findById(id).get();
            ResponseEntity<Friend> entity = new ResponseEntity<>(friend, HttpStatus.OK);
            return entity;
        } catch (Exception e) {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("BAD_ID", String.valueOf(id));
            ResponseEntity<String> entity = new ResponseEntity<>(header, HttpStatus.NOT_FOUND);
            return entity;
        }
    }

    @GetMapping(value = "/name/{fname}", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> read(@PathVariable String fname) {

        Friend friend = new Friend();

        friend.setFname(fname);
        friendRepository.findByFname(fname);

        try {
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            ResponseEntity<Friend> entity = new ResponseEntity<>(friend, header, HttpStatus.OK);


            return entity;
        } catch (Exception e) {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("BAD_NAME", String.valueOf(fname));
            ResponseEntity<String> entity = new ResponseEntity<>(header, HttpStatus.NOT_FOUND);
            return entity;
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> register(@RequestBody Friend friend) {
        try {
            friendList.add(friend);
            friendRepository.save(friend);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            ResponseEntity<String> entity = new ResponseEntity<>("입력 성공", HttpStatus.CREATED);
            return entity;

        } catch (Exception e) {
            ResponseEntity<String> entity = new ResponseEntity<>("입력을 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            return entity;
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> modify(@RequestBody Friend friend) {
        log.info("modify");
        try {
            Friend oldvo = friendRepository.findById(friend.getId()).get();

            oldvo.setFname(friend.getFname());
            oldvo.setFage(friend.getFage());

            ResponseEntity<String> entity = new ResponseEntity<>("수정 성공", HttpStatus.RESET_CONTENT);

            return entity;
        } catch (Exception e) {
            ResponseEntity<String> entity = new ResponseEntity<>("수정 실패", HttpStatus.NOT_FOUND);
            return entity;
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> remove(@PathVariable int id) {
        log.info("remove");

        try {
            friendRepository.findById(id);
            friendRepository.deleteById(id);

            ResponseEntity<String> entity = new ResponseEntity<>("삭제 성공", HttpStatus.RESET_CONTENT);

            return entity;
        } catch (Exception e) {
            ResponseEntity<String> entity = new ResponseEntity<>("삭제 실패", HttpStatus.NOT_FOUND);
            return entity;
        }
    }

}
