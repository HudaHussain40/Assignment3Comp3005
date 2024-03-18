To run the program, open the ConnectDB.java file in the directory.
Ensure that you run the script to create the database before you attempt to connect and make any changes.

To create the database:

1) Use pgadmin or any other software to create your database.
2) Run the following script:

CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
	email TEXT NOT NULL UNIQUE,
	enrollment_date DATE
);

insert into students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');

To run the functions:
	1) To connect to a db, replace the url,username,and password for the postgresql server 
	you would like to connect to.
	2) Call functions in the main function.
	3) This program was coded using Visual Studio Code, and it is recommended to use this when running 
	this program.
