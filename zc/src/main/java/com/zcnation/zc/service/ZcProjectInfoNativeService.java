package com.zcnation.zc.service;

import java.security.PublicKey;
import java.util.List;

import com.zcnation.zc.domain.ZcProjectInfo;


public interface ZcProjectInfoNativeService {
	
	public  List<ZcProjectInfo> queryByUserCodeAndProShStatusAndProNameAndProShStatus(Integer userCode,String proName,String proShStatus);
	
	public List<ZcProjectInfo> queryByProShStatus();
	public List<ZcProjectInfo> queryByProTime();

	public List<ZcProjectInfo> queryByProShStatusAndPage(Integer currentPage,Integer sortSele,Integer sortBy,Integer proType,Integer proFabric,Integer stateSele,Integer userCode);
	public Integer queryTotalByProShStatusAndPage(Integer sortSele,Integer sortBy,Integer proType,Integer proFabric,Integer stateSele);
	  public int updateProCanceTimeByProCode(Integer proCode);
}