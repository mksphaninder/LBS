import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Wallet {
   /**
    * The RandomAccessFile of the wallet file
    */  
   //Wallet wallet = new Wallet();
   private RandomAccessFile file;

   /**
    * Creates a Wallet object
    *
    * A Wallet object interfaces with the wallet RandomAccessFile
    */
    public Wallet () throws Exception {
	this.file = new RandomAccessFile(new File("wallet.txt"), "rw");
    }

   /**
    * Gets the wallet balance. 
    *
    * @return                   The content of the wallet file as an integer
    */

    synchronized public int getBalance() throws IOException 
    {
        this.file.seek(0);
        //System.out.println("from getBalace method "+ Integer.parseInt(this.file.readLine()));
        return Integer.parseInt(this.file.readLine());
    }

   /**
    * Sets a new balance in the wallet
    *
    * @param  newBalance          new balance to write in the wallet
    */
   
    public void setBalance(int newBalance) throws Exception 
    {
    	this.file.setLength(0);
    	String str = new Integer(newBalance).toString()+'\n'; 
    	this.file.writeBytes(str); 
    }

    public synchronized int safeWithdraw(int valueToWithdraw) throws Exception{

        /* this method should basically replace the set balance method and has to avoid dataraces.*/
    
        int newBalance = this.getBalance() - valueToWithdraw;
        // if(this.getBalance() >= (valueToWithdraw) )
        // {
            if(valueToWithdraw <= 0) {throw new Exception("Invalid I/P");}
            //newBalance = this.getBalance() - valueToWithdraw;
            this.file.setLength(0);
            String str = new Integer(newBalance).toString()+'\n'; 
            this.file.writeBytes(str);
            return newBalance;
        // }
        // System.out.println("The value to withdraw is " + valueToWithdraw);
        // else{
            // 
        // }
    }

    public synchronized int safeDecline(int valueToWithdraw) throws Exception{

        // if (this.getBalance() < valueToWithdraw) {
            int temp = this.getBalance() ;
            this.file.setLength(0);
            String str = new Integer(0).toString()+'\n';
            this.file.writeBytes(str);
            return temp;
        // }
    }


    public synchronized void safeDeposit(int valueToDeposit) throws Exception{
        /*
            In this method I should add money in the wallet.
        */
        this.file.setLength(0);
        String str = new Integer(valueToDeposit).toString()+'\n'; 
        this.file.writeBytes(str);
    }

   /**
    * Closes the RandomAccessFile in this.file
    */
    public void close() throws Exception {
	this.file.close();
    }
}
