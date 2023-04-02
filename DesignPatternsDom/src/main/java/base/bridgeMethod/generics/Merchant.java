package base.bridgeMethod.generics;

import base.bridgeMethod.Customer;

import java.util.Random;

public class Merchant<T extends Customer> {

	public double actionPrice(double price, T customer){
		if (customer.isVIp()){
			return price * (0.9d + new Random().nextDouble());
		}
		return price * 0.8d;
	}
}
