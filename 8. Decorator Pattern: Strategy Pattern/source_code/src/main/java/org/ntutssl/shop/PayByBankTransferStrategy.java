package org.ntutssl.shop;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.DecimalFormat;

public class PayByBankTransferStrategy implements PayStrategy, EventListener {
  private Scanner scanner;
  private String bankCode;
  private String accountNum;
  private EventManager eventManager;

  private static volatile PayByBankTransferStrategy instance = null;

  public static PayByBankTransferStrategy getInstance() {
    // lazy initialization
    // double check locking
    if (instance == null) {
      synchronized(PayByBankTransferStrategy.class) {
        if (instance == null) {
          instance = new PayByBankTransferStrategy();
        }
      }
    }
    return instance;
  }

  public PayByBankTransferStrategy() {
    this.eventManager = EventManager.getInstance();
    this.eventManager.subscribe(EventType.CALCULATE, this);
   }

  public void onEvent(Event event) {
    if(event.type().equals(EventType.CALCULATE)){
      getInput();
      if(isValidated()){
        this.calculate(Double.parseDouble((String)event.data()));
      }else{
        System.out.println("Pay failed.");
      }
    }
   }

  /**
   * Bank code are 3 digits.
   * Account number are 12 digits.
   */
  @Override
  public void calculate(double totalPrice) { 
    DecimalFormat format = new DecimalFormat("###.00");
    String finalPrice = "$" + format.format(totalPrice + 0.49);
    this.eventManager.publish(new StringEvent(EventType.PRINT_RECEIPT, finalPrice));
    System.out.println("Pay successfully!");
  }

  private void getInput(){
    this.scanner = new Scanner(System.in);
    System.out.print("Enter the bank code: ");
    this.bankCode = this.scanner.nextLine();
    System.out.print("Enter the account number: ");
    this.accountNum = this.scanner.nextLine();
  }

  private boolean isValidated(){
    Pattern bankCodePattern = Pattern.compile("[0-9]{3}");
    Pattern accountNumPattern = Pattern.compile("[0-9]{12}");
    return bankCodePattern.matcher(this.bankCode).matches() && accountNumPattern.matcher(this.accountNum).matches();
  }
}
