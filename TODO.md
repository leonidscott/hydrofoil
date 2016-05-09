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

##Bug Report
* **It seems as double values that are negative, consistanty have rounding error**
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
1. ~~Fix `upper-surface-x-function`~~
2. ~~Fix `lower-surface-x-function`~~
3. ~~Fix `lower-surface-y-function`~~
4. ~~Test `upper-surface-x-function`~~
5. ~~Test `lower-surface-x-function`~~
6. ~~Test `lower-surface-y-function`~~
7. Test `left-rule`
8. Test `right-rule`
9. Test `middle-rule`
10. Test `trapazoid rule`
11. Test `simpson's rule`
12. Build Area Function
13. Coefficient of lift stuff

##Evolution
##Core
