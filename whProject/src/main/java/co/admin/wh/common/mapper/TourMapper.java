package co.admin.wh.common.mapper;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.common.vo.TourVO;

public interface TourMapper {
	int tourInsert(TourVO vo);
	List<TourVO> tourList();
	int imgInsert(ImageVO ivo);
	int tourUpdate(TourVO vo);
	int tourAdminInsert(TourVO vo);
}
