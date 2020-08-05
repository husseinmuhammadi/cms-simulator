package com.asan.cms.to;

import com.asan.cms.to.base.Audit;
import com.asan.cms.to.base.Auditable;
import com.asan.cms.to.base.Printable;
import com.asan.cms.to.listener.AuditListener;

import javax.persistence.*;

@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class EntityBase implements Auditable, Printable {

    @Id
    @GeneratedValue(generator = "SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private final Audit audit;

    EntityBase() {
        audit = new Audit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Audit getAudit() {
        return audit;
    }
}
