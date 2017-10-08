package com.pb.dps.ensemble;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pb.dps.algos.DistanceAlgorithm;
import com.pb.dps.algos.PhoneticAlgorithm;
import com.pb.dps.algos.SimilarityAlgorithm;
import com.pb.dps.dto.AlgoResponseDto;
import com.pb.dps.dto.EnsembleMethodInput;
import com.pb.dps.dto.EnsembleOutputDto;
import com.pb.dps.utils.Utils;

public class ClassifyEntity {

	public EnsembleOutputDto matchString(String str1, String str2) throws Exception{
		EnsembleOutputDto response_obj=new EnsembleOutputDto();
		String distanceAlgo=Utils.getResourceProperty("distance.algo");
		String phoneticsAlgo=Utils.getResourceProperty("phonetics.algo");
		String similarityAlgo=Utils.getResourceProperty("similarity.algo");
		String distanceEnsembleMethodType=Utils.getResourceProperty("distance.ensemble");
		String similarityEnsembleMethodType=Utils.getResourceProperty("similarity.ensemble");
		String phoneticEnsembleMethodType=Utils.getResourceProperty("phonetic.ensemble");
		Double distance_threshold= Double.parseDouble(Utils.getResourceProperty("distance.threshold"));
		Double similarity_threshold=Double.parseDouble(Utils.getResourceProperty("similarity.threshold"));
		Double distance_similarity_threshold=Double.parseDouble(Utils.getResourceProperty("distance.similarity.combined.threshold"));


		Map<String,Double> distanceAlgo_weightMap= parseAlgoMetaData(distanceAlgo);
		Map<String,Double> phoneticsAlgo_weightMap= parseAlgoMetaData(phoneticsAlgo);
		Map<String,Double> similarityAlgo_weightMap= parseAlgoMetaData(similarityAlgo);

		//Phonetics Algo Response
		List<EnsembleMethodInput> phonetics_algo_response_list=new ArrayList<EnsembleMethodInput>();

		Iterator<String> it=phoneticsAlgo_weightMap.keySet().iterator();
		while(it.hasNext()){
			String algoKey= it.next();
			Double algoWeight=phoneticsAlgo_weightMap.get(algoKey);
			String algoClassName=Utils.getResourceProperty(algoKey);
			PhoneticAlgorithm phonetics_alg=(PhoneticAlgorithm) Class.forName(algoClassName).newInstance();
			boolean phonetic_response= phonetics_alg.getPhonetic(str1, str2);
			Double algo_score = null;
			if(phonetic_response){
				algo_score=1.0;
			}else{
				algo_score=0.0;
			}
			EnsembleMethodInput emi=new EnsembleMethodInput();
			emi.setAlgoScore(algo_score);
			emi.setAlgoWeight(algoWeight);
			AlgoResponseDto individual_algo_response=new AlgoResponseDto();
			individual_algo_response.setAlgorithm(algoKey);
			individual_algo_response.setScore(algo_score);
			phonetics_algo_response_list.add(emi);
			response_obj.getScoreList().add(individual_algo_response);
		}

		// Distance Algo Response
		List<EnsembleMethodInput> distance_algo_response_list=new ArrayList<EnsembleMethodInput>();

		Iterator<String> it1=distanceAlgo_weightMap.keySet().iterator();
		while(it1.hasNext()){
			String algoKey= it1.next();
			Double algoWeight=distanceAlgo_weightMap.get(algoKey);
			String algoClassName=Utils.getResourceProperty(algoKey);
			DistanceAlgorithm dis_alg=(DistanceAlgorithm) Class.forName(algoClassName).newInstance();
			Double algo_score= dis_alg.getDistance(str1, str2);	

			if(algoKey.equalsIgnoreCase("distance.Levenshtein")){
				algo_score=algo_score/Math.min(str1.length(), str2.length());
			}

			EnsembleMethodInput emi=new EnsembleMethodInput();
			emi.setAlgoScore(algo_score);
			emi.setAlgoWeight(algoWeight);
			AlgoResponseDto individual_algo_response=new AlgoResponseDto();
			individual_algo_response.setAlgorithm(algoKey);
			individual_algo_response.setScore(algo_score);
			distance_algo_response_list.add(emi);
			response_obj.getScoreList().add(individual_algo_response);
		}

		//Similarity Algo Response
		List<EnsembleMethodInput> similarity_algo_response_list=new ArrayList<EnsembleMethodInput>();

		Iterator<String> it2=similarityAlgo_weightMap.keySet().iterator();
		while(it1.hasNext()){
			String algoKey= it2.next();
			Double algoWeight=similarityAlgo_weightMap.get(algoKey);
			String algoClassName=Utils.getResourceProperty(algoKey);
			SimilarityAlgorithm sim_alg=(SimilarityAlgorithm) Class.forName(algoClassName).newInstance();
			Double algo_score= sim_alg.getSimilarity(str1, str2);			
			EnsembleMethodInput emi=new EnsembleMethodInput();
			emi.setAlgoScore(algo_score);
			emi.setAlgoWeight(algoWeight);
			AlgoResponseDto individual_algo_response=new AlgoResponseDto();
			individual_algo_response.setAlgorithm(algoKey);
			individual_algo_response.setScore(algo_score);
			similarity_algo_response_list.add(emi);
			response_obj.getScoreList().add(individual_algo_response);
		}

		//  Ensembling the response of all algorithms 

		PhoneticsEnsemble phonetic_ensemble_algo=(PhoneticsEnsemble) Class.forName(phoneticEnsembleMethodType).newInstance();
		boolean phonetic_ensemble_output=phonetic_ensemble_algo.getEnsembleOutput(phonetics_algo_response_list);

		DistanceEnsemble dis_ensemble_algo=(DistanceEnsemble) Class.forName(distanceEnsembleMethodType).newInstance();
		Double dist_ensemble_output=dis_ensemble_algo.getEnsembleOutput(distance_algo_response_list);

		SimilarityEnsemble sim_ensemble_algo=(SimilarityEnsemble) Class.forName(similarityEnsembleMethodType).newInstance();
		Double sim_ensemble_output=sim_ensemble_algo.getEnsembleOutput(similarity_algo_response_list);

		response_obj.setInputString1(str1);
		response_obj.setInputString2(str2);

		if(phonetic_ensemble_output){
			response_obj.setMatchScore(1.0);
		}else if(dist_ensemble_output <= distance_threshold){
			response_obj.setMatchScore(1.0-dist_ensemble_output);
		}else if(sim_ensemble_output >=similarity_threshold){
			response_obj.setMatchScore(sim_ensemble_output);
		}else if(((1.0-dist_ensemble_output)+sim_ensemble_output)/2 >= distance_similarity_threshold){
			response_obj.setMatchScore(0.5);
		}else{
			response_obj.setMatchScore(0.0);
		}

		return response_obj;

	}

	private Map<String,Double> parseAlgoMetaData(String algoMetaData){
		Map<String,Double> metaDataMap= new HashMap<String,Double>();
		String[] dArr1=algoMetaData.split(",");

		for(int i=0;i<dArr1.length;i++){
			String[] dArr2=dArr1[i].split(":");
			String algoName=dArr2[0];
			Double score= Double.parseDouble(dArr2[1]) ;
			metaDataMap.put(algoName, score);			
		}
		return metaDataMap;
	}
}
