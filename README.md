CAB302 Software Development
===========================

# Week 6: Database Connectivity

The classes for this week are about the use of JDBC in a fairly simple application which talks to a backend  database.
Note that there may be individual differences between these instructions and your experience based on how you have
installed your backend database-- adjust settings accordingly.

There are two options for this week's practical; MariaDB and SQLite3. SQLite3 takes less setup work and may be more
ideal for getting something working faster, while MariaDB will give you experience with setting things up to
work with a 'proper' database.

## Preparation - Getting started with SQLite3

SQLite3 does not require any actual database server to be installed and running on your machine. The JDBC driver
itself acts as the database server, which means you only need to install the JDBC driver for SQLite3, point it at
a file, and it will use that file as its database.

First, install the JDBC driver. The easiest way is to do this via Maven. Open up Project Structure, go to Libraries,
click the plus (+) icon ('New Project Library'), choose 'From Maven...', paste in `org.xerial:sqlite-jdbc:3.36.0.3` then
click 'OK'. When it asks you which project you want to add the module to, choose this project (prac06).

You are now almost all set, at least to attempt the address book exercise in this practical. The only thing you need
to do is alter your `db.props` file so that it looks like this:

    jdbc.url=jdbc:sqlite:cab302.db
    jdbc.schema=
    jdbc.username=
    jdbc.password=

Now, when you first connect to this database, the `cab302.db` file will be created  in your project directory. This
file contains the full contents of the database and will be modified whenever you save to it from the AddressBook.

## Preparation - Getting started with MariaDB

First, if you have not done so already, you will need to install MariaDB Server. MariaDB Server can be downloaded from [https://mariadb.org/download/](https://mariadb.org/download/).

The exact process of installation will depend on your particular platform and requirements. There are two specific steps
during installation you should make note of:

1. The port number. By default, MariaDB runs on a port of 3306. This is usually fine, but if you already have another
   MariaDB or MySQL instance installed and running, you should choose another port so they do not clash.
   
2. The root password. You will need this to make any changes to the database.

Note the file 'db.props' in both the lecture examples and this repository. It contains the username and password for the
database. We have set this up with a username of "root" and a password of "", but you will probably need to change these
values. The easiest way to get these exercises running is to just provide your root username and password, but in
the long term it is best to create new users and give them the necessary access to just one database. This way you
do not need to put your MariaDB root password in a file in your project directory.

If you intend to run the lecture examples you will need to run `names.sql`, which is available on the Blackboard
page for Week 6 in the `lec06-dataconn.zip` archive.

Install the MariaDB JDBC driver in any IntelliJ project by opening Project Structure -> Libraries -> New Project Library (+ button) -> From Maven -> Search for `org.mariadb.jdbc:mariadb-java-client` and install the latest version.
Alternatively, the MySQL JDBC driver (MySQL Connector/J) is available from [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/), along with connectors for 
other programming languages. If you download the .jar from the site, you will want to put it in your project directory and then add it as a library in Project Structure.

## Exercise – The JDBC AddressBook

Make sure you have the SQLite3 or MariaDB JDBC driver prepared for this project. If you are using MariaDB you will
also need to have that running.

This code in this repository provides a simple Address Book application used in an earlier Java course by Mal Corney. 
In that environment, it was made to talk to a number of different data sources. Here we shall hook it up to a MariaDB
database via the usual JDBC connectivity. Note that we shall assume the same account as described above.

![Samuel Wonder Dog](imgs/img0.png)

The application itself is as shown, a simple database which holds address details. What follows is a step by step 
approach to entering the relevant code. To help you understand the architecture, `AddressBook` is a simple main class 
which hosts the `AddressBookUI`, which constructs and manages the GUI, utilising the external database via a 
connection obtained via the `DBConnection` class. The individual record is defined by the `Person` class, and the 
remaining classes and interface are concerned with the data source and its management.

The abstraction used by the `AddressBook` application to interact with an external data source is defined in the 
`AddressBookDataSource` interface, and `JDBCAddressBookDataSource` is the JDBC oriented implementation of this 
interface, and thus contains the majority of the SQL code for this exercise. Finally, we note that the `AddressBookData` 
class is a list based structure to allow the application to work consistently with the data, essentially defining a 
collection of persons to be managed and displayed as appropriate.

Data entered through the GUI is to be saved in a MySQL table named `address` in a database named `cab302`. The 
application will need to create the table file if it does not already exist. SQL statements have been provided for 
this purpose, and this should be straightforward.

![`AddressBookDataSource`](imgs/img1.png)

Implementation of the JDBC-based data source is based on the `AddressBookDataSource` interface but uses JDBC constructs 
to implement this functionality.

For simplicity the address data fields (name, street, suburb, phone and email) are all stored as Strings/VARCHAR.

***You will need to have the Java API open and be looking at the `java.sql` packages for this exercise. Instructions 
regarding which exceptions to catch have been left out. Detailed instructions for the completion of the exercise are 
as follows. Code positions are clearly marked in the relevant source files.***

**The `JDBCAddressBookDataSource` class:**

  - Constructor
    - Set up the `PreparedStatement` objects for the SQL queries to be used.
  - `addPerson()`
    - Use the `setString(int, String)` method to set values into each of the place holders for the `addPerson` 
      `PreparedStatement`.
    - Execute the statement.
  - `nameSet()`
    - Execute the `getNameList` statement.
    - Loop through the `ResultSet` and add each  name retrieved to the `Set`.
  - `getPerson()`
    - Set the place holder for the `name` parameter.
    - Execute the query and assign the returned `ResultSet` to `rs`.
    - Extract the data from the columns and use the setter methods in `Person` to receive the data.
  - `getSize()`
    - Execute the rowCount statement.
    - Use `ResultSet next()`, then retrieve the number of rows as an `int`.
  - `deletePerson()`
    - Set the name parameter into the statement and execute the update query.
  - `close()`
    - Close the `connection`.

**The `AddressBookData` class:**

The `JDBCAddressBookDataSource` class should now be able to be integrated with the Address Book application by adding 
an instance variable of type `AddressBookDataSource` and instantiating `JDBCAddressBookDataSource` – with these 
actions to take place in the constructor. The remainder of the class is unchanged.
