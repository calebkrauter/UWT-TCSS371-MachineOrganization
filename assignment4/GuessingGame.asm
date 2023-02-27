; Caleb Krauter
; Collaborated with Elroy Mbabazi

                .ORIG               x3000               ; Start at orgin x3000
Loop            LEA                 R0, Prompt          ; Ask for input    
                PUTS                                    ; display the prompt
                GETC                                    ; read a char into R0
                OUT                                     ; write the char from R0 to the monitor
                ; Check if input is greater or equal to MinValid.
                ; if MinValid + (-input) = 0 or is neg, then it meets the MinValid requirement.
        
                
                LD                  R1, MinValid ; LD into R1 the minValid value
                NOT                 R3, R0          ; Negate R0's value          
                ADD                 R2, R3, R1      ; R1 - R0 (ex: min(x0030 = 0) - in(x002F = some value) = pos, if in is x0030 then it's zero, if it is greater than x0030 it is neg, (valid range for min is when input sum = nz))      
                BRn                 Loop
                BR                  END
                ;BRz                  Loop 
                
;CheckMax         ;LEA                 R0, Error
 ;               PUTS
   ;             GETC
   ;             OUT
   ;             BR                  Loop
               
          
END             HALT
Prompt          .STRINGZ            "\nGuess a number 0 to 9: "
Error           .STRINGZ            "\nInvalid input."            
MinValid          .FILL               x0030              ; value for ASCII character 0.    
                .END                