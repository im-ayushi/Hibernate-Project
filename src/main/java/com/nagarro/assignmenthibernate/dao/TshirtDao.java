package com.nagarro.assignmenthibernate.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.assignmenthibernate.domain.*;
import com.nagarro.assignmenthibernate.domain.Tshirt;
import com.nagarro.assignmenthibernate.dto.TshirtSearchDto;
import com.nagarro.assignmenthibernate.util.Hibernate;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class TshirtDao {

	public static List<Tshirt> getAllRecords(TshirtSearchDto inputData) {
		Transaction transaction = null;
		List<Tshirt> user = null;
		String sortWith = "";

		if (inputData.getOutputPreference().name().equalsIgnoreCase("rating"))
			sortWith = "order by Rating desc";
		if (inputData.getOutputPreference().name().equalsIgnoreCase("price"))
			sortWith = "order by Price";
		if (inputData.getOutputPreference().name().equalsIgnoreCase("both"))
			sortWith = "order by Rating desc, Price";

		try (Session session = Hibernate.buildSessionFactory().getCurrentSession()) {

			transaction = session.beginTransaction();

			user = session
					.createQuery(
							"from Tshirt where Color='" + inputData.getColor() + "' and Gender='"
									+ inputData.getGender() + "' and Size='" + inputData.getSize() + "'" + sortWith,
							Tshirt.class)
					.list();

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	private static void readCsvFile(String file) {
		try {
			Transaction transaction = null;
			CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
			CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(parser).withSkipLines(1)
					.build();
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				Tshirt tshirt = new Tshirt();
				tshirt.setId(nextLine[0]);
				tshirt.setName(nextLine[1]);
				tshirt.setColor(Color.valueOf(nextLine[2].toUpperCase()));
				tshirt.setGender(Gender.valueOf(nextLine[3].toUpperCase()));
				tshirt.setSize(Size.valueOf(nextLine[4].toUpperCase()));
				tshirt.setPrice(Double.parseDouble(nextLine[5]));
				tshirt.setRating(Double.parseDouble(nextLine[6]));
				tshirt.setAvailability(nextLine[7]);

				try (Session session = Hibernate.buildSessionFactory().getCurrentSession()) {

					transaction = session.beginTransaction();

					session.saveOrUpdate(tshirt);

					transaction.commit();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (CsvValidationException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public List<Tshirt> getAllTshirts(TshirtSearchDto inputData) {

		readCsvFile("src\\main\\resources\\Adidas.csv");
		readCsvFile("src\\main\\resources\\Nike.csv");
		readCsvFile("src\\main\\resources\\Puma.csv");

		List<Tshirt> user1 = getAllRecords(inputData);

		return user1;

	}
}
