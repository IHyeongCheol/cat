package com.project.cat.measure.model.service;

import java.util.ArrayList;

import com.project.cat.measure.model.vo.CatProfile;

public interface CatProfileService {
	int insertCatProfile(CatProfile catprofile);
	ArrayList<CatProfile> selectCatOneCount(CatProfile catprofile);
}
