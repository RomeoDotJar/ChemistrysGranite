package ru.romeo.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.romeo.data.db.entity.RoomChapter;
import ru.romeo.data.db.entity.RoomLesson;

@Dao
public interface RoomLessonDao {
    @Query("SELECT * FROM Lessons ORDER BY Id ASC")
    List<RoomLesson> roomLessonGetAll();

    // SELECT ONE BY ID
    @Query("SELECT * FROM Lessons WHERE Id = :id")
    RoomLesson roomLessonGetById(long id);

    // INSERT NEW RECORD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void roomLessonAdd(RoomLesson roomLesson);

    // DELETE RECORD BY ID
    @Query("DELETE FROM Lessons WHERE Id = :id")
    void roomLessonDeleteById(long id);
}
