package org.brickmvc.core.tests;

import org.brickmvc.core.validation.EmailValidator;
import org.junit.Test;

import junit.framework.TestCase;

public class EmailValidatorTest extends TestCase {
	EmailValidator e;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		e = new EmailValidator();
	}

	@Test
	public void test() {
		assertEquals("Value asd@dsa.com ", true, e.validateValue("asd@dsa.com"));
		assertEquals("Value dsa.com ", false, e.validateValue("dsa.com"));
		assertEquals("Value asd#dsa.com ", false, e.validateValue("asd#dsa.com"));
		assertEquals("Value asd@dsa%com ", false, e.validateValue("asd@dsa%com"));
		assertEquals("Value asd@dsa.org.eg ", true, e.validateValue("asd@dsa.gov.eg"));
		assertEquals("Value a@b.c ", true, e.validateValue("a@b.c"));
	}
}
