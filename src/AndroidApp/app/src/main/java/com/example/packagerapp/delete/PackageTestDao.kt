import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.packagerapp.delete.PackageTest

@Dao
interface PackageTestDao {
    @Query("SELECT * FROM packagetest")
    fun getAll(): List<PackageTest>

    @Query("SELECT * FROM packagetest WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<PackageTest>

    @Insert
    fun insertAll(vararg users: PackageTest)

    @Delete
    fun delete(user: PackageTest)
}