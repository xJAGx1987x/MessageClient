import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class XController {

    XController(){}

    public void setUser(User user){
        this.user = user ;
    }

    public Commands handleCreate(Commands command){
        connectToServer();
        try{
            clientOutput.writeObject(command) ;
            this.response = (Commands) clientInput.readObject() ;
            return response ;
        } catch (IOException | ClassNotFoundException errCreate) {
            System.out.println(errCreate.getMessage());
            return null;
        } finally {
            disconnectFromServer() ;
        }
    }

    public Commands handleLogin(Commands command) {
        connectToServer() ;
        try {
            clientOutput.writeObject(command);
            this.response = (Commands) clientInput.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            disconnectFromServer() ;
        }
    }

    public Commands handleRefresh(Commands command) {
        connectToServer() ;
        try {
            clientOutput.writeObject(command);
            Commands cmd = (Commands) clientInput.readObject();
            return cmd ;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            disconnectFromServer() ;
        }
    }

    public Commands close(Commands closeCommand) {
        connectToServer() ;
        try {
            clientOutput.writeObject(closeCommand);
            Commands disconnect = (Commands) clientInput.readObject() ;
            return disconnect ;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null ;
        } finally {
            disconnectFromServer() ;
        }
    }

    public Commands handleDelete(Commands deleteCommand){
        connectToServer();
        try{
            clientOutput.writeObject(deleteCommand) ;
            Commands response = (Commands) clientInput.readObject() ;
            return response ;
        } catch (IOException | ClassNotFoundException sfErr) {
            System.out.println(sfErr.getMessage()) ;
            return null;
        } finally {
            disconnectFromServer() ;
        }
    }

    public Commands handleSendFile(Commands sendFileCommand){
        connectToServer();
        try{
            clientOutput.writeObject(sendFileCommand) ;
            Commands response = (Commands) clientInput.readObject() ;
            return response ;
        } catch (IOException | ClassNotFoundException sfErr) {
            System.out.println(sfErr.getMessage()) ;
            return null;
        } finally {
            disconnectFromServer() ;
        }
    }

    public User getUser(){
        return user ;
    }

    private void connectToServer(){
        try{
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT) ;
            clientOutput = new ObjectOutputStream(clientSocket.getOutputStream()) ;
            clientInput = new ObjectInputStream(clientSocket.getInputStream()) ;
        } catch(IOException connectErr){
            System.out.println(connectErr.getMessage()) ;
        }
    }

    private void disconnectFromServer() {
        try {
            if (clientOutput != null) {
                clientOutput.close();
            }
            if (clientInput != null) {
                clientInput.close();
            }
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error while disconnecting: " + e.getMessage());
        }
    }

    private Commands response ;
    private User user ;
    private final String SERVER_HOST = "LocalHost" ;
    private final int SERVER_PORT = 6999 ;
    private Socket clientSocket ;
    private ObjectOutputStream clientOutput ;
    private ObjectInputStream clientInput ;
}
