package simulation.anylogicsimulationservice

import jakarta.xml.ws.WebFault
import jakarta.xml.bind.annotation.XmlType

@WebFault(name = "fault", targetNamespace = "http://localhost:8080/wsdl")
class AnyLogicWebServiceFaultMessage : Exception {
    private val faultInfo: AnyLogicExceptionType

    constructor(message: String, faultInfo: AnyLogicExceptionType) : super(message) {
        this.faultInfo = faultInfo
    }

    constructor(message: String, faultInfo: AnyLogicExceptionType, cause: Throwable) : super(message, cause) {
        this.faultInfo = faultInfo
    }

    fun getFaultInfo(): AnyLogicExceptionType = faultInfo
}