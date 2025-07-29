package com.example.realestate.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "terms")
public class Terms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private LocalDate dateAvailable;
	@Column(nullable = false)
	private boolean deposit;
	@Column(nullable = false)
	private boolean forStudents;
	@Column(nullable = false)
	private boolean forWorkers;
	@Column(nullable = false)
	private boolean smokingAllowed;
	@Column(nullable = false)
	private boolean petsAllowed;

	@OneToMany(mappedBy = "terms")
	private List<Listing> listings;
}
