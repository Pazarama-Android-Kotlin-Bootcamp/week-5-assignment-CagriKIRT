import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "posts")
data class Post(

    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String?,

    @SerializedName("userId")
    val userId: Int?,

    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?
)
