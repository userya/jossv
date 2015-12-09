package com.jossv.framework.dao.annotation.factory.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.jossv.framework.dao.model.Entity;
import com.jossv.system.model.table.EntityVO;

public class StaticEntityFactory extends ClassEntityFactory{

	private List<EntityVO> entities;
	
	 public StaticEntityFactory(List<EntityVO> entities) {
		super();
		this.entities = entities;
	}

	@Override
	public void init() {
		if(entities != null) {
			for (EntityVO entityVO : entities) {
				String json = entityVO.getContent();
				Entity e = JSON.parseObject(json, Entity.class);
				//TODO 详细写
				this.entityMap.put(e.getId(), e);
			}
		}
		
	}
	
}
