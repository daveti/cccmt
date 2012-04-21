/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cccmt;

/**
 *
 * @author daveti
 * CcmProc is used to analyze the data in FnmetricData
 * and update the data in CcmData accordingly
 */
public class CcmProc {

	public CcmProc(CcmData[] ccmDataArrayObj, FnmetricData fnmetricDataObj) {
		this.ccmDataArrayObj = ccmDataArrayObj;
		this.fnmetricDataObj = fnmetricDataObj;
	}

	public void updateCcmData(int deptValue, long ccmValue) {
		if (deptValue == -1) {
			System.out.println("Error: invalid department value");
			return;
		}

		// Besides updating the corresponding CcmData based on deptValue,
		// we also need to update the CcmData regardless of deptvalue -
		// ALL for all the source files. Assume the deptValue for ALL
		// should be the last one in CcmDataArray - index 3!
		if (ccmValue >= 50) {
			ccmDataArrayObj[ deptValue].
				increaseNumOfFunctionsCcmEqualAbove50();
			ccmDataArrayObj[ DEPT_VALUE_FOR_ALL].
				increaseNumOfFunctionsCcmEqualAbove50();
		} else if ((ccmValue >= 20) && (ccmValue < 50)) {
			ccmDataArrayObj[ deptValue].
				increaseNumOfFunctionsCcmBelow50EqualAbove20();
			ccmDataArrayObj[ DEPT_VALUE_FOR_ALL].
				increaseNumOfFunctionsCcmBelow50EqualAbove20();
		} else if ((ccmValue >= 10) && (ccmValue < 20)) {
			ccmDataArrayObj[ deptValue].
				increaseNumOfFunctionsCcmBelow20EqualAbove10();
			ccmDataArrayObj[ DEPT_VALUE_FOR_ALL].
				increaseNumOfFunctionsCcmBelow20EqualAbove10();
		} else {
			ccmDataArrayObj[ deptValue].
				increaseNumOfFunctionsCcmBelow10();
			ccmDataArrayObj[ DEPT_VALUE_FOR_ALL].
				increaseNumOfFunctionsCcmBelow10();
		}

		ccmDataArrayObj[ deptValue].increaseNumOfTotalFunctions();
		ccmDataArrayObj[ DEPT_VALUE_FOR_ALL].increaseNumOfTotalFunctions();
	}

	public void processFnmetricData() {
		// Process the FnmetricData at first
		// as currently we have only got file and fnmet
		// after CcmXmlParser...

		// Filter checking to bypass certain FnmetircData
		// Currently only got file filter
		for (String elem : CcmFilter.fileFilter) {
			if (elem.equalsIgnoreCase(fnmetricDataObj.getFile()) == true) {
				// Filter this entry
				return;
			}
		}

		// Analyze this FnmetircData entry
		fnmetricDataObj.setSourceFileName();
		fnmetricDataObj.setFnmetFields();
		fnmetricDataObj.setDepartmentValue();

		// Debugging info
		// fnmetricDataObj.dumpFnmetricData();

		// Update the corresponding CcmData
		updateCcmData(fnmetricDataObj.getDepartmentValue(),
			fnmetricDataObj.getCcmValue());
	}

	private CcmData[] ccmDataArrayObj;
	private FnmetricData fnmetricDataObj;
	private final int DEPT_VALUE_FOR_ALL = 3;
}
