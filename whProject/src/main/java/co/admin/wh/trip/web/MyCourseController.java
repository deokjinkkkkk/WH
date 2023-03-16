package co.admin.wh.trip.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.admin.wh.tag.mapper.TagMapper;
import co.admin.wh.tag.service.TagService;
import co.admin.wh.tag.vo.TagVO;
import co.admin.wh.trip.mapper.MyCourseFreeMapper;
import co.admin.wh.trip.mapper.MyCourseMapper;
import co.admin.wh.trip.service.MyCourseFreeService;
import co.admin.wh.trip.service.MyCourseService;
import co.admin.wh.trip.vo.MyCourseFreeVO;
import co.admin.wh.trip.vo.MyCourseVO;

@Controller
public class MyCourseController {

	@Autowired
	MyCourseFreeMapper myCourseFreeMapper;

	@Autowired
	MyCourseMapper myCourseMapper;

	@Autowired
	MyCourseService myCourseService;

	@Autowired
	MyCourseFreeService myCourseFreeService;

	@Autowired
	TagService tagService;

	@Autowired
	TagMapper tagMapper;

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
		if (result > 0) {
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
		if (result > 0) {
			resultValue = "success";
		}
		return resultValue;
	}
	
	// 나만의 코스 하단에 소개글 업데이트
	@PostMapping("/myCouIntroUpdate")
	@ResponseBody
	public String myCouIntroUpdate(@RequestBody MyCourseVO vo) {
		int result = myCourseMapper.myCouIntroUpdate(vo);
		String resultValue = "fail";
		if (result > 0) {
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
		if (result > 0) {
			resultValue = "success";
		}
		return resultValue;
	}
	
	// 리스트 삭제 시 순서 번호 업데이트
	@PostMapping("/myCouSeqUpdate")
	@ResponseBody
	public String myCouSeqUpdate(@RequestBody MyCourseVO vo) {
		int result = myCourseMapper.myCouSeqUpdate(vo);
		String resultValue = "fail";
		if (result > 0) {
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
		if (result > 0) {
			resultValue = "success";
		}
		return resultValue;
	}
	
	// 여행지 삭제 시 순서 번호 업데이트
	@PostMapping("/myCouUpdateOrd")
	@ResponseBody
	public String myCouUpdateOrd(@RequestBody MyCourseFreeVO vo) {
		int result = myCourseFreeMapper.couOrdUpdate(vo);
		String resultValue = "fail";
		if (result > 0) {
			resultValue = "success";
		}
		return resultValue;
	}
	
	// 나만의 코스 상세페이지의 여행지 순서 바꾸기
	/* ajax에서 배열을 썼으므로, List 쓰고 for문 돌려야 한다. */
	@PostMapping("/moveSaveTrip")
	@ResponseBody
	public String moveSaveTrip(@RequestBody List<MyCourseFreeVO> list) {
		String resultValue = "fail";
		for (MyCourseFreeVO vo : list) {
            int result = myCourseFreeMapper.myCouUpdate(vo);
            resultValue = "fail";
            if (result > 0) {
               resultValue = "success";
            }
         }
         return resultValue;
      }
	
		// 상태값을 myCourse테이블에 넘기기
		@PostMapping("/myCouStateSave")
		@ResponseBody
		public String myCouStateSave(@RequestBody MyCourseVO vo) {
			int result = myCourseMapper.myCouStateUpdate(vo);
			String resultValue = "fail";
			if (result > 0) {
				resultValue = "success";
			}
			return resultValue;
		}



	// 상세페이지 보기 
		@RequestMapping(value = "/myCourseDetail/{myCourseCode}", method = RequestMethod.GET)
		public String CourseDetail(@PathVariable("myCourseCode") int myCourseCode, MyCourseVO vo, MyCourseFreeVO fvo,
				Model model) {
		    ObjectMapper object = new ObjectMapper();
			vo.setMyCourseCode(myCourseCode);
			model.addAttribute("myCourse", myCourseService.detailSelect(vo));
			model.addAttribute("myCouDet", myCourseFreeService.myCourseSelect(fvo));
			  try {
			         model.addAttribute("json", object.writeValueAsString(myCourseFreeService.myCourseSelect(fvo)));
			      } catch (JsonProcessingException e) {
			         e.printStackTrace();
			      }

			return "trip/myCourseDetail";
		}
		
		/*
		// 새로운 tag db 저장
	    if (newTag != null && !newTag.isEmpty()) {
	        TagVO tagCode = tagService.findTagBytag(newTag);
	        if (tagCode == 0) {
	            tagService.saveTag(newTag);
	            tagCode = tagService.findTagBytag(newTag);
	        }
	        tagService.addCntTag(tagCode);
	    }
		
		return "trip/myCourseDetail";
	}*/
	
}

