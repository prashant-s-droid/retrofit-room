import com.google.gson.annotations.SerializedName

data class ContactResponse (

	@SerializedName("contacts") val contactData : List<ContactData>
)