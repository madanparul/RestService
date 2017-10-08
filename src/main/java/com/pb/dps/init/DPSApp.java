package com.pb.dps.init;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.pb.dps.dto.DeniedPartyListDto;
import com.pb.dps.dto.DeniedPartyListsDto;
import com.pb.dps.utils.Utils;

public class DPSApp {
	
	private static DPSApp obj = null;
	private DeniedPartyListDto metaData =null;

	public DeniedPartyListDto getMetaData() {
		return metaData;
	}

	private void populateData() throws Exception{
		metaData = new DeniedPartyListDto();
		BufferedInputStream fstream=(BufferedInputStream)DPSApp.class.getResourceAsStream("/DPS_List");
		DataInputStream dstream = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(dstream));
		String strLine = null;
		int count = 0;
		while ((strLine = br.readLine()) != null) {
			DeniedPartyListsDto metaDataEntity = new DeniedPartyListsDto();
			String[] dArr = strLine.split("\\|");
			if(count == 0) {
				count++;
				continue;
			}
			String EntityType = dArr[0].toLowerCase();
			String Name = dArr[1].toLowerCase();
			String Address = dArr[2].toLowerCase();
			metaDataEntity.setEntityType(EntityType);
			metaDataEntity.setName(Name);
			metaDataEntity.setAddress(Address);
			metaData.getDeniedList().add(metaDataEntity);
		}
		br.close();
	}

	public static DPSApp getInstance() throws Exception {
		if(obj == null) {
			obj = new DPSApp();
			obj.populateData();
		}
		return obj;
	}	

	private DPSApp() throws Exception {	
	}
}
