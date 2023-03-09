package co.admin.wh.trip.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.trip.service.MyCourseService;
import co.admin.wh.trip.vo.MyCourseFreeVO;
import co.admin.wh.trip.vo.MyCourseVO;
import co.admin.wh.trip.service.MyCourseFreeService;

@Controller
public class MyCourseController {
	
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
	
	@RequestMapping("/myCourseDetail")
	public String CourseDetail(Model model) {
		model.addAttribute("test1", myCourseFreeService.myCourseTripList());
		return "trip/myCourseDetail";
	}
}
