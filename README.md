# A Demo project using [SHAFT_Engine](https://github.com/shafthq/SHAFT_ENGINE)
<img src="https://github.com/ShaftHQ/SHAFT_ENGINE/raw/main/src/main/resources/images/shaft.png" alt="SHAFT_ENGINE" style="display:block; margin-left:auto; margin-right:auto;"/>

## I. Steps
1. Login to Website URL: `https://oyn-adminportal-qc-demo.salmonsky-1edff179.westeurope.azurecontainerapps.io/identity/login`
2. Use below user
   - Role: `Store Admin - store 'Almarai'`
   - Email: `store@admin.com`
   - Password: `P@ssw0rd`
3. Click on the "Login" button
4. Verify that the login is successful by checking if the user is redirected to the home page or if a success
   message is displayed.
5. Print the title of the home page.
6. Logout from the application by clicking on the "Logout" button or a similar element.
7. Verify that the user is successfully logged out by checking if the login page is displayed again.
8. Close the browser.

### II. Requirements
• Use Selenium WebDriver with Java.
• Use the appropriate locators and actions to interact with the web elements.
• Make sure to handle any necessary waiting or synchronization.
• Use assertions to verify the expected results.
• Provide clear and readable code with appropriate comments.

### III. Execution
- Navigate to the module that you want to play with. For example ```GUI_Web```
- Navigate to any test class. For example ```src/test/java/TestPackage/TestClass.java```
- Press the green play button next to the class name to run the entire class, or next to a test method to run only that method.
- Test execution will begin, and you'll see the browser opening and the test running.
- The Test execution report will open automatically in your default web browser after the test run is completed.

### IV. Configuration and User Guide
- Change the target browser, operating system, timeouts, and other configurations using the ⚙️ [Configuration Manager](https://ShaftHQ.github.io/SHAFT_ENGINE/).
- Learn more about using SHAFT_Engine from the 👤 [User Guide](https://ShaftHQ.github.io/SHAFT_Engine_Docusaurus/) and 📚 [Javadocs](https://ShaftHQ.github.io/SHAFT_ENGINE/apidocs/index.html).
- <b>Join</b> our ![GitHub Repo stars](https://img.shields.io/github/stars/shafthq/shaft_engine?logoColor=black&style=social) to get notified by email when a new release is pushed out.
- After upgrading your Engine it is sometimes recommended to delete the properties folder ```src\main\resources\properties``` and allow SHAFT to regenerate the defaults by running any test method.

### V. CI/CD & DevOps
- This template comes preloaded with some Selenium grid 4 powered CI/CD pipelines to get you started!
- Navigate to "Actions" and then select "Desktop Browser Tests" from the side menu
- Click "Run workflow" and confirm your choice.
- You will notice that the pipeline has started building, and you can download the execution reports once the execution is completed.

### Stop Reinventing the wheel! Start using SHAFT!