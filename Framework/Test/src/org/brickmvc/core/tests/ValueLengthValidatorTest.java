package org.brickmvc.core.tests;

import org.brickmvc.core.validation.ValueLengthValidator;
import org.junit.Test;

import junit.framework.TestCase;

public class ValueLengthValidatorTest extends TestCase {

	ValueLengthValidator vl;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		vl = new ValueLengthValidator();
	}

	@Test
	public void test() {
		vl.setMinLength(2);
		vl.setMaxLength(5);

		assertEquals("Sending value length 1: ", false, vl.validateValue("2"));
		assertEquals("Sending value length 7: ", false, vl.validateValue("Careful"));
		assertEquals("Sending value length 4: ", true, vl.validateValue("1145"));
		assertEquals("Sending value length 2: ", true, vl.validateValue("Me"));
		assertEquals("Sending value length 5: ", true, vl.validateValue("Hello"));
	}

}
