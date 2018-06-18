package io.renren.modules.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;

@Controller
@RequestMapping("/home")
public class HomeController extends AbstractController {
	
	@RequestMapping("/index")
	public String home() {
		System.out.println("跳转到首页");
		return "/modules/web/home";
	}
	
	@RequestMapping("/submit")
	public @ResponseBody R submit(@RequestParam Map<String, Object> params) {
		System.out.print("提交成功");
		
		return R.ok();
	}
}
