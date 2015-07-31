TicTacToe is a complete Spring Boot MVC web stack. The project is implemented using the Eclipse IDE, and designed to
run in the embedded Tomcat servlet container provided by Spring Boot. Please note that the project has been successfully
tested, compiled and run (ie, we do not commit broken code). Bellow we will detail the setup needed to correctly build and
run the application. Also, how to include the required libraries to the project's build path.

Note: Maven central repository system was used to manage the dependencies, and therefore no binaries will be submitted/committed
in the submission as you would expect. 

To run the application successfully you will need to:
* Load the application using Eclipse.
* Run the `Install` goal from pom.xml to pull in all dependencies.

Once the needed libraries are put in place you can run the TicTacToe game within Eclipse by running the Java main function found
in `com.morganstanley.tictactoe.Application`. Once the application is built and running, simply open a browser and point to
`http://localhost:8080/morganstanley/tictactoe` 

Architectural  Notes:

(i) The Spring Boot Web stack uses the Model View Controller (MVC) architecture
(ii) For users, a generalization/specialization was created for both (X) and (O) users
(iii) A simple round robin is implemented to manage user turns

Assumptions:

Both users are human (ie, human vs. human). Should there be a need to implement a human vs. computer
scenario, the application can be easily extended to accommodate such context.

Further Works:

Replace the View from JSP to a pure HTML5/CSS3 front end and create a communication layer between the
Model and Views using JSON.