package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import co.admin.wh.common.service.GreatService;

@Controller
public class GreatController {
	@Autowired GreatService service;
	
	//좋아요 check
	//좋아요 등록 
	//좋아요 취소
	//좋아요 Total
	
}
