package driver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import driver.model.Riembursement;

public interface Repository extends JpaRepository<Riembursement, Long> {
  List<Riembursement> findByPublished(boolean published);

  List<Riembursement> findByTitleContaining(String title);
}
