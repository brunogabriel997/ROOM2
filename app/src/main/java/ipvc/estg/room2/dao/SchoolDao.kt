package ipvc.estg.room2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ipvc.estg.room.entities.City

@Dao
interface SchoolDao {
    
    @Query("SELECT * from school_table ORDER BY school ASC")
    fun getAllSchools(): LiveData<List<School>>

    @Query("SELECT * FROM school_table WHERE distrit == :distrit")
    fun getCitiesByDistrit(distrit: String): LiveData<List<School>>

    @Query("SELECT * FROM school_table WHERE school == :school")
    fun getDistritFromSchool(school: String): LiveData<School>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(school: School)

    @Update
    suspend fun updateSchool(school: School)

    @Query("DELETE FROM school_table")
    suspend fun deleteAll()

    @Query("DELETE FROM school_table where school == :school")
    suspend fun deleteBySchool(school: String)

    @Query("UPDATE school_table SET distrit=:distrit WHERE school == :school")
    suspend fun updateDistritFromSchool(school: String, distrit: String)
}