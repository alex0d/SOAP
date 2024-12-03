package simulation.anylogicsimulationservice

import jakarta.jws.WebService
import pr11.CustomExperiment
import pr11.Main
import java.util.*
import kotlin.jvm.Throws

@WebService(serviceName = "AnyLogicWebService",
    targetNamespace = "http://localhost:8080/AnyLogicWebService/wsdl",
    portName = "AnyLogicModelApplicationPortSOAP")
class AnyLogicWebService : AnyLogicWebServiceApplication {
    @Throws(AnyLogicWebServiceFaultMessage::class)
    override fun applyAnyLogicModel(anyLogicModel: AnyLogicModelType): AnyLogicModelResponse? {
        return try {
            // Взаимодействие с моделью AnyLogic
            val faultInfo = AnyLogicExceptionType()
            val random = java.util.Random(System.currentTimeMillis())

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
                Сценарий = anyLogicModel.scenarioNumber
                Темп_бурения = anyLogicModel.drillingRate
                Цена_на_нефть = anyLogicModel.oilPrice
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