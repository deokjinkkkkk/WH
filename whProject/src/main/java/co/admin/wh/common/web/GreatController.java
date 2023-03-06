package co.admin.wh.common.web;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import co.admin.wh.common.service.GreatService;
import co.admin.wh.common.vo.GreatVO;


@RestController
public class GreatController {
  @Autowired
  private GreatService greatService;

  @PostMapping("/great/{greatNcode}/{greatFlag}")
  public Map<String, Object> greatUp(@PathVariable int greatNcode, @PathVariable String greatFlag, HttpSession session,GreatVO vo) {
      Map<String, Object> resultMap = new HashMap<>();
      
      String userId = (String) session.getAttribute("id");
      if (userId == null || userId.isEmpty()) {
          resultMap.put("result", "fail");
          resultMap.put("message", "로그인이 필요합니다.");
          return resultMap;
      }

      vo.setId((String)session.getAttribute("user1"));
      vo.setGreatCode(greatNcode);
      vo.setGreatFlag((String)session.getAttribute(greatFlag));
      
      System.out.println("greatNcode: " + greatNcode);
      System.out.println("greatFlag: " + greatFlag);
      System.out.println("userId: " + userId);
      
      int result = greatService.greatUP(vo);

      if (result == 1) {
          resultMap.put("result", "success");
          resultMap.put("message", "좋아요가 추가되었습니다.");
      } else {
          resultMap.put("result", "fail");
          resultMap.put("message", "좋아요 추가에 실패했습니다.");
      }

      return resultMap;
   
      
  }
  
}