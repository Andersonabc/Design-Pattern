package org.ntutssl.termfrequency;

public class Main {

    public static void main(String[] args){
        //Get the input arguments
        String dataPath = args[0];
        int range = 0 ;
        try {
            range = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new WordFrequencyException("Please Input range as a number", e);
        }
        String sortingOrder = args[2];
        if(!sortingOrder.equals("asc") && !sortingOrder.equals("des")){
            throw new WordFrequencyException("Invalid sorting order");
        }
        //Initialize listener interfaces
        EventManager em = new EventManager();
        EventListener wfc = new WordFrequencyController(em);
        EventListener swm = new StopWordManager(em);
        EventListener wfm = new WordFrequencyManager(em);
        EventListener dsm = new DataStorageManager(em);
        EventListener output = new Output(em, range, sortingOrder);
        //Start the code
        em.publish(EventType.START, dataPath);

        
     }
}
// mvn exec:java -Dexec.mainClass="org.ntutssl.termfrequency.Main" -Dexec.args="input/pride-and-prejudice.txt 3 desc"