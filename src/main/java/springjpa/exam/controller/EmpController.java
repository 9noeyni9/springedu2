package springjpa.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import springjpa.exam.entity.Emp;
import springjpa.exam.repository.EmpRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmpController {

//	@Autowired
	private final EmpRepository dao;//final형이 아니면 autowired가 자동으로 안 만들줌..? nullpointException에서 자유롭지 못함 (lombok이 정해놓은 룰)
	
	@GetMapping("/countnum")
	public ModelAndView count() {
		ModelAndView mav = new ModelAndView();
		long num = dao.count();
		mav.addObject("num", num);
		mav.setViewName("empResult");
		return mav;
	}
	
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		List<Emp> list = dao.findAll();
		list.parallelStream().forEach(System.out::println);
		mav.addObject("list", list);
		mav.setViewName("empResult");
		return mav;
	}
	
	@GetMapping("/part")//size : 한 페이지에 몇개의 데이터 보여줄지 page : 몇 페이지 보여줄지 
	public ModelAndView part(int page, int size) {
		ModelAndView mav = new ModelAndView();
		PageRequest pageRequest = PageRequest.of(page, size);//PageRequest API: 객체 생성시 페이지와 사이즈 지정해 .of 메서드로 객체 생성 우선 사이즈로 몇개씩 쪼갤지 판단(size) 몇쪽을 보여줄건지 판단(page)
		Page<Emp> pageObj = dao.findAll(pageRequest);
		List<Emp> list = pageObj.toList();
		mav.setViewName("empResult");
		mav.addObject("list", list);
		return mav;
	}
	
	@GetMapping("/partsal")
	public ModelAndView partsal(int page, int size) {
		ModelAndView mav = new ModelAndView();
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("sal").descending());
		Page<Emp> pageObj = dao.findAll(pageRequest);
		List<Emp> list = pageObj.toList();
		mav.setViewName("empResult");
		mav.addObject("list", list);
		return mav;
	}
	
	@GetMapping("/parthiredate")
	public ModelAndView parthiredate(int page, int size) {
		ModelAndView mav = new ModelAndView();
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("hiredate"));
		Page<Emp> pageObj = dao.findAll(pageRequest);
		List<Emp> list = pageObj.toList();
		mav.setViewName("empResult");
		mav.addObject("list", list);
		return mav;
	}
}