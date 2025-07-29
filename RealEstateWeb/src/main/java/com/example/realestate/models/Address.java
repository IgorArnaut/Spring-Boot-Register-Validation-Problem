package com.example.realestate.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Entity(name = "addresses")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 255, nullable = false)
	private String city;
	@Column(length = 255, nullable = false)
	private String street;
	@Column(length = 255, nullable = false)
	private int streetNum;

	@OneToOne(mappedBy = "address")
	private Building building;

	@Override
	public String toString() {
		return this.street + " " + this.streetNum + ", " + this.city;
	}
}
