import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
    Socket echoSocket = null;
    PrintWriter out = null;
    BufferedReader  in = null;
    BufferedReader stdIn;
    int portNumber;


    String fileName = null;
    String ipClient  = null;
    String portUDP = null;
    String currentDate = null;

    public Client(int portNumber)
    {
        this.portNumber = portNumber;
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

    public void udpConnection()
    {
        int fileSize;
    }

    public void sendMessageInfo()
    {
        //To send data to server: write to PrintWriter
        //To receive data from server: read from BufferedReader
        // Internal taking the values from the keyboard

        System.out.print("Give the name of file which you downlaod from server: ");
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        try {
                fileName = stdIn.readLine();

        }
        catch(IOException e)
        {
            System.err.println("Couldn't get filename");
            System.exit(1);
        }

        System.out.print("At what port do you want to send the packet through UDP: ");
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            portUDP = stdIn.readLine();
        }
        catch(IOException e)
        {
            System.out.println("Incorrect UDP number");
            System.exit(1);
        }

        try {
            ipClient = InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException e)
        {
            System.out.println("Address of the host is not known");
            System.exit(1);
        }
        Date date = new Date();
        currentDate = date.toString();
        String distinguishPattern = "(%^&)";

        String message = distinguishPattern + currentDate + distinguishPattern + fileName + distinguishPattern + ipClient + distinguishPattern + portUDP + distinguishPattern;
        System.out.format("Message sent by TCP: %s",message);
        out.println(message);
    }

    public void closeTCPSocket()
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
