package springjpa.exam.repository;

import java.util.List;

import springjpa.exam.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Integer>{

	public List<Visitor> findByMemoContains(String keyword);	
	
}
