#!/bin/bash

echo Linear Combination Model LC27 > LinearCombination27Diagnostics.txt
cd ../hydrofoil-LC27 
pwd >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo ------LC27 Max Speed Tests----- >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "Run 1: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 1: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1" > ../hydrofoil-tests-LC27/LC27-Max-R1.txt
echo "" >> ../hydrofoil-tests-LC27/LC27-Max-R1.txt
nice -5 lein run 0.6805 163.17 2.1 >> ../hydrofoil-tests-LC27/LC27-Max-R1.txt
echo "Run 1: Max Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 2: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 2: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1" > ../hydrofoil-tests-LC27/LC27-Max-R2.txt
echo "" >> ../hydrofoil-tests-LC27/LC27-Max-R2.txt
nice -5 lein run 0.6805 163.17 2.1 >> ../hydrofoil-tests-LC27/LC27-Max-R2.txt
echo "Run 2: Max Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 3: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 3: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1" > ../hydrofoil-tests-LC27/LC27-Max-R3.txt
echo "" >> ../hydrofoil-tests-LC27/LC27-Max-R3.txt
nice -5 lein run 0.6805 163.17 2.1 >> ../hydrofoil-tests-LC27/LC27-Max-R3.txt
echo "Run 3: Max Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo ------LC27 Max Cruise Tests----- >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "Run 1: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 1: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests-LC27/LC27-Cruise-R1.txt
echo "" >> ../hydrofoil-tests-LC27/LC27-Cruise-R1.txt
nice -5 lein run 0.7708 135.83 2.1 >> ../hydrofoil-tests-LC27/LC27-Cruise-R1.txt
echo "Run 1: Cruise Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 2: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 2: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests-LC27/LC27-Cruise-R2.txt
echo "" >> ../hydrofoil-tests-LC27/LC27-Cruise-R2.txt
nice -5 lein run 0.7708 135.83 2.1 >> ../hydrofoil-tests-LC27/LC27-Cruise-R2.txt
echo "Run 2: Cruise Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 3: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 3: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests-LC27/LC27-Cruise-R3.txt
echo "" >> ../hydrofoil-tests-LC27/LC27-Cruise-R3.txt
nice -5 lein run 0.7708 135.83 2.1 >> ../hydrofoil-tests-LC27/LC27-Cruise-R3.txt
echo "Run 3: Cruise Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo ------LC27 Max Slow Tests----- >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "Run 1: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 1: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests-LC27/LC27-Slow-R1.txt
echo "" >> ../hydrofoil-tests-LC27/LC27-Slow-R1.txt
nice -5 lein run 0.7708 30 2.1 >> ../hydrofoil-tests-LC27/LC27-Slow-R1.txt
echo "Run 1: Slow Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 2: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 2: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests-LC27/LC27-Slow-R2.txt
echo "" >> ../hydrofoil-tests-LC27/LC27-Slow-R2.txt
nice -5 lein run 0.7708 30 2.1 >> ../hydrofoil-tests-LC27/LC27-Slow-R2.txt
echo "Run 2: Slow Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 3: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo "Run 3: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests-LC27/LC-27-Slow-R3.txt
echo "" >> ../hydrofoil-tests-LC27/LC-27-Slow-R3.txt
nice -5 lein run 0.7708 30 2.1 >> ../hydrofoil-tests-LC27/LC-27-Slow-R3.txt
echo "Run 3: Slow Speed: Complete" `date` >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt

echo "" >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
echo ------LC27 Tests Complete----- >> ../hydrofoil-tests-LC27/LinearCombination27Diagnostics.txt
