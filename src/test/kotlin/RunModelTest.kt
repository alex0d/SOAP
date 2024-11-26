@file:JvmName("RunModelTest")

import pr11.CustomExperiment
import pr11.Main
import java.util.*

fun main() {
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
        Сценарий = 4222
        Темп_бурения = 200
        Цена_на_нефть = 150.0
        Курс_доллара = 30.0
    }

    while (engine.time < 30) {
        engine.step()
    }
    engine.stop()
}