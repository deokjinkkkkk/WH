package co.admin.wh.bookmark.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.bookmark.mapper.BookmarkMapper;
import co.admin.wh.bookmark.service.BookmarkService;
import co.admin.wh.bookmark.vo.BookmarkVO;
import co.admin.wh.member.vo.MemberVO;

@Controller
public class BookmarkController {
	@Autowired
	private BookmarkMapper bMapper;
	
	//bookmark 전체 페이지 ->마이페이지에서 확인 가능
	@RequestMapping("/bookmark")
	public String bookmark(Model model) {
		model.addAttribute("bookMark", bMapper.bookList());
		return "bookmark/bookmark";
	}
	
	//즐겨찾기 여부 확인
	@RequestMapping("/bookCheck/{bookNcode}/{id}") // 1.@RequestMapping을 통해 ("/~")url로 요청이 들어오면
	@ResponseBody  //3. 반환값을 json형태로 응답
	public boolean bookCheck(@PathVariable("bookCheck") int bookNcode, BookmarkVO vo,
			@PathVariable("id")String id, MemberVO mvo, Principal principal) { // 2.@PathVariable를 사용하여 bookNcode와id를 추출
		boolean bookCheck = false; //즐겨찾기 하지 않았다고 가정    //Principal --> Spring Security에서 현재 인증된 사용자 정보 제공하는 인터페이스
		mvo.setId(principal.getName());							//즉, 현재 로그인한 사용자의 id가 무엇인지 파악하기 위한것.				
		
		vo.setBookNcode(bookNcode);
		//db에서 사용자와 코드에 대한 북마크 여부 확인
		
		if(bMapper.bookCheck(vo)) {
			bookCheck = true;
		}
		
		return bookCheck;
	}
	
	
	
	
	//db에 즐겨찾기(bookmark) 정보 등록 기능
	 @PostMapping("/bookInsert/{bookNcode}") //@PostMapping를 통해 ("/~") url로 post요청이 들어오면
	public Map<String, Object> bookInsert(@PathVariable("bookNcode")int bookNcode, //@PathVariable를 사용하여 bookNcode추출
			String bookFlag, BookmarkVO vo, String id ){ //Map<String, Object>형태로 결과값 반환
		Map<String, Object> resultMap = new HashMap<>(); 
		
		vo.setId(id);
		vo.setBookNcode(bookNcode);
		vo.setBookFlag(bookFlag);  //전달받은 파라미터를 BookmarkVO 객체에 설정함
		
		int result = bMapper.bookInsert(vo); //즐겨찾기 정보를 db에 등록
		
		if(result == 1) { //결과값에 따라 성공/실패 메세지 반환
			resultMap.put("result", "success");
			resultMap.put("message", "즐겨찾기 추가");
			
		}else {
			resultMap.put("result", "fail");
			resultMap.put("message", "즐겨찾기 추가 실패");
		}
		return resultMap;
	}
	 
	 //즐겨찾기 취소
	 @PostMapping("/bookDelete/{bookFlag}/{bookNcode}/{id}")
	 public Map<String, Object> bookDelete(@PathVariable("bookFlag") String bookFlag, 
			 @PathVariable("bookNcode") int bookNcode, BookmarkVO vo, @PathVariable("id")String id ){ 
			Map<String, Object> resultMap = new HashMap<>(); 
			
			vo.setId(id);
			vo.setBookNcode(bookNcode);
			vo.setBookFlag(bookFlag);  //전달받은 파라미터를 BookmarkVO 객체에 설정함
			
			int result = bMapper.bookInsert(vo); //즐겨찾기 정보를 db에 등록
			
			if(result == 1) { //결과값에 따라 성공/실패 메세지 반환
				resultMap.put("result", "success");
				resultMap.put("message", "즐겨찾기 취소");
				
			}else {
				resultMap.put("result", "fail");
				resultMap.put("message", "즐겨찾기 취소 실패");
			}
			return resultMap;
	
}
}
