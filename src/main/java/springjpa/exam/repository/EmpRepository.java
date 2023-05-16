package springjpa.exam.repository;

import springjpa.exam.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpRepository extends JpaRepository<Emp, Integer>{

}
