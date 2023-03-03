package co.admin.wh.common.vo;

import lombok.Data;

@Data
public class GreatVO {
  int greatCode; //좋아요 번호
  String id; //회원
  int greatFlag; //좋아요 구분 
  int greatNcode; //게시글 좋아요 번호
}
