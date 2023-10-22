# Chat System

## Description
This is a chat system that allows multiple clients to connect to a server and send messages to each other. The server will keep a log of all the messages.
## server.py
### Description
The server is the host of the chat system. It is responsible for handling the clients. Multiple clients can connect to the server at the same time. The server will receive the messages from the clients and send them to all the other clients. The server will also keep a log of all the messages.

### Libraries
```python
import socket
import threading
```
 - **socket**: for creating sockets
 - **threading**: for creating threads

### Constants
```python
HEADER = 64
PORT = 5050
SERVER = socket.gethostbyname(socket.gethostname())
ADDR = (SERVER, PORT)
FORMAT = 'utf-8'
```
- **HEADER**: the length of the message
- **PORT**: the port number
- **SERVER**: the server's IP address
  - **gethostbyname()**: gets the IP address of the host
  - **gethostname()**: gets the name of the host
- **ADDR**: the address of the server
- **FORMAT**: the format of the message

### Variables
```python
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(ADDR)

clients = []
conversation_log = []
```

- **server**: the server socket
  - **socket.AF_INET**: the socket family
  - **socket.SOCK_STREAM**: the socket type
  - **server.bind(ADDR)**: binds the socket to the address
- **clients**: the list of clients
- **conversation_log**: the list of messages

### Functions
```python
def handle_client(conn, addr):
    print(f"[NEW CONNECTION] {addr} connected.")
    clients.append(conn)
    
    connected = True

    while connected:
        try:
            msg_length = conn.recv(HEADER).decode(FORMAT)
            if msg_length:
                msg_length = int(msg_length)
                msg = conn.recv(msg_length).decode(FORMAT)

                print(msg)
                conn.send("Msg received".encode(FORMAT))

        except ConnectionResetError:
            print(f"[DISCONNECTION] {addr} has disconnected.")
            break
    
    clients.remove(conn)
    conn.close()
```
- **handle_client(conn, addr)**: handles the client
  - **conn**: the client socket
  - **addr**: the client address
  - **clients.append(conn)**: adds the client to the list of clients
  - **connected**: the connection status
  - **while connected**: while the client is connected
    - **try**: tries to receive a message from the client
      - **msg_length**: the length of the message
      - **if msg_length**: if the message length is not 0
        - **msg_length = int(msg_length)**: converts the message length to an integer
        - **msg**: the message
        - **print(msg)**: prints the message
        - **conn.send("Msg received".encode(FORMAT))**: sends a message to the client
    - **except ConnectionResetError**: if the client disconnects
      - **clients.remove(conn)**: removes the client from the list of clients
      - **conn.close()**: closes the connection

```python
def start():
    server.listen()
    print(f"[LISTENING] Server is listening on {SERVER}")
    while True:
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_client, args=(conn, addr))
        thread.start()
        print(f"[ACTIVE CONNECTIONS] {threading.active_count() - 1}")
```
- **start()**: starts the server
  - **server.listen()**: listens for connections
  - **conn**: the client socket
  - **addr**: the client address
  - **thread**: the thread
  - **threading.Thread(target=handle_client, args=(conn, addr))**: creates a thread
  - **thread.start()**: starts the thread
  - **threading.active_count()**: the number of active threads

### Main
```python
def main():
    print("[STARTING] Server is starting...")
    start()

if __name__ == "__main__":
    main()
```
- **main()**: the main function
  - **start()**: starts the server
- **if __name__ == "__main__"**: if the file is run directly
  - **main()**: runs the main function

## client.py
### Description
The client is the user of the chat system. It is responsible for sending messages to the server. The client can connect to a server, set a nickname, send messages, and quit.

### Libraries
```python
import socket
from datetime import datetime
```
- **socket**: for creating sockets
- **datetime**: for getting the current time

### Constants
```python
HEADER = 64
FORMAT = 'utf-8'
```
- **HEADER**: the length of the message
- **FORMAT**: the format of the message

### Classes
```python
class Client:
    def __init__(self):
        self.socket = None
        self.nickname = None
```
- **Client**: the client class
  - **self.socket**: the client socket
  - **self.nickname**: the client nickname

