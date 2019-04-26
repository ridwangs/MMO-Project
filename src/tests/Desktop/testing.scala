package tests.Desktop

import Extras.collision
import MVC.Model.fruits.{apple, banana, orange}
import MVC.Model.objects.Humans
import org.scalatest.FunSuite

class testing extends FunSuite {
  test(" i am speed") {
    val sample: Humans = new Humans()
    sample.consumeObject(new orange)
    val expectedSpeed: Double = 15.0
    val actualSpeed: Double = sample.speed
    assert(actualSpeed == expectedSpeed, actualSpeed)
  }
  test("tanky"){
    val sample: Humans = new Humans()
    sample.consumeObject(new apple)
    val expectedHealth: Double = 105.0
    val actualHealth: Double = sample.health
    assert(actualHealth == expectedHealth, actualHealth)
  }
  test("bruise"){
    val sample: Humans = new Humans()
    sample.consumeObject(new banana)
    val expectedStrength: Double = 10.0
    val actualStrength: Double = sample.strength
    assert(actualStrength == expectedStrength, actualStrength)
  }
  test("consumer two"){
    val sample: Humans =  new Humans()
    sample.consumeObject(new banana)
    sample.consumeObject(new banana)
    val expectedStrength: Double = 15.0
    val actualStrength: Double = sample.strength
    assert(actualStrength == expectedStrength, actualStrength)
  }
  test("overall stat"){
    val sample: Humans =  new Humans()
    sample.consumeObject(new banana)
    sample.consumeObject(new apple)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    val expectedStrength: Double = 10.0
    val expectedSpeed: Double = 20.0
    val expectedHealth: Double = 105.0
    val actualStrength: Double = sample.strength
    val actualSpeed: Double =  sample.speed
    val actualHealth: Double = sample.health
    assert(actualHealth == expectedHealth, actualHealth)
    assert(actualSpeed == expectedSpeed, actualSpeed)
    assert(actualStrength == expectedStrength, actualStrength)
  }
  test("not hungry"){
    val sample: Humans =  new Humans()
    val expectedStrength: Double = 5.0
    val expectedSpeed: Double = 10.0
    val expectedHealth: Double = 100.0
    val actualStrength: Double = sample.strength
    val actualSpeed: Double =  sample.speed
    val actualHealth: Double = sample.health
    assert(actualHealth == expectedHealth, actualHealth)
    assert(actualSpeed == expectedSpeed, actualSpeed)
    assert(actualStrength == expectedStrength, actualStrength)

  }
  test("allergic to oranges") {
    val sample: Humans = new Humans()
    sample.consumeObject(new banana)
    sample.consumeObject(new apple)
    val expectedStrength: Double = 10.0
    val expectedSpeed: Double = 10.0
    val expectedHealth: Double = 105.0
    val actualStrength: Double = sample.strength
    val actualSpeed: Double = sample.speed
    val actualHealth: Double = sample.health
    assert(actualHealth == expectedHealth, actualHealth)
    assert(actualSpeed == expectedSpeed, actualSpeed)
    assert(actualStrength == expectedStrength, actualStrength)
  }
  test("Addicted") {
    val sample: Humans = new Humans
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    sample.consumeObject(new orange)
    val expectedSpeed: Double = 110.0
    val actualSpeed: Double = sample.speed
    assert(actualSpeed == expectedSpeed, actualSpeed)
  }
  test("collisionTrue"){
    val x: Humans = new Humans
    val y: Humans = new Humans
    val collide = new collision
    x.shape.radius = 5
    y.shape.radius = 5
    x.shape.centerX = 10.0
    x.shape.centerY = 5.0
    y.shape.centerX = 5.0
    y.shape.centerY = 5.0
    assert(collide.collide(x.shape,y.shape))
  }
  test("collisionFalse"){
    val x: Humans = new Humans
    val y: Humans = new Humans
    val collide = new collision
    y.shape.radius = 5
    x.shape.radius = 5
    x.shape.centerX = 5.0
    x.shape.centerY = 5.0
    y.shape.centerY = 20.0
    y.shape.centerX = 20.0
    assert(collide.collide(x.shape,y.shape) == false)
  }
}
