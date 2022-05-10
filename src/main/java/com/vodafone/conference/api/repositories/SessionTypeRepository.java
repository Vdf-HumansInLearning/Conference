package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.SessionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface SessionTypeRepository extends JpaRepository<SessionType, UUID> {

    @Transactional
    @Modifying
    @Query("update SessionType st set st.type = ?1, st.sessionLength = ?2 where st.id = ?3")
    void update(String type, int session_length, UUID id);

}
