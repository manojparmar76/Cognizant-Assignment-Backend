package com.rabobank.customer.report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;
import com.rabobank.customer.util.Constant;
import com.rabobank.customer.util.RecordDescription;

/**
 * @author Manoj Parmar
 *
 */
public class PrepareReport {

	/**
	 * Method to get file path from user to download the file on given path.
	 * 
	 * @return
	 */
	@SuppressWarnings("resource")
	private static String getFilePath() {
		// File download message for user.
		System.out.println(Constant.FILE_DOWNLOAD_PATH_MESSAGE);
		// Get the file path from user.
		Scanner scanner = new Scanner(System.in);
		String filePath = scanner.nextLine();
		return filePath;
	}

	/**
	 * Method to prepare CSV file based on parameter values.
	 * 
	 * @param recordDescriptions
	 */
	public static void prepareCsvOfFailedRecords(ArrayList<RecordDescription> recordDescriptions) {
		String filePath = getFilePath();
		if (filePath != null && !"".equals(filePath)) {
			
			try {
				// Prepare complete file path with file name.
				filePath = filePath + "/" + Constant.CSV_FAILED_RECORDS_FILE_NAME;

				// Create a file on the given path and open the writer stream on
				// the
				// file to writer the data.
				File csvFile = new File(filePath);
				FileWriter fileWriter = new FileWriter(csvFile);
				CSVWriter csvWriter = new CSVWriter(fileWriter);

				List<String[]> csvRows = new ArrayList<String[]>();

				// Set the Headers for the records.
				String[] headerData = new String[] { "Transaction Reference", "Description", "Failure Message" };
				csvRows.add(headerData);

				// Iterate the given data and prepare the data for writing into
				// the
				// CSV file.
				for (RecordDescription recordDescription : recordDescriptions) {
					String[] rowData = new String[] { recordDescription.getTransactionReference().toString(),
							recordDescription.getDescription(), recordDescription.getMessage() };
					csvRows.add(rowData);
				}

				// Writer the data into the file in one shot.
				csvWriter.writeAll(csvRows);

				// Close all the writer stream.
				csvWriter.close();
				fileWriter.close();

				// Give message to user after successfully created the CSV file.
				System.out.println(Constant.FILE_DOWNLOAD_SUCCESS_MESSAGE);
			} catch (IOException e) {
				// Exception message.
				e.printStackTrace();
			}
		} else {
			// Give message to user if output file path is invalid.
			System.out.println(Constant.INVALID_FILE_PATH_MESSAGE);
		}
	}

}
