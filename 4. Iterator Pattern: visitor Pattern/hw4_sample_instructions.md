# This is an example about how the program work. (hw4)

## 1. Run program(Main.java):

```
$ mvn exec:java -Dexec.mainClass="org.ntutssl.document.Main"
...
Please enter the following instruction to start editing:
  1. 'add title': to add a title to the editor
  2. 'add paragraph': to add a paragraph to the editor
  3. 'add article': to add an article to the editor
  4. 'find content': to find the specific string in the editor
  5. 'output html': to transfer the text to html format
  6. 'exit': to exit the program
```

## 2. Enter instruction: `add title` (Editor)
```
Please enter the information of title:
Text of title: Design Patterns
Size of title: 1
Title added to the editor.
Please enter the following instruction to start editing: 
  1. 'add title': to add a title to the editor
  2. 'add paragraph': to add a paragraph to the editor   
  3. 'add article': to add an article to the editor      
  4. 'find content': to find the specific string in the editor
  5. 'output html': to transfer the text to html format       
  6. 'exit': to exit the program
```

## 3. Enter instruction: `add paragraph` (Editor)
```
Please enter the information of paragraph:
Text of paragraph: This course discusses issues in principles of object-oriented design through design patterns.
Paragraph added to the editor.
Please enter the following instruction to start editing: 
  1. 'add title': to add a title to the editor
  2. 'add paragraph': to add a paragraph to the editor
  3. 'add article': to add an article to the editor
  4. 'find content': to find the specific string in the editor
  5. 'output html': to transfer the text to html format
  6. 'exit': to exit the program
```

## 4. Enter instruction: `add article` (Editor)
```
Please enter the information of article:
Topic of article: Course Design Pattern
Level of article: 1
Please enter the following instruction to edit the article: 
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

### 4-1. Enter instruction: `add title` (Article1)
```
Please enter the information of title:
Text of title: Information
Size of title: 2
Title added to the article.
Please enter the following instruction to edit the article:
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

### 4-2. Enter instruction: `add paragraph` (Article1)
```
Please enter the information of paragraph:
Text of paragraph: Professor: YC Cheng
Paragraph added to the article.
Please enter the following instruction to edit the article:
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

### 4-3. Enter instruction: `add article` (Article1)
```
Please enter the information of article:
Topic of article: Introduction
Level of article: 2
Please enter the following instruction to edit the article: 
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

#### 4-3-1. Enter instruction: `add title` (Article2)
```
Please enter the information of title:
Text of title: What is design pattern?
Size of title: 3
Title added to the article.
Please enter the following instruction to edit the article: 
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

#### 4-3-2. Enter instruction: `add paragraph` (Article2)
```
Please enter the information of paragraph:
Text of paragraph: Design pattern are solutions to general problems that appear repeatedly in software engineering.
Paragraph added to the article.
Please enter the following instruction to edit the article:
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

#### 4-3-3. Enter instruction: `exit` (Article2)
```
Article added to the article.
Please enter the following instruction to edit the article:
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```
### 4-4. Enter instruction: `add title` (Article1)
```
Please enter the information of title:
Text of title: References
Size of title: 2
Title added to the article.
Please enter the following instruction to edit the article:
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

### 4-5. Enter instruction: `add paragraph` (Article1)
```
Please enter the information of paragraph:
Text of paragraph: Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.
Paragraph added to the article.
Please enter the following instruction to edit the article:
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

### 4-6. Enter instruction: `exit` (Article1)
```
Article added to the editor.
Please enter the following instruction to start editing:
  1. 'add title': to add a title to the editor
  2. 'add paragraph': to add a paragraph to the editor
  3. 'add article': to add an article to the editor
  4. 'find content': to find the specific string in the editor
  5. 'output html': to transfer the text to html format
  6. 'exit': to exit the program
```

## 5. Enter instruction: `find content`
```
Enter the word you want to find: design pattern
Title           text: Design Patterns
                size: 1
Paragraph       text: This course discusses issues in principles of object-oriented design through design patterns.
Article         topic: Course Design Pattern
                level: 1
Title           text: What is design pattern?
                size: 3
Paragraph       text: Design pattern are solutions to general problems that appear repeatedly in software engineering.
Paragraph       text: Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.

Please enter the following instruction to start editing:
  1. 'add title': to add a title to the editor
  2. 'add paragraph': to add a paragraph to the editor
  3. 'add article': to add an article to the editor
  4. 'find content': to find the specific string in the editor
  5. 'output html': to transfer the text to html format
  6. 'exit': to exit the program
```

## 6. Enter instruction: `output html`
```
<h1>Design Patterns</h1>
<p>This course discusses issues in principles of object-oriented design through design patterns.</p>
<article topic='Course Design Pattern'>
  <h2>Information</h2>
  <p>Professor: YC Cheng</p>
  <article topic='Introduction'>
    <h3>What is design pattern?</h3>
    <p>Design pattern are solutions to general problems that appear repeatedly in software engineering.</p>
  </article>
  <h2>References</h2>
  <p>Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.</p>
</article>

Please enter the following instruction to start editing:
  1. 'add title': to add a title to the editor
  2. 'add paragraph': to add a paragraph to the editor
  3. 'add article': to add an article to the editor
  4. 'find content': to find the specific string in the editor
  5. 'output html': to transfer the text to html format
  6. 'exit': to exit the program
```

## 7. Enter instruction: `exit` to terminate the program

## 8. Exceptions
### 8-1. Enter instruction: `not an instruction` (Editor)
```
Invalid Instruction
Please enter the following instruction to start editing:
  1. 'add title': to add a title to the editor
  2. 'add paragraph': to add a paragraph to the editor
  3. 'add article': to add an article to the editor
  4. 'find content': to find the specific string in the editor
  5. 'output html': to transfer the text to html format
  6. 'exit': to exit the program
```

### 8-2. Enter instruction: `not an instruction` (Article)
```
Invalid Instruction
Please enter the following instruction to edit the article:
  1. 'add title': to add a title to the article
  2. 'add paragraph': to add a paragraph to the article
  3. 'add article': to add an article to the article
  4. 'exit': to exit the process
```

### 8-3. Enter invalid size value of title
```
Please enter the information of title:
Text of title: title
Size of title: 0
Invalid Input: The size should be in range 1 to 6
```

### 8-4. Enter invalid level value of article
```
Please enter the information of article:
Topic of article: topic
Level of article: -1
Invalid Input: The level should be positive or higher than the level of the current article
```