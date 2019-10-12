package com.windcoder.updateFile.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_t_user")
public class TUser  implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String code;
	private String displayName;
	@Column(unique = true)
	private String phoneNumber;
}
