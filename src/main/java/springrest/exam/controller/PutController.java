package springrest.exam.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springrest.exam.dto.MemberDTO;
//post와 동일하게 body에 요청 파라미터 들어가는 게 동일 post는 요청 바디에 주면서 추가해줘 put은 기존걸 변경해줘 이 차이
@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
    @PutMapping(value = "/default")
    public String putMethod() {
        return "안녕? PUT 방식 요청 했네~~~";
    }

    @PutMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @PutMapping(value = "/member1")
    public String postMemberDto1(@RequestBody MemberDTO memberDto) {
        return memberDto.toString();
    }


    @PutMapping(value = "/member2")
    public MemberDTO postMemberDto2(@RequestBody MemberDTO memberDto) {
        return memberDto;
    }

    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDTO> postMemberDto3(@RequestBody MemberDTO memberDto) {
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)//201
            .body(memberDto);//ResponseEntity: 생성자 가지고 해도 되고(PostController가봐) static가지고 해도 됨
    }
}