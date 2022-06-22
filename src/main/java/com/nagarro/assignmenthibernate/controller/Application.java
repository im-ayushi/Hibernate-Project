bpackage com.nagarro.assignmenthibernate.controller;


import java.io.IOException;
import java.util.ArrayList;

import com.nagarro.assignmenthibernate.domain.Tshirt;
import com.nagarro.assignmenthibernate.dto.TshirtSearchDto;
import com.nagarro.assignmenthibernate.service.TshirtSearchService;
import com.nagarro.assignmenthibernate.view.Input;
import com.nagarro.assignmenthibernate.view.Output;

public class Application {
	public static void main(String[] args) throws IOException {

		Input input = new Input();
		TshirtSearchDto tshirtSearchDto = input.getInput();

		TshirtSearchService tshirtsearchservice = new TshirtSearchService();
		ArrayList<Tshirt> matchedTShirts = tshirtsearchservice.getMatchingTshirts(tshirtSearchDto);

		Output output = new Output();
		output.displayOutput(matchedTShirts);
	}

}
