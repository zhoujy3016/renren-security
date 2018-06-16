package io.renren.modules.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.renren.modules.sys.controller.AbstractController;

@Controller
public class HomeController extends AbstractController {
	
	@RequestMapping("/home")
	public String home() {
		System.out.println("跳转到首页");
		return "/modules/web/home";
	}
}
