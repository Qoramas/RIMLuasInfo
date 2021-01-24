package com.shaycock.rimluasinfo.data.model

import com.shaycock.rimluasinfo.util.DateTimeUtil
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "stopInfo", strict = false)
class StopInfo {

    companion object {
        const val STOP_MAR = "mar"
        const val STOP_STI = "sti"
    }

    @field: Attribute(name = "created", required = false)
    var created : String? = ""

    @field: Attribute(name = "stop", required = false)
    var stop : String? = ""

    @field: ElementList(inline = true)
    var direction: List<Direction>? = null

    @field: Element(name = "message")
    var message: String? = null

    var selectedDirection = Direction.DIRECTION_OUTBOUND

    var createFormatted: String = ""
        get() = DateTimeUtil.isoToTimeDateFullFormat(created)

    private fun getTrams(dirName: String) : List<Tram> {
        if (direction != null) {
            for (d in direction!!) {
                if (d.name == dirName) {
                    return d.tramList!!
                }
            }
        }
        return listOf()
    }

    fun getTrams() = getTrams(selectedDirection)
}