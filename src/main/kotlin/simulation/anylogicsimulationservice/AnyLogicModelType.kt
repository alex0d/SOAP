package simulation.anylogicsimulationservice

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AnyLogicModelType")
class AnyLogicModelType() {

    @XmlElement(required = true, nillable = false)
    var scenarioNumber: Int = 0

    @XmlElement(required = true, nillable = false)
    var drillingRate: Int = 0

    @XmlElement(required = true, nillable = false)
    var oilPrice: Double = 0.0

    constructor(scenarioNumber: Int, drillingRate: Int, oilPrice: Double) : this() {
        this.scenarioNumber = scenarioNumber
        this.drillingRate = drillingRate
        this.oilPrice = oilPrice
    }
}
