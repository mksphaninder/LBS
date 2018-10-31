import java.util.Scanner;
import java.util.Date;

public class ShoppingCart {
  public static void main(String[] args) throws Exception{

		Wallet w = new Wallet();

		int balance = w.getBalance();
		//Replace <YOUR NAME> below with your name!!! 
		System.out.println("Welcome to Kri$h's ShoppingCart. The time now is " + (new Date()).toString());
		System.out.println("Your balance: " + balance+ " credits\n");
		System.out.println("Please select your product: \n" + Store.asString());
		Scanner input = new Scanner(System.in);
		System.out.println("What do you want to buy, type e.g., pen ");
		String product = input.next();
		int price = Store.getPrice(product);
		if(price <= 0) throw new Exception("Invalid product");
		try{
			Wallet w2 = new Wallet();
			balance = w2.getBalance();
			if(balance >= price)
			{
				w2.safeWithdraw(price);
				Pocket pocket = new Pocket();
				pocket.addProduct(product);
				System.out.println("Your new balance: " + w2.getBalance()+ " credits");
			}
		
			else
			{

				System.out.println("cannot buy");
				w2.safeDecline(price);


			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
  	}
}
	