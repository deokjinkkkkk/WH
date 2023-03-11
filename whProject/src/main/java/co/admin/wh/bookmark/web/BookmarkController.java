package co.admin.wh.bookmark.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.bookmark.mapper.BookmarkMapper;
import co.admin.wh.bookmark.vo.BookmarkVO;
import co.admin.wh.member.vo.MemberVO;

@Controller
public class BookmarkController {
	@Autowired
	private BookmarkMapper bMapper;

	// bookmark 전체 페이지 ->마이페이지에서 확인 가능
	@RequestMapping("/bookmark")
	public String bookmark(Model model) {
		model.addAttribute("bookMark", bMapper.bookList());
		return "bookmark/bookmark";
	}

	// 즐겨찾기 여부 확인
	@GetMapping("/bookCheck/{bookNcode}/{id}") // GET 요청을 처리하기 위해 @GetMapping 사용
	@ResponseBody // 반환값을 JSON 형태로 응답
	public boolean bookCheck(@PathVariable("bookNcode") int bookNcode, BookmarkVO vo, @PathVariable("id") String id,
			MemberVO mvo, Principal principal) {
		// @PathVariable을 사용하여 bookNcode와 id를 추출
		boolean bookCheck = false; // 즐겨찾기 하지 않았다고 가정
		
		mvo.setId(principal.getName());
		vo.setBookNcode(bookNcode);
		

		// db에서 사용자와 코드에 대한 북마크 여부 확인
		if (bMapper.bookCheck(vo)) {
			bookCheck = true;
		}

		return bookCheck;
	}

	// db에 즐겨찾기(bookmark) 정보 등록 기능
	@PostMapping("/bookInsert/{bookNcode}") // POST 요청을 처리하기 위해 @PostMapping 사용
	@ResponseBody // 반환값을 JSON 형태로 응답
	public Map<String, Object> bookInsert(@PathVariable("bookNcode") int bookNcode, String bookFlag, BookmarkVO vo,
			String id) { // Map<String, Object>형태로 결과값 반환
		Map<String, Object> resultMap = new HashMap<>();

		vo.setId(id);
		vo.setBookNcode(bookNcode);
		vo.setBookFlag(bookFlag); // 전달받은 파라미터를 BookmarkVO 객체에 설정함

		int result = bMapper.bookInsert(vo); // 즐겨찾기 정보를 db에 등록

		if (result == 1) { // 결과값에 따라 성공/실패 메세지 반환
			resultMap.put("result", "success");
			resultMap.put("message", "즐겨찾기 추가");
		} else {
			resultMap.put("result", "fail");
			resultMap.put("message", "즐겨찾기 추가 실패");
		}
		return resultMap;
	}

	// 즐겨찾기 취소
	@PostMapping("/bookDelete/{bookFlag}/{bookNcode}/{id}")
	public Map<String, Object> bookDelete(@PathVariable("bookFlag") String bookFlag,
			@PathVariable("bookNcode") int bookNcode, BookmarkVO vo, @PathVariable("id") String id) {
		Map<String, Object> resultMap = new HashMap<>();

		vo.setId(id);
		vo.setBookNcode(bookNcode);
		vo.setBookFlag(bookFlag); // 전달받은 파라미터를 BookmarkVO 객체에 설정함

		int result = bMapper.bookDelete(vo); // 즐겨찾기 정보를 db에 등록

		if (result == 1) { // 결과값에 따라 성공/실패 메세지 반환
			resultMap.put("result", "success");
			resultMap.put("message", "즐겨찾기 취소");

		} else {
			resultMap.put("result", "fail");
			resultMap.put("message", "즐겨찾기 취소 실패");
		}
		return resultMap;

	}
}
