; A first assembly code example
;
; Multiply two numbers (using a do/while loop)

;label       opcode       operands      ; comments

             .ORIG        x3000         ; place the program at address x3000
             
             AND          R1,R1,#0      ; clear R1
             AND          R2,R2,#0      ; clear R2
             AND          R3,R3,#0      ; clear R3
             
             LD           R1, NumberA   ; sum
             LD           R2, NumberB   ; loop counter
             
Loop         ADD          R3,R3,R1      ; add R1 to the sum
             ADD          R2,R2,#-1     ; decrement the counter
             LEA          R0, NumberA
             PUTS
             BRp          Loop 
             
             HALT 
NumberA      .FILL        #6
NumberB      .FILL        #3
             .END