### Client Class Functions
```python
    def connect(self, ip, port):
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.connect((ip, port))
        print(f"[CONNECTED] Connected to {ip}:{port}")
```
- **connect(self, ip, port)**: connects to a server
  - **self.socket**: the client socket
    - **socket.AF_INET**: the socket family
    - **socket.SOCK_STREAM**: the socket type
  - **self.socket.connect((ip, port))**: connects to the server

```python
    def set_nickname(self, nickname):
        self.nickname = nickname
        print(f"[NICKNAME] Nickname set to {nickname}")
```
- **set_nickname(self, nickname)**: sets the nickname
  - **self.nickname**: the client nickname

```python
    def send_message(self, msg):
        if not self.socket:
            print("[ERROR] You are not connected to a server.")
            return
        if not self.nickname:
            print("[ERROR] You have not set a nickname.")
            return
        
        timestamp = datetime.now().strftime("[%I:%M %p]")
        formatted_msg = f"{timestamp} {self.nickname}: {msg}"
        encoded_msg = formatted_msg.encode(FORMAT)

        msg_length = len(encoded_msg)
        encoded_length = str(msg_length).encode(FORMAT)
        encoded_length += b' ' * (HEADER - len(encoded_length))

        self.socket.send(encoded_length)
        self.socket.send(encoded_msg)
        print(f"[SENT] {formatted_msg}")
```
- **send_message(self, msg)**: sends a message to the server
  - **if not self.socket**: if the client is not connected to a server
    - **print("[ERROR] You are not connected to a server.")**: prints an error message
    - **return**: returns
  - **if not self.nickname**: if the client has not set a nickname
    - **print("[ERROR] You have not set a nickname.")**: prints an error message
    - **return**: returns
  - **timestamp**: the current time
  - **formatted_msg**: the formatted message
  - **encoded_msg**: the encoded message
  - **msg_length**: the length of the encoded message
  - **encoded_length**: the encoded length of the encoded message
  - **encoded_length += b' ' * (HEADER - len(encoded_length))**: adds spaces to the encoded length until it is the length of the header
  - **self.socket.send(encoded_length)**: sends the encoded length to the server
  - **self.socket.send(encoded_msg)**: sends the encoded message to the server

```python
    def quit(self):
        if self.socket:
            self.socket.close()
            print("Disconnected from the server.")
            exit(0)
        else:
            print("Not connected to any server.")
```
- **quit(self)**: quits the client
  - **if self.socket**: if the client is connected to a server
    - **self.socket.close()**: closes the connection
    - **print("Disconnected from the server.")**: prints a message
    - **exit(0)**: exits the program
  - **else**: if the client is not connected to a server
    - **print("[ERROR] Not connected to any server.")**: prints an error message

```python
    def handle_command(self, command):
        if command.startswith("/connect "):
            parts = command.split(" ")
            ip, port = parts[1].split(":")
            self.connect(ip, int(port))
        elif command.startswith("/nick "):
            nickname = command.split(" ")[1]
            self.set_nickname(nickname)
        elif command.startswith("/msg "):
            message = command[5:]
            self.send_message(message)
        elif command == "/quit":
            self.quit()
        else:
            print("[ERROR] Unknown command")
```
- **handle_command(self, command)**: handles the command
  - **if command.startswith("/connect ")**: if the command is to connect to a server
    - **parts**: the parts of the command
    - **ip, port = parts[1].split(" : ")**: splits the IP address and port number
    - **self.connect(ip, int(port))**: connects to the server
  - **elif command.startswith("/nick ")**: if the command is to set a nickname
    - **nickname**: the nickname
    - **self.set_nickname(nickname)**: sets the nickname
  - **elif command.startswith("/msg ")**: if the command is to send a message
    - **message**: the message
    - **self.send_message(message)**: sends the message
  - **elif command == "/quit"**: if the command is to quit
    - **self.quit()**: quits the client
  - **else**: if the command is unknown
    - **print("Unknown command")**: prints an error message
### Main
```python
def main():
    client = Client()
    while True:
        user_input = input("Enter a command: ")
        client.handle_command(user_input)

if __name__ == "__main__":
    main()
```
- **main()**: the main function
  - **client**: the client
  - **while True**: while the program is running
    - **user_input = input("Enter a command: ")**: gets the user input
    - **client.handle_command(user_input)**: handles the command