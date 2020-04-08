import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.packagerapp.delete.NameValueConverter
import com.example.packagerapp.delete.PackageTest

@Database(entities = [PackageTest::class], version = 1)
@TypeConverters(NameValueConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): PackageTestDao

    companion object
    {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null)
            {
                instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "packagesTest.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
}