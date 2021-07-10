package org.ntutssl.shop;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.DecimalFormat;

public class PayByCreditCardStrategy implements PayStrategy, EventListener {
  private Scanner scanner;
  private String cardNumber;
  private String date;
  private String CVV;
  private EventManager eventManager;

  private static volatile PayByCreditCardStrategy instance = null;

  public static PayByCreditCardStrategy getInstance() {
    // lazy initialization
    // double check locking
    if (instance == null) {
      synchronized(PayByCreditCardStrategy.class) {
        if (instance == null) {
          instance = new PayByCreditCardStrategy();
        }
      }
    }
    return instance;
  }

  public PayByCreditCardStrategy() {
    this.eventManager = EventManager.getInstance();
    this.eventManager.subscribe(EventType.CALCULATE, this);
   }

  public void onEvent(Event event) { 
    if(event.type().equals(EventType.CALCULATE)){
      getInput();
      if(validate()){
        this.calculate(Double.parseDouble((String)event.data()));
      }else{
        System.out.println("Pay failed.");
      }
    }
  }

  /**
   * Card number are 16 digits.
   * The format of card expiration date is 'mm/yy', such as '06/21'.
   * CVV code are 3 digits.
   */
  @Override
  public void calculate(double totalPrice) { 
    DecimalFormat format = new DecimalFormat("###.00");
    String finalPrice = "$" + format.format((totalPrice * 0.9));
    this.eventManager.publish(new StringEvent(EventType.PRINT_RECEIPT, finalPrice));
    System.out.println("Pay successfully!");
  }

  private void getInput(){
    this.scanner = new Scanner(System.in);
    System.out.print("Enter the card number: ");
    this.cardNumber = this.scanner.nextLine();
    System.out.print("Enter the card expiration date 'mm/yy': ");
    this.date = this.scanner.nextLine();
    System.out.print("Enter the CVV code: ");
    this.CVV = this.scanner.nextLine();
  }

  private boolean validate(){
    return this.cardNumber.matches("[0-9]{16}") && (this.date.matches("[0-9]{2}/[0-9]{2}") && Integer.parseInt(this.date.substring(0, 2)) <= 12 && Integer.parseInt(this.date.substring(0, 2)) > 0) && this.CVV.matches("[0-9]{3}");
  }
}
