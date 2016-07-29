/**
 * 
 */
package org.brickmvc.core.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author Mustafa
 * 
 */
public class TestRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Result result = JUnitCore.runClasses(FullTests.class);
		if (result.getFailureCount() == 0)
			System.out.println("All tests ran successfully!");
		else {
			for (Failure f : result.getFailures()) {
				System.out.println(f.getMessage());
			}
		}
	}
}
