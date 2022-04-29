package com.project.cat.manage.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cat.measure.model.vo.CatProfile;

@Repository("manageDao")
public class ManageDao implements Serializable{
	
	private static final long serialVersionUID = -110812515373L;
	
	@Autowired
	private SqlSessionTemplate session;
	
	//한 페이지에 출력할 게시글 조회
	public ArrayList<CatProfile> selectAllCatProfile(String user_id) {
		List<CatProfile> list = session.selectList("manageMapper.selectAllCatProfile", user_id);
		return (ArrayList<CatProfile>)list;
	}
	
	public ArrayList<CatProfile> selectNameCatProfile(CatProfile catprofile){
		List<CatProfile> list = session.selectList("manageMapper.selectNameCatProfile", catprofile);
		return (ArrayList<CatProfile>)list;
	}
	
	public CatProfile selectOneCatProfile(CatProfile catprofile) {
		return session.selectOne("manageMapper.selectOneCatProfile", catprofile);
	}
	
	public int deleteCatProfile(CatProfile catprofile) {
		return session.delete("manageMapper.deleteCatProfile", catprofile);
	}
}
