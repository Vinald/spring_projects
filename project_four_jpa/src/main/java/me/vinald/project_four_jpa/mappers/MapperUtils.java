package me.vinald.project_four_jpa.mappers;

import java.util.List;

final class MapperUtils {
	private MapperUtils() {
	}

	static <T> List<T> nullSafe(List<T> list) {
		return list != null ? list : List.of();
	}
}
