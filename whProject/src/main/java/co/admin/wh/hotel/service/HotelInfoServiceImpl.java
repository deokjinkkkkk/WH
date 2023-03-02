package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.mapper.HotelMapper;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.RoomVO;

@Service
public class HotelInfoServiceImpl implements HotelInfoService {
	
	private HotelMapper hotelMapper;
	
	//생성자 주입
	@Autowired
	public HotelInfoServiceImpl(HotelMapper hotelMapper) {
		this.hotelMapper = hotelMapper;
	}

	@Override
	public void insertHotelInfo(HotelVO hotelVO) throws IOException, ParserConfigurationException, SAXException {
		hotelMapper.insertHotelInfo(hotelVO);
	}

	@Override
	public void insertRoomInfo(RoomVO roomVO) {
		hotelMapper.insertRoomInfo(roomVO);
		
	}
	
	@Override
	public List<HotelVO> hotelList() {
		return hotelMapper.hotelList();
	}

	@Override
	public List<HotelVO> CrawlingList() {
		// TODO Auto-generated method stub
		return hotelMapper.CrawlingList();
	}

	@Override
	public void updatehotelId() {
		hotelMapper.updatehotelId();
	}

	@Override
	public HotelVO detailSelect(HotelVO vo) {
		return hotelMapper.detailSelect(vo);
	}
}
