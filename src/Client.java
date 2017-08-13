import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.*;

public class Client
{
    Socket echoSocket = null;
    PrintWriter out = null;
    BufferedReader  in = null;
    BufferedReader stdIn;
    int portNumber;

    public Client(int portNumber1)
    {
        portNumber = portNumber1;
    }

    public  void createSocket()
    {
        try {
            InetAddress address = InetAddress.getByName("IdeaPad-Z510");
            echoSocket = new Socket(address, portNumber);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        }
            catch ( UnknownHostException e)
            {
                System.err.print("Don't know about host host.");
                System.exit(1);
            }
            catch ( IOException e)
            {
            System.err.println("Couldn't get I/O for the connection to host");
            System.exit(1);
        }
    }

    public void sendMessage()
    {
        //To send data to server: write to PrintWriter
        //To receive data from server: read from BufferedReader
        // Internal taking the values from the keyboard
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        try {
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        }
        catch(IOException e)
        {
            System.err.println("Couldn't get I/O");
        }
    }

    public void closeSocket()
    {
        try {
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        }
        catch(IOException e)
        {
            System.err.println("Connection couldn't be closed");
        }
    }

}
