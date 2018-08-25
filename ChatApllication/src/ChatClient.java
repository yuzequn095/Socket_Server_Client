
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ChatClient {

	static JFrame chatWindow = new JFrame("Chat Application");
	static JTextArea chatArea = new JTextArea(22, 40);
	static JTextField textField = new JTextField(40);
	static JLabel blankLabel = new JLabel("           ");//between chat area and text field
	static JButton sendButton = new JButton("Send");
	
	static BufferedReader in;
	static PrintWriter out;
	
	static JLabel nameLabel = new JLabel("        ");
	
	
	//constructor
	ChatClient(){
		
		chatWindow.setLayout(new FlowLayout());
		chatWindow.add(nameLabel);
		chatWindow.add(new JScrollPane(chatArea));
		chatWindow.add(blankLabel);
		chatWindow.add(textField);
		chatWindow.add(sendButton);
		
		chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close button
		chatWindow.setSize(475, 500);
		chatWindow.setVisible(true);//display
		textField.setEditable(false);//can't enter anything before established
		chatArea.setEditable(false);
		
		sendButton.addActionListener(new Listener());
		textField.addActionListener(new Listener());
	}
	
	void startChat() throws Exception{
		String ipAddress = JOptionPane.showInputDialog(//display dialog box
					chatWindow,
					"Enter IP Address:",
					"IP Address Required!!",
					JOptionPane.PLAIN_MESSAGE);
		
		Socket soc = new Socket(ipAddress, 9806);
		in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		out = new PrintWriter(soc.getOutputStream(), true);
		
		while(true){//read the data what we get
			String str = in.readLine();
			
			if(str.equals("NAME REQUIRED")){
				String name = JOptionPane.showInputDialog(
							chatWindow,
							"Enter a unique name:",
							"Name Required!!",
							JOptionPane.PLAIN_MESSAGE
						);
				out.println(name);
			}//if ends
			
			else if(str.equals("NAME ALREADY EXISTS")){
				String name = JOptionPane.showInputDialog(
							chatWindow,
							"Enter another name:",
							"Name Already Exist!!",
							JOptionPane.WARNING_MESSAGE
						);
				out.println(name);
			}//else if ends
			
			else if(str.startsWith("NAME ACCEPTED")){
				textField.setEditable(true);
				nameLabel.setText("You are logged in as:" + str.substring(13));
			}//else if ends
			
			else{
				chatArea.append(str + "\n");
			}//else ends
		}
				
	}
	
	public static void main(String[] args) throws Exception{
		ChatClient client = new ChatClient();
		client.startChat();
	}
	
}

//for send
class Listener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		ChatClient.out.println(ChatClient.textField.getText());
		ChatClient.textField.setText("");
	}
}

/***
//ChatClient.java

import javax.swing.*;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.*;

import java.net.Socket;

public class ChatClient {

     

      static JFrame chatWindow = new JFrame("Chat Application");

    static JTextArea chatArea = new JTextArea(22, 40);

    static JTextField textField = new JTextField(40);

    static JLabel blankLabel = new JLabel("           ");

    static JButton sendButton = new JButton("Send");

    static BufferedReader in;

    static PrintWriter out;

    static JLabel nameLabel = new JLabel("         ");

   

    ChatClient()

    {

       

        chatWindow.setLayout(new FlowLayout());

      

        chatWindow.add(nameLabel);

        chatWindow.add(new JScrollPane(chatArea));

        chatWindow.add(blankLabel);

        chatWindow.add(textField);

        chatWindow.add(sendButton);

        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatWindow.setSize(475, 500);

        chatWindow.setVisible(true);

       

        textField.setEditable(false);

        chatArea.setEditable(false);

       

        sendButton.addActionListener(new Listener());

        textField.addActionListener(new Listener());

    }

   

   

    void startChat() throws Exception

    {

       String ipAddress = JOptionPane.showInputDialog(

                chatWindow,

                "Enter IP Address:",

                "IP Address Required!!",

                JOptionPane.PLAIN_MESSAGE);    

 

       Socket soc = new Socket(ipAddress, 9806);

       in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

       out = new PrintWriter(soc.getOutputStream(), true);

       while (true)

       {

         String str = in.readLine();

           if (str.equals("NAMEREQUIRED"))

           {

           String name = JOptionPane.showInputDialog(

                       chatWindow,

                       "Enter a unique name:",

                       "Name Required!!",

                       JOptionPane.PLAIN_MESSAGE);

          

               out.println(name);

              

           }

           else if(str.equals("NAMEALREADYEXISTS"))

           {

           String name = JOptionPane.showInputDialog(

                       chatWindow,

                       "Enter another name:",

                       "Name Already Exits!!",

                       JOptionPane.WARNING_MESSAGE);

          

               out.println(name);

           }

           else if (str.startsWith("NAMEACCEPTED"))

           {

               textField.setEditable(true);

               nameLabel.setText("You are logged in as: "+str.substring(12));

              

           }

           else

           {

               chatArea.append(str + "\n");

           }

       }

   }

   

      public static void main(String[] args) throws Exception {

            // TODO Auto-generated method stub

            ChatClient client = new ChatClient();

            client.startChat();

      }

}

class Listener implements ActionListener

{

      @Override

      public void actionPerformed(ActionEvent e) {

            // TODO Auto-generated method stub

            ChatClient.out.println(ChatClient.textField.getText());

            ChatClient.textField.setText("");

      }

     

}

***/