public class Main {

    public static void main(String[] args) {
        System.out.println("Client is writing the data!");
        Client clientHubert = new Client(10010);
        clientHubert.createSocket();
        clientHubert.sendMessageInfo();
        clientHubert.closeTCPSocket();
    }
}
