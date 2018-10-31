import java.util.*;

public aspect ShoppingCartAspect
{

	//pointcut safeWithdraw(int price) : call(* Wallet.safeWithdraw(..)); 

	pointcut safeWithdraw(int price) : call(* Wallet.safeWithdraw(int)) && args(price);

	before(int price):safeWithdraw(price)
	{
	try {
			if(price > new Wallet().getBalance()){
				System.out.println("AOP MESSAGE (BEFORE): You don't have enough balance!");
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	

	after(int price) returning (int withdrawnAmount): safeWithdraw(price)
	{
		try{
			 
			new Wallet().safeDeposit(withdrawnAmount); 
			System.out.println("AOP MESSAGE (AFTER): The balance is " + withdrawnAmount+"\n");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}