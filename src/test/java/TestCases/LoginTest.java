package TestCases;

import org.openqa.selenium.By;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
//import base.BaseTest1;
import utilities.DataUtil;

public class LoginTest extends BaseTest {

	
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void VerifyLoginSuccess(String username, String password) {

		type("username_XPATH", username);
		type("password_XPATH", password);
		click("submit_XPATH");
		verifyTitle("Expected_Login_TITLE");

//		Assert.assertTrue(OR.getProperty("Expected_Login_TITLE").equals(driver.getTitle()), "Title Mismatch");
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void VerifyRegistrationProcess(String firstName, String lastName, String phone, String email,
			String address1, String city, String state, String postalCode, String country, String userName,
			String password) {
//		click("register_LINKTEXT");
		driver.findElement(By.linkText("REGISTER")).click();
		type("firstname_NAME", firstName);
		type("lastname_NAME", lastName);
		type("phone_NAME", phone);
		type("email_NAME", userName);
		type("Address_NAME", address1);
		type("city_NAME", city);
		type("state_NAME", state);
		type("PIN_NAME", postalCode);
		type("country_NAME", country);
		type("username_NAME", email);
		type("password_NAME", password);
		type("confirmpassword_NAME", password);
		click("submit_NAME");

//		Assert.assertTrue(OR.getProperty("Expected_Login_TITLE").equals(driver.getTitle()), "Title Mismatch");
	}

//		type("upload0_ID", "C:\\Users\\swaroop reddy\\Documents\\Selenium Course details.xlsx");

//	public void userReg() {
//
//		type("username_ID",username);
////		click("upload_XPATH");
//		type("upload0_ID", "C:\\Users\\swaroop reddy\\Documents\\Selenium Course details.xlsx");
//	}
}

//@DataProvider(name = "dp")
//public Object[][] getData() {
//	String sheetName="Sheet1";
//	int Rows = excel.getRowCount(sheetName);
//	int Cols = excel.getColumnCount(sheetName);
//	System.out.println(Rows-1+" "+Cols);
//	Object[][] data = new Object[Rows - 1][Cols];
//
//	for (int r=2;r<=Rows;r++) {
//		for (int c=0;c<Cols;c++) {
//			data[r-2][c]=excel.getCellData(sheetName, c, r);
//		}
//	}
//	return data;
//}
