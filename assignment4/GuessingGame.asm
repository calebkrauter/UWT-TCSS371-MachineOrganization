; Caleb Krauter
; Collaborated with Elroy Mbabazi

                .ORIG               x3000               ; Start at orgin x3000
                
              ;  LD                  R6, #7              ; Value to search for.
                
Loop            LEA                 R0, Prompt          ; Ask for input    
                PUTS                                    ; display the prompt
                GETC                                    ; read a char into R0
                OUT                                     ; write the char from R0 to the monitor
                ; Check if input is greater or equal to MinValid.
                ; if MinValid + (-input) = 0 or is neg, then it meets the MinValid requirement.
                
                ; Clear registers each time we loop.
                ;LD                  R1, #0
                ;LD                  R2, #0
                ;LD                  R3, #0
                ;LD                  R4, #0
                ;LD                  R5, #0
        
                
MinBoundCheck   LD                  R1, MinValid ; LD into R1 the minValid value
                NOT                 R3, R0          ; Negate R0's value          
                ADD                 R2, R3, R1      ; R1 - R0 (ex: min(x0030 = 0) - in(x002F = some value) = pos, if in is x0030 then it's zero, if it is greater than x0030 it is neg, (valid range for min is when input sum = nz))      
                BRn                 MaxBoundCheck            ; Check if input meets requirements for min bound check.
                BRz                 END
                BRp                 END

                
MaxBoundCheck   LD                  R4, MaxValid ; LD into R4, maxV
                ADD                 R5, R3, R4   ; R0's value is already negated, Add max with -INPUT 
                BRn                 END          ; Check if input meets requirements for max bound check.
                BRz                 END
                BRp                 Loop

;CompareForWin   ADD                                
          
END             HALT
Prompt          .STRINGZ            "\nGuess a number 0 to 9: "
Error           .STRINGZ            "\nInvalid input."            
MinValid          .FILL               x0030              ; value for ASCII character 0.    
MaxValid          .FILL               x003B              ; value for ASCII character 0.   
Lesser          .STRINGZ            "\nToo small."
Greater          .STRINGZ            "\nToo big."
                .END                