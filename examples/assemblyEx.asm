; A first assembly code example
; Multiply two numbers

; Get used too not using R0 and R7 since they are used by input/output routines

; Label     Opcode      Operands    ; Comments 

            .ORIG       X3000       ; Place the program at x3000
            
            AND         R1, R1, #0  ; Clear R1
            AND         R2, R2, #0  ; Clear R2
            AND         R3, R3, #0  ; Clear R3
            
            LD          R1, NumberA ; 
            LD          R2, NumberB ; Loop counter
            
Loop        BRz         Done
            ADD         R3, R3, R1  ; Add R1 to sum
            ADD         R2, R2, #-1 ; Decrement loop counter
            BR          Loop ; While R2 is not 0, branch to top of loop (BRn/BR/z/BRp)
Done        ST          R3, Result
            HALT ; Program stops running at HALT but during assembly the next lines still are used.
NumberA     .FILL       #6
NumberB     .FILL       #3
Result      .FILL       #0
            .END ; END of program
