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
* Rounding error in gradient-function:
  * M = 4.5 | P = .0001| T = 12| x = 0.64
  * Expected: -0.0576, Was: -0.0575

##Model
1. ~~Test `gradient-function`~~
2. Test `upper-surface-x-function`
3. Test `upper-surface-y-function`
4. Test `lower-surface-x-function`
5. Test `lower-surface-y-function`
6. Figure out how to use Reimond Sum program from java
7. Build Area Function
8. Test CenterBox, Trapazoid, and Simpson Approaches to approximate integration
9. Coefficient of lift stuff

##Evolution
##Core
