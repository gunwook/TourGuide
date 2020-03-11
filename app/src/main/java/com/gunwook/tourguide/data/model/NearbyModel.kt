package com.gunwook.tourguide.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@Root(name = "response" , strict = false)
data class NearbyModel(
    @field:Element(name = "header") var header : NearByHeader = NearByHeader(),
    @field:Element(name = "body" , required = false) var body : NearByBody = NearByBody()
) {

    @Root(name = "header" , strict = false)
    data class NearByHeader(
        @field:Element(name = "resultCode") var resultCode : String = "",
        @field:Element(name = "resultMsg") var resultMsg : String = ""
    )


    @Root(name = "body" , strict = false)
    data class NearByBody(
        @field:Element(name = "items") var items : NearByItems = NearByItems(),
        @field:Element(name = "numOfRows"  , required = false) var numOfNums : Int = 30,
        @field:Element(name = "pageNo" , required = false) var pageNo : Int = 1,
        @field:Element(name = "totalCount" , required = false) var totalCount : Int = 0
    )



    @Root(name = "items" , strict = false)
    data class NearByItems(
        @field:ElementList(name = "item" , required = false , inline = true) var items : MutableList<NearByItem> = mutableListOf()
    )

    @Root(name = "item" , strict = false)
    data class NearByItem(
        @field:Element(name = "addr1" , required = false) var addr1 : String = "",
        @field:Element(name = "contentid", required = false) var contentid : String = "",
        @field:Element(name = "contenttypeid" , required = false) var contenttypeid : String = "",
        @field:Element(name = "createdtime" , required = false) var createdtime : String = "",
        @field:Element(name = "firstimage" , required = false) var firstimage : String = "",
        @field:Element(name = "mapx" , required = false) var mapx : String = "",
        @field:Element(name = "mapy" , required = false) var mapy : String = "",
        @field:Element(name = "title" , required = false) var title : String = ""
        )

}

