package be.tftic.web2023.demo11_async_webrequest.models

import com.google.gson.annotations.SerializedName


data class FinalFantasyPicture (

    @SerializedName("id"           ) var id           : String? = null,
    @SerializedName("url"          ) var url          : String? = null,
    @SerializedName("primary"      ) var primary      : Int?    = null,
    @SerializedName("collectionId" ) var collectionId : String? = null

)