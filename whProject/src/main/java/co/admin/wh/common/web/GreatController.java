package co.admin.wh.common.web;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.admin.wh.common.service.GreatService;
import co.admin.wh.common.vo.GreatVO;


@RestController
public class GreatController {
  @Autowired
  private GreatService greatService;
  
  
  @RequestMapping("/greatCheck/{greatNcode}")
  @ResponseBody
  public boolean greatCheck(@PathVariable("greatNcode") int greatNcode, GreatVO vo) {
	    boolean greatCheck = false; // 좋아요를 누르지 않았다고 가정
	    vo.setId("user1");
	    vo.setGreatNcode(greatNcode);

	    // 데이터베이스에서 사용자와 코드에 대한 좋아요 여부를 확인합니다.
	    if (greatService.greatCheck(vo)) {
	        greatCheck = true;
	    }

	    return greatCheck;
	    
	   
	}

  @PostMapping("/greatUp/{greatNcode}")
  public Map<String, Object> greatUp(@PathVariable("greatNcode") int greatNcode, String greatFlag, GreatVO vo) {
      Map<String, Object> resultMap = new HashMap<>();
    
     vo.setId("user1");
      vo.setGreatNcode(greatNcode);
      vo.setGreatFlag(greatFlag);
      
      
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
  
  
  @PostMapping("/greatDown/{greatNcode}")
  public Map<String, Object> greatDown(@PathVariable("greatNcode") int greatNcode, String greatFlag, GreatVO vo) {
      Map<String, Object> resultMap = new HashMap<>();
    
      vo.setId("user1");
      vo.setGreatNcode(greatNcode);
      vo.setGreatFlag(greatFlag);
      
      
      int result = greatService.greatDown(vo);

      if (result == 1) {
          resultMap.put("result", "success");
          resultMap.put("message", "좋아요가 취소되었습니다.");
      } else {
          resultMap.put("result", "fail");
          resultMap.put("message", "좋아요 취소에 실패했습니다.");
      }
      
      return resultMap;
   
      
  }
}