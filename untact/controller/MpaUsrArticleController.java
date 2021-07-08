package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.service.ArticleService;
import com.sbs.untact.util.Util;

@Controller
public class MpaUsrArticleController {
	@Autowired
	private ArticleService articleService;

	

	/////////// 글쓰기/////////////
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {

		if (Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 입력해주세요.");
		}
		if (Util.isEmpty(body)) {
			return new ResultData("F-2", "내용을 입력해주세요.");
		}

		int id = articleService.writeArticle(title, body);
		Article article = articleService.getArticleById(id);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);
	}

	/////////// 수정/////////////
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {

		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		if (Util.isEmpty(title)) {
			return new ResultData("F-2", "제목을 입력해주세요.");
		}
		if (Util.isEmpty(body)) {
			return new ResultData("F-3", "내용을 입력해주세요.");
		}
		////////
		boolean modified = articleService.ModifyArticle(id, title, body);

		if (modified == false) {
			return new ResultData("S-1", id + "번 글이 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 수정되었습니다.", "article", articleService.getArticleById(id));
	}

	/////////// 삭제////////////
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {

		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		////////
		boolean deleted = articleService.deleteArticleById(id);

		if (deleted == false) {
			return new ResultData("S-1", id + "번 글이 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", id + "번 글이 삭제되었습니다.", "id", id);

	}

	/////////// 글 show////////////
	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {

		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}

		return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
	}

	
}// mpaUsrArticleController - end
