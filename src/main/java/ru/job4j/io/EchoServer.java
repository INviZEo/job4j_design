package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        if (string.equals("GET /?msg=Exit HTTP/1.1")) {
                            server.close();
                        } else if (string.equals("GET /?msg=Hello HTTP/1.1")) {
                            string = "Hello";
                        }
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}