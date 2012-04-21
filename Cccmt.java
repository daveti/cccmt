/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cccmt;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author daveti
 * Coverity Code Complexity Metrics Tool
 */
public class Cccmt {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException {

		// Create the working objects...
		// FnmetricData
		FnmetricData fnmetricDataObj = new FnmetricData();

		// CcmDataArray
		CcmData[] ccmDataArrayObj = {
			new CcmData( 0),	// LCP
			new CcmData( 1),	// MSI
			new CcmData( 2),	// CDMA
			new CcmData( 3)		// ALL
			};

		// CcmProc
		CcmProc ccmProcObj = new CcmProc(ccmDataArrayObj, fnmetricDataObj);

		// CcmXmlParser
		CcmXmlParser ccmXmlParserObj = new CcmXmlParser(ccmProcObj, fnmetricDataObj);

		// Argument processing
		if ((args.length == 2) && (args[ 0].trim().equals("-f") == true)) {
			String myFile = args[ 1].trim();
			if (new File(myFile).exists() == true) {
				System.out.println("Processing " + myFile);
				ccmXmlParserObj.startParser(myFile);

				// Generate the report
				for (CcmData elem : ccmDataArrayObj) {
					elem.dumpCcmData();
				}
			} else {
				System.out.println("Error: " + myFile +
					" does not exist");
			}
		} else {
			printHelp();
		}
	}

	private static void printHelp() {
		System.out.println("Usuage:\n" +
			"\tcccmt -f abc.xml\n" +
			"\t\tgenerate the CCM report from input XML file\n" +
			"\tcccmt -h\n" +
			"\t\tdisplay this help menu");
	}
}