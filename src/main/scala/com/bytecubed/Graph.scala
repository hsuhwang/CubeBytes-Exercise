package com.bytecubed

import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, MutableList}


/**
  * Created by hhwang on 5/2/18.
  */
class Graph {
    var adjacencyList:HashMap[String,ListBuffer[String]] = HashMap.empty[String,ListBuffer[String]]
    /** undirected graph **/
    def addEntries = (city:String,neighborCity:String) => {
        addToAdjacentList(city,neighborCity)
        addToAdjacentList(neighborCity,city)
    }
    def addToAdjacentList = (city:String,neighborCity:String) => {
        var neighborVertices: ListBuffer[String] = adjacencyList.getOrElse(city,ListBuffer.empty[String])
        neighborVertices += neighborCity
        adjacencyList += city -> neighborVertices
    }
}
