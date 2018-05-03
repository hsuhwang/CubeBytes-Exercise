package com.bytecubed


import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by hhwang on 5/2/18.
  * Assumption there is no loop around a node
  */
class TeleportExpertSystem {


    /**
      *  get the colletion of cities with N jump to the startingCity.
      *  N >=1
      *
      */

    def getCitiesWithinNJump(graph: Graph, startingCity: String, N: Int): Set[String] = {
        var cityList = ListBuffer.empty[String]
        getAllCitiesWithinNJump(graph, startingCity, N, cityList)
        var citySet = cityList.toSet
        citySet -= startingCity
        citySet
    }

    private def getAllCitiesWithinNJump(graph: Graph, startingCity: String, n: Int, cityList: ListBuffer[String]): Unit = {
        var adjCities: ListBuffer[String] = graph.adjacencyList.getOrElse(startingCity, ListBuffer.empty[String])
        cityList ++= adjCities
        for (adjCity <- adjCities) {
            if (n - 1 > 0) {
                getAllCitiesWithinNJump(graph, adjCity, n - 1, cityList)
            }
        }
    }


    /**
      *  use depth-first algorithm to detect a loop from the goalCity
      */

    def isThereALoop(graphOfCities: Graph, goalCity: String): Boolean = {
        var isThereARoute = false
        var searchStack = new mutable.Stack[String]
        var visitedCityList = ListBuffer.empty[String]
        searchStack.push(goalCity)

        while (!searchStack.isEmpty) {
            val currentCity = searchStack.pop()
            if (!visitedCityList.contains(currentCity)) {

                visitedCityList += currentCity

                var adjCities: ListBuffer[String] = graphOfCities.adjacencyList.getOrElse(currentCity, ListBuffer.empty[String])
                for (adjCity <- adjCities) {
                    if (!visitedCityList.contains(adjCity) && !searchStack.contains(adjCity)) {
                        searchStack.push(adjCity)
                    }
                    if(isBackEdge(goalCity,currentCity,adjCity,visitedCityList,graphOfCities)){
                        return true
                    }
                }
            }
        }
        isThereARoute
    }

    /**
      * visitingCity -> goalCity is a backedge if and only if
      * goalCity is not a parent of visitingCity in sapnning tree
      * of the connected component of the graph containing the goalcity)
      *
      */

    private def isBackEdge (goalCity:String,currentCity:String,adjacentCity:String, visitedCityList:ListBuffer[String], graph:Graph):Boolean = {
        var isBackEdge = false
        if(adjacentCity != goalCity) return false

            for(visitedCity <- visitedCityList){
                if (visitedCity != goalCity){
                    isBackEdge = graph.adjacencyList.getOrElse(visitedCity, ListBuffer.empty[String]).contains(currentCity)
                    if(isBackEdge) return isBackEdge
                }
            }

        isBackEdge
    }


    /**
      * use bread-first algorithm to get the shortest path from A to B
      * if there is any
      */

    def twoCitiesConnected(graph: Graph, city: String, cityB: String): Boolean = {
        var connected = false
        var searchedQueue = new mutable.Queue[String]
        var visitedCityList = new mutable.HashSet[String]
        visitedCityList += city
        searchedQueue.enqueue(city)

        while (!searchedQueue.isEmpty) {
            val visitingCity = searchedQueue.dequeue()
            val adjCities: ListBuffer[String] = graph.adjacencyList.getOrElse(visitingCity, ListBuffer.empty[String])
            for (adjCity <- adjCities) {
                if (!visitedCityList.contains(adjCity)) {
                    visitedCityList += adjCity
                    searchedQueue.enqueue(adjCity)
                }

                if (adjCity == cityB) {
                    connected = true
                    return connected
                }
            }
        }
        connected
    }

}
