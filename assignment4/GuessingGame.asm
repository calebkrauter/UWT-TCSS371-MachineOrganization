; Caleb Krauter
; Program description !
          
            .ORIG           x3000           ; Start at orgin x3000
Loop        LEA             R0, Prompt      ; Ask for input    
            PUTS                            ; display the prompt
            GETC                            ; read a char into R0
            OUT                             ; write the char from R0 to the monitor
            BR              Loop                

            
            HALT
Prompt      .STRINGZ        "\nGuess a number 0 to 9: "
            
            
            .END
            