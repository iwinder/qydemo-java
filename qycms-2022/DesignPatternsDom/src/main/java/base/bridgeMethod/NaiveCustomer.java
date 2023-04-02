package base.bridgeMethod;

public class NaiveCustomer implements Customer{

	private boolean isVIP = false;

	public NaiveCustomer(){
		 isVIP = false;
	}

	public  NaiveCustomer(boolean isVIP){
		this.isVIP = isVIP;
	}

	@Override
	public boolean isVIp() {
		return this.isVIP;
	}


}
