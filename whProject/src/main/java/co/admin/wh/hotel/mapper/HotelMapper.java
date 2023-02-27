package co.admin.wh.hotel.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.admin.wh.hotel.vo.HotelVO;

@Repository
public interface HotelMapper {

	public void insertInfo(HotelVO hotelVO);
	public List<HotelVO> hotelList();
}
