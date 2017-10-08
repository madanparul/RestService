package com.pb.dps.dto;

import java.util.ArrayList;
import java.util.List;


public class DeniedPartyListDto {
	
	List<DeniedPartyListsDto> DeniedList = new ArrayList<DeniedPartyListsDto>();

	public List<DeniedPartyListsDto> getDeniedList() {
		return DeniedList;
	}

	public void setDeniedList(List<DeniedPartyListsDto> deniedList) {
		DeniedList = deniedList;
	}
	
	@Override
	public String toString() {
		return "DeniedPartyListDto [DeniedList=" + DeniedList + "]";
	}

}
