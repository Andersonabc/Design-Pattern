package org.ntutssl.shop;
import java.util.Scanner;

public class InstructionHandler {
  private EventManager eventManager;
  private Scanner scanner;
  public InstructionHandler() { 
    this.eventManager = EventManager.getInstance();
    this.scanner = new Scanner(System.in);
    new ShoppingCart();
		new GoodsParser();
		new Shop();
  }

  public void run() {
    this.eventManager.publish(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
    String input;
    printInstructions();
    input = this.scanner.nextLine();
    while (!input.equals("5")){
			handleInstruction(input);
			printInstructions();
			input = this.scanner.nextLine();
		}
		this.scanner.close();
  }

  private void printInstructions() {
    System.out.println("Please enter the following instruction(number) to start shopping:");
		System.out.println("  1. to list all the goods that the shop has");
		System.out.println("  2. to import your shopping list into the shopping cart");
		System.out.println("  3. to check the content of your shopping cart");	
		System.out.println("  4. to go to pay");
		System.out.println("  5. to exit the program");
   }

  private void handleInstruction(String instruction) { 
    if(instruction.equals("1")){ 
      instructionListShop();
    }else if(instruction.equals("2")){
      instructionImportShoppingList();
    }else if(instruction.equals("3")){ 
      instructionListShoppingCart();
    }else if(instruction.equals("4")){
      instructionPay();
    }else{
      System.out.println("invalid instruction");
    }

  }

  private void instructionListShop() { 
		eventManager.publish(new StringEvent(EventType.LIST_SHOP, ""));
  }

  private void instructionImportShoppingList() { 
    System.out.print("Please input the file path: ");
    String path = this.scanner.nextLine();
    eventManager.publish(new StringEvent(EventType.IMPORT_SHOPPING_LIST, path));
    System.out.println("import successfully");
  }

  private void instructionListShoppingCart() { 
    eventManager.publish(new StringEvent(EventType.LIST_CART, ""));
  }

  private void instructionPay() { 
    System.out.println("Please, select a payment method by number:");
    System.out.println("  1. Bank Transfer");
    System.out.println("  2. Credit Card");
    String input = this.scanner.nextLine();
    if(input.equals("1")){
      PayByBankTransferStrategy.getInstance();
      this.eventManager.publish(new StringEvent(EventType.PAY, ""));
    }else if(input.equals("2")){
      PayByCreditCardStrategy.getInstance();
      this.eventManager.publish(new StringEvent(EventType.PAY, ""));
    }else{
      System.out.println("invalid option");
    }

  }
}
