package co.admin.wh.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.trip.TripVO;
import co.admin.wh.trip.mapper.TripMapper;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TripServiceImpl implements TripService{
	
	private TripMapper tripMapper;

	@Autowired
	public TripServiceImpl(TripMapper tripMapper) {
		this.tripMapper = tripMapper;
	}
	
	@Override
	public void insertInfo(TripVO tripVO) {
		tripMapper.insertInfo(tripVO);
	}

	@Override
	public List<TripVO> tripList() {
		return tripMapper.tripList();
	}
	
	
}
