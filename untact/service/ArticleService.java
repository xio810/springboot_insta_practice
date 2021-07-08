package com.sbs.untact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.untact.dao.ArticleDao;
import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

////////////////////////내부////////////////////////

	public ResultData ModifyArticle(int id, String title, String body) {

		Article article = getArticleById(id);

		if (article == null) {
			return new ResultData("F-4", "존재하지 않는 게시물 번호 입니다.", "id", id);
		}
		articleDao.ModifyArticle(id, title, body);
		
		return new ResultData("S-1", "게시물이 수정 되었습니다.", "id", id);
	}

	public ResultData deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", "게시물이 존재하지 않습니다.", "id", id);
		}
		articleDao.deleteArticleById(id);
		
		return new ResultData("S-1", id + "번 게시물이 삭제되었습니다.", "id", id);
	}

	public ResultData writeArticle(String title, String body) {
		
		int id = articleDao.writeArticle(title, body);
		
		return new ResultData("s-1", "게시물이 작성되었습니다.", "id", id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

}
