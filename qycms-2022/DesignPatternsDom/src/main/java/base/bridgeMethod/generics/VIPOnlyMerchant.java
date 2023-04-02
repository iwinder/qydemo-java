package base.bridgeMethod.generics;

import base.bridgeMethod.Customer;
import base.bridgeMethod.NaiveCustomer;

import java.util.Random;

public class VIPOnlyMerchant extends Merchant<NaiveCustomer> {

	@Override
	public double actionPrice(double price, NaiveCustomer customer){
		if (customer.isVIp()){
			return price * (0.8d + new Random().nextDouble());
		}
		return price * 0.8d;
	}
}
