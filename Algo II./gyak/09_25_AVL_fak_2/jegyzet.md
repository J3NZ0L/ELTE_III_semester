                7
            5       10
        3          8    13
            4       9  11   15
                        12
remMin()
                7++
            5-      10+
        4          8    13
                    9  11   15
                        12

(++,+) forgatas

## Feladat
                            68
                        47      74
                    35    52    72  82
                21   38  50 56        78
            10      49        61

torles: 68
jobb reszfa legkisebb eleme. 72
remmin jobb reszfa utan kialakult reszfan egy (++,-) forgatas utan, bal reszfat bekenhagytuk
                68
                    78
                 74     82

fo fa -- lesz, --,= forgatas kell


                    47
            35..        68 -> utolso lepeskent 72re kicsereljuk
                     52..     74.. 


## Feladat
                        50
                30                56
        25        35         52         70 
    10    26        40        55      68    77  
                                           75
delete(56)
remmin(p->56)
minp->68

                    (56)
                52          70++
                  55            77
                              75

                (56)
            52            75
               55       70  77

utso lepes: 56 csere 68ra

                (68)!
            52            75
               55       70  77