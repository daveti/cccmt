/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cccmt;

/**
 *
 * @author daveti
 * Coverity Code Complexity Data
 */
public class CcmData {

	public CcmData(int ccmType) {
		numOfTotalFunctions = 0;
		numOfFunctionsCcmEqualAbove50 = 0;
		numOfFunctionsCcmBelow50EqualAbove20 = 0;
		numOfFunctionsCcmBelow20EqualAbove10 = 0;
		numOfFunctionsCcmBelow10 = 0;
		this.ccmType = ccmType;
		setCcmTypeString();
	}

	public long getNumOfTotalFunctions() {
		return numOfTotalFunctions;
	}

	public void increaseNumOfTotalFunctions() {
		numOfTotalFunctions++;
	}

	public long getNumOfFunctionsCcmEqualAbove50() {
		return numOfFunctionsCcmEqualAbove50;
	}

	public void increaseNumOfFunctionsCcmEqualAbove50() {
		numOfFunctionsCcmEqualAbove50++;
	}

	public long getNumofFunctionsCcmBelow50EqualAbove20() {
		return numOfFunctionsCcmBelow50EqualAbove20;
	}

	public void increaseNumOfFunctionsCcmBelow50EqualAbove20() {
		numOfFunctionsCcmBelow50EqualAbove20++;
	}

	public long getNumOfFunctionsCcmBelow20EqualAbove10() {
		return numOfFunctionsCcmBelow20EqualAbove10;
	}

	public void increaseNumOfFunctionsCcmBelow20EqualAbove10() {
		numOfFunctionsCcmBelow20EqualAbove10++;
	}

	public long getNumOfFunctionsCcmBelow10() {
		return numOfFunctionsCcmBelow10;
	}

	public void increaseNumOfFunctionsCcmBelow10() {
		numOfFunctionsCcmBelow10++;
	}

	public void dumpCcmData() {
		System.out.println("==============================================");
		System.out.println("Coverity Code Complexity Metrics (CCM) for " +
			ccmTypeString);
		System.out.println(numOfTotalFunctions +
			" total functions");
		System.out.println(numOfFunctionsCcmEqualAbove50 +
			" functions with CCM >=50");
		System.out.println(numOfFunctionsCcmBelow50EqualAbove20 +
			" functions with CCM <50 >=20");
		System.out.println(numOfFunctionsCcmBelow20EqualAbove10 +
			" functions with CCM <20 >=10");
		System.out.println(numOfFunctionsCcmBelow10 +
			" functions with CCM <10");
	}

	private void setCcmTypeString() {
		// NOTE: the mapping should be lined up with the definition
		// in FnmetricData!
		switch (ccmType) {
			case 0:
				ccmTypeString = "LCP";
				break;
			case 1:
				ccmTypeString = "MSI";
				break;
			case 2:
				ccmTypeString = "CDMA";
				break;
			default:
				ccmTypeString = "UNKNOWN DEPT";
				break;
		}
	}
	
	private long numOfTotalFunctions;
	private long numOfFunctionsCcmEqualAbove50;		// >=50
	private long numOfFunctionsCcmBelow50EqualAbove20;	// >=20, <50
	private long numOfFunctionsCcmBelow20EqualAbove10;	// >=10, <20
	private long numOfFunctionsCcmBelow10;			// <10
	private int ccmType;			// possible value - 0, 1, 2
	private String ccmTypeString;		// mapping - LCP, MSI, CDMA
}
