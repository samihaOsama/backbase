** Home Page **

#TESTCASE-1
#### TITLE: Check number of computers is consistent
#### Preconditions
* N/A
#### Test steps
*Open [Home page](http://computer-database.herokuapp.com/computers)
*Check number in title and total number of computers in pagination current tab 
#### Assertions
* numbers are equal
#### Test result
* Passed


#TESTCASE-2
#### TITLE: Check pagination forward
#### Preconditions
* Next button is clickable
#### Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on next button
#### Assertions
* Page is changed on current tab
#### Test result
* Passed


#TESTCASE-3
#### TITLE: Check pagination backward
#### Preconditions
* previous button is clickable
#### Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on previous button
#### Assertions
* Page is changed on current tab
#### Test result
* Passed

#TESTCASE-4
#### TITLE: Check pagination backward is disabled if no previous records are available
#### Preconditions
* User is on home page on first entry of table
#### Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on previous button
#### Assertions
* Nothing changed
#### Test result
* Passed

#TESTCASE-5
#### TITLE: Check pagination forward is disabled if no records are available afterwards
#### Preconditions
* User is on home page on last entry of table
#### Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on next button
#### Assertions
* Nothing changed
#### Test result
* Passed

#TESTCASE-6
#### TITLE: Check search functionality
#### Preconditions
* At least one record is displayed
#### Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Enter the name of an exsisting computer
* Click search button
#### Assertions
* All records that contain the searching keyword should be displayed
#### Test result
* Passed


********************************************************************************************************************
********************************************************************************************************************

** Computer Detail form **

#TESTCASE-1
## TITLE: Add a new computer with only mandatory fields
## Preconditions
* N/A
## Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on add a new computer button
* Add only computer name
## Assertions
* computer is added successfully
#### Test result
* Passed


#TESTCASE-2
## TITLE: Add new computer with all fields
## Preconditions
* N/A
## Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on add a new computer button
* Add All available fields 
## Assertions
* computer is added successfully
#### Test result
* Passed

#TESTCASE-3
## TITLE: Add new computer with wrong dates
## Preconditions
* N/A
## Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on add a new computer button
* Add All available fields with introduced date is later than discontinued date
## Assertions
* computer is not added
* Error stating date should be added successfully
#### Test result
* Failed

#TESTCASE-4
## TITLE: View computer details from the form
## Preconditions
* At least one computer is available
## Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on a computer's name to view
## Assertions
* computer's form page is opened
* Details of computer is the same as shown on the table
#### Test result
* Passed


#TESTCASE-5
## TITLE: Edit a computer that already exist
## Preconditions
* At least one computer is presented
## Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on a computer's name to view
* change name, introduced date, discontinued date and company with new data and save
## Assertions
* Computer details changed
#### Test result
* Passed


#TESTCASE-6
## TITLE: Edit computer's details from the form then cancel instead of saving
## Preconditions
* At least one computer is presented
## Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on a computer's name to view
* change name, introduced date, discontinued date and company with new data and click cancel
## Assertions
* Computer details didnt changed
#### Test result
* Passed


#TESTCASE-7
## TITLE: Delete computer from the database
## Preconditions
* At least one computer is presented
## Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on a computer's name to view
* Click on delete button
## Assertions
* Computer is deleted successfuly
* Deletion confirmation message is shown
#### Test result
* Passed


#TESTCASE-8
## TITLE: Adding a new computer with exact name as an existing one
## Preconditions
* At least one computer is presented
## Test steps
* Open [Home page](http://computer-database.herokuapp.com/computers)
* Click on add a new computer button
* fill the form with computer's details, having a name exactly as a presented one
## Assertions
* Computer is not added 
* Error message should be stated
#### Test result
* Failed



********************************************************************************************************************
********************************************************************************************************************




