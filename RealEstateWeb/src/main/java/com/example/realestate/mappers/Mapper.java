package com.example.realestate.mappers;

public interface Mapper<E, D> {
	E toEntity(D dto);

	D toDTO(E entity);
}
