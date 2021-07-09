package com.sbs.untact.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untact.dto.Article;

@Mapper
public interface ArticleDao {

	// modify article
	// update
	boolean modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	// write article
	// insert
	void writeArticle(@Param("boardId") int boardId, @Param("memberId") int memberId, @Param("title") String title,
			@Param("body") String body);

	// get article by ID
	// SELECT * from article
	Article getArticleById(@Param("id") int id);

	//
	int getLastInsertId();

	// delete article by ID
	/*
	 * update article set delStatus = 1, delDate = now(), where id = #{id}
	 */
	void deleteArticleById(@Param("id") int id);

}//
