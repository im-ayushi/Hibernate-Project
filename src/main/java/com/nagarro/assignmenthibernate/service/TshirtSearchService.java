package com.nagarro.assignmenthibernate.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.nagarro.assignmenthibernate.dao.TshirtDao;
import com.nagarro.assignmenthibernate.domain.Tshirt;
import com.nagarro.assignmenthibernate.dto.TshirtSearchDto;

public class TshirtSearchService {
	public ArrayList<Tshirt> getMatchingTshirts(TshirtSearchDto tshirtSearchDto) throws FileNotFoundException {

		TshirtDao tshirtDao = new TshirtDao();
		ArrayList<Tshirt> tshirtAllData = (ArrayList<Tshirt>) tshirtDao.getAllTshirts(tshirtSearchDto);

		return tshirtAllData;
	}

}
