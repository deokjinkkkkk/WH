package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.mapper.CommentsMapper;
import co.admin.wh.common.vo.CommentsVO;

@Controller
public class CommentsController {
	@Autowired CommentsMapper commentsMapper;
	
	@RequestMapping("/comments")
	public String commentsForm(Model model, CommentsVO vo) {
		vo.setComCode(1);
		model.addAttribute("com", commentsMapper.commentsList());
		return "comments/commentsForm";
	}
//	
//	@GetMapping("")
//	public int commentsCount() {
//		return 1;
//	}
//	
//	@PostMapping("")
//	public int commentsInsert() {
//		return 1;
//	}
//	
//	@GetMapping("/comments")
//	public String commentsList(Model model,CommentsVO vo) {
//		model.addAttribute("commentsList", commentsMapper.commentsList());
//		return "comments/commentsForm";
//	}
//	
//	@DeleteMapping("")
//	public int commentsDelete() {
//		return 1;
//	}
//	
//	@PutMapping("")
//	public int commentsUpdate() {
//		return 1;
//	}
}
