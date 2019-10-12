package com.windcoder.updateFile.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_t_user")
public class TUser  implements Serializable {


	/**
	 * 账户状态枚举类
	 */
	public enum AccountStatus {
		/**
		 * 活动的
		 */
		ACTIVE,
		/**
		 * 锁定的
		 */
		LOCKED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String code;
	private String displayName;
	/**
	 * 目前看可能为空--存在问题
	 */
	@Column(unique = true)
	private String phoneNumber;
	/**
	 *  账号的执业/非执业类型。 默认为0，表示不启用该类型区分，5 表示执业会员；4 表示非执业会员
	 */
	@ColumnDefault("0")
	private Integer practicing;

	/**
	 * 账户类型
	 */
	@Enumerated(EnumType.STRING)
	private AccountStatus status;
}
