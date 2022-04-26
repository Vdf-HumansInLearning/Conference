package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConferenceRepository extends JpaRepository<Conference, UUID> {

    @Override
    Optional<Conference> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

    @Transactional
    @Modifying
    @Query("update Conference c set c.location = ?1, c.theme = ?2, c.description = ?3 where c.id = ?4")
    void update(String location, String theme, String description, UUID id);

    //@Transactional
    //@Modifying
    //@Query("update Conference c set c.days = ?1, c.location = ?2, c.theme = ?3, c.description = ?4, c.participants = ?5 " +
    //        "where c.id = ?6")
    //void update(List<Day> days, String location, String theme, String description, List<Participant> participants, UUID id);


}
