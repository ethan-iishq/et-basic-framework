package ethan.etframework.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ethan.etframework.entity.RecvFile;
import ethan.etframework.service.RecvFileService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ethanx
 * @since 2017-12-11
 */
@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private RecvFileService fileService;
	
	@GetMapping("status")
	public String fileStatus(){
		return "file_recv_percent";
	}
	
	@GetMapping("data")
	@ResponseBody
	public Object fileStatusData(){
		List<RecvFile> list = fileService.selectList(null);
		return list;
	}
}

