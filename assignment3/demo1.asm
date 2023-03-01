;
; Use a loop to add 1 + 2 + 3 + 4 + 5

0011 000 000000000  ; Load addresss x3000 as the start location

; Clear R0 and R1
0101 000 000 1 00000 ; Set R0 to zero   R0 <- 0 Ro is the SUM
0101 001 001 1 00000 ; Set R1 to zero   R1 <- 0 R1 is the loop counter

; Initialize the counter to 5
0001 001 001 1 00101

; Start the loop
; add the current value of R1 to the sum (R0)
0001 001 001 1 00101
; Decrement the loop counter
0001 001 001 1 11111
0000 001 001 1 11111


1111 0000 0010 0101 ; halt the program (TRAP with vector x25)