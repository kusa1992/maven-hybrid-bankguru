#!/bin/sh
echo "------ Step 01: Set project path to variable -------"
project_path="/Users/thao/Documents/Automation class/03 - Hybrid Framework/hybrid-framework-bankguru"
echo "------ Step 02: Go to project path folder -------"
cd "$project_path"
echo "------ Step 03: Run the testcases -------"
java -javaagent:"$project_path/libAllure/aspectjweaver-1.8.10.jar" -classpath "$project_path/bin:$project_path/libAllure/*:$project_path/libFaker/*:$project_path/libExtentV4/*:$project_path/libLogging/*:$project_path/libraries/*:$project_path/libReportNG/*:$project_path/libWebDriverManager/*" org.testng.TestNG "$project_path/bin/runHRMTestCases.xml"
echo "------ Step 04: Load allure command line setting -------"
source ~/.bash_profile
echo "------ Step 05: Generate Allure HTML Report -------"
allure serve ./allure-json/