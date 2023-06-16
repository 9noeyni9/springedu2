package springjpa.exam.repository;

import java.util.List;

import springjpa.exam.entity.Meeting;
import springjpa.exam.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	public List<Reply> findByRefid(Meeting vo);//어떤 메인글의 연결되있는 댓글들만 출력하겠다
	
}
