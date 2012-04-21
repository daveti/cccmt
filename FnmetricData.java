/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cccmt;

/**
 *
 * @author daveti
 * Coverity Code Complexity Metrics XML Entry
 */

/* example for xml fnmetric entry...
<fnmetric>
<file>/home_nbu/daveti/R2608/ipm_cov_off_bsub/glob/src/BSPlinux/sharedIP.c</file>
<fnmet>set_shared_ip_state_up;1;26;19;102;40;19;10;11946.4;0.309893;;;339</fnmet>
</fnmetric>
*/
public class FnmetricData {

	public FnmetricData() {
		fileSeparater = "/";
		fnmetSeparater = ";";
		initData();
	}
	
	public void initData() {
		file = null;
		fnmet = null;
		sourceFileName = null;
		fnmetFields = null;
		deptValue = -1;
	}

	public void dumpFnmetricData() {
		System.out.println("----------------------");
		System.out.println("Dump Fenmetric Data:");
		System.out.println("fileSeparater = " + fileSeparater);
		System.out.println("fnmetSeparater = " + fnmetSeparater);
		System.out.println("file = " + file);
		System.out.println("fnmet = " + fnmet);
		System.out.println("sourceFileName = " + sourceFileName);
		System.out.println("deptValue = " + getDepartmentValue());
		System.out.println("functionName = " + getFunctionName());
		System.out.println("ccmValue = " + getCcmValue());
		dumpFnmetFields();
	}

	public int getDepartmentValue() {
		return deptValue;
	}

	public void setDepartmentValue() {
		if (file == null) {
			deptValue = ERR;
			return;
		}

		String[] dirArray = file.split(fileSeparater);
		for (String elem : dirArray) {
			if (elem.equals(DEPT_MSI) == true) {
				deptValue = MSI;
				return;
			}

			if (elem.equals(DEPT_CDMA) == true) {
				deptValue = CDMA;
				return;
			}
		}

		// Default would be LCP
		deptValue = LCP;
	}

	public void setSourceFileName() {
		if (file == null) {
			sourceFileName = null;
		}

		String[] dirArray = file.split(fileSeparater);
		sourceFileName = dirArray[ dirArray.length - 1];
	}

	public void setFnmetFields() {
		if (fnmet == null) {
			fnmetFields = null;
			System.out.println("Error: null fnmet");
		} else {
			fnmetFields = fnmet.split(fnmetSeparater);
		}
	}

	public void dumpFnmetFields() {
		if (fnmetFields == null) {
			System.out.println("fnmetFields = null");
		} else {
			int i = 0;
			for (; i < fnmetFields.length; i++) {
				System.out.println("fnmetFields" + i + " = " + fnmetFields[ i]);
			}
		}
	}

	public String getFunctionName() {
		// Function name is postioned at field 1 - index 0
		if (fnmetFields == null) {
			return null;
		}

		return fnmetFields[ 0];
	}

	public int getCcmValue() {
		// CCM value is postioned at field 8 - index 7
		if ((fnmetFields == null) ||
			(fnmetFields[ 7] == null) ||
			(fnmetFields[ 7].equals(""))) {
			return 0;
		}

		return (Integer.parseInt(fnmetFields[ 7]));
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFnmet() {
		return fnmet;
	}

	public void setFnmet(String fnmet) {
		this.fnmet = fnmet;
	}

	private String fileSeparater;
	private String fnmetSeparater;
	private String file;
	private String fnmet;
	private String sourceFileName;
	private String[] fnmetFields;
	private int deptValue;
	private final String DEPT_MSI = "msi";
	private final String DEPT_CDMA = "cdma";
	public final int ERR = -1;
	public final int LCP = 0;
	public final int MSI = 1;
	public final int CDMA = 2;
}