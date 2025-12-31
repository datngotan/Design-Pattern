package structural

/*
========================================
Factory Method – Quick Recall Note
========================================

Definition:
Factory Method defines a method for creating an object,
but lets subclasses decide which concrete class to instantiate.

Core Intent:
- Separate object creation from object usage
- Defer creation decisions to subclasses

Mental Model:
- Base class knows WHEN to create
- Subclass knows WHAT to create

Key Structure:
1. Product interface
2. Concrete products
3. Creator (abstract class)
4. Factory Method (abstract method)
5. Concrete creators override the method

Canonical Shape:
abstract class Creator {
    fun operation() {
        val product = createProduct()
        product.use()
    }
    protected abstract fun createProduct(): Product
}

MOST IMPORTANT RULE:
✔ Creation must happen via a METHOD
✘ Not via a field/property

Lifecycle Insight:
- Factory Method may create new objects
- OR reuse / cache / pool objects
- It controls HOW objects are obtained, not HOW MANY

When to Use:
- Only ONE product is created
- Creation varies by subclass
- Avoid `new` in business logic
- Follow Open/Closed Principle

When NOT to Use:
- Need multiple related products → Abstract Factory
- Trivial creation logic → constructor
- External lifecycle control → DI

Common Confusions:
- Simple Factory → static when/if
- Factory Method → polymorphic method
- Abstract Factory → product families
- Builder → step-by-step construction

Android Analogies:
- ViewModelProvider.Factory
- WorkerFactory (WorkManager)
- FragmentFactory

Interview One-Liner:
Factory Method lets a superclass define the workflow,
while subclasses decide which concrete object to create.

Mnemonic:
FM = Method decides creation
AF = Factory decides family
========================================
*/



interface Transport {
    fun ship()
}

class Truck : Transport {
    override fun ship() {
        println("Truck is shipping ...")
    }
}

class Ship : Transport {
    override fun ship() {
        println("Ship is shipping ...")
    }
}

abstract class Logistic {

    fun delivery() {
        val transport = createTransport()
        transport.ship()
    }

    protected abstract fun createTransport(): Transport
}

class RoadLogistic : Logistic() {
    override fun createTransport(): Transport = Truck()
}

class SeaLogistic : Logistic() {
    override fun createTransport(): Transport = Ship()
}

fun main() {
    val road = RoadLogistic()
    road.delivery()

    val sea = SeaLogistic()
    sea.delivery()
}
