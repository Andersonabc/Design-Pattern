# Design Patterns 2021 Spring Assignment

## Assignment 3

__Deadline__: 3/23 23:59.

Here's the
[template code](https://ssl-gitlab.csie.ntut.edu.tw/course/dp2021s_hw_template)
of this assignment, please clone this repo and __DELETE THE FOLDER `.git` AND
FOLLOW THIS TEMPLATE TO COMPLETE YOUR HW__. This template is just the skeleton.
You should finish implementation by yourself.

For this assignment, you'll implement Document objects by using 
__composite pattern__ discussed in class.

Please complete the given code to satisfy all the specs of the Document problem 
and the following conditions.
- interface `Document`, which should have a text, and define the default
  behavior for `add`, `get level` and `get content`.
- class `Title`, `Paragraph` and `Article`, where all of them are `Document`. 
- class `Article`, which can contain [0, n] `Document`, and has an attribute 
  `level` representing different hierarchy. You should implement behavior
  `get content` from `Document`.
- class `Title` and `Paragraph` can not add any `Document`, and should not have
  attribute `level`. Of course, you can not get content from them.
- class `DocumentException`, which should extends `RuntimeException`. You should 
  use it to handle exception.
- please follow the file structure below (note that the package path become
  `org.ntutssl.document`):
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
      │                   ├── Paragraph.java
      │                   └── Title.java
      └── test
          └── java
              └── org
                  └── ntutssl
                      └── document
                          ├── ArticleTest.java
                          ├── ParagraphTest.java
                          └── TitleTest.java
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

## References
[Oracle Java Document](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)

## Environment Setting
Tutorial: https://ssl-gitlab.csie.ntut.edu.tw/course/environment_setting_java