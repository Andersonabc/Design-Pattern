# Design Patterns 2021 Spring Assignment

## Assignment 5

__Deadline__: 5/8 23:59.

Here's the
[template code](https://ssl-gitlab.csie.ntut.edu.tw/course/dp2021s_hw_template)
of this assignment, please clone this repo and __DELETE THE FOLDER `.git` AND
FOLLOW THIS TEMPLATE TO COMPLETE YOUR HW__. This template is just the skeleton.
You should finish implementation by yourself.

For this assignment, you'll solve the term frequency counter problem using event
driven architecture.

Please complete the given code to satisfy all the specs of the Term Frequency
Counter and the following conditions.
- enum `EventType`, which defines the type of events happened in the system.
  - START: to start the process
  - LOAD: to load the necessary file
  - RUN: to run the term frequency counter
  - VALIDATE: to check if the word is not a stop word
  - COUNT: to count the word frequency
  - EOF: reached the end of file
  - OUTPUT: to output the result
- class `EventManager`, which manages any subscription and publication of event,
  that is, it can let an `EventListener` subscribe an `EventType`, or publish a
  specific type of event with data to its subscribers.
- interface `EventListener`, which contains exactly one function `onEvent` that
  defines different actions according to different published `EventType`.
- class `StopWordManager`, `DataStorageManager`, `WordFrequencyManager`, 
  `WordFrequencyController` and `Output`, all of which implement `EventListener`.
- class `Output`, which should **print the result to stdout** according to given
  range and order.
- class `Main`, which should have exactly one function `main()` and start the 
  process. You should initialize `EventManager`, `StopWordManager`,
  `DataStorageManager`, `WordFrequencyManager`, `WordFrequencyController` and
  `Output`, and use dependency injection technique in `main()`.
- class `WordFrequencyException`, which should extend `RuntimeException`. You 
  should use it to handle exception.
- for the arguments of `main(String args[])`,
  - args[0] is the file path of text to be counted.
  - args[1] is the range.
  - args[2] is the sorting order.
- please do exception handling and write test cases for invalid command line 
  argument.
- please follow the rules below:

  | class                   | subscribe      | publish       |
  |-------------------------|----------------|---------------|
  | DataStorageManager      | LOAD, RUN      | VALIDATE, EOF |
  | StopWordManager         | LOAD, VALIDATE | COUNT         |
  | WordFrequencyManager    | COUNT, EOF     | OUTPUT        |
  | WordFrequencyController | START          | LOAD, RUN     |
  | Output                  | OUTPUT         | none          |
  | Main                    | none           | START         |

- please follow the file structure below:
  ```bash
    .
    ├── input
    │   ├── pride-and-prejudice.txt
    │   └── stop_words.txt
    ├── output
    │   └── sample_output.md
    ├── pom.xml
    └── src
        ├── main
        │   └── java
        │       └── org
        │           └── ntutssl
        │               └── termfrequency
        │                   ├── DataStorageManager.java
        │                   ├── EventListener.java
        │                   ├── EventManager.java
        │                   ├── EventType.java
        │                   ├── Main.java
        │                   ├── Output.java
        │                   ├── StopWordManager.java
        │                   ├── WordFrequencyController.java
        │                   ├── WordFrequencyException.java
        │                   └── WordFrequencyManager.java
        └── test
            └── java
                └── org
                    └── ntutssl
                        └── termfrequency
                            ├── DataStorageManagerTest.java
                            ├── EventManagerTest.java
                            ├── StopWordManagerTest.java
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
- You don't have to modify `EventType` and `EventListener` provided in code 
  template.
- You should check `range` is a number and `order` is valid in `Main`, and 
  `range` is less than the total number of distinct words in `Output`.
- The file of stop words should be hardcoded in `StopWordManager`
- The example of data which should be published with event OUTPUT:
  p.s. The order doesn't matter.
  ```
  pattern: 1
  design: 2
  ```

__Hints__:
- Execute the following command to run your `main()` function:
  ```
  $ mvn exec:java -Dexec.mainClass="org.ntutssl.termfrequency.Main" -Dexec.args="input/{textFile} {range} {order}"
  ```
  `textFile` is the file path of text to be counted.
  
  `range` should be any positive integer less than the total number of distinct
  words. (e.g. 1, 2, 3, ...) 

  `order` should be `asc` or `des`.
- You can refer to the following code snippet to test
  ```
  class TestListener implements EventListener {
    public boolean isTriggered = false;
    public void onEvent(EventType eventType, String event) {
      this.isTriggered = eventType == EventType.{EVENT_TYPE};
    }
  }
  ``` 

__Grading Standard__:
1. Unit tests written by yourself: 35%.
1. Unit tests written by TA: 35%.
1. The executable program: 30%.

## References
[Oracle Java Document](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)

## Environment Setting
Tutorial: https://ssl-gitlab.csie.ntut.edu.tw/course/environment_setting_java