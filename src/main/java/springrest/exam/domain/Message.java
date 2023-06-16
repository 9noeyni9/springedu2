package springrest.exam.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class Message {
    private String message1;
    private String message2;
    private String message3;
    @Builder//사양에 맞춰서 build기능에(build패턴에 맞는) 메서드를 자동으로 생성해줘
    public Message(String msg1, String msg2, String msg3) {
        this.message1 = msg1;
        this.message2 = msg2;
        this.message3 = msg3;
        System.out.println("@Builder 가 설정된 생성자 호출");
    }
}
