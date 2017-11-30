#!/bin/bash

echo Linear Prioritizing Model LP > LinearPrioritizingDiagnostics.txt
cd ../hydrofoil 
pwd >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo ------LP Max Speed Tests----- >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "Run 1: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 1: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1" > ../hydrofoil-tests/LP-Max-R1.txt
echo "" >> ../hydrofoil-tests/LP-Max-R1.txt
nice -5 lein run 0.6805 163.17 2.1 >> ../hydrofoil-tests/LP-Max-R1.txt
echo "Run 1: Max Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 2: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 2: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1" > ../hydrofoil-tests/LP-Max-R2.txt
echo "" >> ../hydrofoil-tests/LP-Max-R2.txt
nice -5 lein run 0.6805 163.17 2.1 >> ../hydrofoil-tests/LP-Max-R2.txt
echo "Run 2: Max Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 3: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 3: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1" > ../hydrofoil-tests/LP-Max-R3.txt
echo "" >> ../hydrofoil-tests/LP-Max-R3.txt
nice -5 lein run 0.6805 163.17 2.1 >> ../hydrofoil-tests/LP-Max-R3.txt
echo "Run 3: Max Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo ------LP Max Cruise Tests----- >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "Run 1: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 1: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests/LP-Cruise-R1.txt
echo "" >> ../hydrofoil-tests/LP-Cruise-R1.txt
nice -5 lein run 0.7708 135.83 2.1 >> ../hydrofoil-tests/LP-Cruise-R1.txt
echo "Run 1: Cruise Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 2: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 2: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests/LP-Cruise-R2.txt
echo "" >> ../hydrofoil-tests/LP-Cruise-R2.txt
nice -5 lein run 0.7708 135.83 2.1 >> ../hydrofoil-tests/LP-Cruise-R2.txt
echo "Run 2: Cruise Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 3: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 3: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests/LP-Cruise-R3.txt
echo "" >> ../hydrofoil-tests/LP-Cruise-R3.txt
nice -5 lein run 0.7708 135.83 2.1 >> ../hydrofoil-tests/LP-Cruise-R3.txt
echo "Run 3: Cruise Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo ------LP Max Slow Tests----- >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "Run 1: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 1: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests/LP-Slow-R1.txt
echo "" >> ../hydrofoil-tests/LP-Slow-R1.txt
nice -5 lein run 0.7708 30 2.1 >> ../hydrofoil-tests/LP-Slow-R1.txt
echo "Run 1: Slow Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 2: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 2: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests/LP-Slow-R2.txt
echo "" >> ../hydrofoil-tests/LP-Slow-R2.txt
nice -5 lein run 0.7708 30 2.1 >> ../hydrofoil-tests/LP-Slow-R2.txt
echo "Run 2: Slow Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 3: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1 | date: " `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo "Run 3: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1" > ../hydrofoil-tests/LP-Slow-R3.txt
echo "" >> ../hydrofoil-tests/LP-Slow-R3.txt
nice -5 lein run 0.7708 30 2.1 >> ../hydrofoil-tests/LP-Slow-R3.txt
echo "Run 3: Slow Speed: Complete" `date` >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt

echo "" >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
echo ------LP Tests Complete----- >> ../hydrofoil-tests/LinearPrioritizingDiagnostics.txt
