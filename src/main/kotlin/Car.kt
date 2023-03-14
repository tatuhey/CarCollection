import java.io.Serializable

class Car : Serializable{
    var make: String
    var model: String? = null
    var year: Int? = null
    var odometer = 0

    constructor(make: String, model: String?, year: Int?, odometer: Int) {
        this.make = make
        this.model = model
        this.year = year
        this.odometer = odometer
    }

    fun display() {
        println(" $make $model\tYear: $year")
        println("\t\t\tkm travelled: $odometer\n")
    }
}