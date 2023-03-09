package co.admin.wh.common.service;

import java.util.List;

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

	@Override
	public List<ReportVO> reportCompanion(ReportVO rvo) {
		// TODO Auto-generated method stub
		return reportMapper.reportCompanion(rvo);
	}

	@Override
	public List<ReportVO> reportFree(ReportVO rvo) {
		// TODO Auto-generated method stub
		return reportMapper.reportFree(rvo);
	}

	@Override
	public List<ReportVO> reportReview(ReportVO rvo) {
		// TODO Auto-generated method stub
		return reportMapper.reportReview(rvo);
	}

	@Override
	public List<ReportVO> reportComment(ReportVO rvo) {
		// TODO Auto-generated method stub
		return reportMapper.reportComment(rvo);
	}

	@Override
	public int durationUpdate(ReportVO rvo) {
		// TODO Auto-generated method stub
		return reportMapper.durationUpdate(rvo);
	}

	@Override
	public ReportVO reportCode(ReportVO rvo) {
		// TODO Auto-generated method stub
		return reportMapper.reportCode(rvo);
	}

}
