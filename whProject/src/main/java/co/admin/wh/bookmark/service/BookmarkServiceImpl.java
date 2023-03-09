package co.admin.wh.bookmark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.bookmark.mapper.BookmarkMapper;
import co.admin.wh.bookmark.vo.BookmarkVO;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	@Autowired BookmarkMapper mapper;
	
	@Override
	public boolean bookCheck(BookmarkVO vo) {
		return mapper.bookCheck(vo) ;
	}

	@Override
	public int bookInsert(BookmarkVO vo) {
		return mapper.bookInsert(vo);
	}

	@Override
	public int bookDelete(BookmarkVO vo) {
		return mapper.bookDelete(vo);
	}

	@Override
	public List<BookmarkVO> bookList() {		
		return mapper.bookList();
	}

}
