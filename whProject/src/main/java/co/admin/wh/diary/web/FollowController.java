package co.admin.wh.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.admin.wh.diary.mapper.FollowMapper;
import co.admin.wh.diary.service.FollowService;

@Controller
public class FollowController {
	@Autowired FollowMapper mapper;
	
	@Autowired FollowService service;
	
	
	
}
