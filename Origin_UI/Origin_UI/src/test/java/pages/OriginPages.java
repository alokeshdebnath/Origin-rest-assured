package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OriginPages {
	static WebElement userId = null;
	static WebElement password = null;
	static WebElement signIn = null;
	static WebElement LOS = null;
	static WebElement lead = null;
	static WebElement bookLoan = null;
	static WebElement bookLoanH = null;
	static WebElement selectCompany = null;
	static WebElement selectProduct = null;
	static WebElement selectFinsallCompany = null;
	static WebElement selectFinsallProduct = null;
	static WebElement partnerLoanAppID = null;
	static WebElement partnerBorrowerID = null;
	static WebElement firstName = null;
	static WebElement lastName = null;
	static WebElement typeOfAddr = null;
	static WebElement residentAddr = null;
	static WebElement stateName = null;
	static WebElement stateNameTextbox = null;
	static WebElement cityName = null;
	static WebElement pinCode = null;
	static WebElement contactNo = null;
	static WebElement panNo = null;
	static WebElement emailID = null;
	static WebElement dob = null;
	static WebElement gender = null;
	static WebElement addrIdType = null;
	static WebElement addrProofNo = null;
	static WebElement qualification = null;
	static WebElement maritalStatus = null;
	static WebElement selectCompanyDropDownArrow = null;
	static WebElement bookloanProductDropDownArrow = null;
	static WebElement leadProductDropDownArrow = null;
	static WebElement stateNameDropDown = null;
	static WebElement cityNameDropDown = null;
	static WebElement cityNameTextbox = null;	
	static WebElement submit = null;
	static WebElement fromDate = null;		
	static WebElement toDate = null;	
	static WebElement searchButton = null;	
	static WebElement closeButton = null;
	static WebElement actionButton = null;
	static WebElement h6Header = null;
	static WebElement editCityName = null;

	
	
	

	public static WebElement input(WebDriver driver,String strLabel) {
		WebElement input = driver.findElement(By.xpath("//input[@placeholder='"+strLabel+"']"));
		return input;
	}
	public static WebElement inputID(WebDriver driver,String strLabel) {
		WebElement input = driver.findElement(By.xpath("//input[@id='"+strLabel+"']"));
		return input;
	}
	public static WebElement signIn(WebDriver driver) {
		signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
		return signIn;	
	}
	public static WebElement LOS(WebDriver driver) {
		LOS = driver.findElement(By.xpath("//div[@class='MuiPaper-root MuiDrawer-paper MuiDrawer-paperAnchorLeft MuiDrawer-paperAnchorDockedLeft MuiPaper-elevation0']/div/ul/a[text()='LOS']"));
		return LOS;	
	}
	public static WebElement lead(WebDriver driver) {
		 lead = driver.findElement(By.xpath("//ul[@class='MuiList-root jss6 MuiList-padding']/a[text()='Lead']"));
		return lead;	
	}

	public static WebElement h6headercheck(WebDriver driver,String strLabel) {
		WebElement h6headercheck = driver.findElement(By.xpath("//h6[text()='"+strLabel+"']"));
		return h6headercheck;
	}
	public static WebElement h6Header(WebDriver driver) {
		h6Header = driver.findElement(By.xpath("//h6[contains (@class,'MuiTypography-root MuiTypography-h6')]"));
		return h6Header;	
	}
	public static WebElement h2headercheck(WebDriver driver,String strLabel) {
		WebElement h2headercheck = driver.findElement(By.xpath("//h2[text()='"+strLabel+"']"));
		return h2headercheck;
	}
	public static WebElement bookLoan(WebDriver driver) {
		bookLoan = driver.findElement(By.xpath("//ul[@class='MuiList-root jss6 MuiList-padding']/a[text()='Book loan']"));
		return bookLoan;	
	}
	public static WebElement selectCompanyDropDownArrow(WebDriver driver) {
		selectCompanyDropDownArrow = driver.findElement(By.xpath("(//label[text()='Select company']/following::button)[1]"));
		return selectCompanyDropDownArrow;	
	}
	public static WebElement selectFinsallCompany(WebDriver driver) {
		selectFinsallCompany = driver.findElement(By.xpath("//li[@id='companies-option-10']"));
		return selectFinsallCompany;	
	}
	public static WebElement bookloanProductDropDownArrow(WebDriver driver) {
		bookloanProductDropDownArrow = driver.findElement(By.xpath("(//label[text()='Select company']/following::button)[3]"));
		return bookloanProductDropDownArrow;	
	}
	public static WebElement leadProductDropDownArrow(WebDriver driver) {
		leadProductDropDownArrow = driver.findElement(By.xpath("(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-6 css-qqlytg']/div/div/label[text()='Select product']/following::div[@class='MuiAutocomplete-endAdornment css-2iz2x6']/button)[2]"));
		return leadProductDropDownArrow;	
	}
	public static WebElement selectFinsallProduct(WebDriver driver) {
		selectFinsallProduct = driver.findElement(By.xpath("//li[@id='product-option-0']"));
		return selectFinsallProduct;	
	}
	public static WebElement partnerLoanAppID(WebDriver driver) {
		partnerLoanAppID = driver.findElement(By.xpath("//input[@placeholder='Partner Loan App Id']"));
//		partnerLoanAppID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));

		return partnerLoanAppID;	
	}
	public static WebElement partnerBorrowerID(WebDriver driver) {
		partnerBorrowerID = driver.findElement(By.xpath("//input[@placeholder='Partner Borrower Id']"));
		return partnerBorrowerID;	
	}
	public static WebElement firstName(WebDriver driver) {
		firstName = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		return firstName;	
	}
	public static WebElement lastName(WebDriver driver) {
		lastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		return lastName;	
	}
	public static WebElement typeOfAddr(WebDriver driver) {
		typeOfAddr = driver.findElement(By.xpath("//input[@placeholder='Type Of Addr.']"));
		return typeOfAddr;	
	}
	public static WebElement residentAddr(WebDriver driver) {
		residentAddr = driver.findElement(By.xpath("//input[@placeholder='Resident Addr. Line 1']"));
		return residentAddr;	
	}
	public static WebElement stateNameDropDown(WebDriver driver) {
		stateNameDropDown = driver.findElement(By.xpath("(//div[@class='MuiAutocomplete-endAdornment css-2iz2x6']/button)[5]"));
		return stateNameDropDown;	
	}
	public static WebElement stateNameTextbox(WebDriver driver) {
		stateNameTextbox = driver.findElement(By.xpath("//label[text()='State Name']")); 
		return stateNameTextbox;	
	}
	public static WebElement stateName(WebDriver driver) {
		stateName = driver.findElement(By.xpath("//li[@id='mui-20-option-0']")); 
		return stateName;	
	}
	public static WebElement cityNameDropDown(WebDriver driver) {
		cityNameDropDown = driver.findElement(By.xpath("(//div[@class='MuiAutocomplete-endAdornment css-2iz2x6']/button)[6]"));
		return cityNameDropDown;	
	}
	public static WebElement cityNameTextbox(WebDriver driver) {
		cityNameTextbox = driver.findElement(By.xpath("//p[text()='City Name']/following::input[@id='mui-3']")); 
		return cityNameTextbox;	
	}
	public static WebElement cityName(WebDriver driver) {
//		cityName = driver.findElement(By.xpath("//li[@id='mui-5-option-1']"));
		cityName = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/ul[1]/li[2]"));	
		return cityName;	
	}
	public static WebElement editCityName(WebDriver driver) {
		editCityName = driver.findElement(By.xpath("//li[@id='mui-2-option-0']"));
		return editCityName;	
	}
	public static WebElement pinCode(WebDriver driver) {
		pinCode = driver.findElement(By.xpath("//div[@class='MuiFormControl-root MuiTextField-root css-i44wyl']/div/input[@placeholder='Pincode']"));
		return pinCode;	
	}
	public static WebElement contactNo(WebDriver driver) {
		contactNo = driver.findElement(By.xpath("//div[@class='MuiFormControl-root MuiTextField-root css-i44wyl']/div/input[@placeholder='Contact No.']"));
		return contactNo;	
	}
	public static WebElement panNo(WebDriver driver) {
		panNo = driver.findElement(By.xpath("//input[@placeholder='Pan No.']"));
		return panNo;	
	}
	public static WebElement emailID(WebDriver driver) {
		emailID = driver.findElement(By.xpath("//input[@placeholder='Email Id']"));
		return emailID;	
	}
	public static WebElement dob(WebDriver driver) {
		dob = driver.findElement(By.xpath("//input[@placeholder='Date Of Birth']"));
		return dob;	
	}
	public static WebElement gender(WebDriver driver) {
		gender = driver.findElement(By.xpath("//input[@placeholder='Gender']"));
		return gender;	
	}
	public static WebElement addrIdType(WebDriver driver) {
		addrIdType = driver.findElement(By.xpath("//input[@placeholder='Addr. Id Type']"));
		return addrIdType;	
	}
	public static WebElement addrProofNo(WebDriver driver) {
		addrProofNo = driver.findElement(By.xpath("//input[@placeholder='Addr. Id Proof No.']"));
		return addrProofNo;	
	}
	public static WebElement qualification(WebDriver driver) {
		qualification = driver.findElement(By.xpath("//input[@placeholder='Qualification']"));
		return qualification;	
	}
	public static WebElement maritalStatus(WebDriver driver) {
		maritalStatus = driver.findElement(By.xpath("//input[@placeholder='Marital Status']"));
		return maritalStatus;	
	}
	public static WebElement submit(WebDriver driver) {
		submit = driver.findElement(By.xpath("//button[text()='Submit']"));
		return submit;	
	}
	public static WebElement fromDate(WebDriver driver) {
		fromDate = driver.findElement(By.xpath("(//input[@placeholder='mm/dd/yyyy'])[1]"));
		return fromDate;	
	}
	public static WebElement toDate(WebDriver driver) {
		toDate = driver.findElement(By.xpath("(//input[@placeholder='mm/dd/yyyy'])[2]"));
		return toDate;	
	}
	public static WebElement searchButton(WebDriver driver) {
		searchButton = driver.findElement(By.xpath("//button[@variant='contained']"));
		return searchButton;	
	}
	public static WebElement closeButton(WebDriver driver) {
		closeButton = driver.findElement(By.xpath("//button[text()='Close']"));
		return closeButton;	
	}
	public static WebElement actionButton(WebDriver driver) {
		actionButton = driver.findElement(By.xpath("(//table[@class='MuiTable-root css-135kdly']/tbody/tr/td/button[@aria-label='fill loan'])[2]"));
		return actionButton;	
	}
	public static By getTableData(String strLabel) {
		By coloumnValues = (By.xpath("//table[@class='MuiTable-root css-135kdly']/tbody/tr/td[count(//table[@class='MuiTable-root css-135kdly']/thead/tr/th[text()"
				+ "='"+strLabel+"']/preceding-sibling::th)+1]"));
		return coloumnValues;
	}
	public static WebElement leadQueueButton(WebDriver driver,String strLabel) {
		WebElement leadQueueButton = driver.findElement(By.xpath("(//table[@class='MuiTable-root css-135kdly']/tbody/tr/td/button[@aria-label='"+strLabel+"'])[1]"));
		return leadQueueButton;
	}
	public static WebElement buttonText(WebDriver driver,String strLabel) {
		WebElement buttonText = driver.findElement(By.xpath("//button[text()='"+strLabel+"']"));
		return buttonText;
	}
	public static WebElement editableFields(WebDriver driver,String strLabel) {
		WebElement editableFields = driver.findElement(By.xpath("//p[text()='"+strLabel+"']/following-sibling::div/div/input"));
		
		return editableFields;	
	}
	public static WebElement editableFieldsInput(WebDriver driver,String strLabel) {
		WebElement editableFieldsInput = driver.findElement(By.xpath("//input[@name='"+strLabel+"']"));
		
		return editableFieldsInput;	
	}
	public static WebElement dropDownButton(WebDriver driver,String strLabel) {
		WebElement dropDownButton = driver.findElement(By.xpath("(//div[@class='MuiAutocomplete-endAdornment css-2iz2x6']/button)["+strLabel+"]"));
		return dropDownButton;	
	}
	public static WebElement h2HeaderClick(WebDriver driver,String strLabel) {
		WebElement h2HeaderClick = driver.findElement(By.xpath("//h2[text()='"+strLabel+"']/parent::div/parent::div"));
		return h2HeaderClick;	
	}

	public static WebElement selectCompany(WebDriver driver,String strLabel) {
		WebElement selectCompany = driver.findElement(By.xpath("//li[@id='"+strLabel+"']"));
		return selectCompany;	
	}
	public static WebElement selectProduct(WebDriver driver,String strLabel) {
		WebElement selectProduct = driver.findElement(By.xpath("//li[@id='"+strLabel+"']"));
		return selectProduct;	
	}
	public static WebElement liID(WebDriver driver,String strLabel) {
		WebElement liID = driver.findElement(By.xpath("//li[@id='"+strLabel+"']"));
		return liID;	
	}
	public static WebElement aText(WebDriver driver,String strLabel) {
		WebElement aText = driver.findElement(By.xpath("//a[text()='"+strLabel+"']"));
		return aText;	
	}
	public static WebElement loanQueueButtons(WebDriver driver,String strLabel) {
		WebElement loanQueueButtons = driver.findElement(By.xpath("//button[@aria-label='"+strLabel+"']//*[name()='svg']//*[name()='path' and contains(@d,'M')]"));		
		return loanQueueButtons;	
	}
	public static WebElement threeDots(WebDriver driver,String strLabel) {
		WebElement threeDots = driver.findElement(By.xpath("//button[@id='"+strLabel+"']"));		
		return threeDots;	
	}
	public static WebElement threeDotsMenu(WebDriver driver,String strLabel) {
		WebElement threeDotsMenu = driver.findElement(By.xpath("//li[text()='"+strLabel+"']"));		
		return threeDotsMenu;	
	}
	
	public static WebElement select(WebDriver driver,String strLabel) {
		WebElement select = driver.findElement(By.xpath("//select[@name='"+strLabel+"']"));
		return select;	
	}
	public static WebElement firstLoanID(WebDriver driver) {
		WebElement firstLoanID = driver.findElement(By.xpath("//tr[@class='MuiTableRow-root jss187 jss188 css-1gqug66']/td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium jss178 jss190 css-q34dxg']"));
		return firstLoanID;	
	}
	public static WebElement loanTable(WebDriver driver,String strLabel) {
		WebElement loanTable = driver.findElement(By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']/tr/td[text()='"+strLabel+"']"));
		return loanTable;
	}
	public static WebElement spanText(WebDriver driver,String strLabel) {
		WebElement spanText = driver.findElement(By.xpath("//span[text()='"+strLabel+"']/following-sibling::div"));
		return spanText;
	}
	public static WebElement menu(WebDriver driver,String strLabel) {
		WebElement selectMenu = driver.findElement(By.xpath("//ul[@class='MuiList-root jss5 MuiList-padding']/a[text()='"+strLabel+"']"));
		return selectMenu;	
	}
	public static WebElement subMenu(WebDriver driver,String strLabel) {
		WebElement selectSubMenu = driver.findElement(By.xpath("//ul[@class='MuiList-root jss6 MuiList-padding']/a[text()='"+strLabel+"']"));
		return selectSubMenu;	
	}
	public static WebElement loanTableCheckbox(WebDriver driver,String strLabel) {
		WebElement loanTableCheckbox = driver.findElement(By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']/tr/td[text()='"+strLabel+"']/preceding-sibling::td/span"));
		return loanTableCheckbox;
	}
	public static WebElement textArea(WebDriver driver,String strLabel) {
		WebElement textArea = driver.findElement(By.xpath("//textarea[@placeholder='"+strLabel+"']"));
		return textArea;
	}
	
	
	
	
}
