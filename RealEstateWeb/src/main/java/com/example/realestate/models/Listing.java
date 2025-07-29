package com.example.realestate.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "listings")
public class Listing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 255, nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "apartmentId", nullable = false)
	private Apartment apartment;
	@ManyToOne
	@JoinColumn(name = "termsId", nullable = false)
	private Terms terms;
	@ManyToOne
	@JoinColumn(name = "posterId", nullable = false)
	private AppUser poster;
//
//	@ManyToMany(mappedBy = "listings")
//	private List<User> followers;

	@Override
	public String toString() {
		return this.title;
	}
}
