package ru.sazonov.future1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sazonov.future1.enteties.Lord;

import java.util.Optional;

@Repository
public interface LordRepository extends JpaRepository<Lord, Long> {
    @Query("SELECT l FROM Lord l ORDER BY age DESC")
    Page<Lord> findFirst10Lords(Pageable pageable);

    @EntityGraph(attributePaths = {"planets"})
    Optional<Lord> findById(Long lordId);
}
