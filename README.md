# DemoBlaze Web Automation 🛒

> End-to-end UI automation of [demoblaze.com](https://www.demoblaze.com) — a demo e-commerce store — built with **Katalon Studio**. **70 test cases** across 9 modules, 80 page objects, 7 suites, 2 execution collections, and **10 documented defects**.

![Katalon Studio](https://img.shields.io/badge/Tool-Katalon%20Studio-blue)
![Language](https://img.shields.io/badge/Language-Groovy-orange)
![Type](https://img.shields.io/badge/Type-E2E%20UI%20Automation-purple)
![Test Cases](https://img.shields.io/badge/Test%20Cases-70-green)
![Page Objects](https://img.shields.io/badge/Page%20Objects-80-lightgrey)
![Defects](https://img.shields.io/badge/Defects%20Documented-10-red)
![Status](https://img.shields.io/badge/Status-Active-brightgreen)

---

## ✨ 3 Highlights

### 1️⃣ 70 E2E Test Cases — Full Purchase Journey
**70 automated UI scenarios across 9 modules** covering the complete e-commerce flow: **Sign Up → Login → Browse categories → Product detail → Add to cart → Cart management → Checkout → Order confirmation**, plus content pages (Home, Contact, About Us). Cases are grouped into **7 focused suites** with **2 execution collections** (Full & Regression) and a **6-case Smoke suite** for fast feedback on every build.

### 2️⃣ 10 Defects Documented + a "Known Bugs" Suite
Not only happy paths. Negative, boundary, and security-payload scenarios surfaced **10 defects** — empty-field checkouts, invalid payment data, contact form bypass, and stored-XSS on sign-up. All 9 automatable ones are bundled into a **dedicated `Known Bugs` suite that asserts the *current faulty* behavior** — so the suite turns **green when a bug is fixed and red if it regresses**. A reverse regression guard.

### 3️⃣ Real Assertions — Not Just Clicks
Element presence is the floor, not the ceiling. Cart totals are validated **numerically** (`verifyEqual` + `Integer.parseInt` summing item prices vs displayed total), credentials are stored with **`setEncryptedText`** instead of plain text, and the whole suite is **environment-agnostic** via `GlobalVariable.baseUrl` with **80 reusable page objects**.

---

## 📦 Module Coverage

| # | Module | Test Cases | Coverage |
|---|--------|:----------:|----------|
| 1 | Home Page | 10 | Navbar, logo, carousel (slide / indicator / auto), product pagination, footer |
| 2 | Sign Up | 11 | Success, empty fields, duplicate user, 1-char & 100-char boundary, spaces, XSS payload, cancel |
| 3 | Login | 10 | Success, wrong password, unknown user, blank field, logout, session after logout, SQLi payload, uppercase username, refresh |
| 4 | Categories | 6 | Phone / Laptop / Monitor filters, product card info, price format |
| 5 | Detail Product | 6 | Product info, add to cart, double add, add without login, invalid ID, back to list |
| 6 | Cart | 7 | Cart view (1 & 2 items), delete 1, delete all, empty-cart order, persistence after refresh, responsive |
| 7 | Checkout | 12 | Success, missing name / credit card / country+city / month+year, invalid card, invalid month, cart cleared, double-click, close modal, total match, guest checkout |
| 8 | Contact | 6 | Success, empty email / name / message, invalid email format, close modal |
| 9 | About Us | 2 | Video play, play & pause |
| | **TOTAL** | **70** | |

## 🗂 Test Suites & Collections

| Suite | Cases | Contents |
|-------|:-----:|----------|
| **Auth Suites** | 19 | Sign Up (10) + Login (9) |
| **Shopping Suites** | 25 | Categories (6) + Detail Product (6) + Cart (6) + Checkout (7) |
| **Content Suites** | 14 | About Us (2) + Contact (3) + Home Page (9) |
| **End To End Suites** | 7 | Sign Up → Login → Add to cart → Cart → Checkout → Verify cart → Logout |
| **Smoke Test Suites** | 6 | Critical path only — fastest pass/fail signal |
| **Non-Functional Suites** | 2 | Cart responsive layout, double-click purchase |
| **Known Bugs Suites** | 9 | Asserts current defective behavior (flips red on fix) |
| **Full Collection** | 7 suites | Everything, including known bugs |
| **Regression Collection** | 6 suites | All except Known Bugs — the suite to gate a release |

> **Smoke Test Suites** — Sign Up → Login → Home Page loads → Add to cart → Cart Page → Success Checkout.
> **Regression Collection** excludes Known Bugs, so a green run means "no new regressions".

---

## 🐛 Documented Defects

| ID | Module | Scenario | Expected Behavior | Risk if Unvalidated |
|----|--------|----------|-------------------|---------------------|
| DB-01 | Checkout | Purchase without **Country & City** | Validation error, order blocked | Incomplete shipping data, undeliverable order |
| DB-02 | Checkout | Purchase without **Month & Year** | Validation error, order blocked | Unverifiable expiry, payment reconciliation failure |
| DB-03 | Checkout | **Invalid credit card** (non-numeric) | Format validation error | Garbage payment data persisted |
| DB-04 | Checkout | **Invalid month** (out of 1–12 range) | Range validation error | Invalid card data accepted |
| DB-05 | Contact | Send message with **empty email** | Required-field validation error | Unreachable sender, spam channel abuse |
| DB-06 | Contact | Send message with **empty name** | Required-field validation error | Unidentifiable sender, no follow-up possible |
| DB-07 | Contact | Send message with **empty message** | Required-field validation error | Empty payload reaches support inbox |
| DB-08 | Login | **SQL injection payload** in username | Rejected safely, no auth bypass, no DB error leak | Authentication bypass / injection surface |
| DB-09 | Sign Up | **XSS payload** as username (`<script>…`) | Input sanitized, no script execution | Stored XSS → session theft, account takeover |
| DB-10 | Home Page | Page access when **connection lost** | Informative error state, not a blank screen | Poor UX, no recovery guidance |

> DB-10 is documented but not part of the `Known Bugs` suite — it requires manual network manipulation and cannot be reliably automated in a CI run.

---

## 🗂 Project Structure

```
demoblaze-katalon/
├── Object Repository/            # 80 page objects, grouped per module
│   ├── About Us/  ├── Cart/  ├── Categories/
│   ├── Checkout/  ├── Contact/  ├── Detail Product/
│   ├── Home Page/ ├── Login/   └── Sign Up/
├── Scripts/                      # 70 test cases (Groovy)
│   └── <Module>/TC-XXX <name>/
├── Test Cases/                   # Katalon test case metadata
├── Test Suites/                  # 7 suites + Full & Regression collections
├── Profiles/default.glbl         # GlobalVariable: baseUrl, username
├── Include/config/log.properties
├── build.gradle
└── console.properties
```

---

## 🚀 Getting Started

### Prerequisites
- [Katalon Studio](https://katalon.com/download-studio-free) (latest)
- Google Chrome (default browser for `WebUI.openBrowser`)

### Clone
```bash
git clone https://github.com/fachrialhadiramadhan/demoblaze-katalon.git
```

### Run

**Option 1 — Katalon Studio (GUI)**
1. Open the project in **Katalon Studio**.
2. Open **Test Suites → Full Collection** (or **Regression Collection** for release gate).
3. Click **Execute**.

**Option 2 — Smoke first**
```
Test Suites → Smoke Test Suites → Execute
```

**Option 3 — Headless CLI**
```bash
katalon -noSplash -runMode=console \
  -projectPath="$(pwd)/demoblaze web test.prj" \
  -retry=0 -testSuitePath="Test Suites/Full Collection" \
  -browserType="Chrome (headless)" \
  -executionProfile="default" \
  -apiKey="<your-katalon-api-key>"
```

### Environment Configuration
Set in **Profiles/default.glbl**:

| Variable | Default | Description |
|----------|---------|-------------|
| `baseUrl` | `https://demoblaze.com/` | Target environment — swap for staging |
| `username` | `''` | Unique user for sign-up flows |

---

## 🛠 Tech Stack

| Tool | Role |
|------|------|
| Katalon Studio | Test automation framework |
| Groovy | Scripting language |
| Selenium WebDriver | Underlying browser engine |
| Chrome | Primary execution browser |

**Key keywords used:** `openBrowser`, `navigateToUrl`, `click`, `setText`, `sendKeys`, `setEncryptedText`, `waitForElementVisible`, `waitForAlert`, `acceptAlert`, `verifyElementVisible`, `verifyElementText`, `verifyEqual`, `getText`, `closeBrowser`.

---

## 🔍 Design Notes

- **Page objects are reusable** — every locator lives in `Object Repository`, never hardcoded in scripts, so a UI change is a one-file fix.
- **Explicit waits everywhere** — `waitForElementVisible` / `waitForAlert` instead of `sleep`, keeping runs fast and stable.
- **Browser lifecycle per test** — `openBrowser` at the start, `closeBrowser` at the end, so no state bleeds between cases.
- **Test data is inline and deterministic** — fixed payloads, no random values, so failures are reproducible.
- **Encrypted secrets** — `setEncryptedText` keeps credentials out of plain-text scripts.

---

## 👤 Author

**Fachri Alhadi Ramadhan** — QA Engineer

- GitHub: [@fachrialhadiramadhan](https://github.com/fachrialhadiramadhan)
- Project Link: [demoblaze-katalon](https://github.com/fachrialhadiramadhan/demoblaze-katalon)

---

## 📄 License

Distributed for portfolio and learning purposes.
