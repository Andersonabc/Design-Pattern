# Design Patterns 2021 Spring Assignment

## Assignment 4

__Deadline__: 4/13 23:59.

Here's the
[template code](https://ssl-gitlab.csie.ntut.edu.tw/course/dp2021s_hw_template)
of this assignment, please clone this repo and __DELETE THE FOLDER `.git` AND
FOLLOW THIS TEMPLATE TO COMPLETE YOUR HW__. This template is just the skeleton.
You should finish implementation by yourself.

For this assignment, you'll access Document objects by using 
__iterator pattern__ and __visitor pattern__ discussed in class.

Please complete the given code to satisfy all the specs of the Document problem 
and the following conditions.
- interface `Document`, which should have method `getText()`, `accept()`,
  `toString()` to be overrode, and define the default behavior for
  `add other Document`, `get size`, `get level` and `be accessed by iterator`.
- class `Title`, `Paragraph` and `Article`, where all of them are `Document`. 
- class `Title`, which has an attribute `size` representing the font size.
- class `Article`, which can contain [0, n] `Document`, and has an attribute 
  `level` representing different hierarchy. It can also 
  `be accessed by iterator`, and can't be added to the lower or the same level 
  article.
---
- interface `Visitor`, which is a generic type, and should do different tasks 
  when assigned to different Document objects.
- class `FindContentVisitor` and `HtmlOutputVisitor`, which implement `Visitor`
  with different type parameter.
- class `FindContentVisitor`, which should find the Document objects whose text
  contains the target string, the search should be case-insensitive.
- class `HtmlOutputVisitor`, which should output the Document objects to html 
  format.
---
- class `Editor`, which helps you to generate a text file.
- class `InstructionHandler`, which should show the instructions to user,
  execute the instruction which is chosen by user, and check if the input 
  instructions or the input values are valid. 
- class `Main`, which should have exactly one function `main()`. You should 
  initialize `Editor` and `InstructionHandler`, and use dependency injection 
  technique.
- class `DocumentException`, which extends `RuntimeException`. You should 
  use it to handle exception.
- please follow the file structure below:
  ```bash
  .
  ├── pom.xml
  └── src
      ├── main
      │   └── java
      │       └── org
      │           └── ntutssl
      │               └── document
      │                   ├── Article.java
      │                   ├── Document.java
      │                   ├── DocumentException.java
      │                   ├── Editor.java
      │                   ├── FindContentVisitor.java
      │                   ├── HtmlOutputVisitor.java
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
                          ├── EditorTest.java
                          ├── FindContentVisitorTest.java
                          ├── HtmlOutputVisitorTest.java
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
- Please retain the test cases of hw3, and make some modifications if needed.
- Please follow [sample instructions](./hw4_sample_instructions.md) to complete the 
  context.

__Hints__:
- Execute the following command to run your `main()` function:
```
$ mvn exec:java -Dexec.mainClass="org.ntutssl.document.Main"
```
- `private functions` are not necessary, but you can take them for reference.
- You don't need to modify the interface `Visitor` provided in code template.
- It is recommended to use `\t` in `Document::toString()`.

## References
[Oracle Java Document](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)

[Generic Types](https://docs.oracle.com/javase/tutorial/java/generics/types.html)

[Visitor Pattern in Java](https://www.baeldung.com/java-visitor-pattern)

## Environment Setting
Tutorial: https://ssl-gitlab.csie.ntut.edu.tw/course/environment_setting_java