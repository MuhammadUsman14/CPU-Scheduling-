import java.io.*;
import java.io.BufferedReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

class Process {
    private int productid,arrivalTime,burstTime,completionTime,turnaroundTime,waitTime,responseTime,burstTimeRemaining;
    private boolean isComplete,inQueue;
    static int idleTime = 0;
    static int clock;
    public Process() {
        productid = 0;
        arrivalTime = 0;
        burstTime = 0;
        burstTimeRemaining = 0;
        completionTime = 0;
        turnaroundTime = 0;
        waitTime = 0;
        isComplete = false;
        inQueue = false;
        responseTime = -1;
    }
    public int getResponseTime() {return responseTime;}
    public void setResponseTime(int responseTime) {this.responseTime = responseTime;}
    public int getPid() {return productid;}
    public void setPid(int productid) {this.productid = productid;}
    public int getArrivalTime() {return arrivalTime;}
    public void setArrivalTime(int arrivalTime) {this.arrivalTime = arrivalTime;}
    public int getBurstTime() {return burstTime;}
    public void setBurstTime(int burstTime) {this.burstTime = burstTime;}
    public int getBurstTimeRemaining() {return burstTimeRemaining;}
    public void setBurstTimeRemaining(int burstTimeRemaining) {this.burstTimeRemaining = burstTimeRemaining;}
    public int getCompletionTime() {return completionTime;}
    public void setCompletionTime(int completionTime) {this.completionTime = completionTime;}
    public int getTurnaroundTime() {return turnaroundTime;}
    public void setTurnaroundTime(int turnaroundTime) {this.turnaroundTime = turnaroundTime;}
    public int getWaitTime() {return waitTime;}
    public void setWaitTime(int waitTime) {this.waitTime = waitTime;}
    public boolean isComplete() {return isComplete;}
    public void setComplete(boolean complete) {isComplete = complete;}
    public boolean isInQueue() {return inQueue;}
    public void setInQueue(boolean inQueue) {this.inQueue = inQueue;}
}
public class Main {
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  
    static void findWaitTime(ArrayList<Process> processes, int quantum) {

        
        Queue<Process> readyqueue = new LinkedList<>();
     
        Process.clock = 0;

        
        int context_switch = 0;

  
        while (Process.clock < processes.get(0).getArrivalTime()) {
            Process.clock++;
        }
        System.out.println("Log of Round Robin Events");
     
        for (Process process : processes) {

            if (Process.clock >= process.getArrivalTime()) {
                System.out.println("Process " + process.getPid() +
                        " has entered into the ready queue at " + Process.clock + "ms");
                readyqueue.add(process);
                process.setInQueue(true); 
            }
        }
        
        while (true) {
            boolean done = true; 

      
            while (!readyqueue.isEmpty()) { 

                if (readyqueue.peek().getBurstTimeRemaining() > 0) {
                    done = false; 

                    if (readyqueue.peek().getBurstTimeRemaining() > quantum) {

                     
                        System.out.println("Process " + readyqueue.peek().getPid()
                                + " has left ready queue and being processed at " + Process.clock + "ms");

                        if (readyqueue.peek().getResponseTime() == -1) {
                            readyqueue.peek().setResponseTime(Process.clock);
                            System.out.println("Process " + readyqueue.peek().getPid() +
                                    " has a response time of " + readyqueue.peek().getResponseTime() + "ms");
                        }
                        
                        Process.clock += quantum;

                    
                        readyqueue.peek().setBurstTimeRemaining(readyqueue.peek().getBurstTimeRemaining() - quantum);

                        for (Process process : processes) {

                            if ((Process.clock >= process.getArrivalTime()) && !process.isInQueue() && !process.isComplete()) {
                                System.out.println("Process " + process.getPid()
                                        + " has entered into the ready queue at " + Process.clock + "ms");
                                readyqueue.add(process);
                                process.setInQueue(true);
                            }
                        }
                        context_switch++;
                        System.out.println("Process " + readyqueue.peek().getPid()
                                + " has been placed back into the ready queue at " + Process.clock + "ms");
                        readyqueue.add(readyqueue.peek());
                        readyqueue.remove();

                    }
             
                    else {
                        if (readyqueue.peek().getResponseTime() == -1) {
                            readyqueue.peek().setResponseTime(Process.clock);
                            System.out.println("Process " + readyqueue.peek().getPid() +
                                    " has a response time of " + readyqueue.peek().getResponseTime() + "ms");
                        }
                       
                        Process.clock = Process.clock + readyqueue.peek().getBurstTimeRemaining();

                    
                        readyqueue.peek().setWaitTime(Process.clock - readyqueue.peek().getArrivalTime() -
                                readyqueue.peek().getBurstTime());

                   
                        readyqueue.peek().setBurstTimeRemaining(0);

              
                        readyqueue.peek().setComplete(true);

                        readyqueue.peek().setInQueue(false);

                  
                        readyqueue.peek().setCompletionTime(Process.clock);
               
                        System.out.println("Process " + readyqueue.peek().getPid() +
                                " has left the ready queue at " + Process.clock + "ms");

       
                        System.out.println("Process " + readyqueue.peek().getPid() +
                                " was completed at " + readyqueue.peek().getCompletionTime() + "ms");
                    
                        readyqueue.remove();

                        context_switch++;
                    }
                }
              
                for (Process process : processes) {

                    if ((Process.clock >= process.getArrivalTime()) && !process.isInQueue() && !process.isComplete()) {
                        readyqueue.add(process);
                        process.setInQueue(true); 
                        System.out.println("Process " + process.getPid()
                                + " has entered into the ready queue at " + Process.clock + "ms");
                    }
                }
            }
       
            for (Process process : processes) {
                if (!process.isComplete()) {
                    readyqueue.add(process); 
                    System.out.println("Process " + process.getPid() +
                            " has entered into the ready queue at " + Process.clock + "ms");
                    Process.clock += (process.getArrivalTime() - Process.clock);
                    break;
                }
            }
      
            if (done) {
                break;
            }

        }
        System.out.println("\nThere has been " + context_switch + " total context switches");
    }


