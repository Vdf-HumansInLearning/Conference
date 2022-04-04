package com.vodafone.conference.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vodafone.conference.models.entities.Participant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    // JPA Palette generated
    @Override
    List<Participant> findAll();


    @Override
    Optional<Participant> findById(UUID uuid);

    //List<Participant> findBySessions_Id(UUID id);

    List<Participant> findByConference_Id(UUID id);

    @Transactional
    @Modifying
    @Query("update Participant p set p.firstName = ?1, p.lastName = ?2, p.title = ?3, p.email = ?4, p.phoneNumber = ?5, p.username = ?6, p.password = ?7 " +
            "where p.id = ?8")
    void update(String firstName, String lastName, String title, String email, String phoneNumber, String username, String password, UUID id);



    @Override
    void deleteById(UUID uuid);

}
