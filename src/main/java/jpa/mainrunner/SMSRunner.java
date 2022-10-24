package jpa.mainrunner;

/**
 * The 'SMSRunner' class runs the entire application. It only contains and invokes the main method. 
 * @author deyaniragenao
 *
 */
public class SMSRunner {
	
	public static void main(String[] args) {
		
		SMS sms = new SMS();
		
		
		sms.setUpTables();
		
		
		sms.run();
		
	}
	
	
}
