package com.project.cat.measure.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cat.measure.model.vo.CatProfile;


@Repository("catprofile")
public class CatProfileDao {

	@Autowired
	private SqlSessionTemplate session;
	
	public CatProfileDao() {}
	
	public int insertCatProfile(CatProfile catprofile) {
		return session.insert("catprofileMapper.insertCatProfile", catprofile);
	}
	
	public ArrayList<CatProfile> selectCatOneCount(CatProfile catprofile){
		List<CatProfile> list = session.selectList("catprofileMapper.selectCatOneCount", catprofile);
		System.out.println("올텐데");
		return (ArrayList<CatProfile>)list;
	}

}
