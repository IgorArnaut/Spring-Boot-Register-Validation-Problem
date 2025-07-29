package com.example.realestate.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Entity(name = "apartments")
public class Apartment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private int floor;
	@Column(nullable = false)
	private int area;
	@Column(nullable = false)
	private int numOfRooms;
	@Column(nullable = false)
	private int price;
	@Column(length = 255, nullable = false)
	private String state;
	@Column(length = 255, nullable = false)
	private String heating;
	@Column(length = 255, nullable = false)
	private String equipment;

	@ManyToOne
	@JoinColumn(name = "buildingId", nullable = false)
	private Building building;

	@OneToMany(mappedBy = "apartment")
	private List<Listing> listings;

	@ManyToMany
	@Builder.Default
	@JoinTable(name = "apartmentItems", joinColumns = @JoinColumn(name = "apartmentId"), inverseJoinColumns = @JoinColumn(name = "itemId"))
	private List<Item> items = new ArrayList<>();

	public String getFloorString() {
		return this.floor == 0 ? "Prizemlje" : this.floor + "";
	}

	public String getNumOfRoomsString() {
		return this.numOfRooms == 0 ? "Garsonjera" : this.numOfRooms + " soba";
	}
}
