Random Sort
===========

The solution to a coding exercise given as part of a recruitment process.

The Task (summarised from the original Swedish)
-----------------------------------------------
* Write a sort routine that takes an arbitrary length list of random integers and sorts them by randomly swapping two elements in the list until the list has reached a sorted state (ascending values).
* Write an awesome web page that allows the initial list to be entered and the sort routine to be triggered.
* The results of each sort should persist between executions of the application.
* Recent results should be displayed on the web page.

This task was estimated to take 1 to 2 hours.  I spent just over 4 hours.

Improvements
------------
As I was already over the 'expected time', there were a few things left undone:
* Add error handling to the Javascript
* Add tests for the Javascript
* Clean up the UX
* Revist the Java test coverage / quality
* General review of the Java to ensure quality

Technology
----------
* Spring Boot
* Java 8
* AngularJS 1.4

Compiling the Solution
----------------------

From the directory containing this file, type:

    mvn clean package

Running the Solution
--------------------

From the directory containing this file, type:

    mvn spring-boot:run

The web ui can be accessed via a browser at

    http://localhost:8080
