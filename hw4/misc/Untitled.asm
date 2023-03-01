; Example of conditional code in LC-3 assembly code.

; Label         Opcodes         Operands        ; Comments
                .ORIG           x3000        
                
                AND             R1, R1, #0
                AND             R2, R2, #0
               
                ADD             R1, R1, #5     ; R1 <- 5 ; Try with R1 <- -5 to see how the branch statements work, also try 0
                ADD             R2, R2, #-13    ; R2 <- -13
                
                ADD             R1, R1, #0      ; Set a condition to test if R1 > 0
                BRnz            #2              ; Could use labels instead.
                ADD             R1, R1, #1      ; if R1 > 0 the R1 <- R1 + 1
                ;BR              #1
                BR              Done
                Add             R1, R1, #-1     ; else R1 <- R1 - 1
                
Done            HALT
                .END
