package com.example.realestate.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Entity(name = "buildings")
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private int yearConstructed;
	@Column(nullable = false)
	private int numOfFloors;
	@Column(nullable = false)
	private boolean parking;
	@Column(nullable = false)
	private boolean garage;
	@Column(nullable = false)
	private boolean elevator;
	@Column(nullable = false)
	private boolean cctv;
	@Column(nullable = false)
	private boolean intercom;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId", referencedColumnName = "id")
	private Address address;

	@OneToMany(mappedBy = "building")
	private List<Apartment> apartments;

	@Override
	public String toString() {
		return this.address.toString();
	}
}
