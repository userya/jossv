package com.jossv.system.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.jossv.framework.dao.annotation.Entity;
import com.jossv.framework.dao.annotation.Main;
import com.jossv.framework.dao.annotation.Relationship;
import com.jossv.framework.dao.annotation.RelationshipType;
import com.jossv.system.model.table.EntityVO;
import com.jossv.system.model.table.PageVO;
import com.jossv.system.model.table.ServiceVO;
import com.jossv.system.model.table.StageVO;

@Entity
public class Stage {
	
	@Main(alias = "s")
	private StageVO stage ;
	
	//(select * from table t where t.id = ? and exists (select 1 from (select * from stage s where s.id = tables.))
	@Relationship(alias = "tbls", condition = "${tbls.t.stageId} = ${s.id}", type = RelationshipType.child)
	private List<Table> tables = new ArrayList<>();
	
	@Relationship(alias = "ents", condition = "${ents.stageId} = ${s.id}", type = RelationshipType.child)
	private List<EntityVO> entitis = new ArrayList<>();
	
	@Relationship(alias = "servs", condition = "${servs.stageId} = ${s.id}", type = RelationshipType.child)
	private List<ServiceVO> services = new ArrayList<>();
	
	@Relationship(alias = "pgs", condition = "${pgs.stageId} = ${s.id}", type = RelationshipType.child)
	private List<PageVO> pages = new ArrayList<>();

	public StageVO getStage() {
		return stage;
	}

	public void setStage(StageVO stage) {
		this.stage = stage;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public List<EntityVO> getEntitis() {
		return entitis;
	}

	public void setEntitis(List<EntityVO> entitis) {
		this.entitis = entitis;
	}

	public List<ServiceVO> getServices() {
		return services;
	}

	public void setServices(List<ServiceVO> services) {
		this.services = services;
	}

	public List<PageVO> getPages() {
		return pages;
	}

	public void setPages(List<PageVO> pages) {
		this.pages = pages;
	}

	
	
	
}
