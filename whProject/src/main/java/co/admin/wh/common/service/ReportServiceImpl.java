package co.admin.wh.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.mapper.ReportMapper;
import co.admin.wh.common.vo.ReportVO;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportMapper reportMapper;

	@Override
	public int reportInsert(ReportVO rvo) {
		// TODO Auto-generated method stub
		return reportMapper.reportInsert(rvo);
	}
}
