package com.sbs.untact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MpaUsrHomeController {
	@RequestMapping("/mpaUser/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕";
	}
	
	@RequestMapping("/mpaUser/home/main2")
	@ResponseBody
	public String showMain2() {
		return "bye";
	}
	
	@RequestMapping("/mpaUser/home/main3")
	@ResponseBody
	public int showMain3(int a, int b) {
		return a-b;
	}
}
