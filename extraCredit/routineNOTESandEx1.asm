; use JSR to jump to a subtroutine that you label (jump to its label)

; Create the subroutines after HALT

            .ORIG   x3000
            AND     R4, R4, #0
            ADD     R4, R4, #3
            JSR     PRINT

            JSR     ADD2
            JSR     PRINT
            
            JSR     ADD2
            JSR     PRINT
            
            
            HALT
            
            
ADD2
            ADD     R4, R4, #2
            RET
            
PRINT
            LD      R0, ZERO
            ADD     R0, R4, R0
            OUT     
            RET
            
ZERO        .FILL   #48
            .END