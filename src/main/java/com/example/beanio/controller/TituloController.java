package com.example.beanio.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.beanio.model.Employee;
import com.example.beanio.service.BeanReaderService;

@Controller
@RequestMapping("/")
public class TituloController {
	
	
	@Autowired
	private BeanReaderService beanReaderService;
	
    @GetMapping
    public String main() {
        return "index";
    }
	
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
        file.transferTo(convFile);
        
        beanReaderService.lerArquivo(convFile);
        return "redirect:/";
    }
    
    @GetMapping("escrever")
    public String escreveArquivo() {
    
    	beanReaderService.escreveArquivo();
    
    return "redirect:/";
    }
	

	
	
	
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public String uploadFile(MultipartFile file)
//	      throws IOException, ServletException {
//	   System.out.println("File: " + file); // REM: Debug Print
//	   return "FileUpload";
//	}


}
