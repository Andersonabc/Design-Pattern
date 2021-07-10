# This is an example about how the program work.

## 1. Run program(Main.java):

```
$ mvn exec:java -Dexec.mainClass="org.ntutssl.shop.Main"
...
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
>
```

## 2. Enter instruction: `1`
```
================================================================================
ID  name                  description                             price   count 
--------------------------------------------------------------------------------
1   apple                 good apple                              0.49    100   
2   banana bag            a bag of banana                         0.29    30
3   cat                   cute cat                                0.49    50
4   dog                   a bag of Chihuahuas                     0.0     123   
5   egg                   cheap!                                  0.05    500
6   fruit                 cdesc                                   6.48    300   
7   pencil                cheap pencil                            0.29    1000
8   eraser                cheap eraser                            0.25    400
9   pen                   cheat pen                               0.49    400
10  stationery pack       stationery pack                         1.03    50
11  MSI laptop            superb RGB light up your world          700.0   30
12  MacBook Pro           Small chip. Giant leap.                 1299.0  10
13  laptop pack           laptop pack                             799.98  5
================================================================================
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
>
```

## 3. Enter instruction: `2`
```
Please input the file path: input/shopping_list.json
import successfully
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
>
```

## 4. Enter instruction: `3`
```
================================================================================
ID  name                  description                             price   count
--------------------------------------------------------------------------------
1   apple                 good apple                              0.49    3
2   banana bag            a bag of banana                         0.29    1
3   cat                   cute cat                                0.49    2     
4   dog                   a bag of Chihuahuas                     0.0     2
5   egg                   cheap!                                  0.05    4     
6   fruit                 cdesc                                   6.48    1
================================================================================
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
>
```

## 5. Enter instruction: `4`
```
Please, select a payment method by number:
  1. Bank Transfer
  2. Credit Card
>
```

### 5-1. Enter instruction: `1`
```
Enter the bank code: 123
Enter the account number: 123456123456
================================================================================
Receipt:
name                                    price     count     
apple                                   $0.49     3
banana bag                              $0.29     1
cat                                     $0.49     2
dog                                     $0.0      2
egg                                     $0.05     4
fruit                                   $6.48     1
--------------------------------------------------------------------------------
Total Price: $9.91
================================================================================
Pay successfully!
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
>
```

### 5-2. Enter instruction: `2`
```
Enter the card number: 1234567812345678
Enter the card expiration date 'mm/yy': 10/22
Enter the CVV code: 123
================================================================================
Receipt:
name                                    price     count
apple                                   $0.49     3         
banana bag                              $0.29     1
cat                                     $0.49     2
dog                                     $0.0      2
egg                                     $0.05     4
fruit                                   $6.48     1
--------------------------------------------------------------------------------
Total Price: $8.48
================================================================================
Pay successfully!
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
> 
```

## 6. Enter instruction: `5` to terminate the program

## 7. Exceptions
### 7-1 Enter invalid instruction
```
invalid instruction
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
>
```

### 7-2 Enter invalid payment option
```
invalid option
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
>
```

### 7.3 Enter invalid information for payment
```
Pay failed.
Please enter the following instruction(number) to start shopping:
  1. to list all the goods that the shop has
  2. to import your shopping list into the shopping cart
  3. to check the content of your shopping cart
  4. to go to pay
  5. to exit the program
>
```