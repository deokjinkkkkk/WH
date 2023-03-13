package co.admin.wh.trip.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.trip.service.MyCourseService;
import co.admin.wh.trip.vo.CourseVO;
import co.admin.wh.trip.vo.MyCourseFreeVO;
import co.admin.wh.trip.vo.MyCourseVO;
import co.admin.wh.trip.mapper.MyCourseFreeMapper;
import co.admin.wh.trip.mapper.MyCourseMapper;
import co.admin.wh.trip.service.MyCourseFreeService;

@Controller
public class MyCourseController {
	
	@Autowired MyCourseFreeMapper myCourseFreeMapper;
	
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
		String resultValue = "fail";
		if(result > 0) {
			resultValue = "success";			
		}
		return resultValue;
	}
	
	
	// 여행지 정보 입력하기
	@PostMapping("/myCouTripInsert")
	@ResponseBody
	public String myCouTripInsert(@RequestBody MyCourseFreeVO vo) {
		int result = myCourseFreeMapper.myCourseInsert(vo);
		String resultValue = "fail";
		if(result > 0) {
			resultValue = "success";
		}
		return resultValue;
	}
	
	// 나만의 코스 리스트에 있는 항목 삭제하기
	@PostMapping("/myCourseDelete")
	@ResponseBody
	public String myCourseDelete(@RequestBody MyCourseVO vo) {
		int result = myCourseMapper.myCourseDelete(vo);
		String resultValue = "fail";
		if(result > 0 ) {
			resultValue = "success";
		}
		return resultValue;
	}
	
	
	// 나만의 코스 상세페이지에 해당 여행지 삭제하기
	@PostMapping("/myCouDetailDel")
	@ResponseBody
	public String myCouDetailDel(@RequestBody MyCourseFreeVO vo) {
		int result = myCourseFreeMapper.myCouDetailDel(vo);
		String resultValue = "fail";
		if(result > 0 ) {
			resultValue = "success";
		}
		return resultValue;
	}
	
	
	
	//상세페이지 보기
	@RequestMapping(value =  "/myCourseDetail/{myCourseCode}", method=RequestMethod.GET)
	public String CourseDetail(@PathVariable("myCourseCode") String myCourseCode, MyCourseVO vo, MyCourseFreeVO fvo, Model model) {
		vo.setMyCourseCode(myCourseCode);
		model.addAttribute("myCourse", myCourseService.detailSelect(vo));
		model.addAttribute("myCouDet", myCourseFreeService.myCourseSelect(fvo));
		return "trip/myCourseDetail";		
	}
	
}
