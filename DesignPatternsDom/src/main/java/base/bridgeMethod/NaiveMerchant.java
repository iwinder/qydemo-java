package base.bridgeMethod;

import base.covariantReturnType.thinkInJava.WheatMill;

import java.util.Random;

public class NaiveMerchant extends Merchant{

	public Double actionPrice(double price, Customer customer){
		if (customer.isVIp()){
			return price * (0.8d + new Random().nextDouble());
		}
		return price * 0.8d;
	}
//
//	public Double actionPrice2() {
//		return 40 * 0.8d;
//	}
//
//	public WheatMill actionPrice3() {
//		return new WheatMill();
//	}


}
