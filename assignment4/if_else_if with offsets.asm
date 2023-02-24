; A third example LC-3 assembly language program.
;
; Implement if/else logic.
;
; if R1 > 0 the increment R1
; else if R1 < 0 decrement R1
;
; We can use hex values x???? or decimal values #???? to indicate offsets for branches
; In this example I use hex values
;
          .ORIG     x3000
;          
          ADD       R1, R1, #0
          BRz       x0004          ; if R1 is zero do not increment and do not decrement
; if R1 > 0 then increment R1
          BRn       x0002          ; if R1 is < zero then branch to the decrement part
          ADD       R1, R1, #1     ; R1 must have been positive, so increment
          BR        x0001          ; branch past the decrement
; else if R1 < 0 then decrement R1
          ADD       R1, R1, #-1    ; R1 < zero, so decrement
;          
          HALT

          .END