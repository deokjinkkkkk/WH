package co.admin.wh.bookmark.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.bookmark.mapper.BookmarkMapper;
import co.admin.wh.bookmark.vo.BookmarkVO;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	@Autowired BookmarkMapper mapper;
	

	@Override
	public int insertBookHotel(BookmarkVO vo) {
		
		return mapper.insertBookHotel(vo);
	}

	@Override
	public List<Map<String, Object>> hotelList(String id) {
		
		return mapper.hotelList(id);
	}

	@Override
	public Map<String, Object> selectBookHotel(BookmarkVO vo) {
		
		return mapper.selectBookHotel(vo);
	}

	@Override
	public int insertBookTrip(BookmarkVO vo) {
		
		return mapper.insertBookTrip(vo);
	}

	@Override
	public List<Map<String, Object>> tripList(String id) {
		
		return mapper.tripList(id);
	}

	@Override
	public Map<String, Object> selectBookTrip(BookmarkVO vo) {
		
		return mapper.selectBookTrip(vo);
	}

	@Override
	public int bookDel(BookmarkVO vo) {
		
		return mapper.bookDel(vo);
	}

	@Override
	public int selectBook(BookmarkVO vo) {
		
		return mapper.selectBook(vo);
	}

	@Override
	public boolean bookmarkCheck(BookmarkVO vo) {
		
		return mapper.bookmarkCheck(vo);
	}
	

}
