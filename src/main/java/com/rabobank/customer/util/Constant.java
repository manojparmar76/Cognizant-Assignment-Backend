/**
 * 
 */
package com.rabobank.customer.util;

/**
 * @author Manoj Parmar
 *
 */
public class Constant {

	// Constants
	public static final String CSV_FILE_EXTENSION = ".csv";
	public static final String XML_FILE_EXTENSION = ".xml";

	// File Names
	public static final String CSV_FAILED_RECORDS_FILE_NAME = "failed_report.csv";

	// Messages
	public static final String WELCOME_MESSAGE = "-----> Welcome to The Rabobank Customer Statement Processor Application <-----";
	public static final String APPLICATION_INSTRUCTION_MESSAGE = "System will only process CSV or XML file only.";
	public static final String FILE_UPLOAD_PATH_MESSAGE = "Please provide the complete file path with file name: ";
	public static final String FILE_DOWNLOAD_PATH_MESSAGE = "Please provide the path for downloading the file: ";
	public static final String INVALID_FILE_EXTENSION_MESSAGE = "Application supports CSV or XML file only. Please try again.";
	public static final String INVALID_TRANSACTION_REFERENCE_MESSAGE = "Duplicate transaction reference number.";
	public static final String INVALID_END_BALANCE_MESSAGE = "End balance is not valid.";
	public static final String RECORD_PROCESS_SUCCESS_MESSAGE = "Successfully processed";
	public static final String FILE_DOWNLOAD_SUCCESS_MESSAGE = "File downloaded successfully on the given path. Thank You!";
	public static final String HEADING_SUCCESS_MESSAGE = "List of successfully processed records";
	public static final String HEADING_FAIL_MESSAGE = "List of failed records";
	public static final String INVALID_FILE_PATH_MESSAGE = "The output file path is invalid. report will not genereate";

}
