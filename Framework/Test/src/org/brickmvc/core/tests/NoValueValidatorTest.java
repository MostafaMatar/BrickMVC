package org.brickmvc.core.tests;

import org.brickmvc.core.validation.NoValueValidator;
import org.junit.Test;

import junit.framework.TestCase;

public class NoValueValidatorTest extends TestCase {

	NoValueValidator nv;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		nv = new NoValueValidator();
	}

	@Test
	public void test() {
		assertEquals("Sending no value: ", false, nv.validateValue(""));
		assertEquals("Sending null value: ", false, nv.validateValue(null));
		assertEquals("Sending String value: ", true, nv.validateValue("Hello"));
		assertEquals("Sending Integer value: ", true, nv.validateValue("25"));
	}
}
