import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class ContactData (
	@PrimaryKey(autoGenerate = true)
	var id: Int=0,
	@SerializedName("contact_name") val contactName : String,
	@SerializedName("contact_type") val contactType : String,
	@SerializedName("note") val note : String,
	@SerializedName("contact_number") val contactNumber : String
)

