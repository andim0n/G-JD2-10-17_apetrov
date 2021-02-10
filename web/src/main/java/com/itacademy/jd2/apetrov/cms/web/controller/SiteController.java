package com.itacademy.jd2.apetrov.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.apetrov.cms.service.ISiteService;
import com.itacademy.jd2.apetrov.cms.web.converter.SiteToDTOBConverter;
import com.itacademy.jd2.apetrov.cms.web.dto.SiteDTO;

@Controller
@RequestMapping(value = "/site")
public class SiteController {

	private ISiteService siteService;
	private SiteToDTOBConverter toDtoConverter;

	@Autowired
	private SiteController(ISiteService brandService,
			SiteToDTOBConverter toDtoConverter) {
		super();
		this.siteService = brandService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final SiteFilter filter = new SiteFilter();

		final List<ISite> entities = siteService.find(filter);
		List<SiteDTO> dtos = entities.stream().map(toDtoConverter)
				.collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("site.list", models);
	}

}
