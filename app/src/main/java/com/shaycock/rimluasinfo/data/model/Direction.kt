package com.shaycock.rimluasinfo.data.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "direction", strict = false)
class Direction {
    companion object {
        const val DIRECTION_OUTBOUND = "Outbound"
        const val DIRECTION_INBOUND = "Inbound"
    }

    @field: Attribute(name = "name", required = false)
    var name : String? = ""

    @field: ElementList(inline = true)
    var tramList: List<Tram>? = null
}