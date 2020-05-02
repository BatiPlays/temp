package de.schulze.csv.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface IArtikelRepositiry extends JpaRepository<Artikel,Integer>
{
    @Query(value = "SELECT * FROM `artikel` WHERE artikelnummer = ?1 AND palettenID = ?2", nativeQuery = true)
    Artikel findByArtikelNummerAndPalettenID(String artikelnummer, int palettenID);

    @Query(value = "SELECT artikelID FROM `artikel` WHERE artikelnummer = ?1 AND palettenID = ?2", nativeQuery = true)
    int getArtikelIDByArtikelNummerAndPalettenID(String artikelNummer, int palettenID);

    @Query(value = "SELECT artikel.artikelID FROM `artikel` join `palette` WHERE palette.palettennummer = ?1 AND palette.palettenID = artikel.palettenID", nativeQuery = true)
    List<Integer> getPalettenCount(String palettenNummer);

    @Query(value = "SELECT palettenID FROM `artikel`", nativeQuery = true)
    List<Integer> getPaletten();

    @Query(value = "SELECT * FROM `artikel` WHERE palettenID = ?1", nativeQuery = true)
    List<Artikel> getArtikelByPalettenID(int palettenID);


    @Modifying
    @Transactional
    @Query(value = "UPDATE artikel SET artikelbezeichnung = ?1 WHERE artikelnummer = ?2 AND palettenID = ?3", nativeQuery = true)
    void updateArtikelBezeichnung(String artikelbezeichnung, String artikelnummer, int palettenID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE artikel SET datum = ?1 WHERE artikelnummer = ?2 AND palettenID = ?3", nativeQuery = true)
    void updateArtikelDatum(Date datum, String artikelnummer, int palettenID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE artikel SET stat = ?1 WHERE artikelnummer = ?2 AND palettenID = ?3", nativeQuery = true)
    void updateArtikelStat(String stat, String artikelnummer, int palettenID);
}
