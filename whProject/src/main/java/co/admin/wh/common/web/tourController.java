package co.admin.wh.common.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.mapper.TourMapper;
import co.admin.wh.common.vo.tourVO;

@Controller
public class tourController {
	@Autowired
	TourMapper tourMapper;
	
	@RequestMapping("/tourReForm")
	public String tourReform(tourVO vo) {
		
		return "tour/tourRequestForm";
	}
	
	@RequestMapping("/jusoPopup")
	public String jusoPopup(Model model,HttpServletRequest request) {
		   String inputYn = request.getParameter("inputYn");
		    String roadFullAddr = request.getParameter("roadFullAddr");
		    String roadAddrPart1 = request.getParameter("roadAddrPart1");
		    String roadAddrPart2 = request.getParameter("roadAddrPart2");
		    String engAddr = request.getParameter("engAddr");
		    String jibunAddr = request.getParameter("jibunAddr");
		    String zipNo = request.getParameter("zipNo");
		    String addrDetail = request.getParameter("addrDetail");
		    String admCd = request.getParameter("admCd");
		    String rnMgtSn = request.getParameter("rnMgtSn");
		    String bdMgtSn = request.getParameter("bdMgtSn");
		    String entX = request.getParameter("entX");
		    String entY = request.getParameter("entY");
		    
		   
		    String confmKey = "U01TX0FVVEgyMDIzMDMwODE1MDk1NzExMzU3NDY=";
		    
		    model.addAttribute("confmKey", confmKey);
		    model.addAttribute("inputYn", inputYn);
		    model.addAttribute("entX", entX);
		    model.addAttribute("entY", entY);
		    
		   
		return "tour/jusoPopup";
	}
}
