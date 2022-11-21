package com.example.plantmall.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.plantmall.dao.FundingDao;
import com.example.plantmall.domain.Funding;
import com.example.plantmall.domain.Product;

@Service
@Transactional
public class FundingServiceImpl implements FundingService{

	@Autowired
	private FundingDao fundingDao;
	
	@Autowired
	private TaskScheduler scheduler;
	
	@Override
	public List<Funding> getAllFundingList() {
		return fundingDao.getAllFundingList();
	}

	@Override
	public Funding getFunding(String id) {
		return fundingDao.getFunding(id);
	}

	@Override
	public void insertFunding(Funding funding) {
		
		Runnable updateTableRunner = new Runnable() {
			@Override
			public void run() {
				Date curTime = new Date();
				fundingDao.closeFunding(curTime);
			}
		};
		fundingDao.insertFunding(funding);
		scheduler.schedule(updateTableRunner, funding.getCloseDate());
	}

	@Override
	public List<Product> getMyAllProductList(String email) {
		return fundingDao.getMyAllProductList(email);
	}

	@Override
	public void updateFunding(Funding funding) {
		fundingDao.updateFunding(funding);
		
	}

	@Override
	public void deleteFunding(String id) {
		fundingDao.deleteFunding(id);
		
	}

	@Override
	public List<Funding> getAllMyFundingList(String id) {
		return fundingDao.getAllMyFundingList(id);
	}

}
