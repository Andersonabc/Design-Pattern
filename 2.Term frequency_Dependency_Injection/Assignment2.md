# Design Patterns 2021 Spring Assignment

## Assignment 2

__Deadline__: 3/15 23:59.

Here's the
[template code](https://ssl-gitlab.csie.ntut.edu.tw/course/dp2021s_hw_template)
of this assignment, please clone this repo and __DELETE THE FOLDER `.git` AND
FOLLOW THIS TEMPLATE TO COMPLETE YOUR HW__. This template is just the skeleton.
You should finish implementation by yourself.

For this assignment, you'll solve the term frequency counter problem using
abstract things style.

Please complete the given code to satisfy all the specs of the Term Frequency
Counter and the following conditions.
- interface `IStopWordManager`, `IDataStorageManager`, `IWordFrequencyManager`,
  which will be implemented by class `StopWordManager`, `DataStorageManager`,
  `WordFrequencyManager`, and `WordFrequencyManagerStream` respectively.
- `WordFrequencyManager` should use `Collection.sort()` to sort.
- `WordFrequencyManagerStream` should use `stream().sorted()` to sort.
- class `IOHandler`, which should
  - read the text file, normalize words to lower case and filter word with 
  regular expression, and
  - output your data to a text file.
- class `Main`, which should have exactly one function `main()`. You should
  initialize `IStopWordManager`, `IDataStorageManager`, `IWordFrequencyManager`, 
  `IOHandler` and `WordFrequencyController`, and use dependency injection 
  technique in `main()`.
- class `WordFrequencyException`, which should extends `RuntimeException`. You 
  should use it to handle exception condition.
- for the arguments of `main(String args[])`,
  - args[0] is the file path of stop word.
  - args[1] is the file path of text to be counted.
  - args[2] is the file path of output.
  - args[3] is the range.
  - args[4] is the sorting order.
- please follow the file structure below:
  ```bash
    .
    ├── input
    │   ├── pride-and-prejudice.txt
    │   └── stop_words.txt
    ├── output
    │   ├── result.txt
    │   ├── sample_error_message.md
    │   └── sample_output.txt
    ├── pom.xml
    └── src
        ├── main
        │   └── java
        │       └── org
        │           └── ntutssl
        │               └── termfrequency
        │                   ├── DataStorageManager.java
        │                   ├── IDataStorageManager.java
        │                   ├── IOHandler.java
        │                   ├── IStopWordManager.java
        │                   ├── IWordFrequencyManager.java
        │                   ├── Main.java
        │                   ├── StopWordManager.java
        │                   ├── WordFrequencyController.java
        │                   ├── WordFrequencyException.java
        │                   ├── WordFrequencyManager.java
        │                   └── WordFrequencyManagerStream.java
        └── test
            └── java
                └── org
                    └── ntutssl
                        └── termfrequency
                            ├── DataStorageManagerTest.java
                            ├── IOHandlerTest.java
                            ├── StopWordManagerTest.java
                            ├── WordFrequencyManagerStreamTest.java
                            └── WordFrequencyManagerTest.java
  ```

__Notes__:
- Please execute `mvn compile` or `mvn test` after modifications in your code,
  otherwise, your latest code won't be executed.
- If your code fails to compile on jenkins server, you'll get __no point__ for
  the assignment.
- Your program should be able to handle unexpected input data, that is, you
  should do error handling if necessary.
- You should make your unit test fail if the program that should throw error 
  runs without any problem.
- When writing unit tests, you should take as many situations as possible into
  consideration.
- In TA test, we use StopWathch in package `com.google.guava` to compare the 
  execution time of the two different data structure in `StopWordManager`.
- For testing convenience, we specify the output message of exception in TA test 
  case, please refer to `output/sample_error_message.md`

__Hints__:
- Execute the following command to run your `main()` function:
  ```
  $ mvn exec:java -Dexec.mainClass="org.ntutssl.termfrequency.Main" -Dexec.args="input/stop_words.txt input/{textFile} output/{outFile} {range} {order}"
  ```
  `textFile` is the file path of text to be counted.

  `outFile` is the file path of output.
  
  `range` should be any positive integer less than the total number of distinct
  words. (e.g. 1, 2, 3, ...) 

  `order` should be `asc` or `des`.

__Grading Standard__:
1. Unit tests written by yourself: 35%.
1. Unit tests written by TA: 35%.
1. The executable program: 30%.

## References
[Oracle Java Document](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)

[Dependency Injection](https://www.freecodecamp.org/news/a-quick-intro-to-dependency-injection-what-it-is-and-when-to-use-it-7578c84fa88f/)

[Java Stream](https://zetcode.com/lang/java/streams/)

[Exception Handling in Java](https://stackabuse.com/exception-handling-in-java-a-complete-guide-with-best-and-worst-practices/)

## Environment Setting
Tutorial: https://ssl-gitlab.csie.ntut.edu.tw/course/environment_setting_java