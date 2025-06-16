# MVI Clean Architecture Sample App

這是一個使用 **MVI 架構** 並結合 **Clean Architecture 分層設計** 的 Android 範例專案。
專案使用 **Jetpack Compose** 建立 UI，並透過 **Koin** 進行依賴注入，搭配 **Orbit MVI** 管理狀態與事件流程。

---

## 🚀 功能說明

- APP 啟動後自動載入 20 筆假資料
- 搜尋功能：根據輸入關鍵字搜尋資料名稱（不區分大小寫）
- 編輯功能：點擊列表項目中的 ✏️ icon 進入編輯名稱模式
- 刪除功能：點擊列表項目中的 🗑️ icon 刪除該筆資料
- 重新載入資料：
    - 清空列表後可重新載入假資料
    - 當搜尋結果為空時也可重新載入

---

## 🧱 專案架構

本專案依照 **Clean Architecture** 進行模組分層，並使用 MVI 模式處理 UI 狀態與事件：

```plaintext
📦 app/
├── data
│   ├── database        # 本地資料來源實作（例如：假資料儲存）
│   └── entities        # 資料實體（Entity）定義
│
├── domain
│   └── use_case        # 業務邏輯處理（資料過濾、更新等）
│
├── presentation
│   ├── widget          # 可重複使用的 Compose UI 元件
│   ├── ListPage        # UI 畫面與事件定義（顯示資料、觸發事件）
│   └── ListViewModel   # MVI ViewModel，處理邏輯與狀態管理
│
├── ui.theme            # 自定義主題與樣式
│
├── MainActivity        # APP 進入點，設定 Navigation
├── MainApplication     # Application 類，初始化設定（例如：Koin DI）
└── Module              # 管理和定義應用程式中的依賴注入，便於各層之間的協作和測試。
```
---

## 🛠️ 使用技術與套件

- **Jetpack Compose** - 建立聲明式 UI
- **Koin** - 輕量級依賴注入框架
- **Orbit MVI** - Kotlin 的 MVI 狀態管理框架
- **Gson** - JSON 處理（如有假資料序列化/反序列化需求）

---

## 📷 畫面展示

[damo.mp4](damo/damo.mp4)

---

## 👨‍💻 作者
- This project was created by [Hsieh Jia Shiuan]