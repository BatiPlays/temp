package de.schulze.csv.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ISettingRepositiry extends JpaRepository<Setting,Integer>
{
    @Query(value = "SELECT * FROM `setting` WHERE artikelID = ?1", nativeQuery = true)
    List<Setting> getSettingsByArtikelID(int artikelID);

    @Query(value = "SELECT * FROM `setting` WHERE artikelID = ?1 AND settingtitle = ?2", nativeQuery = true)
    Setting getSettingsByArtikelIDAndSettingTitle(int artikelID, String settingTitle);

    @Modifying
    @Transactional
    @Query(value = "UPDATE setting SET settingtitle = ?1 WHERE artikelID = ?2", nativeQuery = true)
    void updateSettingTitle(String settingTitle, int artikelID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE setting SET settingvalue = ?1 WHERE settingID = ?2", nativeQuery = true)
    void updateSettingValue(String settingValue, int settingID);
}
