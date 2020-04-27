package base.String;

import Utills.PrintUtill;

import java.text.DecimalFormat;

public class StringDecimalFormat {
	public enum IdCardType {
		/**
		 * 居民身份证
		 */
		ID_CARD,
		/**
		 * 护照
		 */
		PASSPORT,
		/**
		 * 港澳通行证
		 */
		EEP_HM,
		/**
		 *  大陆居民往来台湾通行证
		 */
		EEP_TAIWAN
	}
	public static void main(String[] args) {
		String periodStr = "32.03";
		Float period = Float.parseFloat(periodStr);
		PrintUtill.println(new DecimalFormat("#.#").format(period));

		periodStr = "32.06";
		period = Float.parseFloat(periodStr);
		PrintUtill.println(new DecimalFormat("#.#").format(period));
		PrintUtill.println(IdCardType.EEP_HM.name());

	}
}
