package org.brickmvc.core.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ConfigurationManagerTest.class,
	WebComponentLoaderTest.class,
	NoValueValidatorTest.class,
	ValueLengthValidatorTest.class,
	NumberRangeValidatorTest.class,
	EmailValidatorTest.class
})
public class FullTests {

}
