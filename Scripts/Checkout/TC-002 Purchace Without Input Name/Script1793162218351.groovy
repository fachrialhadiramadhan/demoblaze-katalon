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

WebUI.click(findTestObject('Detail Product/nav_cart'))

WebUI.waitForElementVisible(findTestObject('Cart/price_1'), 3)

WebUI.click(findTestObject('Cart/button_place_order'))

WebUI.waitForElementVisible(findTestObject('Cart/card_modal_place_order'), 3)

WebUI.sendKeys(findTestObject('Checkout/input_name'), '')

WebUI.sendKeys(findTestObject('Checkout/input_country'), 'Indonesia')

WebUI.sendKeys(findTestObject('Checkout/input_city'), 'Jakarta')

WebUI.sendKeys(findTestObject('Checkout/input_cc'), '4111111111111111')

WebUI.sendKeys(findTestObject('Checkout/input_month'), '12')

WebUI.sendKeys(findTestObject('Checkout/input_year'), '2027')

WebUI.click(findTestObject('Checkout/btn_purchase'))

WebUI.waitForAlert(3)

WebUI.acceptAlert()

WebUI.click(findTestObject('Checkout/btn_close'))

WebUI.closeBrowser()

