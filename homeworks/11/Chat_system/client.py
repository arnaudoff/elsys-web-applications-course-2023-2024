# * Libraries:

import socket
from datetime import datetime

# * Constants:

HEADER = 64
FORMAT = 'utf-8'

# * Classes:

class Client:
    def __init__(self):
        self.socket = None
        self.nickname = None

    def connect(self, ip, port):
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.connect((ip, port))
        print(f"[CONNECTED] Connected to {ip}:{port}")

    def set_nickname(self, nickname):
        self.nickname = nickname
        print(f"[NICKNAME] Nickname set to {nickname}")

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

    def quit(self):
        if self.socket:
            self.socket.close()
            print("Disconnected from the server.")
            exit(0)
        else:
            print("[ERROR] Not connected to any server.")

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

# * Main:

def main():
    client = Client()
    while True:
        user_input = input("Enter a command: ")
        client.handle_command(user_input)

if __name__ == "__main__":
    main()