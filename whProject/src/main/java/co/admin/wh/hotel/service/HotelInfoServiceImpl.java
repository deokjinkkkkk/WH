package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.mapper.HotelMapper;
import co.admin.wh.hotel.vo.HotelVO;

@Service
public class HotelInfoServiceImpl implements HotelInfoService {
	
	private HotelMapper hotelMapper;
	
	//생성자 주입
	@Autowired
	public HotelInfoServiceImpl(HotelMapper hotelMapper) {
		this.hotelMapper = hotelMapper;
	}

	@Override
	public void insertInfo(HotelVO hotelVO) throws IOException, ParserConfigurationException, SAXException {
		hotelMapper.insertInfo(hotelVO);
	}

	@Override
	public List<HotelVO> hotelList() {
		return hotelMapper.hotelList();
	}

}