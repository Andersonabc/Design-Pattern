# Design Patterns 2021 Spring Assignment

## Assignment 6

__Deadline__: 5/18 23:59.

Here's the
[template code](https://ssl-gitlab.csie.ntut.edu.tw/course/dp2021s_hw_template)
of this assignment, please clone this repo and __DELETE THE FOLDER `.git` AND
FOLLOW THIS TEMPLATE TO COMPLETE YOUR HW__. This template is just the skeleton.
You should finish implementation by yourself.

For this assignment, you'll parse the documents from JSON file and build the
`Document` objects by using __builder pattern__ discussed in class.

Please complete the given code to satisfy all the specs of the Document problem 
and the following conditions.

- The specs of `Document` objects, `Main`, `DocumentException` is same as 
assignment 4.
---
- class `FindContentConsumer` and `HtmlOutputConsumer`, both of which implement 
  `Consumer`.
- class `FindContentConsumer`, which should find the Document objects whose text
  contains the target string, the search should be case-insensitive.
- class `HtmlOutputConsumer`, which should output the Document objects to html 
  format.
- class `DocumentBuilder`, which helps to build the whole composite structure.
- class `DocumentParser`, which can parse `JsonObject` and use `DocumentBuilder`
  to get an `Document` object.
---
- class `Editor`, which helps you to import a number of `Document` objects from
  json file, find specific string, and export to html file.
- class `InstructionHandler`, which should show the instructions to user,
  execute the instruction which is chosen by user, and check if the input 
  instructions or the input values are valid. 
- please follow the file structure below:
  ```bash
  .
  ├── input
  │   ├── sample_input.json
  ├── output
  │   └── sample_output.html
  ├── pom.xml
  └── src
      ├── main
      │   └── java
      │       └── org
      │           └── ntutssl
      │               └── document
      │                   ├── Article.java
      │                   ├── Document.java
      │                   ├── DocumentBuilder.java
      │                   ├── DocumentException.java
      │                   ├── DocumentParser.java
      │                   ├── Editor.java
      │                   ├── FindContentConsumer.java
      │                   ├── HtmlOutputConsumer.java
      │                   ├── InstructionHandler.java
      │                   ├── Main.java
      │                   ├── NullIterator.java
      │                   ├── Paragraph.java
      │                   ├── Title.java
      │                   └── Visitor.java
      └── test
          └── java
              └── org
                  └── ntutssl
                      └── document
                          ├── ArticleTest.java
                          ├── DocumentBuilderTest.java
                          ├── DocumentParserTest.java
                          ├── EditorTest.java
                          ├── FindContentConsumerTest.java
                          ├── HtmlOutputConsumerTest.java
                          ├── NullIteratorTest.java
                          ├── ParagraphTest.java
                          └── TitleTest.java
  ```

__Grading Rubrics__:
1. Unit tests written by yourself: 35%.
2. Unit tests written by TA: 35%.
3. The executable program: 30%.

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
- You can assume that there is no exception when importing a file.
- You should use module `com.google.gson` to parse json file.
- You should follow [sample instructions](./hw6_sample_instructions.md) to complete
  the context.

__Hints__:
- Execute the following command to run your `main()` function:
```
$ mvn exec:java -Dexec.mainClass="org.ntutssl.document.Main"
```
- `private functions` are not necessary, but you can take them for reference.
- It is recommended to use `\t` in `Document::toString()`.
- You can use `ByteArrayOutputStream` and `PrintStream` to test string in stdout.

## References
[Oracle Java Document](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)

[Module com.google.gson](https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/module-summary.html)

## Environment Setting
Tutorial: https://ssl-gitlab.csie.ntut.edu.tw/course/environment_setting_java