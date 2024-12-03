package simulation.anylogicsimulationservice

import jakarta.jws.WebService
import pr11.CustomExperiment
import pr11.Main
import java.util.*
import kotlin.jvm.Throws

@WebService(
    serviceName = "AnyLogicWebService",
    portName = "AnyLogicModelApplicationPortSOAP",
    endpointInterface = "simulation.anylogicsimulationservice.AnyLogicWebService",
    targetNamespace = "http://localhost:8080/wsdl",
)
class AnyLogicWebService : AnyLogicWebServiceApplication {
    @Throws(AnyLogicWebServiceFaultMessage::class)
    override fun applyAnyLogicModel(anyLogicModelType: AnyLogicModelType): AnyLogicModelResponse? {
        return try {
            // Взаимодействие с моделью AnyLogic
            val faultInfo = AnyLogicExceptionType()
            val random = Random(System.currentTimeMillis())

            val experiment = CustomExperiment(null)
            val engine = experiment.createEngine()
            engine.defaultRandomGenerator = random

            val main = Main(engine, null, null).apply {
                setParametersToDefaultValues()
                defaultRandomGenerator = random
            }
            engine.start(main)

            val startDate = Date()
            val calendar = Calendar.getInstance().apply {
                time = startDate
                add(Calendar.YEAR, 30)
            }
            val stopDate = calendar.time

            engine.apply {
                this.startDate = startDate
                this.stopDate = stopDate
                realTimeMode = false
            }
            main.apply {
                Сценарий = anyLogicModelType.scenarioNumber
                Темп_бурения = anyLogicModelType.drillingRate
                Цена_на_нефть = anyLogicModelType.oilPrice
                Курс_доллара = 100.0
            }
            while (engine.time < 30) {
                engine.step()
            }
            engine.stop()
            AnyLogicModelResponse()
        } catch (ex: Exception) {
            println(ex.message)
            null
        }
    }
}