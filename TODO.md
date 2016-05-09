#TODO
##Latest Progeress:
* Added clojure docs to `thickness-function`, `camber-function`,
... `gradient-function`, and `upper-surface-x-function`
* Got testing framwork done. There are two different ways of running tests:
  * `lein autoexpect` will re run tests everytime you save
  * `lein test` will run all tests the way you would expect
  * **NOTE:** the testing framwork will break if there is any syntaxtical error anywhere in your code,
not just the classes you are testing.
* Got `thickness-function` tested
  * **NOTE:** It does not work when `x = 1`
* Re-wrote `camber-function` to take an individual
  * **NOTE:** Use lets to define M, P, and T
* **Using Simpson's rule with 90 particians**

##Bug Report
* **It seems as double values that are negative consistanty have rounding error**
* Rounding error in gradient-function:
  * *M = 4.5 | P = .0001| T = 12 | x = 0.64*
  * Expected: -0.0576, Was: -0.0575
* Systematic rounding error in lower-surface-y-function:
  * *M = 0 | P = 0.5 | T = 20 | x = 0.5:*
  * Expected: -0.0881, Was: -0.088
  * *M = 4.75 | P = 4.5 | T = 20| x = 0.5*
  * Expected: -0.041, Was: -0.0409
  * *M = 9.5 | P = 9| T = 40| x = 0.5*
  * Expected: -0.0992, Was: -0.0991

##Model
1. More Integration Methods Testing
  * Try more function cases to narrow in partican counts
  * Time results
2. Implement Derivatives for Area Function
3. Test Area Function
4. Test coefficeant of lift function
5. Test Lift function

##Evolution
##Core
