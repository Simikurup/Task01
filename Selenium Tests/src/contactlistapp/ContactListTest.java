package contactlistapp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactListTest {
private WebDriver driver;
private static final String HOMEPAGE_URL = "https://thinking-tester-contact-list.herokuapp.com/";	
private static final String ADDUSER_URL="https://thinking-tester-contact-list.herokuapp.com/addUser";
private static final String FIRSTNAME = "Simi";	
private static final String LASTNAME = "Girija";	
private static final String EMAIL = "rohan@gmail.com";	
private static final String PASSWORD = "Simigirija";	
public static final String CONTACTLIST_URL ="https://thinking-tester-contact-list.herokuapp.com/contactList";
private static final String ADD_CONTACT_URL ="https://thinking-tester-contact-list.herokuapp.com/addContact";
private static final String BIRTH_DATE = "2000/03/10";
private static final String PHONE = "0453637383";
private static final String ADDRESS1="101";
private static final String ADDRESS2="Tudor Street";
private static final String CITY="Hamilton";
private static final String STATE="New South Wales";
private static final String POSTALCODE="2287";
private static final String COUNTRY="Australia";
private static final String CONTACT_DETAILS_URL="https://thinking-tester-contact-list.herokuapp.com/contactDetails";
String name=FIRSTNAME +" "+  LASTNAME;
String address=ADDRESS1+" " +ADDRESS2;
String addressdetails=CITY+" " +STATE+" "+POSTALCODE;

@BeforeMethod
public void SetUp(){
driver=new ChromeDriver();
driver.manage().window().maximize();
}

@AfterMethod
public void TearDown() {
driver.quit();	
}
@Test
public void ContactListAppTest() throws InterruptedException   {
LogInPage loginPage=new LogInPage(driver);
loginPage.openHomePage(HOMEPAGE_URL);
Assert.assertEquals(loginPage.getUrl(), HOMEPAGE_URL);

AddUser addUser=loginPage.clickSignup();
Assert.assertEquals(addUser.getUrl(), ADDUSER_URL);

AddNewContact addNewContact=addUser.addUserdetails(FIRSTNAME,LASTNAME,EMAIL,PASSWORD);
Assert.assertEquals(addNewContact.getURL(), CONTACTLIST_URL);

AddContact addContact=addNewContact.addANewContact();
Assert.assertEquals(addContact.getURL(), ADD_CONTACT_URL );

AddNewContact addNewContacts=addContact.contactDetails(FIRSTNAME,LASTNAME,BIRTH_DATE,EMAIL,PHONE,ADDRESS1,ADDRESS2,CITY,STATE,POSTALCODE,COUNTRY);
Assert.assertEquals(addNewContacts.getURL(), CONTACTLIST_URL);

Assert.assertTrue(addNewContact.isPhoneNoDisplayed(PHONE));
Assert.assertTrue(addNewContact.isNameDisplayed(FIRSTNAME,LASTNAME));
Assert.assertTrue(addNewContact.isDOBDisplayed(BIRTH_DATE));
Assert.assertTrue(addNewContact.isEmailDisplayed(EMAIL));
Assert.assertTrue(addNewContact.isAddressDisplayed(ADDRESS1,ADDRESS2));
Assert.assertTrue(addNewContact.isCityStatePostalcodeDisplayed(CITY,STATE,POSTALCODE));
Assert.assertTrue(addNewContact.isCountryDisplayed(COUNTRY));

Assert.assertTrue(addNewContacts.verifyName().equalsIgnoreCase(name));
Assert.assertEquals(addNewContacts.verifyDOB(),BIRTH_DATE);
Assert.assertEquals(addNewContacts.verifyEmail(),EMAIL);
Assert.assertEquals(addNewContacts.verifyPhoneNo(),PHONE);
Assert.assertTrue(addNewContacts.verifyAddress().equalsIgnoreCase(address));
Assert.assertTrue(addNewContacts.verifyCityState().equalsIgnoreCase(addressdetails));
Assert.assertEquals(addNewContacts.verifyCountry(),COUNTRY);


ContactDetails contactdetails=addNewContact.clickContact();
Assert.assertEquals(contactdetails.getURL(), CONTACT_DETAILS_URL);



AddNewContact addNewcontactpage= contactdetails.deleteContact();
Alert alert = driver.switchTo().alert();
alert.accept();

Assert.assertFalse(addNewContact.isDateOfBirthDisplayed(BIRTH_DATE));


}

}
