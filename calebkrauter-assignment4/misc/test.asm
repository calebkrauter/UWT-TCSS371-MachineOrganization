                .ORIG               x3000               ; Start at orgin x3000
                LD                  R1, #0
                LD                  R2, #-10
                
Loop            ADD                 R1, R1, #1
                ADD                 R0, R0, R1
                PUTS
                OUT
                ADD                 R3, R2, R1
                BR                  Loop

                HALT
                .END