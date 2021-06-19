# CD_SimulatorV2
unix cd simulator written in Java (doesn't use a built-in network)

Java Simulator<br />
-I was able to fully implement the Java abstract change directory function.<br />
-The file runs from the command line<br />
-I was able to run the Junit testing file from within IntelliJ IDE<br />
-Although the Junit file won't run from the command line, it is very easy to get up and running inside IntelliJ<br />
  -Add the org.junit.jupiter:junit-jupiter:5.6.0-M12 Library to the project structure<br />
  -The run button on the testing file should then work as expected.<br />
-Instead of using a built-in network, this implementation assumes that any valid directory argument exists.<br />
-This allowed me to implement the change directory simulator without a Directory class, only string manipulation.<br />
-In order to run the file: javac ChangeDirectory.java   and then  java ChangeDirectory<br />
