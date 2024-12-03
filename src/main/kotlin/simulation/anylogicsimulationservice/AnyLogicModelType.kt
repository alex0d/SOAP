package simulation.anylogicsimulationservice

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlElement

@XmlAccessorType(XmlAccessType.FIELD)
data class AnyLogicModelType(
    @XmlElement(required = true, nillable = false)
    val scenarioNumber: Int,

    @XmlElement(required = true, nillable = false)
    val drillingRate: Int,

    @XmlElement(required = true, nillable = false)
    val oilPrice: Double,
)