# Design Patterns 2021 Spring Assignment

## Assignment 1

__Deadline__: 3/8 23:59.

Here's the
[template code](https://ssl-gitlab.csie.ntut.edu.tw/course/dp2021s_hw_template)
of this assignment, please clone this repo and __DELETE THE FOLDER `.git` AND
FOLLOW THIS TEMPLATE TO COMPLETE YOUR HW__. This template is just the skeleton.
You should finish implementation by yourself.

For this assignment, you'll solve the term frequency counter problem discussed
in class.

Please complete the given code to satisfy all the specs of the Term Frequency
Counter and the following conditions.
- class `StopWordManager`, `DataStorageManager`, `WordFrequencyManager`.
- class `StopWordManager` should be able to store stop words by two different
  data structures, where they are `List` and `Set`, and you should compare the
  execution time for both in your unit test. __Please don't just print it out.__
- for class `StopWordManger`, you should consider every single character to be a
  stop word, even if they don't appear in the input file of stop word.
- for class `DataStorageManager`, you should use regular expression to filter
  word.
- class `WordFrequencyController`, which contains only one function `main()`, 
  should print out the result after reading the input file and counting the term 
  frequency.
- for the arguments of `main(String args[])`,
  - args[0] is the file path of stop word.
  - args[1] is the file path of text to be counted.
  - args[2] is the range.
  - args[3] is the sorting order.
- please follow the file structure below:
  ```bash
  .
  ├── input
  │   ├── pride-and-prejudice.txt
  │   └── stop_words.txt
  ├── pom.xml
  └── src
      ├── main
      │   └── java
      │       └── org
      │           └── ntutssl
      │               └── termfrequency
      │                   ├── StopWordManager.java
      │                   ├── DataStorageManager.java
      │                   ├── WordFrequencyController.java
      │                   └── WordFrequencyManager.java
      └── test
          └── java
              └── org
                  └── ntutssl
                      └── termfrequency
                          ├── StopWordManagerTest.java
                          ├── DataStorageManagerTest.java
                          └── WordFrequencyManagerTest.java
  ```

__Notes__:
- Please execute `mvn compile` or `mvn test` after modifications in your code,
  otherwise, your latest code won't be executed.
- If your code fails to compile on jenkins server, you'll get __no point__ for
  the assignment.
- Your program should be able to handle unexpected input data, that is, you
  should do error handling if necessary.
- When writing unit tests, you should take as many situations as possible into
  consideration.
- Please use `try with resources` rather than just `try-catch-finally`.
- Please take sample_output.txt as reference to print out the result.

__Hints__:
- `Assert.assertEquals()` has multiple signatures. You should not compare
  primitive type `int` and reference type `Integer` directly, which will
  cause ambiguity.
- execute the following command to run your `main()` function:
  ```
  $ mvn exec:java -Dexec.mainClass="org.ntutssl.termfrequency.WordFrequencyController" -Dexec.args="input/stop_words.txt input/{textFile} {range} {order}"
  ```
  `textFile` is the file path of text to be counted.
  
  `range` should be any positive integer less than the total number of distinct
  words. (e.g. 1, 2, 3, ...) 

  `order` should be `asc` or `des`.

## References
- [Oracle Java Document](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)

- [Regular Expression](https://www.aivosto.com/articles/regex.html)

## Environment Setting
Tutorial: https://ssl-gitlab.csie.ntut.edu.tw/course/environment_setting_java
