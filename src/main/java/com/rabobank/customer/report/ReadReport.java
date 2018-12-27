/**
 * 
 */
package com.rabobank.customer.report;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opencsv.CSVReader;
import com.rabobank.customer.util.Constant;
import com.rabobank.customer.util.RecordDescription;
import com.rabobank.customer.util.Validate;

/**
 * @author Manoj Parmar
 *
 */
public class ReadReport {

	/**
	 * Method to process CSV file from given file path.
	 * 
	 * @param csvFile
	 */
	@SuppressWarnings("resource")
	public static void readCsv(String csvFilePath) {
		// Variables to hold the different types of records.
		ArrayList<RecordDescription> processedRecords = new ArrayList<RecordDescription>();
		ArrayList<RecordDescription> failedRecords = new ArrayList<RecordDescription>();

		try {
			// Reader object on the given file path.
			FileReader fileReader = new FileReader(csvFilePath);
			CSVReader csvReader = new CSVReader(fileReader);

			// Ignore headers.
			csvReader.readNext();

			// Now reading the data from CSV file using csvReader.
			String[] rowData;
			while ((rowData = csvReader.readNext()) != null) {
				// Prepare RecordDescription object from each of the fetched
				// row.
				RecordDescription recordDescription = new RecordDescription();
				recordDescription.setTransactionReference(Long.valueOf(rowData[0]));
				recordDescription.setAccountNumber(rowData[1]);
				recordDescription.setDescription(rowData[2]);
				recordDescription.setStartBalance(Double.valueOf(rowData[3]));
				recordDescription.setMutation(Double.valueOf(rowData[4]));
				recordDescription.setEndBalance(Double.valueOf(rowData[5]));

				// Validate the row for the TransactionReference.
				if (Validate.isValidTransactionReference(recordDescription.getTransactionReference())) {
					// Validate the row for the EndBalance
					if (Validate.isValidEndBalance(recordDescription.getStartBalance(), recordDescription.getMutation(),
							recordDescription.getEndBalance())) {
						// If record is valid then set user message and add
						// record to main collection of processed records.
						recordDescription.setMessage(Constant.RECORD_PROCESS_SUCCESS_MESSAGE);
						processedRecords.add(recordDescription);
					} else {
						// If EndBalance is not valid then set user message and
						// add record to main collection of failed records.
						recordDescription.setMessage(Constant.INVALID_END_BALANCE_MESSAGE);
						failedRecords.add(recordDescription);
					}
				} else {
					// If TransactionReference is duplicate then set user
					// message and add record to main collection of failed
					// records.
					recordDescription.setMessage(Constant.INVALID_TRANSACTION_REFERENCE_MESSAGE);
					failedRecords.add(recordDescription);
				}
			}

			// Prepare the CSV report for failure records.
			PrepareReport.prepareCsvOfFailedRecords(failedRecords);

			// Print the success and failed records on console for CSV file.
			printReport(processedRecords, Constant.HEADING_SUCCESS_MESSAGE);
			printReport(failedRecords, Constant.HEADING_FAIL_MESSAGE);
		} catch (Exception e) {
			// Exception message.
			e.printStackTrace();
		}
	}

	/**
	 * Method to process XML file from given file path.
	 * 
	 * @param xmlFilePath
	 */
	public static void readXml(String xmlFilePath) {
		// Variables to hold the different types of records.
		ArrayList<RecordDescription> processedRecords = new ArrayList<RecordDescription>();
		ArrayList<RecordDescription> failedRecords = new ArrayList<RecordDescription>();

		try {
			// Create a file and create a document for the given XML file path.
			File fXmlFile = new File(xmlFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// Normalize the nodes of the XML
			doc.getDocumentElement().normalize();

			// Get all the nodes list.
			NodeList nList = doc.getElementsByTagName("record");

			// Process nodes
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// Prepare RecordDescription object from each of the fetched
					// element.
					RecordDescription recordDescription = new RecordDescription();
					recordDescription.setTransactionReference(Long.valueOf(eElement.getAttribute("reference")));
					recordDescription
							.setAccountNumber(eElement.getElementsByTagName("accountNumber").item(0).getTextContent());
					recordDescription
							.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
					recordDescription.setStartBalance(
							Double.valueOf(eElement.getElementsByTagName("startBalance").item(0).getTextContent()));
					recordDescription.setMutation(
							Double.valueOf(eElement.getElementsByTagName("mutation").item(0).getTextContent()));
					recordDescription.setEndBalance(
							Double.valueOf(eElement.getElementsByTagName("endBalance").item(0).getTextContent()));

					// Validate the row for the TransactionReference.
					if (Validate.isValidTransactionReference(recordDescription.getTransactionReference())) {
						// Validate the row for the EndBalance
						if (Validate.isValidEndBalance(recordDescription.getStartBalance(),
								recordDescription.getMutation(), recordDescription.getEndBalance())) {
							// If record is valid then set user message and add
							// record to main collection of processed records.
							recordDescription.setMessage(Constant.RECORD_PROCESS_SUCCESS_MESSAGE);
							processedRecords.add(recordDescription);
						} else {
							// If EndBalance is not valid then set user message
							// and add record to main collection of failed
							// records.
							recordDescription.setMessage(Constant.INVALID_END_BALANCE_MESSAGE);
							failedRecords.add(recordDescription);
						}
					} else {
						// If TransactionReference is duplicate then set user
						// message and add record to main collection of failed
						// records.
						recordDescription.setMessage(Constant.INVALID_TRANSACTION_REFERENCE_MESSAGE);
						failedRecords.add(recordDescription);
					}
				}

			}

			// Prepare the CSV report for failure records.
			PrepareReport.prepareCsvOfFailedRecords(failedRecords);

			// Print the success and failed records on console for XML file.
			printReport(processedRecords, Constant.HEADING_SUCCESS_MESSAGE);
			printReport(failedRecords, Constant.HEADING_FAIL_MESSAGE);
		} catch (Exception e) {
			// Exception message
			e.printStackTrace();
		}
	}

	/**
	 * Method to print to console logs
	 * 
	 * @param recordDescriptions
	 * @param message
	 */
	private static void printReport(ArrayList<RecordDescription> recordDescriptions, String message) {
		System.out.println("\n" + message + "\n");
		if (recordDescriptions != null) {
			for (RecordDescription recordDescription : recordDescriptions) {
				System.out.print(recordDescription.getTransactionReference() + "\t");
				System.out.print(recordDescription.getAccountNumber() + "\t");
				System.out.print(recordDescription.getDescription() + "\t");
				System.out.print(recordDescription.getStartBalance() + "\t");
				System.out.print(recordDescription.getMutation() + "\t");
				System.out.print(recordDescription.getEndBalance() + "\t");
				System.out.println(recordDescription.getMessage() + "\t");
			}
		}
	}

}
