# CampusPermit
This is a relatively simple simulation of a permit generator for a campus parking permit


## Errors
Any errors found within the system's self unit testing system will prevent the application from running, as the unit tests are all internal to the code itself, it can only be resolved by recompiling the code (a design choice on my part). However, in the event that the unit testing system is the reason for the application not running (this would occur before the "welcome to the campus permit" would even print) please notify me in order to fix the unit tests, so that the system will run.

## Design
### Preface:
I made a few creative changes to the original system design in order to make the most efficent programming (least amount of effort on my part). However, in the process I realized that some of the choices I had made did not make sense. ResidentPermit and CommuterPermit should have both been a Permit, not individual classes because the code for each is copy paste with mabye 3 lines different between the two. I realized this will copying and pasting and thinking that there is definitely a better solution. However, I can to the conclusion that it would have been better to just make Permit an abstract class, rather than an interface, thereby reducing the amount of copying and pasting to virtually zero. From there I came to realize that the only difference between the resident permit and the commuter permit is the base price. Therefore, why even have a commuter class and a resident class? Just have a permit non-abstract class that has an enumerator for the base price (as the commuter price of $35 at %15 off is in essence actually just $29 and change). However to stick to the intended design of the applicatiion I decied that leaving the implementation as is (despite the fact that changing how permits calculate their price would require changing two classes. Annoying to say the least).

### Design Flow:
The classes are seperated as following:

Package "unitTests"
- StandardUnitTests.java
- UnitTest.java

Package "utils"
- OutOfRangeException.java
- Range.java
    
Package "wkeever.campus.permit"
- Permit.java
- ResidentPermit.java
- CommuterPermit.java
- Receipt.java
- Main.java

The Depedencies are as follows:

StandardUnitTests.java
- UnitTest.java

UnitTest.java
- Permit.java

OutOfRangeException.java
- Range.java
    
Permit.java
- ResidentPermit.java
- CommuterPermit.java
- OutOfRangeException.java
- Range.java

Receipt.java
- Permit.java

Main.java
- StandardUnitTests.java
- UnitTest.java
- OutOfRangeException.java
- Range.java
- Permit.java
- ResidentPermit.java
- CommuterPermit.java
- Receipt.java

When it comes to my design choices, firstly I decided upon three packages to quickly organize the types of files I was going to use. I mostly came up with the categories on the fly; However, I was intentional in putting Range and OutOfRangeException together in their own seperate package, and simply because these would be something that could be found in a utility libraray rather than program specific. When I thought about naming the package util, I thought that these classes would be better suited to be in a utility library/project that would have been imported into the project rather than me programming them on the fly. When it came to the unit tests, I also felt similar about these. I felt that they were not necessary to the prgram themsleves, even though they do depend on the prgram. Specifically, I felt that the unit tests shouldn't be in the main package because the prgram itself does not need them to run (although I did force in main the unit tests to be used and they determined whether the program ran or not. However, in the indeal world, these would have been a "debug" feature and only seen if the program was in "debug mode"). 

For the dependencies, I decided on the least number of dependencies I could get away with. Since the main uses all of the files, the main class depends on them all. This cannot be avoided, and is necessary. For the permit interface itself, it has no knowlege of commuter permit, resident permit, out of range excpetion or range, but because its subclasses use range and out of range exception I thought it was necessary to show that all of those classes depend on each other in a way. (Commuter/Resident permit uses range and out of range exception) and Permit is the superclass/interface of commuter and resident permit. Unittest does depend on the permit and that could not be avoided. By default because it depends on permit, it depends on all of the things permit depends on. StandardUnitTests is just the static holder of all unit tests, technically this should just be a file reader and then evaluate the tests themselves, since hard coding the tests doesn't make the most sense. Finally, receipt very very much requires permit as it is the thing that collects all of the permit data and turns it into a comphresive string. Because it takes the reference of the permit and doesn't do any operations upon it (besides casting to get the right information from the subclasses), it does not depend on range and out of range exception, either the permit was constructed or not, and by the time the permit is being passed as a refernce it should already be constructed.

Overall, I would say my design was decent. In the aspect of being extendable, there are some serious downsides to the way permit was constructed, and although permit sets up all the necessary information for resident and commuter permit to be created, it fails to provide the shared code and shared variables that each need (whereas an abstract class could easily do so). Additionally, since receipt is directly dependent on there only being commuter and resident permits, this further inhibits the ability of a new type of permit being created. An annoying but manageable fix.

Now why are the variables in resident and commuter permit public final? Well it was easier to do that for both classes than it was to copy paste a bunch of getters. And since they can only be changed by the constructor, it made sense to make them final. However, I significantly dislike the fact that they are public. Usually I avoid public varaibles (unless they are class constants) in all cases (even though I will supply a getter and a setter), as a personal programmer preference. 
