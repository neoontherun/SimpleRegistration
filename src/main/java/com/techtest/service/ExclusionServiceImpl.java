package com.techtest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("exclusionService")
public class ExclusionServiceImpl implements ExclusionService {
	
	static Map<String, String> exclMap = new HashMap<>();
	
	static {
		exclMap.put("123456789", "");
		exclMap.put("123456790", "");
		exclMap.put("123456791", "");
		exclMap.put("123456792", "");
		exclMap.put("123456793", "");
	}

	@Override
	public boolean validate(String dob, String ssn) {
		return exclMap.containsKey(ssn);
	}

}
