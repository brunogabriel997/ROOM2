package ipvc.estg.room2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.estg.room2.db.SchoolRepository
import ipvc.estg.room2.db.SchoolDB
import ipvc.estg.room2.entities.Escola
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolViewModel (application: Application) : AndroidViewModel(application){

    private val repository: SchoolRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allSchools: LiveData<List<Escola>>

    init {
        val schoolsDao = SchoolDB.getDatabase(application, viewModelScope).SchoolDao()
        repository = SchoolRepository(schoolsDao)
        allSchools = repository.allSchools
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(school: Escola) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(school)
    }

    // delete all
    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    // delete by city
    fun deleteBySchool(school: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteBySchool(school)
    }

    fun getSchoolsByDistrit(distrit: String): LiveData<List<Escola>> {
        return repository.getSchoolsByDistrit(distrit)
    }

    fun getDistritFromSchools(school: String): LiveData<Escola> {
        return repository.getDistritFromSchools(school)
    }

    fun updateSchool(school: Escola) = viewModelScope.launch {
        repository.updateSchool(school)
    }

    fun updateDistritFromSchool(school: String, distrit: String) = viewModelScope.launch {
        repository.updateDistritFromSchool(school, distrit)
    }
}