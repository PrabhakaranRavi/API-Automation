# REST API Automation Project

This repository contains a REST API automation framework built using Rest Assured, Java, Maven, and TestNG. The project also integrates Postman for manual testing, Jenkins for CI/CD, and Extent Reports for generating detailed test reports.

## Project Structure

- **src/test/java**: Contains the test classes and test utilities.
- **src/main/java**: Contains the main framework components like request builders, response handlers, and utility classes.
- **pom.xml**: Maven configuration file, including dependencies and plugins.
- **testng.xml**: TestNG configuration file to define and run test suites.
- **Jenkinsfile**: Jenkins pipeline configuration for automated test execution.
- **ExtentReports**: Directory where test reports will be generated.

## Tools & Technologies

- **Rest Assured**: For REST API automation.
- **Java**: Programming language used.
- **Postman**: For manual API testing.
- **Maven**: Build tool and dependency management.
- **TestNG**: Test framework for structuring and executing tests.
- **Git**: Version control system.
- **Jenkins**: CI/CD tool for automated test execution.
- **Extent Reports**: For generating detailed test reports.

## APIs Tested

This framework is designed to automate the testing of the following APIs:

- [Reqres](https://reqres.in/): A mock API used for testing.
- [Ergast Motor Racing Data](https://ergast.com/mrd/methods/seasons/): API providing motor racing data.
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/): A fake online REST API for testing and prototyping.

## Getting Started

### Prerequisites

- Java 11 or higher
- Git
- Postman (optional for manual testing)
- Jenkins (optional for CI/CD integration)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
   ```

2. **Install Maven dependencies:**

   ```bash
   mvn clean install
   ```

### Running Tests

- **Using Maven:**

  ```bash
  mvn test
  ```

- **Using TestNG:**

  ```bash
  mvn test -DsuiteXmlFile=testng.xml
  ```

### CI/CD Integration

This project can be integrated with Jenkins for continuous integration and delivery. The `Jenkinsfile` included in the repository sets up the pipeline for building, testing, and reporting.

### Reporting

After running tests, reports will be generated in the `ExtentReports` directory. Open the `index.html` file in a browser to view the detailed test reports.

## Contributing

Contributions are welcome! Please fork this repository and submit pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
