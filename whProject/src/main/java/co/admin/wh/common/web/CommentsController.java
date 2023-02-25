package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.common.mapper.CommentsMapper;
import co.admin.wh.common.vo.CommentsVO;

@Controller
public class CommentsController {
	@Autowired CommentsMapper commentsMapper;
	
	
	@RequestMapping("/comments") //댓글 창이동
	public String commentsForm(Model model, CommentsVO vo) {
		model.addAttribute("com", commentsMapper.commentsList());
		
		return "comments/commentsForm";
	}

//	@GetMapping("/comments") //댓글 리스트 띄우기
//	public int commentsCount() {
//		return 1;
//	}
//	
	@PostMapping("/comInsert") //댓글 등록
	@ResponseBody
	public String commentsInsert(@RequestBody CommentsVO vo) {
		System.out.println("댓글 등록하자");
//		if(id == null) {
//		 로그인 안하면 댓글 달지 못하게 하기	
//		}
		commentsMapper.commentsInsert(vo);
		return "success";
	}
//	
//	
//	@DeleteMapping("") //댓글 삭제
//	public int commentsDelete() {
//		return 1;
//	}
//	
//	@PutMapping("") //댓글 수정
//	public int commentsUpdate() {
//		return 1;
//	}
}
