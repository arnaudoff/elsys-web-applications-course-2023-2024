package com.dreamix.overachievers.repository;

import com.dreamix.overachievers.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t from team t order by t.teamName")
    List<Team> orderTeamByTeamName();

    Optional<Team> findByTeamName(String teamName);
}