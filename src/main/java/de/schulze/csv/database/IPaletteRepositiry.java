package de.schulze.csv.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaletteRepositiry extends JpaRepository<Palette,Integer>
{
    @Query(value = "SELECT * FROM `palette` WHERE palettennummer = ?1", nativeQuery = true)
    Palette findByPalettenNummer(String palettennummer);

    @Query(value = "SELECT palette.palettenID FROM `palette` WHERE palettennummer = ?1", nativeQuery = true)
    int getPalettenIDByPalettenNummer(String palettennummer);

    @Query(value = "SELECT * FROM `palette` WHERE lieferantenID = ?1", nativeQuery = true)
    List<Palette> getPalettenByLieferantenID(int lieferantenID);
}
