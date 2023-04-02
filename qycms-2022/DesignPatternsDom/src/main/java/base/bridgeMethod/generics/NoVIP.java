package base.bridgeMethod.generics;

import base.bridgeMethod.Customer;

public class NoVIP implements Customer {
	@Override
	public boolean isVIp() {
		return false;
	}
}
