# Assignment 1 - RMI Add Service

## Files
- AddServerIntf.java
- AddServerImpl.java
- AddServer.java
- AddClient.java

## Prerequisites
- Java 8
- RMI registry
- `rmic` tool

## Compile
```bash
javac *.java
rmic AddServerImpl

## Output:

### Terminal 1:

cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ javac *.java
rmic AddServerImpl
Warning: generation and use of skeletons and static stubs for JRMP
is deprecated. Skeletons are unnecessary, and static stubs have
been superseded by dynamically generated stubs. Users are
encouraged to migrate away from using rmic to generate skeletons and static
stubs. See the documentation for java.rmi.server.UnicastRemoteObject.
cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ rmiregistry -J-Djava.class.path=.

### Terminal 2:
cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ java AddServer
RMI Server is ready.
Bound object name: AddServer
Received request: add(2.0, 5.0)

### Terminal 3:
cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ java AddClient
Enter server IP (localhost or 127.0.0.1): localhost
Enter first number: 2
Enter second number: 5

===== RESULT =====
First number : 2.0
Second number: 5.0
Sum          : 7.0
cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ 

