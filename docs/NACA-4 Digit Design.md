#NACA-4 Digit Design   
This should hopefully be a good guid to 4 Digit NACA designs. 
The NACA series was a system created by NACA (National Advisory Committee for Aeronautics) to classify foils by a set of numbers.
Classifying them is nice because you can standerdize foil shapes. NACA created a bunch of different foil Series:
the 4 series, the 5 series, ... , all the way up to the 8 series. We are starting out with the 4 series because they are the simplest, 
have only a few constants to evolve, and are easiest to test. 
## Resources 
* [NACA airfoil wikipedia] (https://en.wikipedia.org/wiki/NACA_airfoil)There's no better place to start than wikipedia
* [NACA airfoil series pdf](http://people.clarkson.edu/~pmarzocc/AE429/The%20NACA%20airfoil%20series.pdf) A more formal description of the NACA foil series, also describes a bit more of the terminoligy around what a foil is.
* [NACA 4 digit airfoil generator] (http://airfoiltools.com/airfoil/naca4digit) My go to reference. It allows you to play with the Camber, Camber Position, and thickness to see how they effect the foil shape. It also shows the math of how you turn the digits into a foil cross-section, but lacks a bit on some of the context around that math. 

## Backgound 
A foil has a set of terminology that is used to describe its shape. 

![initial state](img/hydrofoil.png)

In the picture above, there is a red dotted line, this is the *chord line* or chord. The chord line connects the leading edge to the trailing edge. Next there is the dotted blue line, this is the *camber line*, also called the *mean camber line*. This line runs through the foil such that at any givin point on the camber line, the upper and lower surfaces are the same distance from it. Finally, you will notice that the thickness increases and decreases around the camber line at a certain rate. These are the characterstics of the foil that the NACA 4 Digit Series are built from 

## Nitty Gritties 
A NACA 4 Digit foil, is called a NACA 4 Digit foil, the shape of the foil can be generated from 4 digits. A NACA 4 Digit foil is recognisable by the word **NACA** and the four digits that follow. The following shows how the NACA 4 Digits are broken down.
# **NACA MPXX**   
* M is the maximum camber
* P is the position of the maximum camber
* XX is the thickness of the foil 

(Its worth noting that 4 Digit NACA Series actually only has three numbers :laughing:)  
***

#### `M: The Maximum Camber`  
This basically controlls how arched the foil is. The number is a percentage of the lenght of the chord, so if the Maximum Camber is 5, that means that the highest point of the mean camber line is 5% the length of the chord.   
#### `P: The Maximum Camber Position`  
This is where the Maximum Camber will lay with respect to the chord line. The Maximum Camber Position is inputed as a percentage of the chord line, so if the Maximum Camber Position was 50, the Maximum Camber would be 50% of the way down the chord. 
#### `XX: Foil Thickness`  
This is a bit of magic. This number is a percentage of the chord.


