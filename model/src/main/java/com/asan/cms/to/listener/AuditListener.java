package com.asan.cms.to.listener;

import com.asan.cms.to.base.Audit;
import com.asan.cms.to.base.Auditable;
import com.asan.cms.type.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AuditListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditListener.class);

    @PrePersist
    public void setCreatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();
        LocalDateTime now = LocalDateTime.now();
        Date current = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        audit.setCreatedOn(current);
        String username = getLoggedInUser();
        audit.setCreatedBy(username);
        LOGGER.info("User [{}] is creating an instance of {} on {}", username, auditable.getClass().getSimpleName(), String.format("%tA, %tB %te, %tY %tl:%tM:%tS %Tp", current, current, current, current, current, current, current, current));
    }

    @PreUpdate
    public void setUpdatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();
        LocalDateTime now = LocalDateTime.now();
        Date current = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        audit.setUpdatedOn(current);
        String username = getLoggedInUser();
        audit.setUpdatedBy(username);
        LOGGER.info("User [{}] is updating an instance of {} on {}", username, auditable.getClass().getSimpleName(), String.format("%tA, %tB %te, %tY %tl:%tM:%tS %Tp", current, current, current, current, current, current, current, current));
    }

    private String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            if (authentication.getPrincipal() instanceof Principal) {
                Principal principal = (Principal) authentication.getPrincipal();
                return principal.getUsername();
            }
        }
        return null;
    }
}
