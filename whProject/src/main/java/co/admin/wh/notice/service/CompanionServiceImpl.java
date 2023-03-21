package co.admin.wh.notice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.CompanionMapper;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;

@Service
public class CompanionServiceImpl implements CompanionService {
	@Autowired
	private CompanionMapper map;
	
	@Override
	public int companionInsert(CompanionVO vo) {
		return map.companionInsert(vo);
	}

	@Override
	public List<CompanionVO> getCompanionList(CompanionSearchVO cvo) {
		// TODO Auto-generated method stub
		return map.getCompanionList(cvo);
	}

	@Override
	public int imgInsert(ImageVO ivo) {
		// TODO Auto-generated method stub
		return map.imgInsert(ivo);
	}

	@Override
	public CompanionVO detailSelect(CompanionVO cvo) {
		// TODO Auto-generated method stub
		return map.detailSelect(cvo);
	}
	
	@Override
	public int getCountTotal(CompanionSearchVO cvo) {
		// 페이지 수 관리
		return map.getCountTotal(cvo);
	}

	@Override
	public int companionUpdate(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.companionUpdate(vo);
	}

	@Override
	public int companionDelete(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.companionDelete(vo);
	}

	@Override
	public int imageSelect(ImageVO ivo) {
		// TODO Auto-generated method stub
		return map.imageSelect(ivo);
	}

	@Override
	public CompanionVO imgSelect(CompanionVO cvo) {
		// TODO Auto-generated method stub
		return map.imgSelect(cvo);
	}


	@Override
	public int comListInsert(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.comListInsert(vo);
	}

	@Override
	public int  comBtn(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.comBtn(vo);
	}

	@Override
	public List<CompanionVO> comSelList(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.comSelList(vo);
	}

	@Override
	public List<CompanionVO> comSelMyList(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.comSelMyList(vo);
	}

	@Override
	public int approve(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.approve(vo);
	}

	@Override
	public int selBtn(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.selBtn(vo);
	}

	@Override
	public int reject(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.reject(vo);
	}

	@Override
	public List<CompanionVO> test(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.test(vo);
	}

	@Override
	public List<CompanionVO> selTitle(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.selTitle(vo);
	}

	@Override
	public int comSelDelete(CompanionVO vo) {
		// TODO Auto-generated method stub
		return map.comSelDelete(vo);
	}

	@Override
	public CompanionVO localSelect(CompanionVO cvo) {
		// TODO Auto-generated method stub
		return map.localSelect(cvo);
	}



}
