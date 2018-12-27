/**
 * 
 */
package com.rabobank.customer.report;

import com.rabobank.customer.util.Constant;

/**
 * @author Manoj Parmar
 *
 */
public class ProcessReport {

	/**
	 * Method to process file path and navigate to respective method based on
	 * CSV or XML file type.
	 * 
	 * @param filePath
	 */
	public static void process(String filePath) {
		// Find the file extension.
		String fileExtension = filePath.substring(filePath.indexOf("."));

		if (Constant.CSV_FILE_EXTENSION.equalsIgnoreCase(fileExtension)) {
			// If file type is CSV then move execution to CSV handler method.
			ReadReport.readCsv(filePath);
		} else if (Constant.XML_FILE_EXTENSION.equalsIgnoreCase(fileExtension)) {
			// If file type is XML then move execution to XML handler method.
			ReadReport.readXml(filePath);
		} else {
			// If file type is not CSV or XML then give message to user.
			System.out.println(Constant.INVALID_FILE_EXTENSION_MESSAGE);
		}
	}

}
