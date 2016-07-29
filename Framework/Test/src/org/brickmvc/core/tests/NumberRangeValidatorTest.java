package org.brickmvc.core.tests;


import org.brickmvc.core.validation.NumberRangeValidator;
import org.junit.Test;

import junit.framework.TestCase;

public class NumberRangeValidatorTest extends TestCase {
	
	NumberRangeValidator nr;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		nr=new NumberRangeValidator();
	}

	@Test
	public void test() {
		nr.setMinValue(5);
		nr.setMaxValue(10);
		
		assertEquals("Sending value 2: ", false, nr.validateValue("2"));
		assertEquals("Sending value 20: ", false, nr.validateValue("20"));
		assertEquals("Sending value 7: ", true, nr.validateValue("7"));
		assertEquals("Sending value 5: ", true, nr.validateValue("5"));
		assertEquals("Sending value 10: ", true, nr.validateValue("10"));
		
	}

}
