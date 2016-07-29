#model.clj API 
`model.clj` is a class that will take a four digit NACA foil and the environment that foil will be preforming in and return
a lift force for that given foil. The function that starts everything in motion is called `(lift-function)`. It takes in an 
*individual* (the foil) and a set of *run constants* (all of the aspects of the environment that are plugged into the equation of lift). For this model. We are using *thin aerofoil theory* as our method of determining lift. It is the easiest model we have come across, so it is a good starting point for what we are doing. You can read a full discription of what [Thin Aerofoil Theory](https://github.com/morrislenny/hydrofoil/blob/master/docs/Thin%20Aerofoil%20Theory.md) is and how we use it.  

##Lift Functions  
#### `(lift-function individual run-constants)`  
This is the function that takes an NACA 4 Digit Foil, and derives from it, its area and coefficient of lift. The NACA 4 Digit Foil is refered to as an `individual` and is represented as a hashmap of *camber*, *camber posisition*, and *thinkness*. 
To see more on 4 Digit NACA foils, see the [NACA 4 Digit Guide](https://github.com/morrislenny/hydrofoil/blob/master/docs/NACA-4%20Digit%20Design.md).
It also recives, from the hashmap of run constants, its *fluid dencity*, *fluid velocity*, and foil *angle of attack*.
This function will call only two other functions `(coefficient-of-lift)` and `(area)`.
To get a better understanding of the equation in place, see the 
[Thin Aerofoil theory link](https://github.com/morrislenny/hydrofoil/blob/master/docs/Thin%20Aerofoil%20Theory.md). 
#### `(coefficient-of-lift individual run-constants)`
This function gets directly called from `(lift-function)`. This function, on its own, solves for the coefficient of lift. 
See the equation in the 
[Thin Aerofoil Theory guide](https://github.com/morrislenny/hydrofoil/blob/master/docs/Thin%20Aerofoil%20Theory.md). Again the *individual* and *run-constants* are hashmaps. 

## NACA 4 Digit Functions 
The equations that create the features of an NACA 4 Digit Foil live in this class. To understand what these functions represent, please see 
[NACA 4 Digit Design](https://github.com/morrislenny/hydrofoil/blob/master/docs/NACA-4%20Digit%20Design.md). 
#### `(thickness-function individual x)`  
This function computes the thickness function for an individual foil, individual, at a point x.   
#### `(camber-function individual x)`   
This function computes the hight of the camber position for an individual foil, individual, at a point x.  
#### `(gradient-function individual x)` 
This function computes the gradient-function (the derivative of the camber-function) for an individual foil, individual, at a point x.  
#### `(upper-surface-x-function individual x)`  
This function computes the upper surface x function for an individual foil, individual, at a point x.   
#### `(lower-surface-x-function individual x)`  
This function computes the lower surface x function for an individual foil, individual, at a point x. 
#### `(upper-surface-y-function individual x)`
This function computes the upper surface y function for an individual foil, individual, at a point x. 
#### `(lower-surface-y-function individual x)`  
This function computes the lower surface y function for an individual foil, individual, at a point x. 

## Area Functions 
Because the 4 Digit NACA Foil is a set of parametric equations, taking the integral is not as simple as finding the integral of the top of the foil minus the integral of the bottom. To find the area properly, we must multiply the y value of the upper or lower function at a certain point by the derivative of the x value of the upper or lower function. The integral in mathematica will look like this:  

![initial state](img/MMA Integral.png)

`upper[x][2]` is the upper function y coordinate and `upper[x][1]` is the x coordinate. The same goes for the lower functions. 
Unfortunatly, this integral is not solvable due to the complexity of its parematric equations, but it is possible to approximate using numerical integration. 

#### `(area individual)`

## Utility Functions
#### `(round-double n)`  
A very simple function that takes a double and rounds it to four decimle places which is the expected accuracy of this model.


