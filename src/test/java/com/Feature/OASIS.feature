Feature: Regression suite for OASIS FLOW verification

  Scenario: Login the HCHB and open the citrix application

       Given Login using valid username and password
       Then  Search the Citrix application link
       Then  Open the Citrix application link
#
#
  Scenario: Delete XML files

        Then  delete xml files

 Scenario: Select the OASIS console and Perform the Workflow-1 Data Preparation

       Given Open the OASIS console
       Then  Apply the status selection for workflow1
       Then  validate the Load button
       Then  Load the data
       Then  Exporting the file for WF1
       Then  Generate the XML files and zip
#
#
  Scenario: Delete XML files

    Then  delete xml files
#
#
  Scenario: Select the OASIS console and Perform the Workflow-2  Data Preparation
#####
#####       Then Apply the status selection for workflow2
#####       Then validate the Load button
#####       Then Load the data
#####       Then Exporting the file for WF2
        Then Generate the CSV file
##
##
##  Scenario: Upload the Data files to s3
##
##    Given Open the s3 bucket
###    Then  upload the XML Zip and CSV files in s3
#
#
##
##  Scenario: Validate the Results of patient Status from Automation Hub Api's
##
##       Given Open the WF2 Data from excel and Load
##       Then  read  Hub API Response
##       Then  assert both records

