package weblanguages.project.client;

import weblanguages.project.client.gui.Gui;

public class Client {
	public Client(){
		Connector con = new Connector();
		ConnLocal col = new ConnLocal();
		Gui gui = new Gui(con, col);
	}
	
	public static void main(String[] args){
		Client c = new Client();
	}
}
