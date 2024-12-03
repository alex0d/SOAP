package simulation.anylogicsimulationservice

import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebResult
import jakarta.jws.WebService
import jakarta.jws.soap.SOAPBinding
import jakarta.xml.ws.Action
import jakarta.xml.ws.BindingType
import jakarta.xml.ws.FaultAction

@WebService(
    serviceName = "AnyLogicWebService",
    endpointInterface = "simulation.AnyLogicWebServiceApplication",
    targetNamespace = "http://localhost:8080/wsdl",
    portName = "AnyLogicModelApplicationPortSOAP"
)
@BindingType(jakarta.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
@SOAPBinding(
    style = SOAPBinding.Style.DOCUMENT,
    use = SOAPBinding.Use.LITERAL,
    parameterStyle = SOAPBinding.ParameterStyle.BARE
)
interface AnyLogicWebServiceApplication {
    @WebMethod(operationName = "ApplyAnyLogicModel")
    @Action(
        input = "",
        output = "",
        fault = [FaultAction(className = AnyLogicWebServiceFaultMessage::class)]
    )
    @WebResult(name = "anyLogicModelResponse", partName = "anyLogicModelResponse")
    @Throws(AnyLogicWebServiceFaultMessage::class)
    fun applyAnyLogicModel(
        @WebParam(name = "AnyLogicModelType")
        anyLogicModel: AnyLogicModelType
    ): AnyLogicModelResponse?
}