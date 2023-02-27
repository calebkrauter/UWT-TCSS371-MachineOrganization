; Caleb Krauter
; Collaborated with Elroy Mbabazi
; This program prompts a user for a value between 0 and 9 giving hints until
; the user enters the target value which in this case is 7.

                .ORIG               x3000               ; Start at orgin x3000
                LD                  R4, StartCount
Loop            LD                  R0, #0
                ADD                 R4, R4, #1
                

                LEA                 R0, Prompt          ; Ask for input    
                PUTS                                    ; display the prompt
                GETC                                    ; read a char into R0
                OUT                                     ; write the char from R0 to the monitor
                ; Check if input is greater or equal to MinValid.
                ; if MinValid + (-input) = 0 or is neg, then it meets the MinValid requirement.

                
MinBoundCheck   LD                  R1, MinValid ; LD into R1 the minValid value
                NOT                 R3, R0          ; Negate R0's value         
                ADD                 R2, R3, R1      ; R1 - R0 (ex: min(x0030 = 0) - in(x002F = some value) = pos, if in is x0030 then it's zero, if it is greater than x0030 it is neg, (valid range for min is when input sum = nz))      
                BRz                 OutError ;  Check if input meets requirements for min bound check.
                BRp                 OutError

MaxBoundCheck   LD                  R1, MaxValid ; LD into R4, maxV
                ADD                 R3, R3, #1      ; Add 1 to complete the 2's complement 
                ADD                 R2, R3, R1   ; R0's value is already negated, Add max with -INPUT 
                BRn                 OutError          ; Check if input meets requirements for max bound check.
                BRz                 OutError
                LD                  R6, TargetValue              ; Value to search for.
                ADD                 R1, R3, R6
                BRn                 OutGreater
                BRp                 OutLesser
                BRz                 OutVictory
                BR                  OutError
OutGreater      LEA                 R0, GreaterMsg
                PUTS
                BR                  Loop
OutLesser       LEA                 R0, LesserMsg
                PUTS
                BR                  Loop
OutVictory      LEA                 R0, VictoryPart1
                PUTS
                ADD                 R0, R4, #0
                OUT
                LEA                 R0, VictoryPart2
                PUTS

                BR                  END
OutError        LEA                 R0, Error
                PUTS
                BR                  Loop

END             HALT
MinValid        .FILL               x0030         
MaxValid        .FILL               x003A  
TargetValue     .FILL               x0037
StartCount      .FILL               #48
MaxCount        .FILL               #57
Prompt          .STRINGZ            "\nGuess a number 0 to 9: "
Error           .STRINGZ            "\nInvalid input."            
GreaterMsg      .STRINGZ            "\nToo big."
LesserMsg       .STRINGZ            "\nToo small."
VictoryPart1    .STRINGZ            "\nCorrect! You took "
VictoryPart2    .STRINGZ            " guesses."
                .END                