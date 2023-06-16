package springjpa.exam.repository;

import java.util.List;

import springjpa.exam.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Integer>{//JpaRepository<Visitor, Integer> 이것만 제대로 알려주면 돼

	public List<Visitor> findByMemoContains(String keyword);	//메모글 가지고 처리하는 메서드 하나 나머지는 다 상속 받음
	
}
