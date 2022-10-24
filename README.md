# School Management System

### About
The School Management System is a command line program that allows registered students to view their current courses and add additional courses to their course list. 
This is an application created for a class assignment to practice working with Java Persistence, Hibernate, and querying/ interacting with data from a database (MariaDB). The application was created following the specifications laid out in the assignment details. 

### Initial Set Up 

1. Begin by running the SQL script provided in the resource folder in your database IDE. This script will first drop the database 'schooldb' if it exists. Then it creates the 'schooldb' and uses it. 

	- File name: dbSetUp.sql

2. Run the SMSRunner class. First, a new SMS object is created. Then, the following line of code will create the Student and Course tables, as well as insert initial data into both tables: 

	- `sms.setUpTables();`
	
If you are running the application multiple times, be sure to comment out the line of code above. Otherwise, this will result in an error from trying to insert duplicate data or from creating pre-existing tables. 

### Running the Application

The following line of code in the SMSRunner main method will immediately run the application in the console. 

- `sms.run();`

To login as a valid user, the following credentials may be used: 
- Email: d@g.com
- Password: 123abc

Once validated, users can view their current courses or register for new courses. 

### Testing the Application 
Right click on the `jpa.service` under the src/test/java folder. 
Choose 'Run as > JUnit Test'. 
If prompted for 'Run Configurations', choose 'JUnit 5'.   


