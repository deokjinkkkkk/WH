package co.admin.wh.trip.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.trip.service.MyCourseService;
import co.admin.wh.trip.vo.MyCourseFreeVO;
import co.admin.wh.trip.vo.MyCourseVO;
import co.admin.wh.trip.mapper.MyCourseMapper;
import co.admin.wh.trip.service.MyCourseFreeService;

@Controller
public class MyCourseController {
	
	@Autowired MyCourseMapper myCourseMapper;
	
	@Autowired
	MyCourseService myCourseService;
	
	@Autowired
	MyCourseFreeService myCourseFreeService;
	
	@RequestMapping("/myCourse")
	public String myCourse(Model model) {
		model.addAttribute("test", myCourseService.myCourseList());
		System.out.println(model.addAttribute("test", myCourseService.myCourseList()));
		return "trip/myCourseList";
	}
	
	// 제목 입력하기
	@PostMapping("/myCourseInsert")
	@ResponseBody
	public String myCourseInsert(@RequestBody MyCourseVO vo) {
		int result = myCourseMapper.titleInsert(vo);
		System.out.println(result);
		String resultValue = "fail";
		if(result > 0) {
			resultValue = "success";			
		}
		return resultValue;
	}
	
	@RequestMapping("/myCourseDetail")
	public String CourseDetail(Model model) {
		return "trip/myCourseDetail";
	}
}
