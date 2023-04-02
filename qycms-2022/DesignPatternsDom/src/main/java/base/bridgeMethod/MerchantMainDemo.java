package base.bridgeMethod;

public class MerchantMainDemo {
	public static void main(String[] args) {
		Merchant merchant = new NaiveMerchant();
		Number price = merchant.actionPrice(40, new NaiveCustomer(true));
//		Number price = merchant.actionPrice2();
		System.out.println(price);

//		price = new NaiveMerchant().actionPrice(40, new NaiveCustomer(true));
//		System.out.println(price);
	}
}
