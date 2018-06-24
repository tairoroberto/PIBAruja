package br.com.trmasolutions.pibaruja.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ecpc_married_couple")
data class EcpcMarriedCouple(

        @PrimaryKey
        @SerializedName("uid")
        @ColumnInfo(name = "uid")
        var uid: String = "",

        @SerializedName("ecpc_date")
        @ColumnInfo(name = "ecpc_date")
        var ecpcDate: String = "",

        @SerializedName("ecpc_date_uid")
        @ColumnInfo(name = "ecpc_date_uid")
        var ecpcDateUid: String = "",


        @SerializedName("husband_name")
        @ColumnInfo(name = "husband_name")
        var husbandName: String = "",

        @SerializedName("husband_nickname")
        @ColumnInfo(name = "husband_nickname")
        var husbandNickname: String = "",

        @SerializedName("husband_birthday")
        @ColumnInfo(name = "husband_birthday")
        var husbandBirthday: String = "",

        @SerializedName("husband_email")
        @ColumnInfo(name = "husband_email")
        var husbandEmail: String = "",

        @SerializedName("husband_cellphone")
        @ColumnInfo(name = "husband_cellphone")
        var husbandCellphone: String = "",

        @SerializedName("husband_father")
        @ColumnInfo(name = "husband_father")
        var husbandFather: String = "",

        @SerializedName("husband_mother")
        @ColumnInfo(name = "husband_mother")
        var husbandMother: String = "",

        @SerializedName("husband_church")
        @ColumnInfo(name = "husband_church")
        var husbandChurch: String = "",

        @SerializedName("husband_observations")
        @ColumnInfo(name = "husband_observations")
        var husbandObservations: String = "",


        @SerializedName("wife_name")
        @ColumnInfo(name = "wife_name")
        var wifeName: String = "",

        @SerializedName("wife_nickname")
        @ColumnInfo(name = "wife_nickname")
        var wifeNickname: String = "",

        @SerializedName("wife_birthday")
        @ColumnInfo(name = "wife_birthday")
        var wifeBirthday: String = "",

        @SerializedName("wife_email")
        @ColumnInfo(name = "wife_email")
        var wifeEmail: String = "",

        @SerializedName("wife_cellphone")
        @ColumnInfo(name = "wife_cellphone")
        var wifeCellphone: String = "",

        @SerializedName("wife_father")
        @ColumnInfo(name = "wife_father")
        var wifeFather: String = "",

        @SerializedName("wife_mother")
        @ColumnInfo(name = "wife_mother")
        var wifeMother: String = "",

        @SerializedName("wife_church")
        @ColumnInfo(name = "wife_church")
        var wifeChurch: String = "",

        @SerializedName("wife_observations")
        @ColumnInfo(name = "wife_observations")
        var wifeObservations: String = "",


        @SerializedName("kids")
        @ColumnInfo(name = "kids")
        var kids: String = "",

        @SerializedName("marriage_date")
        @ColumnInfo(name = "marriage_date")
        var marriageDate: String = "",

        @SerializedName("cep")
        @ColumnInfo(name = "cep")
        var cep: String = "",

        @SerializedName("address")
        @ColumnInfo(name = "address")
        var address: String = "",

        @SerializedName("address_number")
        @ColumnInfo(name = "address_number")
        var addressNumber: String = "",

        @SerializedName("complement")
        @ColumnInfo(name = "complement")
        var complement: String = "",

        @SerializedName("district")
        @ColumnInfo(name = "district")
        var district: String = "",

        @SerializedName("city")
        @ColumnInfo(name = "city")
        var city: String = "",

        @SerializedName("state")
        @ColumnInfo(name = "state")
        var state: String = "",


        @SerializedName("sponsor_name")
        @ColumnInfo(name = "sponsor_name")
        var sponsorName: String = "",

        @SerializedName("sponsor_email")
        @ColumnInfo(name = "sponsor_email")
        var sponsorEmail: String = "",

        @SerializedName("sponsor_telephone")
        @ColumnInfo(name = "sponsor_telephone")
        var sponsorTelephone: String = "",

        @SerializedName("created_at")
        @ColumnInfo(name = "created_at")
        var createdAt: String = "") : Parcelable