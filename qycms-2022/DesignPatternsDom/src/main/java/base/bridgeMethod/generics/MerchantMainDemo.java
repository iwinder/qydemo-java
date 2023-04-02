package base.bridgeMethod.generics;


import base.bridgeMethod.Customer;
import base.bridgeMethod.NaiveCustomer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MerchantMainDemo {
	public static void main(String[] args) {
		Merchant merchant = new VIPOnlyMerchant();
		Number price = merchant.actionPrice(40, new NaiveCustomer(true));
//		Number price = merchant.actionPrice2();
		System.out.println(price);
		// 报错 java.lang.ClassCastException: base.bridgeMethod.generics.NoVIP cannot be cast to base.bridgeMethod.NaiveCustomer
		price = merchant.actionPrice(40, new NoVIP());
//		Number price = merchant.actionPrice2();
//		System.out.println(price);
//		try {
//			VIPOnlyMerchant merchant2 = new VIPOnlyMerchant();
//			Method m = VIPOnlyMerchant.class.getMethod("actionPrice", double.class, NaiveCustomer.class);
//			NaiveCustomer s = new NaiveCustomer(true);
//			m.invoke(merchant, 40, new NaiveCustomer());
//			System.out.println(m.isBridge());
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}

//		Method.isBridge(merchant.actionPrice(40, new NoVIP()))
//		merchant.actionPrice(40, new NoVIP()).isBridge();
//		price = new NaiveMerchant().actionPrice(40, new NaiveCustomer(true));
//		System.out.println(price);
	}
}
