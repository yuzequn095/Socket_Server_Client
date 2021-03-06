import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) throws Exception{
		
		/*****
		try {
			System.out.println("*** Client started ***");
			Socket soc = new Socket("localhost", 9800);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//IP&Port number
		******/
		
		System.out.println("Client started.");
		
		Socket soc = new Socket("localhost", 9806);
		//read from keyboard
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		//read from socket and stream
		BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		//send data to output stream
		PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
		
		//menu
		int option = 0;
		int num1 = 0;
		int num2 = 0;
		
		do{
			System.out.println("Choose an operation:");
			System.out.println("1. Addition");
			System.out.println("2. Substarction.");
			System.out.println("3. Multiplication.");
			System.out.println("4. Division.");
			System.out.println("5. Exit.");
			System.out.println("Enter an option:");
			option = Integer.parseInt(userInput.readLine());
			
			if(option != 5){
				System.out.print("Enter the first number: ");
				num1 = Integer.parseInt(userInput.readLine());
				System.out.print("Enter the second number: ");
				num2 = Integer.parseInt(userInput.readLine());
				
				out.println(option + ":" + num1 + ":" + num2);				
			}
			else{
				out.println(option + ":0:0");
				break;
			}
			
			String answer = in.readLine();
			System.out.println("Server says: " + answer);
			System.out.println("");
		}while(true);
		System.out.println("Client ended.");
	}
	
}
