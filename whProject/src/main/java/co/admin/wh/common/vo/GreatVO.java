package co.admin.wh.common.vo;

import lombok.Data;

@Data
public class GreatVO {
  int greatCode; //좋아요 번호
  String id; //회원
  String greatFlag; //좋아요 구분 (호텔, 여행 순위기록시) 
  int greatNcode; //게시글 번호
  
}
