package com.shaycock.rimluasinfo.data.model

import org.simpleframework.xml.Attribute

class Tram {
    @field: Attribute(name = "dueMins", required = false)
    var dueMins: String? = ""

    @field: Attribute(name = "destination", required = false)
    var destination: String? = "Unknown Destination"

    fun parseDueMins() : Int {
        if (dueMins.isNullOrEmpty()) return -1
        return try {
            Integer.parseInt(dueMins!!)
        } catch (e: Exception) {
            0
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other is Tram) &&
                dueMins == (other as Tram).dueMins &&
                destination == (other as Tram).destination
    }
}