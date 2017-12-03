#!/bin/bash

#Setup Diagnostics.txt
echo Drag-Priority Model > Drag-PriorityDiagnostics.txt
cd ../hydrofoil
pwd >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo ------DP Max Speed Tests----- >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "Run 1: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 1: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/DP-Max-R1.txt
echo "" >> ../drag-priority-scoring/DP-Max-R1.txt
nice -5 lein run 0.6805 163.17 2.1 7769.1>> ../drag-priority-scoring/DP-Max-R1.txt
echo "Run 1: Max Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 2: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 2: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/DP-Max-R2.txt
echo "" >> ../drag-priority-scoring/DP-Max-R2.txt
nice -5 lein run 0.6805 163.17 2.1 7769.1>> ../drag-priority-scoring/DP-Max-R2.txt
echo "Run 2: Max Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 3: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 3: Max Speed| V-infity: 163.17m/s| p = 0.6805kg/m^3| a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/DP-Max-R3.txt
echo "" >> ../drag-priority-scoring/DP-Max-R3.txt
nice -5 lein run 0.6805 163.17 2.1 7769.1>> ../drag-priority-scoring/DP-Max-R3.txt
echo "Run 3: Max Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo ------DP Max Cruise Tests----- >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "Run 1: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 1: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/DP-Cruise-R1.txt
echo "" >> ../drag-priority-scoring/DP-Cruise-R1.txt
nice -5 lein run 0.7708 135.83 2.1 9332.71>> ../drag-priority-scoring/DP-Cruise-R1.txt
echo "Run 1: Cruise Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 2: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 2: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/DP-Cruise-R2.txt
echo "" >> ../drag-priority-scoring/DP-Cruise-R2.txt
nice -5 lein run 0.7708 135.83 2.1 9332.71>> ../drag-priority-scoring/DP-Cruise-R2.txt
echo "Run 2: Cruise Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 3: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 3: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/DP-Cruise-R3.txt
echo "" >> ../drag-priority-scoring/DP-Cruise-R3.txt
nice -5 lein run 0.7708 135.83 2.1 9332.71>> ../drag-priority-scoring/DP-Cruise-R3.txt
echo "Run 3: Cruise Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo ------DP Max Slow Tests----- >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "Run 1: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 1: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/DP-Slow-R1.txt
echo "" >> ../drag-priority-scoring/DP-Slow-R1.txt
nice -5 lein run 0.7708 30 2.1 42256.333>> ../drag-priority-scoring/DP-Slow-R1.txt
echo "Run 1: Slow Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 2: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 2: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/DP-Slow-R2.txt
echo "" >> ../drag-priority-scoring/DP-Slow-R2.txt
nice -5 lein run 0.7708 30 2.1 42256.333>> ../drag-priority-scoring/DP-Slow-R2.txt
echo "Run 2: Slow Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 3: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N| date: " `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo "Run 3: Slow Speed | V-infity: 30 m/s | p = 0.7708kg/m^3 | a = 2.1| max-drag = 7759.1N" > ../drag-priority-scoring/LC-27-Slow-R3.txt
echo "" >> ../drag-priority-scoring/LC-27-Slow-R3.txt
nice -5 lein run 0.7708 30 2.1 42256.333>> ../drag-priority-scoring/LC-27-Slow-R3.txt
echo "Run 3: Slow Speed: Complete" `date` >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt

echo "" >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
echo ------DP Tests Complete----- >> ../drag-priority-scoring/Drag-PriorityDiagnostics.txt
