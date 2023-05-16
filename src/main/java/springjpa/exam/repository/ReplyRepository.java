package springjpa.exam.repository;

import java.util.List;

import springjpa.exam.entity.Meeting;
import springjpa.exam.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	public List<Reply> findByRefid(Meeting vo);
	
}
