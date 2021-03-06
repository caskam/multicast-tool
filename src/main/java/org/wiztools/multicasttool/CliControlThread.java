package org.wiztools.multicasttool;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

/**
 * The thread which stops the execution of the Shutdownable instance.
 * @author subwiz
 */
public class CliControlThread implements Runnable {
    
    private static final List<String> quitCommands = Arrays.asList(
            new String[]{"quit", "bye", "exit", "close", "terminate", "shutdown",
                "hasta la vista", "vanakkam", "danyavada", "danyavaad"});

    private Shutdownable shutdownable;
    
    public CliControlThread(Shutdownable shutdownable) {
        this.shutdownable = shutdownable;
    }

    @Override
    public void run() {
        while(true) {
            final Console console = System.console();
            
            // Do not ask for input when console is not available:
            if(console == null)  {
                break;
            }
            final String command = console.readLine();

            // `command' is null when EOF is received
            if(command == null || quitCommands.contains(command)) {
                shutdownable.shutdown();
                break;
            }
        }
    }
    
}
