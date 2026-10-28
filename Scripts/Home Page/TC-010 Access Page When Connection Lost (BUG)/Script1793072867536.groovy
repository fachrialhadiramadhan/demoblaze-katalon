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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.chromium.ChromiumDriver as ChromiumDriver

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.waitForElementVisible(findTestObject('Home Page/Page_STORE/a_Home (current)'), 5)

def driver = ((DriverFactory.getWebDriver()) as ChromiumDriver)

try {
    // 1. matikan koneksi
    driver.executeCdpCommand('Network.enable', [:])

    driver.executeCdpCommand('Network.emulateNetworkConditions', [('offline') : true, ('latency') : 0, ('downloadThroughput') : -1
            , ('uploadThroughput') : -1])

    // 2. refresh halaman
    WebUI.refresh(FailureHandling.OPTIONAL)

    // verifikasi: bukan layar putih, ada pesan error
    WebUI.verifyTextPresent('ERR_INTERNET_DISCONNECTED', false)
}
finally { 
    // WAJIB: balikin koneksi, biar test lain ga ikut offline
    driver.executeCdpCommand('Network.emulateNetworkConditions', [('offline') : false, ('latency') : 0, ('downloadThroughput') : -1
            , ('uploadThroughput') : -1])

    WebUI.closeBrowser()
}

