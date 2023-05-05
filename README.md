# CPU-Scheduling

 ## CPU SCHEDULING PROJECT REPORT
By:MuhammadUsman(SID:1299708)& MichaelAryeetey (StudentID:1284978) 

CSCI 330 (Operating Systems)

Instructor: Sussan Gass

The objective is to create a program that simulates Round Robin's CPU scheduling.

### Requirements:

The program you will create is used to generate a schedule of processes. The processes are stored in a plaintext file. The program accepts two parameters. The first parameter is the path of the process file. The second parameter is the time quantum.

#### Your simulation should include the following:

● Clock timestamps: all process events, such as creation time, completion time, etc.

● Process Creator: creates processes at arrival time

● CPU: runs processes for a time slice (time quantum)

● Queue: FIFO-ready queue used by both the process creator and CPU

● Process Arrival Time: arrival time of new processes into the ready queue

● Process Service Time: the amount of time required by the processes to complete
the execution

● Time Quantum: time each process can spend in the CPU before it is removed

● Context Switch: number of times a process is switched

#### Your program should also print out the following performance evaluation criteria:

● CPU Utilization

● Throughput

● Average Waiting Time

● Average Turnaround Time

#### Background CPU Scheduling:

In CPU scheduling, one process, usually selected by the CPU scheduler, is moved from the ready queue into the running state at any given time whilst the others remain in the ready queue. The dispatcher found in the CPU is responsible for context switching of processes moving between states. The purpose of this is to maximize CPU utilization and ensure fair distribution of CPU resources.

There are several CPU scheduling algorithms including but not limited to First-Come-First-Served (FCFS), Shortest Job First (SJF), Priority Scheduling, Round-Robin (RR), and 
Priority Scheduling w/ Round-Robin. In this report, we detail the Round-Robin algorithm while including excerpts of code demonstrating the round-robin scheduling algorithm.

#### Round Robin:

In Round-Robin, the user sets a fixed time quantum, which all other processes would have to follow. Processes are steadily moved from the ready queue to the run state, with each process running the equivalent of the time quantum regardless of their burst time. After a process reaches the time quantum, a context switch occurs and said process is moved from the run state back into the ready queue and another process is moved to the run state. With this algorithm, all processes receive equal CPU resources regardless of their priority and/or when they arrive in the ready queue which ultimately prevents starvation.

#### Simulation:

Upon running the program, you will be prompted to enter the name of the CSV file. If the file name is correct the program will create the processes from the CSV file and display confirmation with time stamps.

<img width="484" alt="processcreation" src="https://user-images.githubusercontent.com/78819386/236552212-71f05db2-072b-4029-a3ff-cf1b1d213e35.png">

We will run this program for our first simulation at a time quantum = 1. The image below shows you the log of the Round Robin events. It also provides the total number of context switches.

Continuing the first simulation at time quantum 1, the image shows the chart details. These chart details include Process ID, Arrival Time, Burst Time, Wait Time, Turn Around Time, CPU Utilization, Throughput, Average waiting time, and Average Turnaround Time

<img width="658" alt="quantum1part1" src="https://user-images.githubusercontent.com/78819386/236552273-5f697c60-82b3-4ce3-b0bf-d5c1f25037c1.png">
<img width="661" alt="quantum1part2" src="https://user-images.githubusercontent.com/78819386/236552274-8393fcc9-3fd8-40d3-a398-c87995c6f3ce.png">

 This is an image of the second simulation at time quantum 2. The image shows the Log of Events and the Chart Details.

<img width="962" alt="quantum2" src="https://user-images.githubusercontent.com/78819386/236552319-98a8cc55-52c0-4e82-9397-84cbca34dca8.png">

 This is an image of the third simulation at time quantum 3. The image shows the Log of Events and the Chart Details.
<img width="905" alt="quantum3" src="https://user-images.githubusercontent.com/78819386/236552334-f03d9311-0d6d-443b-b95d-a1683fce076a.png">

 This is an image of the fourth simulation at time quantum 4. The image shows the Log of Events and the Chart Details.
 <img width="983" alt="quantum4" src="https://user-images.githubusercontent.com/78819386/236552350-0c0042d8-380d-448f-849c-7cbb40a601ec.png">

 This is an image of the fifth simulation at time quantum 5. The image shows the Log of Events and the Chart Details.
 <img width="844" alt="quantum5" src="https://user-images.githubusercontent.com/78819386/236552375-d2929c76-d683-4726-bd4c-14de02461581.png">

#### Conclusion

In conclusion, from the above code output coupled with the brief overview of the concept behind the Round-Robin scheduling algorithm, it is clear that this algorithm is useful in ensuring not only maximum CPU utilization but also to ensure the equal allocation of CPU resources to all processes in the ready queue regardless of the number of processes, arrival time, and burst time.
