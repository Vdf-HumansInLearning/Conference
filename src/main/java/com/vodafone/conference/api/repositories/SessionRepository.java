package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Transactional
    @Modifying
    @Query("update SessionType st set st.type = ?1, st.sessionLength = ?2 where st.id = ?3")
    void update(UUID id);

}
