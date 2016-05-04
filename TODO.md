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

##Model
1. ~~Test `thickness-function`~~
2. ~~Test `camber-function`~~
3. Test `gradient-function`
4. ~~Figure out atan~~
5. ~~Finish writing `upper-surface-x-function`~~
6. Test `upper-surface-x-function`
7. ~~Write `upper-surface-y-function`~~
8. Test `upper-surface-y-function`
9. ~~Write `lower-surface-x-function`~~
10. Test `lower-surface-x-function`
11. ~~Write `lower-surface-y-function`~~
12. Test `lower-surface-y-function`
13. ~~Change all functions to take an NACA Design and an x~~
   * `thickness-function` finished
   * `camber-function` finished
   * `gradient-function` finished
14. Figure out how to use Reimond Sum program from java
15. Build Area Function
16. Test CenterBox, Trapazoid, and Simpson Approaches to approximate integration
17. Coefficient of lift stuff

##Evolution
##Core
