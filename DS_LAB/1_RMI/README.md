# Assignment 1 - RMI Add Service

## Aim
Implement multi-threaded client/server process communication using RMI.

---

## Prerequisites

Install Java 8 (required for SPPU DS practicals):

```bash
sudo apt update
sudo apt install openjdk-8-jdk
```

Verify Java installation:

```bash
java -version
javac -version
which java
```

Expected output:

```bash
openjdk version "1.8.0_482"
OpenJDK Runtime Environment (build 1.8.0_482-8u482-ga~us1-0ubuntu1~22.04-b08)
OpenJDK 64-Bit Server VM (build 25.482-b08, mixed mode)

javac 1.8.0_482

/usr/lib/jvm/java-8-openjdk-amd64/bin/java
```

---

## Files

- `AddServerIntf.java`
- `AddServerImpl.java`
- `AddServer.java`
- `AddClient.java`

---

## Compile

Open terminal in the assignment folder and run:

```bash
javac *.java
rmic AddServerImpl
```

Expected warning (this is normal and safe to ignore):

```bash
Warning: generation and use of skeletons and static stubs for JRMP
is deprecated. Skeletons are unnecessary, and static stubs have
been superseded by dynamically generated stubs. Users are
encouraged to migrate away from using rmic to generate skeletons and static
stubs.
```

---

## Run

### Terminal 1 - Start RMI Registry

```bash
rmiregistry -J-Djava.class.path=.
```

Note:
- This terminal will remain blank.
- No output means `rmiregistry` is running successfully.
- Keep this terminal open.

---

### Terminal 2 - Start Server

```bash
java AddServer
```

Expected Output:

```bash
RMI Server is ready.
Bound object name: AddServer
Received request: add(2.0, 5.0)
```

---

### Terminal 3 - Start Client

```bash
java AddClient
```

Example Output:

```bash
Enter server IP (localhost or 127.0.0.1): localhost
Enter first number: 2
Enter second number: 5

===== RESULT =====
First number : 2.0
Second number: 5.0
Sum          : 7.0
```

---

## Full Terminal Output

### Terminal 1

```bash
cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ javac *.java
rmic AddServerImpl

Warning: generation and use of skeletons and static stubs for JRMP
is deprecated. Skeletons are unnecessary, and static stubs have
been superseded by dynamically generated stubs. Users are
encouraged to migrate away from using rmic to generate skeletons and static
stubs. See the documentation for java.rmi.server.UnicastRemoteObject.

cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ rmiregistry -J-Djava.class.path=.
```

### Terminal 2

```bash
cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ java AddServer

RMI Server is ready.
Bound object name: AddServer
Received request: add(2.0, 5.0)
```

### Terminal 3

```bash
cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$ java AddClient

Enter server IP (localhost or 127.0.0.1): localhost
Enter first number: 2
Enter second number: 5

===== RESULT =====
First number : 2.0
Second number: 5.0
Sum          : 7.0

cn@cn-OptiPlex-3000:~/DS_Codes/SPPU_Lab_Assignments/DS_LAB/1_RMI$
```

---

## Viva Questions

### What is RMI?
RMI (Remote Method Invocation) allows a Java program running on one machine to call methods of an object running on another machine.

### What is rmiregistry?
`rmiregistry` is a naming service that stores remote object references and helps clients locate remote objects.

### What is a Stub?
A Stub acts as a proxy object on the client side and forwards requests to the server.

### Why does every remote interface extend `Remote`?
To indicate that methods in the interface can be called remotely.

### Why do remote methods throw `RemoteException`?
To handle network-related communication failures.

### Why is `UnicastRemoteObject` used?
It exports the remote object so it becomes accessible over the network.

### Why is `rmic` used?
`rmic` generates stub files required for remote communication (though modern Java creates them dynamically).