package com.movie.Review.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

	// test4 용
	public List<Map<String, Object>> selectReviewListTest();
}
