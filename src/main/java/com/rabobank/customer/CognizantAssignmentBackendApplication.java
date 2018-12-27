package com.rabobank.customer;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabobank.customer.report.ProcessReport;
import com.rabobank.customer.util.Constant;

/**
 * @author Manoj Parmar
 *
 */
@SpringBootApplication
public class CognizantAssignmentBackendApplication {

	/**
	 * Main method to RUN the application.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//Application welcome message for user.
		System.out.println(Constant.WELCOME_MESSAGE);
		// Application instruction message for user.
		System.out.println(Constant.APPLICATION_INSTRUCTION_MESSAGE);
		// File path message for user.
		System.out.println(Constant.FILE_UPLOAD_PATH_MESSAGE);
		// Get the file path from user.
		Scanner scanner = new Scanner(System.in);
		String filePath = scanner.nextLine();
		// Call the process method to process the file path based on file type.
		ProcessReport.process(filePath);
	}

}
