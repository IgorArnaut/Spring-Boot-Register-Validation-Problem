package com.example.realestate.controllers;

import org.springframework.data.jpa.domain.Specification;

import com.example.realestate.models.Listing;

public class ListingSpecification {
	public static Specification<Listing> apartmentCityContains(String location) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(
			criteriaBuilder.lower(root.get("apartment").get("city")),
			"%" + location.toLowerCase() + "%");
	}

	public static Specification<Listing> apartmentNumOfRoomsEquals(int numOfRooms) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("apartment").get("numOfRooms"),
			numOfRooms);
	}

	public static Specification<Listing> apartmentPriceGe(int priceFrom) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get("apartment").get("price"), priceFrom);
	}

	public static Specification<Listing> apartmentPriceLe(int priceTo) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get("apartment").get("price"), priceTo);
	}

	public static Specification<Listing> apartmentPriceBetween(int priceFrom, int priceTo) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("apartment").get("price"), priceFrom,
			priceTo);
	}

	public static Specification<Listing> apartmentAreaGe(int m2From) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get("apartment").get("area"), m2From);
	}

	public static Specification<Listing> apartmentAreaLe(int m2To) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get("apartment").get("area"), m2To);
	}

	public static Specification<Listing> apartmentAreaBetween(int m2From, int m2To) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("apartment").get("area"), m2From,
			m2To);
	}

	public static Specification<Listing> filter(String location, Integer numOfRooms, Integer priceFrom, Integer priceTo,
		Integer m2From,
		Integer m2To) {
		Specification<Listing> query = Specification.where(null);

		if (numOfRooms != null)
			query = query.and(ListingSpecification.apartmentNumOfRoomsEquals(numOfRooms));
		if (priceFrom != null && priceTo != null)
			query = query.and(ListingSpecification.apartmentPriceBetween(priceFrom, priceTo));
		else if (priceFrom != null)
			query = query.and(ListingSpecification.apartmentPriceGe(priceFrom));
		else if (priceTo != null)
			query = query.and(ListingSpecification.apartmentPriceLe(priceTo));
		if (m2From != null && m2To != null)
			query = query.and(ListingSpecification.apartmentAreaBetween(m2From, m2To));
		else if (m2From != null)
			query = query.and(ListingSpecification.apartmentAreaGe(m2From));
		else if (m2To != null)
			query = query.and(ListingSpecification.apartmentAreaLe(m2To));

		return query;
	}
}
