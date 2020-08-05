package com.asan.cms.dto;

import com.asan.cms.dto.base.Audit;

public abstract class DtoBase {

    private Long id;

    private final Audit audit;

    private DtoBase(Audit audit) {
        this.audit = audit;
    }

    DtoBase() {
        this(new Audit());
    }

    public DtoBase(Long id) {
        this(new Audit());
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Audit getAudit() {
        return audit;
    }
}
