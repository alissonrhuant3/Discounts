package com.game.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.game.store.repository.StoreRepository;

@Controller
public class CooperativoController {

	@Autowired
	private StoreRepository gr;

	@RequestMapping("/coop")
	public String cooperativo() {
		return "page/cooperativo";
	}
}
