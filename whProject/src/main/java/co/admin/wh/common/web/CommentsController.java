package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.mapper.CommentsMapper;

@Controller
public class CommentsController {
	@Autowired CommentsMapper commentsMapper;
	
	@RequestMapping("/comments")
	public String commentsForm() {
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
