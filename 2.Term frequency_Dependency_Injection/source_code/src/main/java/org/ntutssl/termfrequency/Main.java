package org.ntutssl.termfrequency;

public class Main {

    public static void main(String[] args){
        IOHandler ioHandler = new IOHandler();

        String stopwordPath = args[0];
        String dataPath = args[1];
        String outputPath = args[2];
        int range = 0 ;
        try {
            range = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            throw new WordFrequencyException("Out of range! The range should be from 1 to {total distinct words}.", e);
        }
        String sortingOrder = args[4];
        WordFrequencyController wfc = new WordFrequencyController(ioHandler,new StopWordManager(stopwordPath, ioHandler), new DataStorageManager(dataPath,ioHandler), new WordFrequencyManager());
        wfc.run(sortingOrder, range, outputPath);
     }
}
// mvn exec:java -Dexec.mainClass="org.ntutssl.termfrequency.WordFrequencyController" -Dexec.args="input/stop_words.txt input/pride-and-prejudice.txt 3 desc"
// mvn exec:java -Dexec.mainClass="org.ntutssl.termfrequency.Main" -Dexec.args="input/stop_words.txt input/pride-and-prejudice.txt output/output.txt 3 desc"

