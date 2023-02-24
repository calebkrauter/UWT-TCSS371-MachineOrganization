; A fourth example LC-3 assembly language program.
;
; Implement if/else if logic.
; This example uses labels instead of fofsets in the branch commands.
;
; if R1 > 0 the increment R1
; else if R1 < 0 decrement R1
; (else do nothing if R1 equls zero)
;
          .ORIG     x3000
;          
          ADD       R1, R1, #0
          BRz       Done            ; If R1 was zero the branch to the end of the program
; if R1 > 0 then increment R1
          BRn       Decr            ; If R1 was < zero then branch to the decrement part
          ADD       R1, R1, #1      ; R1 must have been > zero, so increment
          BR        Done            ; branch past the decrement part
; else if R1 < 0 then decrement R1
Decr      ADD       R1, R1, #-1
;          
Done      HALT

          .END