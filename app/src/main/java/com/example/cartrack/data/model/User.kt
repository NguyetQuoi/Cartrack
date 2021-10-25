package com.example.cartrack.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import timber.log.Timber
import java.lang.Exception
import java.lang.reflect.Type
import java.util.*

/**
 * @author n.quoi
 * @date 10.18.2021
 */
@Entity(tableName = "user", indices = [Index(value = ["id"], unique = true)])
data class User(
    @PrimaryKey
    val id: Int,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val username: String,

    @ColumnInfo
    val email: String,

    @ColumnInfo
    val address: String,

    @ColumnInfo
    val phone: String,

    @ColumnInfo
    val website: String,

    @ColumnInfo
    val company: String
) : Model {
    fun getUserLocation(): LatLng? {
        val add = address.split(",")
        if (add.size != 6) {
            return null
        }
        val location: LatLng? = try {
            val lat = add[4].toDouble()
            val lng = add[5].toDouble()
            LatLng(lat, lng)
        } catch (exception: Exception) {
            LatLng(Double.MIN_VALUE, Double.MIN_VALUE)
        }

        return location
    }

    fun getUserCompany(): Company? {
        val companyListString = company.split(",")
        if (companyListString.size != 3) {
            return null
        }

        val company: Company? = try {
            Company(companyListString[0], companyListString[1], companyListString[3])
        } catch (exception: Exception) {
            Company("", "", "")
        }
        return company
    }
}

/**
 * Deserializer for User
 */
class UserDeserializer : JsonDeserializer<User> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): User {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asInt

        val name = jsonObject.get("name").asString

        val username = jsonObject.get("username").asString

        val email = jsonObject.get("email").asString

        //Address object
        val addressObject = jsonObject.get("address")?.asJsonObject
        var addressString = ""
        addressObject?.let {
            val street = addressObject.get("street")?.asString
            val suite = addressObject.get("suite")?.asString
            val city = addressObject.get("city")?.asString
            val zipcode = addressObject.get("zipcode")?.asString

            val geoObject = addressObject.get("geo")?.asJsonObject
            var geoString = ""
            geoObject?.let {
                val lat = geoObject.get("lat")?.asString
                val lng = geoObject.get("lng")?.asString
                geoString = "$lat,$lng"
            }
            addressString = "$street,$suite,$city,$zipcode,$geoString"
        }

        val phone = jsonObject.get("phone").asString
        val website = jsonObject.get("website").asString

        //Company object
        val companyObject = jsonObject.get("company").asJsonObject
        var companyString = ""
        companyObject?.let {
            val companyName = companyObject.get("name").asString
            val catchPhrase = companyObject.get("catchPhrase").asString
            val bs = companyObject.get("bs").asString
            companyString = "$companyName,$catchPhrase,$bs"
        }

        return User(id, name, username, email, addressString, phone, website, companyString)
    }
}