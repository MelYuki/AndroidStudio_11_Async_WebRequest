package be.tftic.web2023.demo11_async_webrequest.models

import com.google.gson.annotations.SerializedName


data class FinalFantasyCharacter (

    @SerializedName("id"           ) var id           : String?             = null,
    @SerializedName("name"         ) var name         : String?             = null,
    @SerializedName("japaneseName" ) var japaneseName : String?             = null,
    @SerializedName("age"          ) var age          : String?             = null,
    @SerializedName("gender"       ) var gender       : String?             = null,
    @SerializedName("race"         ) var race         : String?             = null,
    @SerializedName("job"          ) var job          : String?             = null,
    @SerializedName("height"       ) var height       : String?             = null,
    @SerializedName("weight"       ) var weight       : String?             = null,
    @SerializedName("origin"       ) var origin       : String?             = null,
    @SerializedName("description"  ) var description  : String?             = null,
    @SerializedName("pictures"     ) var pictures     : ArrayList<FinalFantasyPicture> = arrayListOf()

)