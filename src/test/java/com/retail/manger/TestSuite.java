package com.retail.manger;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.retail.manger.location.LocationServiceTest;
import com.retail.manger.retail.RetailServiceTest;

@RunWith(Suite.class)
@SuiteClasses({LocationServiceTest.class, RetailServiceTest.class})
public class TestSuite {

}
