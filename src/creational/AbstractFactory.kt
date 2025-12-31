package creational

/*
========================================
Abstract Factory – Quick Recall Note
========================================

Definition:
Abstract Factory provides an interface for creating
FAMILIES of related or dependent objects
without specifying their concrete classes.

Core Intent:
- Create multiple RELATED products together
- Ensure products from the same family are compatible
- Swap entire product families easily

Mental Model:
- Client chooses ONE factory
- Factory produces MANY matching products

Key Structure:
1. Multiple product interfaces (Chair, Sofa, Table, ...)
2. Concrete product families (Victorian, Modern, ...)
3. Abstract Factory interface with MULTIPLE createX() methods
4. Concrete factories create a full product family

Canonical Shape:
interface AbstractFactory {
    fun createProductA(): A
    fun createProductB(): B
    fun createProductC(): C
}

IMPORTANT RULE:
✔ Must create MORE THAN ONE related product
✘ Creating only one product is NOT Abstract Factory

Key Difference vs Factory Method:
- Factory Method → creates ONE product
- Abstract Factory → creates a FAMILY of products
- Abstract Factory often USES Factory Methods internally

When to Use:
- Products must work together (compatibility matters)
- You want to switch UI themes / platforms / vendors
- You want consistency across created objects

When NOT to Use:
- Only one product → Factory Method
- No relationship between products
- Too many product combinations (explosion of classes)

Lifecycle Insight:
- Abstract Factory controls PRODUCT CONSISTENCY
- It does NOT control object lifecycle (new vs cached)

Common Real-world Examples:
- UI toolkit themes (Dark / Light)
- Cross-platform UI (Android / iOS / Web)
- Database drivers (MySQL / Postgres families)
- Design systems (Material / Cupertino)

Android Analogies:
- RoomDatabase.Builder → DAO families
- Platform-specific UI components
- Cross-platform service providers

Interview One-Liner:
Abstract Factory creates families of related objects,
ensuring compatibility without exposing concrete classes.

Mnemonic:
AF = Factory decides the FAMILY
FM = Method decides the PRODUCT
========================================
*/

interface Chair
class VictorianChair: Chair
class ModernChair: Chair

interface CoffeeTable
class VictorianCoffeeTable: CoffeeTable
class ModernCoffeeTable: CoffeeTable

interface Sofa
class VictorianSofa: Sofa
class ModernSofa: Sofa

interface FurnitureFactory {
    fun createChair(): Chair
    fun createCoffeeTable(): CoffeeTable
    fun createSofa(): Sofa
}

class VictorianFurnitureFactory: FurnitureFactory {
    override fun createChair(): Chair {
        return VictorianChair()
    }

    override fun createCoffeeTable(): CoffeeTable {
        return VictorianCoffeeTable()
    }

    override fun createSofa(): Sofa {
        return VictorianSofa()
    }
}

class ModernFurnitureFactory: FurnitureFactory {
    override fun createChair(): Chair {
        return ModernChair()
    }

    override fun createCoffeeTable(): CoffeeTable {
        return ModernCoffeeTable()
    }

    override fun createSofa(): Sofa {
        return ModernSofa()
    }
}