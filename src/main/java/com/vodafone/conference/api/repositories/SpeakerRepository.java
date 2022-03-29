package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vodafone.conference.models.entities.Speaker;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpeakerRepository extends JpaRepository<Speaker, UUID>{

    // JPA Palette generated
    //Optional<Participant> findByIdEquals(UUID id);
    @Override
    List<Speaker> findAll();

    @Override
    Optional<Speaker> findById(UUID uuid);

    List<Speaker> findBySessions_Id(UUID id);

    List<Speaker> findByConference_Id(UUID id);

    @Transactional
    @Modifying
    @Query("update Speaker s set s.participant = ?1, s.title = ?2, s.company = ?3, s.linkedinAcc = ?4, s.twitterAcc = ?5, s.githubAcc = ?6, s.biography = ?7 " +
            "where s.id = ?8")
    void update(Participant participant, String title, String company, String linkedinAcc, String twitterAcc, String githubAcc, String biography, UUID id);



    @Override
    void deleteById(UUID uuid);


}
