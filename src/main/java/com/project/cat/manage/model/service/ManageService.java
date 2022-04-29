package com.project.cat.manage.model.service;

import java.util.ArrayList;


import com.project.cat.common.Paging;
import com.project.cat.measure.model.vo.CatProfile;

public interface ManageService {
	ArrayList<CatProfile> selectAllCatProfile(String user_id); //한 페이지에 출력 조회용
	CatProfile selectOneCatProfile(CatProfile catprofile); //상세보기 조회용
	ArrayList<CatProfile> selectNameCatProfile(CatProfile catprofile); //고양이 이름으로 조회
	int deleteCatProfile(CatProfile catprofile);
}
