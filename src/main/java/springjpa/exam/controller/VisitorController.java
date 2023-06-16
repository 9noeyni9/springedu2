package springjpa.exam.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import springjpa.exam.entity.Visitor;
import springjpa.exam.repository.VisitorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VisitorController {
	private VisitorRepository repository;

	public VisitorController(VisitorRepository repository) {
		this.repository = repository;
	}//컨트롤러가 객체 생성할 때는 annotation을 지정해주지 않아도 생성자를 통해서 받아오면 Autowired 생성 가능
	@RequestMapping("/vlist")
	public ModelAndView list() {
		List<Visitor> list = null;
		list = repository.findAll();
		ModelAndView mav = new ModelAndView();	
		if (list.size() != 0) {
			mav.addObject("list", list);
		} else {
			mav.addObject("msg", "추출된 결과가 없어요");
		}
		mav.setViewName("visitorView");
		return mav;
	}

	@RequestMapping("/vsearch")
	public ModelAndView search(String key) {
		List<Visitor> list = null;
		//list = repository.findByMemoLike(key);
		list = repository.findByMemoContains(key);
		ModelAndView mav = new ModelAndView();
		if (list.size() != 0) {
			System.out.println(list);
			mav.addObject("list", list);
		} else {
			mav.addObject("msg", "추출된 결과가 없어요");
		}
		mav.setViewName("visitorView");
		return mav;
	}

	@RequestMapping(value = "/vdelete")
	@Transactional//dml명령을 필요로 하는 경우에 트랜잭션 상으로 처리 Transactional 사용 에러없이 수행되면 commit 에러 발생 rollback
	public ModelAndView delete(int id) {
		ModelAndView mav = new ModelAndView();
		try {
			repository.deleteById(id);			
			mav.addObject("list", repository.findAll());
		} catch(Exception e) {			
			mav.addObject("msg", "글 삭제에 실패했습니다.");
		}
		mav.setViewName("visitorView");
		return mav;
	}
	
	@RequestMapping(value = "/one", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Visitor one(int id) {
		Optional<Visitor> result = repository.findById(id);//Optional<Visitor>의 객체로 포장해서 return optional객체 안에 visitor 객체 리턴
		return result.get();//get메서드로 꺼내서 사용해야 해  visitor객체를 꺼내는 게 아니라 optional안에 visitor객체를 return하기 때문에		
	}

	@RequestMapping(value = "/vinsert", 
			                     method = RequestMethod.POST)
	@Transactional
	public String insert(Visitor vo, Model model) {
		try {
			repository.save(vo);			
			return "redirect:/vlist";
		} catch(Exception e) {			
			model.addAttribute("msg", "글 작성에 실패했습니다.");
		}
		return "visitorView";
	}
	
	@RequestMapping(value = "/vupdate", 
            method = RequestMethod.POST)
	@Transactional
	public String update(Visitor vo, Model model) {		
		try {
			Visitor entity = repository.findById(vo.getId()).get();
			entity.setName(vo.getName());	
			entity.setMemo(vo.getMemo());
			return "redirect:/vlist";
		} catch(Exception e) {						
			model.addAttribute("msg", "글 수정에 실패했습니다.");
		}
		return "visitorView";	
	}
}



