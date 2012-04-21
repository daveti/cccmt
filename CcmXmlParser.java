/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cccmt;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author daveti
 * Coverity Code Complexity Metrics XML Parser
 */
public class CcmXmlParser {

	public CcmXmlParser(CcmProc ccmProcObj, FnmetricData fnmetricObj) {
		this.ccmProcObj = ccmProcObj;
		this.fnmetricObj = fnmetricObj;
	}

	public void startParser(String filename) throws IOException, ParserConfigurationException {
   		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {

				// NOTE: METRICS.error.xml does NOT have a single
				// root element, which would cause SAXParserException.
				// To make cccmt work, root element <daveti> needs
				// to be added at the very beginning of the file and
				// </daveti> at the very ending, making our xml file
				// well formatted.
				boolean bDaveti = false;
				boolean bFnmetric = false;	
				boolean bFile = false;
				boolean bFnmet = false;
 
				@Override
				public void startElement(String uri, String localName,String qName, 
                			Attributes attributes) throws SAXException {

					// System.out.println("Start Element: " + qName);
 
					if (qName.equalsIgnoreCase("fnmetric")) {
						bFnmetric = true;
					}
 
					if (qName.equalsIgnoreCase("file")) {
						bFile = true;
					}
 
					if (qName.equalsIgnoreCase("fnmet")) {
						bFnmet = true;
					}

					if (qName.equalsIgnoreCase("daveti")) {
						bDaveti = true;
					}
				}
 
				@Override
				public void endElement(String uri, String localName,
					String qName) throws SAXException {
 
					// System.out.println("End Element: " + qName);
				}
 
				@Override
				public void characters(char ch[], int start, int length) throws SAXException {

					if (bDaveti) {
						bDaveti = false;
					}

					if (bFnmetric) {
						bFnmetric = false;
					}
 
					if (bFile) {
						// Set the file in FnmetricData obj
						fnmetricObj.setFile(new String(ch, start, length));
						// System.out.println("file: " + fnmetricObj.getFile());
						bFile = false;
					}
 
					if (bFnmet) {
						// Set the fnmet in FnmetricData obj
						fnmetricObj.setFnmet(new String(ch, start, length));
						// System.out.println("fnmet: " + fnmetricObj.getFnmet());

						// Process this FnmetricData entry
						ccmProcObj.processFnmetricData();
						// Init the FnmetricData obj for next parsing
						fnmetricObj.initData();

						bFnmet = false;
					}
				}
     			};
 
			saxParser.parse(filename, handler);
 
		}catch (SAXParseException spe) {  
			StringBuilder sb = new StringBuilder( spe.toString());  
			sb.append("\n  Line number: ").append(spe.getLineNumber());  
			sb.append("\nColumn number: ").append(spe.getColumnNumber());  
			sb.append("\n Public ID: ").append(spe.getPublicId());  
        		sb.append("\n System ID: ").append(spe.getSystemId()).append("\n");  
        		System.out.println(sb.toString());  
    		}catch(SAXException se) {  
       			System.out.println("SAX threw " + se);         
       			se.printStackTrace(System.out);
		}
	}

	private CcmProc ccmProcObj;
	private FnmetricData fnmetricObj;
}