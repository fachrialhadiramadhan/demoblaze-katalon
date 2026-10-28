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

WebUI.click(findTestObject('Login/Page_STORE/a_login2'))

WebUI.waitForElementVisible(findTestObject('Login/Page_STORE/input_loginusername'), 3)

WebUI.setText(findTestObject('Login/Page_STORE/input_loginusername'), 'aldirektur')

WebUI.setEncryptedText(findTestObject('Login/Page_STORE/input_loginpassword'), 'aeHFOx8jV/A=')

WebUI.click(findTestObject('Login/Page_STORE/button_Log in'))

WebUI.waitForPageLoad(3)

WebUI.waitForElementVisible(findTestObject('Home Page/Page_STORE/a_Samsung galaxy s6'), 3)

WebUI.click(findTestObject('Home Page/Page_STORE/a_Samsung galaxy s6'))

WebUI.waitForElementVisible(findTestObject('Detail Product/button_cart_product'), 3)

WebUI.click(findTestObject('Detail Product/button_cart_product'))

WebUI.waitForAlert(3)

WebUI.acceptAlert()

WebUI.click(findTestObject('Detail Product/nav_cart'))

WebUI.waitForElementVisible(findTestObject('Detail Product/cart_page_h2'), 3)

WebUI.verifyElementVisible(findTestObject('Detail Product/cart_page_title_samsung'))

WebUI.closeBrowser()

