# BankApplication

--- 

## Getting Started
The source code to the project can be accessed by using a git command 'clone' which will create a local repository.

```
 https://github.com/aneri91/bankapplication.git
```
Inside Eclipse

```
File -> Import -> Maven -> Existing Maven project
```

- Create database "cognitivescaledb" in MongoDB
- Change configuration for db in src/main/resources/application.properties

```
cd BankApplication
mvn clean install
```

- copy BankApplication.jar file from target, paste to webapp folder of tomcat and run. or you can directly run from eclipse by running BankApplication.java.

Access the deployed web application at: http://localhost:5554/

---

### Prerequisites

*   Maven 3.x - [Install](http://maven.apache.org/install.html)

*   JRE/JDK 1.8 - [Install](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

*   MongoDB 4.0.0 - [Install](https://docs.mongodb.com/tutorials/install-mongodb-on-windows/)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDK 1.8](https://java.oracle.com/) - Java Developer Kit

## Assumptions

- Account number is created randomly from create account API

## Documentation

- Created Application using spring boot and spring data with MongoDB database
- Added swagger file to test all APIs also by executing http://localhost:5554/swagger-ui.html
- API documents [link](https://documenter.getpostman.com/collection/view/664192-7fc30f50-9160-0837-77ea-95c1d1b1c775)
- Added test cases to test account, beneficiary and transaction APIs using Rest Assured library

## Author

* **Aneri Parikh** - parikhaneri3@gmail.com
