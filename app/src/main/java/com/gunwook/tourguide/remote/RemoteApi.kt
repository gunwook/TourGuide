package com.gunwook.tourguide.remote

import com.gunwook.tourguide.data.model.NearbyModel
import com.gunwook.tourguide.utils.CodeUtils
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteApi {

    @GET(CodeUtils.Network.LOCATION)
    suspend fun locationSearch(
        @Query(CodeUtils.Network.SERVER_KEY , encoded=true) serverKey : String,
        @Query(CodeUtils.Network.MAPX) mapX : String,
        @Query(CodeUtils.Network.MAPY) mapY : String,
        @Query(CodeUtils.Network.PAGENO) pageNo : Int,
        @Query(CodeUtils.Network.RADIUS) radius : String = "2000",
        @Query(CodeUtils.Network.LISTYN) listYn : String = "Y",
        @Query(CodeUtils.Network.NUMOFROWS) numOfRows : Int = 5,
        @Query(CodeUtils.Network.ARRANGE) arrange : String = "A",
        @Query(CodeUtils.Network.MOBILEOS) mobileos : String = "ETC",
        @Query(CodeUtils.Network.MOBILEAPP) mobileapp : String = "TourAPI3.0_Guide"
    )  : NearbyModel

}