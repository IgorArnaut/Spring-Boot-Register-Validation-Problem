package com.example.realestate.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "users")
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String phoneNum;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private LocalDate birthDate;
	@Column(nullable = false)
	private String password;

//	@ManyToMany
//	@Builder.Default
//	@JoinTable(name = "followerListings", joinColumns = @JoinColumn(name = "followerId"), inverseJoinColumns = @JoinColumn(name = "listingId"))
//	private List<Listing> listings = new ArrayList<Listing>();

	@Override
	public String toString() {
		return this.email.split("@")[0];
	}
}
