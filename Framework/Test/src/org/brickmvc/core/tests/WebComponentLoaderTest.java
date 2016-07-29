package org.brickmvc.core.tests;

import junit.framework.TestCase;
import org.brickmvc.core.WebComponentLoader;
import org.junit.Test;

public class WebComponentLoaderTest extends TestCase {

	@Test
	public void test() {

		assertEquals("Sending empty class name: ", null,
				WebComponentLoader.createWebComponent(""));
		assertEquals("sending null for class name: ", null,
				WebComponentLoader.createWebComponent(null));
		assertEquals(
				"Sending name for class that isn't a WebComponent but exists: ",
				null,
				WebComponentLoader
						.createWebComponent("org.brickmvc.core.validation.EmailValidator"));
		assertEquals("Sending non existent class name: ", null,
				WebComponentLoader.createWebComponent("org.brickmvc.core.NonExistent"));

	}
}
