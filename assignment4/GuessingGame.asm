; Caleb Krauter
; Program description !
          
                .ORIG               x3000               ; Start at orgin x3000
Loop            LEA                 R0, Prompt          ; Ask for input    
                PUTS                                    ; display the prompt
                GETC                                    ; read a char into R0
                OUT                                     ; write the char from R0 to the monitor
                BR                  CheckValidity 
                LEA                 R0, Error           ; Reference error in R0.
                PUTS                                    ; Display the error.


CheckValidity   LD                  R1, Valid1          ; Load a valid value into R1
                ADD                 R0, R0, R1          ; Add R0 and R1 together
                BRz                 Loop                ; If R0 + R1 and #0 = 0, continue the loop
                LEA                 R0, Error
                PUTS
            
END             HALT
Prompt          .STRINGZ            "\nGuess a number 0 to 9: "
Error           .STRINGZ            "\nInvalid input."            
Valid1          .FILL               #-48              ; value for ASCII character 0.    
                .END                
            