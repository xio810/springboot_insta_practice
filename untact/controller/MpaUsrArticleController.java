package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Controller
public class MpaUsrArticleController {
	private List<Article> articles;
	private int articleLastId;

	public MpaUsrArticleController() {
		articles = new ArrayList<>();
		articleLastId = 0;
		makeTestData();
	}

	/////////// 글쓰기/////////////
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		int id = writeArticle(title, body);
		Article article = getArticleById(id);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);
	}

	/////////// 수정/////////////
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		boolean modified = ModifyArticle(id, title, body);

		if (modified == false) {
			return new ResultData("S-1", id + "번 글이 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 수정되었습니다.", "article", getArticleById(id));
	}

	/////////// 삭제////////////
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		boolean deleted = deleteArticleById(id);

		if (deleted == false) {
			return new ResultData("S-1", id + "번 글이 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 삭제되었습니다.", "id", id);

	}

	/////////// 글 show////////////
	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
	}

	//////////////////////// 내부////////////////////////
	private boolean ModifyArticle(int id, String title, String body) {

		Article article = getArticleById(id);

		if (article == null) {
			return false;
		}
		
		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setBody(body);
		
		return true;
	}

	private void makeTestData() {
		for (int i = 0; i < 3; i++) {
			writeArticle("제목1", "내용1");
		}
	}

	private boolean deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (article == null) {
			return false;
		}
		articles.remove(article);
		return true;

	}

	private int writeArticle(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();

		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);

		articleLastId = id;

		return id;
	}

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		/*
		 * for ( int i = 0; i < articles.size(); i++ ) { Article article =
		 * articles.get(i);
		 * 
		 * if ( article.getId() == id ) { return article; } }
		 */

		return null;
	}
}// mpaUsrArticleController - end
