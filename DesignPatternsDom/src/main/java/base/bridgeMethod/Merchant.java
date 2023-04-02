package base.bridgeMethod;

import base.covariantReturnType.thinkInJava.Mill;

public class Merchant {
	public Number actionPrice(double price, Customer customer) {
		return price * 0.8d;
	}

//	public Number actionPrice2() {
//		return 40 * 0.8d;
//	}
//
//	public Mill actionPrice3() {
//		return new Mill();
//	}

}
