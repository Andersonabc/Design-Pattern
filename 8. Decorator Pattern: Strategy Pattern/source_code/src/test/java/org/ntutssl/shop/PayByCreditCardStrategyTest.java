package org.ntutssl.shop;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class PayByCreditCardStrategyTest implements EventListener{ 
    private String result = "";
    
    public void onEvent(Event event) {
        if(event.type() == EventType.PRINT_RECEIPT)
            this.result = (String)event.data();
    }
    @Test
    public void pay_cardNumber_failed_test(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(output);
        PrintStream originalOutput = System.out;
        ByteArrayInputStream input = new ByteArrayInputStream("1234\n01/01\n123\n".getBytes());
        InputStream originalInput = System.in;
        System.setOut(printOutput);
        System.setIn(input);
        new PayByCreditCardStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"99.00"));
        assertEquals("Enter the card number: Enter the card expiration date 'mm/yy': Enter the CVV code: Pay failed.\n",output.toString());
        input.reset();
        output.reset();
        System.setIn(originalInput);
        System.setOut(originalOutput);
        EventManager.getInstance().reset();
    }
    @Test
    public void pay_CVV_failed_test(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(output);
        PrintStream originalOutput = System.out;
        ByteArrayInputStream input = new ByteArrayInputStream("1234567887654321\n01/01\n12\n".getBytes());
        InputStream originalInput = System.in;
        System.setOut(printOutput);
        System.setIn(input);
        new PayByCreditCardStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"99.00"));
        assertEquals("Enter the card number: Enter the card expiration date 'mm/yy': Enter the CVV code: Pay failed.\n",output.toString());
        input.reset();
        output.reset();
        System.setIn(originalInput);
        System.setOut(originalOutput);
        EventManager.getInstance().reset();
    }
    @Test
    public void pay_date_failed_test(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(output);
        PrintStream originalOutput = System.out;
        ByteArrayInputStream input = new ByteArrayInputStream("1234567887654321\n13/21\n123\n".getBytes());
        InputStream originalInput = System.in;
        System.setOut(printOutput);
        System.setIn(input);
        new PayByCreditCardStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"99.00"));
        assertEquals("Enter the card number: Enter the card expiration date 'mm/yy': Enter the CVV code: Pay failed.\n",output.toString());
        input.reset();
        output.reset();
        System.setIn(originalInput);
        System.setOut(originalOutput);
        EventManager.getInstance().reset();
    }

    @Test
    public void pay_success_test(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(output);
        PrintStream originalOutput = System.out;
        ByteArrayInputStream input = new ByteArrayInputStream("1234567887654321\n01/01\n123\n".getBytes());
        InputStream originalInput = System.in;
        System.setOut(printOutput);
        System.setIn(input);
        new PayByCreditCardStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"10.00"));
        assertEquals("Enter the card number: Enter the card expiration date 'mm/yy': Enter the CVV code: Pay successfully!\n",output.toString());
        input.reset();
        output.reset();
        System.setIn(originalInput);
        System.setOut(originalOutput);
        EventManager.getInstance().reset();
    }

    @Test
    public void total_price_test(){
        ByteArrayInputStream input = new ByteArrayInputStream("1234567887654321\n01/01\n123\n".getBytes());
        InputStream originalInput = System.in;
        System.setIn(input);
        new PayByCreditCardStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"10.00"));
        assertEquals("$9.00", this.result);
        input.reset();
        System.setIn(originalInput);
        EventManager.getInstance().reset();

    }
}
