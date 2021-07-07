package com.sbs.untact.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.util.Util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class MpaUsrArticleController {
	
	private int articleLastId = 0;
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		int id = articleLastId + 1;
		
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		Article article = new Article(id, regDate, updateDate, title, body);
		
		articleLastId = id;
		
		return new ResultData("S-1", id + "번 글이 작성되었습니다.", article);
	}
	
}//mpaUsrArticleController - end

@AllArgsConstructor
@Data
class ResultData {
	private String resultData;
	private String msg;
	private Article article;
}

@AllArgsConstructor
@Data
class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;

//	
//	//Constructor
//	public Article(int id, String regDate, String updateDate, String title, String body) {
//		super();
//		this.id = id;
//		this.regDate = regDate;
//		this.updateDate = updateDate;
//		this.title = title;
//		this.body = body;
//	}
//	
//	//toString
//	@Override
//	public String toString() {
//		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
//				+ ", body=" + body + "]";
//	}
//	
//	//getter and setter
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getRegDate() {
//		return regDate;
//	}
//	public void setRegDate(String regDate) {
//		this.regDate = regDate;
//	}
//	public String getUpdateDate() {
//		return updateDate;
//	}
//	public void setUpdateDate(String updateDate) {
//		this.updateDate = updateDate;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getBody() {
//		return body;
//	}
//	public void setBody(String body) {
//		this.body = body;
//	}
//	
//	
}
