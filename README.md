
# 📚📚📚  saucedemo-test-project 📚📚📚
> ### _It's a user-friendly Simple Test Automation project for site <https://www.saucedemo.com/>_

___
## 🧩 Technology stack 🧩
* Programming Language: Java
* Application Configuration: Maven
* Security: Authentication
* Test framework: JUnit
* Test automation report tool: Allure Report
* Version Control: Git
___

## 👭Contributing👭
1. Ensure you have Java, Maven, and your preferred IDE installed.
2. Clone repository: clone from the console with the command: git clone <https://github.com/BolsunovaNataliia/book-shop>
3. Check customize the database settings in the application.properties file.
4. Create your feature branch (`git checkout -b your-branch`)
5. Build and run project: mvn spring-boot:run
6. Commit your changes (`git commit -am 'Add some feature'`)
7. Push to the branch (`git push origin your-branch`)
8. Create a new Pull Request

___
## 📈 Allure 📈
To run the Allure Report follow these steps:

1. Allure requires Java 8 or higher 
2. Install Allure commandline: 
   #### npm install -g allure-commandline --save-dev ####
3. Run allure help for a list of supported commands.
4. First running tests using Maven:
   #### mvn clean test ####
   Creating catalog allure-result
5. Report generation:
   #### allure generate ####
6. Second running tests using Maven. New test results are reported to the existing allure-results directory.
   #### mvn clean test ####
4. #### allure generate --clean ####
   The --clean switch is needed to clean an existing allure-report directory, if there is one
5. Open report in browser window:
   #### allure open ####
___