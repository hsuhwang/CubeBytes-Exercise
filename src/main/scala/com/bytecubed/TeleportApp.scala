package com.bytecubed

import scala.collection.mutable.ListBuffer
import scala.io.Source


/**
 * Created by hhwang on 5/2/18.
 *
 */
object TeleportApp {

  def ctrim(s: String) = s.replaceAll("^\\s+", "").replaceAll("\\s+$", "")
  def rtrim(s: String) = s.replaceAll("\\s+$", "")
  def addEntryToGraph(line:String,graph: Graph): Unit ={
    val cityTuple = line.trim.split("-").map(_.trim)
    graph.addEntries(ctrim(cityTuple(0)),ctrim(cityTuple(1)))
  }

  def answerCityJumpsQ(line:String,graph: Graph, teleportExpert:TeleportExpertSystem): Unit ={
    val tuples = line.trim.split(" ").map(_.trim)
    println(line +"?")
    println("The answer is: ")
    teleportExpert.getCitiesWithinNJump(graph,tuples(2),tuples(4).toInt).foreach(x=>print(x+" "))
    println
  }

  def answerTeleportQ(line:String,graph: Graph, teleportExpert:TeleportExpertSystem): Unit ={
    val tuples = line.trim.substring("can I teleport from ".length).split("to").map(_.trim)
    println(line +"?")
    println("The answer is: " + teleportExpert.twoCitiesConnected(graph,tuples(0),tuples(1)))
  }

  def answerLoopQ(line:String,graph: Graph, teleportExpert:TeleportExpertSystem): Unit ={
    val city = line.trim.substring("loop possible from ".length).trim
    println(line +"?")
    println("The answer is: " + teleportExpert.isThereALoop(graph,city))

  }

  def main(args: Array[String]) {
    if(args.length < 1){
      println("Usage: TeleportApp commandFileName")
      return
    }
    args.foreach(println)

    val teleportExpert = new TeleportExpertSystem


    val filename = args(0)
    val inputSource = Source.fromFile(filename)
    var graph = new Graph

    for (line <- inputSource.getLines) {
      if(line.trim.indexOf("cities from") > -1){
        answerCityJumpsQ(line,graph,teleportExpert)
      } else if (line.trim.indexOf("can I teleport") > -1){
        answerTeleportQ(line,graph,teleportExpert)
      } else if (line.trim.indexOf("loop possible") > -1){
        answerLoopQ(line,graph,teleportExpert)
      } else {
        addEntryToGraph(ctrim(line),graph)
      }
    }
  }
}
