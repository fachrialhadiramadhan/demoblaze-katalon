# DemoBlaze Web Automation 🛒

> Otomasi UI end-to-end untuk [demoblaze.com](https://www.demoblaze.com) — toko e-commerce demo — dibangun dengan **Katalon Studio**. **70 test case** di 9 modul, 80 page object, 7 test suite, 2 execution collection, dan **10 defect terdokumentasi**.

![Katalon Studio](https://img.shields.io/badge/Tool-Katalon%20Studio-blue)
![Language](https://img.shields.io/badge/Language-Groovy-orange)
![Type](https://img.shields.io/badge/Type-E2E%20UI%20Automation-purple)
![Test Cases](https://img.shields.io/badge/Test%20Cases-70-green)
![Page Objects](https://img.shields.io/badge/Page%20Objects-80-lightgrey)
![Defects](https://img.shields.io/badge/Defects%20Terdokumentasi-10-red)
![Status](https://img.shields.io/badge/Status-Aktif-brightgreen)

---

## ✨ 3 Highlight Utama

### 1️⃣ 70 Test Case E2E — Alur Pembelian Penuh
**70 skenario UI terotomasi di 9 modul** yang mencakup alur e-commerce lengkap: **Sign Up → Login → Jelajah kategori → Detail produk → Tambah ke cart → Kelola cart → Checkout → Konfirmasi order**, ditambah halaman konten (Home, Contact, About Us). Test case dikelompokkan dalam **7 test suite terfokus** dengan **2 execution collection** (Full & Regression) dan **Smoke suite berisi 6 kasus** untuk feedback cepat di setiap build.

### 2️⃣ 10 Defect Terdokumentasi + Suite "Known Bugs"
Bukan cuma happy path. Skenario negatif, boundary, dan payload keamanan memunculkan **10 defect** — checkout dengan field kosong, data pembayaran invalid, bypass form kontak, sampai stored-XSS di sign-up. Kesembilan yang bisa diotomasi dikumpulkan dalam **suite `Known Bugs` yang menegaskan perilaku *rusak saat ini*** — jadi suite berubah **HIJAU saat bug diperbaiki dan MERAH kalau bug itu kambuh**. Ini regression guard terbalik.

### 3️⃣ Assertion Nyata — Bukan Sekadar Klik
Keberadaan elemen itu batas bawah, bukan target. Total cart divalidasi **secara numerik** (`verifyEqual` + `Integer.parseInt` menjumlahkan harga item vs total yang tampil), kredensial disimpan lewat **`setEncryptedText`** bukan teks biasa, dan seluruh suite **tidak terikat environment** berkat `GlobalVariable.baseUrl` dengan **80 page object reusable**.

---

## 📦 Cakupan Modul

| # | Modul | Test Case | Cakupan |
|---|-------|:---------:|---------|
| 1 | Home Page | 10 | Navbar, logo, carousel (slide / indikator / otomatis), pagination produk, footer |
| 2 | Sign Up | 11 | Sukses, field kosong, user duplikat, boundary 1 & 100 karakter, spasi, payload XSS, cancel |
| 3 | Login | 10 | Sukses, password salah, user tidak ada, field kosong, logout, sesi setelah logout, payload SQLi, username huruf kapital, refresh |
| 4 | Categories | 6 | Filter Phones / Laptops / Monitors, info kartu produk, format harga |
| 5 | Detail Product | 6 | Info produk, tambah ke cart, tambah dua kali, tambah tanpa login, ID invalid, kembali ke daftar |
| 6 | Cart | 7 | Tampilan cart (1 & 2 item), hapus 1, hapus semua, order cart kosong, item bertahan setelah refresh, responsif |
| 7 | Checkout | 12 | Sukses, tanpa name / credit card / country+city / month+year, kartu invalid, bulan invalid, cart terkosongkan, double-click, tutup modal, total cocok, guest checkout |
| 8 | Contact | 6 | Sukses, email / name / message kosong, format email invalid, tutup modal |
| 9 | About Us | 2 | Play video, play & pause |
| | **TOTAL** | **70** | |

## 🗂 Test Suite & Collection

| Suite | Kasus | Isi |
|-------|:-----:|-----|
| **Auth Suites** | 19 | Sign Up (10) + Login (9) |
| **Shopping Suites** | 25 | Categories (6) + Detail Product (6) + Cart (6) + Checkout (7) |
| **Content Suites** | 14 | About Us (2) + Contact (3) + Home Page (9) |
| **End To End Suites** | 7 | Sign Up → Login → Tambah ke cart → Cart → Checkout → Verifikasi cart → Logout |
| **Smoke Test Suites** | 6 | Hanya jalur kritis — sinyal lulus/gagal tercepat |
| **Non-Functional Suites** | 2 | Layout cart responsif, double-click purchase |
| **Known Bugs Suites** | 9 | Menegaskan perilaku defect saat ini (berubah merah saat diperbaiki) |
| **Full Collection** | 7 suite | Semua, termasuk known bugs |
| **Regression Collection** | 6 suite | Semua kecuali Known Bugs — suite untuk gate rilis |

> **Smoke Test Suites** — Sign Up → Login → Home Page tampil → Tambah ke cart → Cart Page → Success Checkout.
> **Regression Collection** mengecualikan Known Bugs, jadi run yang hijau berarti "tidak ada regresi baru".

---

## 🐛 Defect Terdokumentasi

| ID | Modul | Skenario | Perilaku yang Diharapkan | Risiko Bila Tidak Divalidasi |
|----|-------|----------|--------------------------|------------------------------|
| DB-01 | Checkout | Purchase tanpa **Country & City** | Muncul validasi, order diblokir | Data pengiriman tidak lengkap, order tidak bisa dikirim |
| DB-02 | Checkout | Purchase tanpa **Month & Year** | Muncul validasi, order diblokir | Masa berlaku tidak terverifikasi, rekonsiliasi pembayaran gagal |
| DB-03 | Checkout | **Credit card invalid** (non-angka) | Muncul validasi format | Data pembayaran sampah ikut tersimpan |
| DB-04 | Checkout | **Month invalid** (di luar rentang 1–12) | Muncul validasi rentang | Data kartu invalid diterima |
| DB-05 | Contact | Kirim pesan dengan **email kosong** | Muncul validasi field wajib | Pengirim tidak bisa dihubungi, celah spam |
| DB-06 | Contact | Kirim pesan dengan **name kosong** | Muncul validasi field wajib | Pengirim tidak teridentifikasi, tidak bisa ditindaklanjuti |
| DB-07 | Contact | Kirim pesan dengan **message kosong** | Muncul validasi field wajib | Payload kosong masuk ke inbox support |
| DB-08 | Login | **Payload SQL injection** di username | Ditolak dengan aman, tanpa bypass auth, tanpa bocor error DB | Bypass autentikasi / celah injeksi |
| DB-09 | Sign Up | **Payload XSS** sebagai username (`<script>…`) | Input disanitasi, script tidak dieksekusi | Stored XSS → pencurian sesi, pengambilalihan akun |
| DB-10 | Home Page | Akses halaman saat **koneksi terputus** | Tampil error yang informatif, bukan layar kosong | UX buruk, tidak ada panduan pemulihan |

> DB-10 terdokumentasi tapi tidak masuk suite `Known Bugs` — butuh manipulasi jaringan manual dan tidak bisa diandalkan di run CI.

---

## 🗂 Struktur Project

```
demoblaze-katalon/
├── Object Repository/            # 80 page object, dikelompokkan per modul
│   ├── About Us/  ├── Cart/  ├── Categories/
│   ├── Checkout/  ├── Contact/  ├── Detail Product/
│   ├── Home Page/ ├── Login/   └── Sign Up/
├── Scripts/                      # 70 test case (Groovy)
│   └── <Modul>/TC-XXX <nama>/
├── Test Cases/                   # metadata test case Katalon
├── Test Suites/                  # 7 suite + collection Full & Regression
├── Profiles/default.glbl         # GlobalVariable: baseUrl, username
├── Include/config/log.properties
├── build.gradle
└── console.properties
```

---

## 🚀 Cara Menjalankan

### Prasyarat
- [Katalon Studio](https://katalon.com/download-studio-free) (versi terbaru)
- Google Chrome (browser default untuk `WebUI.openBrowser`)

### Clone
```bash
git clone https://github.com/fachrialhadiramadhan/demoblaze-katalon.git
```

### Menjalankan

**Opsi 1 — Katalon Studio (GUI)**
1. Buka project di **Katalon Studio**.
2. Buka **Test Suites → Full Collection** (atau **Regression Collection** untuk gate rilis).
3. Klik **Execute**.

**Opsi 2 — Smoke dulu**
```
Test Suites → Smoke Test Suites → Execute
```

**Opsi 3 — CLI headless**
```bash
katalon -noSplash -runMode=console \
  -projectPath="$(pwd)/demoblaze web test.prj" \
  -retry=0 -testSuitePath="Test Suites/Full Collection" \
  -browserType="Chrome (headless)" \
  -executionProfile="default" \
  -apiKey="<your-katalon-api-key>"
```

### Konfigurasi Environment
Atur di **Profiles/default.glbl**:

| Variabel | Default | Keterangan |
|----------|---------|------------|
| `baseUrl` | `https://demoblaze.com/` | Environment target — ganti untuk staging |
| `username` | `''` | User unik untuk alur sign-up |

---

## 🛠 Tech Stack

| Tool | Peran |
|------|-------|
| Katalon Studio | Framework otomasi test |
| Groovy | Bahasa scripting |
| Selenium WebDriver | Engine browser di baliknya |
| Chrome | Browser eksekusi utama |

**Keyword utama yang dipakai:** `openBrowser`, `navigateToUrl`, `click`, `setText`, `sendKeys`, `setEncryptedText`, `waitForElementVisible`, `waitForAlert`, `acceptAlert`, `verifyElementVisible`, `verifyElementText`, `verifyEqual`, `getText`, `closeBrowser`.

---

## 🔍 Catatan Desain

- **Page object reusable** — semua locator berada di `Object Repository`, tidak pernah di-hardcode di script, jadi perubahan UI cukup diperbaiki di satu file.
- **Explicit wait di semua tempat** — `waitForElementVisible` / `waitForAlert` menggantikan `sleep`, membuat run cepat dan stabil.
- **Siklus browser per test** — `openBrowser` di awal, `closeBrowser` di akhir, sehingga tidak ada state bocor antar kasus.
- **Test data inline dan deterministik** — payload tetap, tanpa nilai acak, sehingga kegagalan mudah direproduksi.
- **Secret terenkripsi** — `setEncryptedText` menjaga kredensial tidak tersimpan sebagai teks biasa.

---

## 👤 Author

**Fachri Alhadi Ramadhan** — QA Engineer

- GitHub: [@fachrialhadiramadhan](https://github.com/fachrialhadiramadhan)
- Link Project: [demoblaze-katalon](https://github.com/fachrialhadiramadhan/demoblaze-katalon)

---

## 📄 Lisensi

Didistribusikan untuk keperluan portofolio dan pembelajaran.
