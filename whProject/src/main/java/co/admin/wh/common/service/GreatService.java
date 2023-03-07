package co.admin.wh.common.service;

import co.admin.wh.common.vo.GreatVO;

public interface GreatService {
	
	
	//좋아요 눌렀는지 안눌렀는지 확인 
	public boolean greatCheck(GreatVO vo);
	
	//좋아요 insert
	public int greatUP(GreatVO vo);
	
	//좋아요 delete
	public int greatDown(GreatVO vo);
	
	
	//좋아요 total
	public int greatTotal(int greatNcode);

	
	
	
}
