package com.project.cat.manage.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cat.common.Paging;
import com.project.cat.manage.model.dao.ManageDao;
import com.project.cat.measure.model.vo.CatProfile;

@Service("manageService")
public class ManageServiceImpl implements ManageService{
	@Autowired
	private ManageDao managedao;
	
	@Override
	public ArrayList<CatProfile> selectAllCatProfile(String user_id) {
		return managedao.selectAllCatProfile(user_id);
	}

	@Override
	public CatProfile selectOneCatProfile(CatProfile catprofile) {
		return managedao.selectOneCatProfile(catprofile);
	}

	@Override
	public ArrayList<CatProfile> selectNameCatProfile(CatProfile catprofile) {
		return managedao.selectNameCatProfile(catprofile);
	}
	
	@Override
	public int deleteCatProfile(CatProfile catprofile) {
		return managedao.deleteCatProfile(catprofile);
		
	}
}
