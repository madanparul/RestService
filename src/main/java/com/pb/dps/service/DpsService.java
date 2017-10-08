package com.pb.dps.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.pb.dps.dto.DeniedPartyListDto;
import com.pb.dps.dto.DeniedPartyListsDto;
import com.pb.dps.dto.EnsembleOutputDto;
import com.pb.dps.dto.ServiceInputDto;
import com.pb.dps.dto.ServiceResponseDto;
import com.pb.dps.ensemble.ClassifyEntity;
import com.pb.dps.init.DPSApp;
import com.pb.dps.utils.Utils;

public class DpsService {

	public enum riskLevel{High, Medium, Low}
	
	public static void main(String[] args) throws Exception{
		DpsService obj1 = new DpsService();
		ServiceInputDto obj2 = new ServiceInputDto();
		obj2.setName("Apurva");
		obj2.setAddress("VimanNagar");
		ServiceResponseDto result= obj1.service(obj2);
		System.out.println(result.toString());
	}

	public ServiceResponseDto service(ServiceInputDto sid) throws Exception{

		ServiceResponseDto responseDto=new ServiceResponseDto();
		DPSApp obj = DPSApp.getInstance();
		ClassifyEntity classifyObj= new ClassifyEntity();
		DeniedPartyListDto metaData = obj.getMetaData();
		String name = sid.getName().toLowerCase();
		//String address = sid.getAddress();
		
		Double threshold= Double.parseDouble(Utils.getResourceProperty("matching.threshold"));

		if(name.isEmpty()) {
			// RETURN EXCEPTION
		}
		/*
		if(address.isEmpty()) {
			// RETURN EXCEPTION
		}*/

		StringTokenizer defaulttokenizer = new StringTokenizer(name);
		ArrayList<String> nameTokens = new ArrayList<String>();

		while(defaulttokenizer.hasMoreTokens()){
			nameTokens.add(defaulttokenizer.nextToken());
		}


		HashMap<String,Double> deniedNameMapScore = new HashMap<String,Double>();
		for(DeniedPartyListsDto deniedParty : metaData.getDeniedList()) {
			String deniedPartyName = deniedParty.getName();
			StringTokenizer defaulttokenizer2 = new StringTokenizer(deniedPartyName);
			ArrayList<String> deniedNameTokens = new ArrayList<String>();
			while(defaulttokenizer2.hasMoreTokens()){
				deniedNameTokens.add(defaulttokenizer2.nextToken());
			}

			HashMap<String,Double> matchScoreMap = new HashMap<String,Double>();
			Iterator<String> iterator = nameTokens.iterator();
			while(iterator.hasNext()){
				Iterator<String> iterator2 = deniedNameTokens.iterator();
				double maxScore = 0;
				String nameToken = iterator.next();
				while(iterator2.hasNext()){					
					String deniedNameToken = iterator2.next();
					//Call Scoring Method 
					EnsembleOutputDto result= classifyObj.matchString(nameToken, deniedNameToken);
					double score =result.getMatchScore();
					if(score > maxScore) {
						maxScore = score;
					}					
				}
				matchScoreMap.put(nameToken, maxScore);
			}
			double nofTokens = nameTokens.size();
			double nofMatchedTokens = 0;
			Iterator<String> it = matchScoreMap.keySet().iterator();
			while(it.hasNext()) {
				String nam = it.next();
				Double sc = matchScoreMap.get(nam);
				if(sc>threshold) {
					nofMatchedTokens=nofMatchedTokens+1;
				}
			}
			double percentScore = nofMatchedTokens/nofTokens;
			deniedNameMapScore.put(deniedPartyName, percentScore);
		}	
		
		double deniedParty_maxScore=-99.0;
		String deniedParty_candidateName=null;

		Iterator<String> it=deniedNameMapScore.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			Double score=deniedNameMapScore.get(key);
			if(score > deniedParty_maxScore){
				deniedParty_maxScore=score;
				deniedParty_candidateName=key;
			}
		}
		if(deniedParty_maxScore > 0.7){
			responseDto.setName(deniedParty_candidateName);
			responseDto.setRiskLevel(riskLevel.High.name());
		}else if(deniedParty_maxScore > 0.3){
			responseDto.setName(deniedParty_candidateName);
			responseDto.setRiskLevel(riskLevel.Medium.name());
		}else if(deniedParty_maxScore> 0){
			responseDto.setName(deniedParty_candidateName);
			responseDto.setRiskLevel(riskLevel.Low.name());
		}
		return responseDto;
	}
}
