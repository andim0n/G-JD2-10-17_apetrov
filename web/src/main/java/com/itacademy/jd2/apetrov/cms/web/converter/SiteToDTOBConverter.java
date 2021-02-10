package com.itacademy.jd2.apetrov.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.web.dto.SiteDTO;

@Component
public class SiteToDTOBConverter implements Function<ISite, SiteDTO> {

	@Override
	public SiteDTO apply(final ISite entity) {
		final SiteDTO dto = new SiteDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setBaseTemplate(entity.getBaseTemplate());
		dto.setMenu(entity.getMenu());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
