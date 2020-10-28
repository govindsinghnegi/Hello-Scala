package future

import scala.util.Try

object CoffeMakerSerial {
  
  println("starting app")
  type CoffeeBeans = String
  type GroundCoffee = String
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  case class Water(temperature: Int)

  def grind(beans: CoffeeBeans): GroundCoffee = "ground coffee of $beans"
  def heatWater(water: Water): Water = water.copy(temperature = 85)
  def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"
  def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"
  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"

  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  def prepareCappuccino(): Try[Cappuccino] = for {
    ground <- Try(grind("arabica beans"))
    water <- Try(heatWater(Water(25)))
    espresso <- Try(brew(ground, water))
    foam <- Try(frothMilk("milk"))
  } yield combine(espresso, foam)
  
  def main(args: Array[String]): Unit = {
    println(prepareCappuccino())   
  }

}