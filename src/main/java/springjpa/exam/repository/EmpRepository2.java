package springjpa.exam.repository;

import java.util.List;

import springjpa.exam.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpRepository2 extends JpaRepository<Emp, Integer>{//emp repository, 타입
	//메서드 이름만 잘 작성해주면 코드를 알아서 채워줌 
	
	public List<Emp> findByEname(String name);//사원이름 찾을건데 혹시 같은 이름의 직원들 있을 수 있으니까 list로 뽑은 것
	public List<Emp> findByEnameIgnoreCase(String name);//대소문자 구분없이
	public List<Emp> findByJob(String job);
	public List<Emp> findByJobOrDeptno(String job, int dno);
	public List<Emp> findByJobAndDeptno(String job, int dno);
	public List<Emp> findDistinctByJob(String job);
	public List<Emp> findByDeptno(int dno);
	public List<Emp> findBySalGreaterThan(int inputsal);
	public List<Emp> findBySalGreaterThanEqual(int inputsal);
	public List<Emp> findBySalLessThan(int inputsal);//작은 연산자
	public List<Emp> findBySalLessThanEqual(int inputsal);//작거나 같은 연산자
	public List<Emp> findBySalBetween(int minsal, int maxsal);
	public List<Emp> findByCommNull();
	public List<Emp> findByCommNotNull();
	public List<Emp> findByHiredateAfter(java.sql.Date d);//날짜 이후
	public List<Emp> findByHiredateBefore(java.sql.Date d);//날짜 이전
	public List<Emp> findByEnameStartsWith(String partname);//어떤 문자로 시작하는
	public List<Emp> findByEnameContains(String partname);//어떤 문자를 포함하는
	public List<Emp> findByDeptnoOrderBySalDesc(int dno);//동일한 아규먼트로 전달된 부서의 정보 (월급순으로 정렬)
	public List<Emp> findTop3ByDeptnoOrderBySalDesc(int dno);//아규먼트로 전달된 부서에서 일하고 있는 직원들만 뽑아서 월급 제일 많이 받는 직원 세명만 추출
}
