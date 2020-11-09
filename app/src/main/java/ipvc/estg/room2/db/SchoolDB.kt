package ipvc.estg.room2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ipvc.estg.room2.dao.SchoolDao
import ipvc.estg.room2.entities.Escola
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Escola::class), version = 8, exportSchema = false)
public abstract class SchoolDB : RoomDatabase() {

    abstract fun SchoolDao(): SchoolDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var schoolDao = database.SchoolDao()

                    // Delete all content here.
                    //schoolDao.deleteAll()

                    // Add sample cities.
                    /*var school = Escola(1, "ESTG", "Viana do Castelo")
                    schoolDao.insert(school)
                    school = Escola(2, "FEUP", "Porto")
                    schoolDao.insert(school)
                    school = Escola(3, "UMinho", "Braga")
                    schoolDao.insert(school)*/

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SchoolDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SchoolDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDB::class.java,
                    "schools_database",
                )
                    //estratégia de destruição
                    //.fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}