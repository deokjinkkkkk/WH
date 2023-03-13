package co.admin.wh.bookmark.web;



import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.bookmark.mapper.BookmarkMapper;
import co.admin.wh.bookmark.service.BookmarkService;
import co.admin.wh.bookmark.vo.BookmarkVO;
import co.admin.wh.member.vo.MemberVO;
import groovyjarjarantlr.collections.List;

@Controller
public class BookmarkController {
	@Autowired
	private BookmarkMapper bMapper;
	@Autowired
	private BookmarkService bService;

	// bookmark 전체 페이지 ->마이페이지에서 확인 가능
	@RequestMapping("/bookmark")
	public String bookmark(Model model, Principal principal) {
	    String id = principal.getName();
	    
	    model.addAttribute("bookHotel", bMapper.hotelList(id));
	    model.addAttribute("bookTrip", bMapper.tripList(id));
	    
	    return "bookmark/bookmark";
	}
	
	@RequestMapping("/bookmarkCheck/{bookNcode}/{id}")
	@ResponseBody
	public boolean bookmarkCheck(@PathVariable("bookNcode") int bookNcode, BookmarkVO vo,
			@PathVariable("id")String id,MemberVO mvo , Principal principal) {
		boolean bookmarkCheck = false;
		
		vo.setId(principal.getName());
		
		vo.setBookNcode(bookNcode);
		
		if(bService.bookmarkCheck(vo)) {
			bookmarkCheck = true;
		}
		return bookmarkCheck;
	}

	//hotel - 즐겨찾기 추가
	@PostMapping("/insertBookHotel/{bookNcode}")
	@ResponseBody
	public BookmarkVO insertBookHotel(BookmarkVO vo, @PathVariable int bookNcode, Principal principal) {
	    vo.setId(principal.getName());
	    vo.setBookNcode(bookNcode);
	    vo.setBookFlag("HT");
	    bService.insertBookHotel(vo);
	    return vo;
	}

	//trip - 즐겨찾기 추가
	@PostMapping("/insertBookTrip/{bookNcode}")	
	public BookmarkVO insertBookTrip(BookmarkVO vo, @PathVariable int bookNcode, Principal principal) {
	    vo.setId(principal.getName());
	    vo.setBookNcode(bookNcode);
	    vo.setBookFlag("TR");
	    return vo;
	}

	//즐겨찾기 삭제
	@PostMapping("/bookDel/{bookCode}")
	public int bookDel(BookmarkVO vo, @PathVariable int bookCode, Principal principal) {
	    vo.setBookCode(bookCode);
	    vo.setId(principal.getName());
	    return bMapper.bookDel(vo);
	}
	}
	
	
	
	
	
	/*
	 * // db에 즐겨찾기(bookmark) 정보 등록 기능
	 * 
	 * @PostMapping("/bookInsert/{bookNcode}") // POST 요청을 처리하기 위해 @PostMapping 사용
	 * 
	 * @ResponseBody // 반환값을 JSON 형태로 응답 public Map<String, Object>
	 * bookInsert(@PathVariable("bookNcode") int bookNcode, String bookFlag,
	 * BookmarkVO vo, String id) { // Map<String, Object>형태로 결과값 반환 Map<String,
	 * Object> resultMap = new HashMap<>();
	 * 
	 * vo.setId(id); vo.setBookNcode(bookNcode); vo.setBookFlag(bookFlag); // 전달받은
	 * 파라미터를 BookmarkVO 객체에 설정함
	 * 
	 * int result = bMapper.bookInsert(vo); // 즐겨찾기 정보를 db에 등록
	 * 
	 * if (result == 1) { // 결과값에 따라 성공/실패 메세지 반환 resultMap.put("result",
	 * "success"); resultMap.put("message", "즐겨찾기 추가"); } else {
	 * resultMap.put("result", "fail"); resultMap.put("message", "즐겨찾기 추가 실패"); }
	 * return resultMap; }
	 * 
	 * // 즐겨찾기 취소
	 * 
	 * @PostMapping("/bookDelete/{bookFlag}/{bookNcode}/{id}") public Map<String,
	 * Object> bookDelete(@PathVariable("bookFlag") String bookFlag,
	 * 
	 * @PathVariable("bookNcode") int bookNcode, BookmarkVO vo, @PathVariable("id")
	 * String id) { Map<String, Object> resultMap = new HashMap<>();
	 * 
	 * vo.setId(id); vo.setBookNcode(bookNcode); vo.setBookFlag(bookFlag); // 전달받은
	 * 파라미터를 BookmarkVO 객체에 설정함
	 * 
	 * int result = bMapper.bookDelete(vo); // 즐겨찾기 정보를 db에 등록
	 * 
	 * if (result == 1) { // 결과값에 따라 성공/실패 메세지 반환 resultMap.put("result",
	 * "success"); resultMap.put("message", "즐겨찾기 취소");
	 * 
	 * } else { resultMap.put("result", "fail"); resultMap.put("message",
	 * "즐겨찾기 취소 실패"); } return resultMap;
	 * 
	 * }
	 */

