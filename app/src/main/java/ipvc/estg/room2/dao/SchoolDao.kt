package ipvc.estg.room2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ipvc.estg.room2.entities.Escola

@Dao
interface SchoolDao {

    @Query("SELECT * from school_table ORDER BY school ASC")
    fun getAllSchools(): LiveData<List<Escola>>

    @Query("SELECT * FROM school_table WHERE distrit == :distrit")
    fun getSchoolsByDistrit(distrit: String): LiveData<List<Escola>>

    @Query("SELECT * FROM school_table WHERE school == :school")
    fun getDistritFromSchools(school: String): LiveData<Escola>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(school: Escola)

    @Update (onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSchool(school: Escola)

    @Delete
    suspend fun deleteId(id: Escola)

    @Query("DELETE FROM school_table")
    suspend fun deleteAll()

    @Query("DELETE FROM school_table where school == :school")
    suspend fun deleteBySchool(school: String)

    @Query("UPDATE school_table SET distrit=:distrit WHERE school == :school")
    suspend fun updateDistritFromSchool(school: String, distrit: String)
}