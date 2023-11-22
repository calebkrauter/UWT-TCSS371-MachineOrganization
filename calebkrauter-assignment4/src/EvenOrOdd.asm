; In a loop, prompt the user to enter a number 1 to 9 or 0 to quit.
; Echo the input to the monitor
; Report if the entered number is even or odd
; Quit when 0 is entered
; Print "Have a nice day!"

        .ORIG        x3000
                                   ; keep in mind that IO commands use R0
        AND          R1, R1, #0    ; clear R1
        AND          R2, R2, #0    ; clear R2
        AND          R3, R3, #0    ; clear R3
        
        LD           R1, Zero      ; store Zero in R1
        NOT          R1, R1        ; store the complement of Zero in R1
        ADD          R1, R1, #1    ; the complement of Zero is used to test when to quit the loop
        
Loop    LEA          R0, Prompt    ; load the start address of the prompt
        PUTS                       ; display the prompt
        GETC                       ; read a char into R0
        OUT                        ; write a char from R0 to the monitor
        
        ADD          R2, R1, R0    ; add the user input and the complement of Zero
        AND          R3, R0, #1    ; mask the least significant bit to decide even or odd
        BRp          Odd           ; if the number is odd jump to the 'odd' label
        
        LEA          R0, EvenStr   ; load 'even'
        PUTS                       ; display the 'even' message
        BR           EndTest       ; skip the 'odd' output; go to the loop test
        
Odd     LEA          R0, OddStr    ; load 'odd'
        PUTS                       ; display the 'odd' message
        
EndTest ADD          R2, R2, #0    ; set CC for loop test
        BRnp         Loop          ; if the sum is zero then we quit the loop
        LEA          R0, EndMsg    ; load the end message
        PUTS                       ; display the end message
        
        HALT
Prompt  .STRINGZ     "\nEnter a number 1 to 9 or 0 to quit: "
EvenStr .STRINGZ     "\nEven"
OddStr  .STRINGZ     "\nOdd"
EndMsg  .STRINGZ     "\nDONE"
Zero    .FILL        x0030         ; hex value for ASCII character 0
        .END