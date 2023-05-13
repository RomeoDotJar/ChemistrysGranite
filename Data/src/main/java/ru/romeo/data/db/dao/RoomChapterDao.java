package ru.romeo.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.romeo.data.db.entity.RoomChapter;

@Dao
public interface RoomChapterDao {
    @Query("SELECT * FROM Chapters ORDER BY Id ASC")
    List<RoomChapter> roomChapterGetAll();

    // SELECT ONE BY ID
    @Query("SELECT * FROM Chapters WHERE Id = :id")
    RoomChapter roomChapterGetById(long id);

    // INSERT NEW RECORD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void roomChapterAdd(RoomChapter roomChapter);

    // DELETE RECORD BY ID
    @Query("DELETE FROM Chapters WHERE Id = :id")
    void roomChapterDeleteById(long id);
}
