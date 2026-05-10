# Assignment 2 - CORBA Reverse String

## Aim

Develop a distributed application using CORBA to demonstrate object brokering using Reverse String operation.

---

## Prerequisites

Install Java 8:

```bash
sudo apt update
sudo apt install openjdk-8-jdk
```

Verify installation:

```bash
java -version
javac -version
which java
which idlj
which orbd
echo $JAVA_HOME
```

Expected Output:

```bash
openjdk version "1.8.0_482"
OpenJDK Runtime Environment (build 1.8.0_482-8u482-ga~us1-0ubuntu1~22.04-b08)
OpenJDK 64-Bit Server VM (build 25.482-b08, mixed mode)
javac 1.8.0_482
/usr/lib/jvm/java-8-openjdk-amd64/bin/java
/usr/lib/jvm/java-8-openjdk-amd64/bin/idlj
/usr/lib/jvm/java-8-openjdk-amd64/bin/orbd
/usr/lib/jvm/java-8-openjdk-amd64

```

---

## Files

Manual files:

- `ReverseModule.idl`
- `ReverseImpl.java`
- `ReverseServer.java`
- `ReverseClient.java`

Generated automatically:

- `ReverseModule/`

---

## Generate CORBA Files

Run:

```bash
idlj -fall ReverseModule.idl
```

This creates the `ReverseModule/` folder.

---

## Compile

```bash
javac *.java ReverseModule/*.java
```

Expected warning:

```bash
ReverseModule/_ReverseStub.java:48: warning: IORCheckImpl is internal proprietary API and may be removed in a future release
     com.sun.corba.se.impl.orbutil.IORCheckImpl.check(str, "ReverseModule._ReverseStub");
                                  ^
Note: ReverseModule/ReversePOA.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
1 warning
```

This warning is normal.

---

## Run

### Terminal 1 - Start ORBD

```bash
orbd -ORBInitialPort 1050
```

Keep terminal open.

---

### Terminal 2 - Start Server

```bash
java ReverseServer -ORBInitialPort 1050 -ORBInitialHost localhost
```

Expected Output:

```bash
50 -ORBInitialHost localhost
Reverse Object Created
CORBA Server is ready...
Waiting for client requests...
Received string: hello world
```

---

### Terminal 3 - Start Client

```bash
java ReverseClient -ORBInitialPort 1050 -ORBInitialHost localhost
```

Example Output:

```bash
Enter String: hello world

===== RESULT =====
Original String : hello world
Reversed String : dlrow olleh
```

## Viva Questions

### What is CORBA?

CORBA (Common Object Request Broker Architecture) is a distributed technology that allows applications on different machines to communicate.

### What is IDL?

IDL (Interface Definition Language) defines interfaces between client and server.

### What is ORB?

ORB (Object Request Broker) acts as middleware between client and server.

### What is `orbd`?

`orbd` is a daemon process that provides naming service in CORBA.

### Why do we use `idlj`?

`idlj` converts `.idl` files into Java classes.

### What does `-fall` mean?

It generates both client-side and server-side files.

### What is POA?

POA (Portable Object Adapter) manages CORBA server objects.

### Why do we use `ReverseHelper.narrow()`?

To safely convert CORBA object reference into the required object type.
