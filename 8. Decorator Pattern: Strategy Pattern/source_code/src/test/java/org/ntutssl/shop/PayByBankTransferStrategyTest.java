package org.ntutssl.shop;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class PayByBankTransferStrategyTest implements EventListener{ 
    private String result = "";
    
    public void onEvent(Event event) {
        if(event.type() == EventType.PRINT_RECEIPT)
            this.result = (String)event.data();
    }
    @Test
    public void pay_bankCode_failed_test(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(output);
        PrintStream originalOutput = System.out;
        ByteArrayInputStream input = new ByteArrayInputStream("12\n123456654321\n".getBytes());
        InputStream originalInput = System.in;
        System.setOut(printOutput);
        System.setIn(input);
        new PayByBankTransferStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"99.00"));
        assertEquals("Enter the bank code: Enter the account number: Pay failed.\n",output.toString());
        input.reset();
        output.reset();
        System.setIn(originalInput);
        System.setOut(originalOutput);
        EventManager.getInstance().reset();
    }
    @Test
    public void pay_accountNumber_failed_test(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(output);
        PrintStream originalOutput = System.out;
        ByteArrayInputStream input = new ByteArrayInputStream("123\n123\n".getBytes());
        InputStream originalInput = System.in;
        System.setOut(printOutput);
        System.setIn(input);
        new PayByBankTransferStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"99.00"));
        assertEquals("Enter the bank code: Enter the account number: Pay failed.\n",output.toString());
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
        ByteArrayInputStream input = new ByteArrayInputStream("123\n123456654321\n".getBytes());
        InputStream originalInput = System.in;
        System.setOut(printOutput);
        System.setIn(input);
        new PayByBankTransferStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"10.00"));
        assertEquals("Enter the bank code: Enter the account number: Pay successfully!\n",output.toString());
        input.reset();
        output.reset();
        System.setIn(originalInput);
        System.setOut(originalOutput);
        EventManager.getInstance().reset();
    }

    @Test
    public void total_price_test(){
        ByteArrayInputStream input = new ByteArrayInputStream("123\n123456654321\n".getBytes());
        InputStream originalInput = System.in;
        System.setIn(input);
        new PayByBankTransferStrategy();
        EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
        EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE,"10.00"));
        assertEquals("$10.49", this.result);
        input.reset();
        System.setIn(originalInput);
        EventManager.getInstance().reset();

    }

}
