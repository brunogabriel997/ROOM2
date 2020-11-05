package ipvc.estg.room2.db

import androidx.lifecycle.LiveData
import ipvc.estg.room2.dao.SchoolDao
import ipvc.estg.room2.entities.Escola

class SchoolRepository (private val schoolDao: SchoolDao){

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allSchools: LiveData<List<Escola>> = schoolDao.getAllSchools()

    fun getSchoolsByDistrit(distrit: String): LiveData<List<Escola>> {
        return schoolDao.getSchoolsByDistrit(distrit)
    }

    fun getDistritFromSchools(school: String): LiveData<Escola> {
        return schoolDao.getDistritFromSchools(school)
    }

    suspend fun insert(school: Escola) {
        schoolDao.insert(school)
    }

    suspend fun deleteAll(){
        schoolDao.deleteAll()
    }

    suspend fun deleteBySchool(school: String){
        schoolDao.deleteBySchool(school)
    }

    suspend fun updateSchool(school: Escola) {
        schoolDao.updateSchool(school)
    }

    suspend fun updateDistritFromSchool(school: String, distrit: String){
        schoolDao.updateDistritFromSchool(school, distrit)
    }
}