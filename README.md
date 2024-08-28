---

# 📊 SAP BusinessObjects Folder Report Structure

![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![BOBJ](https://img.shields.io/badge/BOBJ-Crystal%20Reports-0093d0?style=for-the-badge&logo=sap&logoColor=white)

**Folder Report Structure** is a Java utility that connects to a **SAP BusinessObjects (BOBJ)** environment, retrieves details about reports in a specified folder, and exports this information to an Excel file. This tool is useful for administrators and users who need to manage and audit report structures.

## 🚀 Features

- **Connects to BusinessObjects CMS** to extract report details.
- **Exports report metadata** such as name, owner, creation time, last run time, and folder path to an Excel file.
- **Supports various report types** including Web Intelligence and Crystal Reports.
- **Efficiently handles large datasets** with customizable SQL queries.

## 🛠️ Prerequisites

Before running the script, ensure you have the following:

- **Java Development Kit (JDK)** installed.
- Access to the **BusinessObjects (BOBJ) CMS**.
- Appropriate **Java libraries** for connecting to BOBJ (`crystaldecisions.sdk`).

## 📋 Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/swapnilyavalkar/SAP-BO-Folder-Report-Structure.git
   cd SAP-BO-Folder-Report-Structure
   ```

2. **Add Required Libraries**:
   Ensure the necessary BOBJ SDK libraries are included in your classpath.

3. **Set Up Configuration**:
   Adjust the script's `initcmsexcel` object parameters for your environment.

## 📄 Usage

Follow these steps to run the script:

1. **Compile the Java Code**:
   ```bash
   javac FolderReportStructure.java
   ```

2. **Run the Program**:
   Provide your CMS credentials and folder ID as arguments.
   ```bash
   java FolderReportStructure <CMS_HOST> <Password> <Folder_SI_ID>
   ```

   - `<CMS_HOST>`: The Server name or IP address of your BOBJ CMS e.g. localhost.
   - `<administrator>`: Enterprise default administrator user is used to login.
   - `<Password>`: Enterprise administrator password to access the CMS.
   - `<FolderID>`: The SI_ID of the folder you wish to query.

3. **Check the Output**:
   The Excel file will be generated in the same directory with the relevant report details.

## 🔍 Example

```bash
java FolderReportStructure localhost mypassword 12345
```

This command will generate an Excel file containing the report structure for folder ID `12345` in the specified CMS.

---