package co.admin.wh.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
		model.addAttribute("com", commentsMapper.commentsList(vo));
		return "comments/commentsForm";
	}

	@RequestMapping("/comGetList") //댓글 리스트 띄우기
	@ResponseBody
	public List<CommentsVO> commentsGetList(@RequestBody CommentsVO vo,Model model) {
		List<CommentsVO> comList = commentsMapper.commentsList(vo);
		
		return comList;
	}
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
	@PostMapping("/reComInsert") //댓글 등록
	@ResponseBody
	public String reCommentsInsert(@RequestBody CommentsVO vo) {
		System.out.println("댓글 등록하자");
//		if(id == null) {
//		 로그인 안하면 댓글 달지 못하게 하기	
//		}
		commentsMapper.commentsInsert(vo);
		return "success";
	}
//	
//	
	@DeleteMapping("/reComDelete") //댓글 삭제
	@ResponseBody
	public String reCommentsDelete(CommentsVO vo) {
		System.out.println("삭제");
		System.out.println(vo.getComCode());
		
		commentsMapper.reCommentsDelete(vo);
		return "success";
	}
	
	@DeleteMapping("/comDelete") //댓글 삭제
	@ResponseBody
	public String commentsDelete(CommentsVO vo) {
		System.out.println("삭제");
		System.out.println(vo.getComCode());
		
		commentsMapper.commentsDelete(vo);
		return "success";
	}
	
	@RequestMapping("/comCount")
	@ResponseBody
	public int commentsCounter(CommentsVO vo) {
		int count = commentsMapper.commentsCount(vo);
		return count;
	}
	
	@PostMapping("/comUpdate") //댓글 수정
	@ResponseBody
	public String commentsUpdate(@RequestBody CommentsVO vo) {
		commentsMapper.commentsUpdate(vo);
		return "success";
	}
	@RequestMapping("/comUpList") //댓글 리스트 띄우기
	@ResponseBody
	public List<CommentsVO> commentsUpList( CommentsVO vo,Model model) {
		List<CommentsVO> comList = commentsMapper.commentsList(vo);
		
		return comList;
	}
}