    static void findTurnAroundTime(ArrayList<Process> processes, int n) {
   
        for (int i = 0; i < n; i++) {
            processes.get(i).setTurnaroundTime(processes.get(i).getBurstTime() + processes.get(i).getWaitTime());
        }
    }

  
    static void roundRobin(ArrayList<Process> processes, int n, int quantum) {
       
        int total_wt = 0, total_tat = 0;

        findWaitTime(processes, quantum);

   
        findTurnAroundTime(processes, n);


        processes.sort(new ProcessPIDComparator());


        System.out.println("\nChart Details");
        System.out.println("Process ID" + "\t\t" + "Arrival Time" + "\t" + "Burst Time" + "\t" +
                "\t" + "Wait Time" + "\t" + "Turn Around Time");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + processes.get(i).getWaitTime();
            total_tat = total_tat + processes.get(i).getTurnaroundTime();
            System.out.println(processes.get(i).getPid() + "\t\t\t\t" + processes.get(i).getArrivalTime()
                    + "\t\t\t\t" + processes.get(i).getBurstTime() + "\t\t\t\t" +
                    processes.get(i).getWaitTime() + "\t\t\t" + processes.get(i).getTurnaroundTime());
        }
  
        System.out.println("The CPU Utilization = " + (1 - ((float) Process.idleTime / (float) Process.clock)) * 100 + "%");

      
        System.out.println("The Throughput = " + ((float) processes.size() / (float) Process.clock)
                + " processes per unit/time");

    
        System.out.println("Average wait time = " + ((float) total_wt / (float) n) + "ms");

        System.out.println("Average turn around time = " + ((float) total_tat / (float) n) + "ms");

    }

    static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    static Scanner inp = new Scanner(System.in); 

   
    public static void main(String[] args) {

        String filename;
        ArrayList<Process> processes = new ArrayList<>(); 
        do {
            String line;
            String splitBy = ",";
            System.out.print("Enter the name of the file : ");
            filename = inp.nextLine();
            try {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                System.out.println("\n");
                while ((line = br.readLine()) != null) {
                    String[] process = line.split(splitBy);
                    Process new_process = new Process(); 
                    try {

                        new_process.setPid(Integer.parseInt(process[0]));
                       
                        new_process.setArrivalTime(Integer.parseInt(process[1]));
                       
                        new_process.setBurstTime(Integer.parseInt(process[2]));
                       
                        new_process.setBurstTimeRemaining(Integer.parseInt(process[2]));

                        System.out.println("Process ID " + new_process.getPid() +
                                " created at " + sdf3.format(timestamp));        
                        processes.add(new_process); 

                    } catch (NumberFormatException ignored) { 
                    }
                }
                break;
            } catch (FileNotFoundException e) { 
                System.out.println("Please enter the correct filename");
            } catch (IOException e) { 
                e.printStackTrace();
            }
        } while (true); 
        while (true) {
            int quantum = 0;
            do {
                try {
                    System.out.print("Enter the time quantum : ");
                    quantum = inp.nextInt();
                    inp.nextLine();
                    if (quantum < 1) {
                        throw new InputMismatchException();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number more than 0");
                }
            } while (quantum < 1);

            processes.sort(new ProcessArrivalComparator());
            for (Process process : processes) {
                process.setResponseTime(-1);
                process.setInQueue(false);
                process.setComplete(false);
                process.setCompletionTime(0);
                process.setBurstTimeRemaining(process.getBurstTime());
                process.setTurnaroundTime(0);
                process.setWaitTime(0);
            }
            System.out.println("*****************************************************************************");
       
            roundRobin(processes, processes.size(), quantum);
            String answer = "";
            do {
                System.out.println("Type to Quit (Q) or to Simulate again (S) ");
                answer = inp.nextLine().toUpperCase();

            } while (!answer.equals("S") && !answer.equals("Q"));

            if (answer.equals("Q")) {
                System.out.println("Thank you for using my Round Robin simulator :)");
                break;
            }

        }
    }
}