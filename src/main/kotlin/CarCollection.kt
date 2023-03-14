import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable;

fun main(args: Array<String>) {
    var ArrayofCars: MutableList<Car> = ArrayList()
    ArrayofCars.add(Car("Mazda", "2", 2002, 300000))
    ArrayofCars.add(Car("Honda", "RX8", 2011, 120000))
    ArrayofCars.add(Car("Mazda", "2", 2007, 150000))

    var selection: Int = 0

    do {
        try {
            println(
                "Use the numbers to select the menu.\n" +
                        "1. Display all car details.\n" +
                        "2. Sorts the list by its make.\n" +
                        "3. Searches car by its make.\n" +
                        "4. Stores the collection object in a binary file.\n" +
                        "5. Loads the collection object from a binary file.\n" +
                        "6. Quit program."
            )
            selection = readln().toInt()

            when (selection) {
                1 -> { //1. Display all car details.
                    for (car in ArrayofCars) {
                        car.display()
                    }
                }

                2 -> { //2. Sorts the list by its make.
                    ArrayofCars.sortWith(compareBy<Car> {it.make}. thenBy{it.model}. thenBy { it.year } . thenBy{it.odometer})
                    ArrayofCars.forEach {it.display()}
                }

                3 -> { //3. Searches car by its make.
                    val searchMake = readln()
                    var found = false
                    for (i in 0 until ArrayofCars.size) {
                        if (ArrayofCars[i].make == searchMake) {
                            found = true
                            ArrayofCars[i].display()
                            break
                        }
                    }
                    if (!found) {
                        println("Car not found.")
                    }
                }

                4 -> { //4. Stores the collection object in a binary file.
                    println("Writing to file...")
                    try {
                        val fileOut = FileOutputStream("Cars.dat")
                        val objectOut = ObjectOutputStream(fileOut)
                        objectOut.writeObject(ArrayofCars)
                        fileOut.close()
                        println("Save Complete - Cars.dat")
                    } catch (e: IOException) {
                        println("Saving failed - $e")
                    }
                }

                5 -> { //5. Loads the collection object from a binary file.
                    println("Reading from file...")
                    try {
                        val fileIn = FileInputStream("Cars.dat")
                        val objectIn = ObjectInputStream(fileIn)
                        ArrayofCars = objectIn.readObject() as ArrayList<Car>
                        fileIn.close()
                    } catch (e: IOException) {
                        println("Loading failed - $e")
                    } catch (e: ClassNotFoundException) {
                        println("Loading failed = $e")
                    }
                    ArrayofCars.forEach { it.display() }
                }

                6 -> { //6. Quit program.
                    println("Quitting program...")
                }

                else -> {
                    println("Invalid selection\n")
                }
            }
        } catch (e: NumberFormatException) {
            println("Please enter a number from the provided index.\n")
        } catch (e: Exception) {
            println("Something is wrong.\n")
        }
    } while (selection != 6)


}
