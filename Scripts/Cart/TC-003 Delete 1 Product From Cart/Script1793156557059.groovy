import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser(null)

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.waitForElementVisible(findTestObject('Home Page/Page_STORE/a_Samsung galaxy s6'), 3)

WebUI.click(findTestObject('Home Page/Page_STORE/a_Samsung galaxy s6'))

WebUI.waitForElementVisible(findTestObject('Detail Product/button_cart_product'), 3)

WebUI.click(findTestObject('Detail Product/button_cart_product'))

WebUI.waitForAlert(3)

WebUI.acceptAlert()

WebUI.click(findTestObject('Home Page/Page_STORE/a_Home (current)'))

WebUI.waitForElementVisible(findTestObject('Home Page/Page_STORE/nokia_lumia'), 3)

WebUI.click(findTestObject('Home Page/Page_STORE/nokia_lumia'))

WebUI.waitForElementVisible(findTestObject('Detail Product/button_cart_product'), 3)

WebUI.click(findTestObject('Detail Product/button_cart_product'))

WebUI.waitForAlert(3)

WebUI.acceptAlert()

WebUI.waitForElementVisible(findTestObject('Detail Product/nav_cart'), 3)

WebUI.click(findTestObject('Detail Product/nav_cart'))

WebUI.waitForElementVisible(findTestObject('Cart/price_table_cart'), 3)

WebUI.verifyElementVisible(findTestObject('Cart/price_table_cart'))

WebUI.click(findTestObject('Cart/delete_button'))

price1 = WebUI.getText(findTestObject('Cart/price_1'))

total_price = WebUI.getText(findTestObject('Cart/price_total'))

WebUI.verifyEqual(Integer.parseInt(total_price.trim()), Integer.parseInt(price1.trim()))

WebUI.closeBrowser()

