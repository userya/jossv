package com.jossv.framework.dao.annotation.factory.impl;

import java.util.List;

import com.jossv.framework.dao.sql.Condition;
import com.jossv.model.entity.Entity;
import com.jossv.model.entity.Relationship;
import com.jossv.model.entity.RelationshipType;

public class StaticEntityFactory extends ClassEntityFactory {

	private List<com.jossv.model.entity.Entity> entities;

	public StaticEntityFactory(List<Entity> entities) {
		super();
		this.entities = entities;
	}

	@Override
	public void init() {
		if (entities != null) {
			for (Entity vo : entities) {
				String id = vo.getId();
				com.jossv.framework.dao.model.Entity ent = new com.jossv.framework.dao.model.Entity();
				ent.setId(id);
				ent.setEntityFactory(this);
				ent.setMain(this.getTableFactory().getTable(vo.getMain()));
				ent.setMainAlias(vo.getMainAlias());
				Condition cnd = new Condition(vo.getMainCondition());
				ent.setMainCondition(cnd);
				this.entityMap.put(id, ent);
			}
			for (Entity vo : entities) {
				String id = vo.getId();
				com.jossv.framework.dao.model.Entity ent = this.getEntity(id);
				List<Relationship> rels = vo.getRelationship();
				if (rels != null) {
					for (Relationship relationship : rels) {
						RelationshipType type = relationship.getType();
						com.jossv.framework.dao.model.Relationship ship = new com.jossv.framework.dao.model.Relationship();
						ship.setAlias(relationship.getAlias());
						ship.setCondition(new Condition(relationship.getCondition()));
						ship.setRelObject(this.getTableFactory().contain(relationship.getRelObjectId())
								? getTableFactory().getTable(relationship.getRelObjectId())
								: this.getEntity(relationship.getRelObjectId()));
						ship.setRelObjectId(relationship.getRelObjectId());
						ship.setSourceId(relationship.getSourceId());
						ship.setTargetId(relationship.getTargetId());

						if (type.equals(RelationshipType.REF)) {
							ent.getRels().put(relationship.getAlias(), ship);
						} else {
							ent.getChildren().put(relationship.getAlias(), ship);
						}
					}
				}
			}

		}

	}

}
