package springjpa.exam.repository;

import java.util.List;

import springjpa.exam.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Integer>{
	public List<Meeting> findByTitleContains(String keyword);	
	public List<Meeting> findByName(String name);
}
