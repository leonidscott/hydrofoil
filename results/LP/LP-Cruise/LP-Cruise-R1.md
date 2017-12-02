# LP-Cruise-R1  
Lift-Priority Scoring, Cruise Velocity, Run 1: 3,000,000 generations.  
### Run Constants  
| V-Infinity (V∞) | Density (ρ) | angle-of-attack (AOT) |
|-----------------|-------------|----------------------|
| 135.833 m/s     | 0.7708kg/m^3| 2.1 degrees          |
### Wing Characteristics  
| Span   | Root-Chord | Min-Lift |
|--------|------------|----------|
| 11.24m | 2.67       | 27585N   |  
### Highest Scoring Individual  
**Standard NACA:** (NACA: 0.01, 4.42, 13)    
**Generation:** 416  

| Lift         | Drag        | Cl-2D         |CL-3D       |CD-Induced    |Score|
|--------------|------------ |---------------|------------|--------------|-----|
| 29016.04974N | 298.3259054N| 0.2314744681  |0.1731281632|0.001780001637|999701.6741|

![LP-Cruise-R1-416](LP-Cruise-R1-img/LP-C-Run1-Gen416.png)  

### Progress Over Time  

![Lift-Drag-Score-v-generation](https://docs.google.com/spreadsheets/d/e/2PACX-1vQKV1nUy34pAK1hBfeMi6O0fca8bsVAJj3MrwEwggENrnKN6_fPiUeUYXARFr-zEB7ZQo6unL05EYaf/pubchart?oid=924398028&format=image)

![cl-2D, CL-3D, CD-I vs Generation](https://docs.google.com/spreadsheets/d/e/2PACX-1vQKV1nUy34pAK1hBfeMi6O0fca8bsVAJj3MrwEwggENrnKN6_fPiUeUYXARFr-zEB7ZQo6unL05EYaf/pubchart?oid=938597983&format=image)

![C-M, C-P vs Gen](https://docs.google.com/spreadsheets/d/e/2PACX-1vQKV1nUy34pAK1hBfeMi6O0fca8bsVAJj3MrwEwggENrnKN6_fPiUeUYXARFr-zEB7ZQo6unL05EYaf/pubchart?oid=958500200&format=image)  

**Number of Successful Generations:** 4  

![LP-Cruise-R1-Gif](https://media.giphy.com/media/3o6fIXN6HrkoftCkow/giphy.gif)  

### Raw Data  
```CSV
Run 1: Cruise Speed | V-infity: 135.833 m/s | p = 0.7708kg/m^3 | a = 2.1

:thickness , 23.11 , :generation , 416 , :positon-camber , 4.42 , :CD-Induced , 0.0017800016366885042 , :Lift , 29016.049742250358 , :corrected-thickness , 0.2311 , :score , 999701.6740946337 , :cl-2D , 0.23147446807653918 , :Drag , 298.325905366371 , :corrected-position-camber , 0.442 , :corrected-max-camber , 1.0E-4 , :max-camber , 0.01 , :CL-3D , 0.1731281632012867 ,
:thickness , 14.899999999999999 , :generation , 20 , :positon-camber , 4.365222963832473 , :CD-Induced , 0.0029160316663137532 , :Lift , 37138.48677113836 , :corrected-thickness , 0.149 , :score , 999511.2769735715 , :cl-2D , 0.24206575761630453 , :Drag , 488.7230264284824 , :corrected-position-camber , 0.43652229638324724 , :corrected-max-camber , 0.001 , :max-camber , 0.1 , :CL-3D , 0.2215917761334734 ,
:thickness , 19.83 , :generation , 7 , :positon-camber , 5.425741214243626 , :CD-Induced , 0.019001963392138947 , :Lift , 94804.14477073874 , :corrected-thickness , 0.19829999999999998 , :score , 996815.2962245335 , :cl-2D , 0.31725916055715425 , :Drag , 3184.703775466534 , :corrected-position-camber , 0.5425741214243626 , :corrected-max-camber , 0.0066 , :max-camber , 0.66 , :CL-3D , 0.5656616801330976 ,
:thickness , 21.7 , :generation , 2 , :positon-camber , 4.32 , :CD-Induced , 0.5180386404733199 , :Lift , 495004.5190299399 , :corrected-thickness , 0.217 , :score , 913177.4132963833 , :cl-2D , 0.8391022998300902 , :Drag , 86822.5867036166 , :corrected-position-camber , 0.43200000000000005 , :corrected-max-camber , 0.0519 , :max-camber , 5.19 , :CL-3D , 2.9535110367281647 , 
```
