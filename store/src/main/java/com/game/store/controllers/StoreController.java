package com.game.store.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.game.store.models.Store;
import com.game.store.repository.StoreRepository;

import jakarta.persistence.criteria.Path;

@Controller
public class StoreController {

	private static String caminhoImagens = "C:\\Users\\Alisson\\Documents\\imagensprojetowilly\\";
	
	@Autowired
	private StoreRepository gr;
	
	@RequestMapping(value="/inserirjogo", method=RequestMethod.GET)
	public String inserirJogos() {
		return "page/inserirjogo";
	}
	
	@RequestMapping(value="/inserirjogo", method=RequestMethod.POST)
	public String inserirJogos(@Validated Store store, @RequestParam("file") MultipartFile file) {
		gr.save(store);
		
		try {
			if(!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				java.nio.file.Path caminho = Paths.get(caminhoImagens+ String.valueOf(store.getCodigoGame()) +file.getOriginalFilename());
				Files.write(caminho, bytes);
				
				store.setNomeImagem(String.valueOf(store.getCodigoGame())+file.getOriginalFilename());
				gr.save(store);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/paginas/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
//		System.out.println(imagem);
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
	
	@RequestMapping("/")
	public ModelAndView store() {
		ModelAndView mv = new ModelAndView("index");
		
		Iterable<Store> store = gr.findAll();
		mv.addObject("store", store);
		return mv;
	}
	
	
}
