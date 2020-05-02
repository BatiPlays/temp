package de.schulze.csv.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ILieferantRepositiry extends JpaRepository<Lieferant,Integer>
{
    @Query(value = "SELECT * FROM `lieferant` WHERE lieferantennummer = ?1", nativeQuery = true)
    Lieferant findByLieferantenNummer(String lieferantenNummer);

    @Query(value = "SELECT * FROM `lieferant` WHERE lieferantennummer = ?1", nativeQuery = true)
    int getLieferantenIDByLieferantenNummer(String lieferantenNummer);

    @Query(value = "SELECT palette.palettenID FROM `palette` JOIN `lieferant` WHERE lieferant.lieferantennummer = ?1 AND palette.lieferantenID = lieferant.lieferantenID ", nativeQuery = true)
    List<Integer> getPalettenByLieferantenNummer(String lieferantenNummer);

    @Modifying
    @Transactional
    @Query(value = "UPDATE lieferant SET lieferantenname = ?1 WHERE lieferantennummer = ?2", nativeQuery = true)
    void updateLieferantName(String lieferantenName, String lieferantenNummer);

    @Modifying
    @Transactional
    @Query(value = "UPDATE lieferant SET strasse = ?1 WHERE lieferantennummer = ?2", nativeQuery = true)
    void updateLieferantStraße(String lieferantenName, String lieferantenNummer);

    @Modifying
    @Transactional
    @Query(value = "UPDATE lieferant SET plz = ?1 WHERE lieferantennummer = ?2", nativeQuery = true)
    void updateLieferantPLZ(String lieferantenName, String lieferantenNummer);

    @Modifying
    @Transactional
    @Query(value = "UPDATE lieferant SET ort = ?1 WHERE lieferantennummer = ?2", nativeQuery = true)
    void updateLieferantORT(String lieferantenName, String lieferantenNummer);
}
