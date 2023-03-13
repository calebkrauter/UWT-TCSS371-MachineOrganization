/*
 * Unit tests for the Computer class. 
 */

package simulator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Alan Fowler
 * @version 1.3
 */
class ComputerTest {
	
	// An instance of the Computer class to use in the tests.
	private Computer myComputer;

	@BeforeEach
	void setUp() {
		myComputer = new Computer();
	}
	
	
	
	
	/*
	 * NOTE:
	 * Programs in unit tests should ideally have one instruction per line
	 * with a comment for each line.
	 */

	/**
	 * Test method for {@link simulator.Computer#executeBranch()}.
	 */
	@Test
	void testExecuteBranch() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link simulator.Computer#executeLoad()}.
	 */
	@Test
	void testExecuteLoad() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link simulator.Computer#executeAnd()}.
	 */
	@Test
	void testExecuteAnd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link simulator.Computer#executeNot()}.
	 */
	@Test
	void testExecuteNot5() {
		//myComputer.display();
		
		// NOTE: R5 contains #5 initially when the Computer is instantiated
		// So, iF we execute R4 <- NOT R5, then R4 should contain 1111 1111 1111 1010    (-6)
		// AND CC should be 100
		
		String program[] = {
			"1001100101111111",    // R4 <- NOT R5
			"1111000000100101"     // TRAP - vector x25 - HALT
		};
		
		myComputer.loadMachineCode(program);
		myComputer.execute();
		
		assertEquals(-6, myComputer.getRegisters()[4].get2sCompValue());
		
		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("100".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
		
		//myComputer.display();
	}
//	/**
//	 * Test method for {@link simulator.Computer#executeNot()}.
//	 */
//	@Test
//	void testExecuteNot6() {
//
//		//myComputer.display();
//
//		// NOTE: R6 contains #-5 initially when the Computer is instantiated
//		// So, iF we execute R4 <- NOT R6, then R4 should contain 1111 1111 1111 1010    (-6)
//		// AND CC should be 100
//
//		Need to load into r6 to test this. Can't do this till we make load.
//
//		String program[] = {
//				"1001100110111111",    // R4 <- NOT R6
//				"1111000000100101"     // TRAP - vector x25 - HALT
//		};
//
//		myComputer.loadMachineCode(program);
//		myComputer.execute();
//
//		assertEquals(4, myComputer.getRegisters()[4].get2sCompValue());
//
//		// Check that CC was set correctly
//		BitString expectedCC = new BitString();
//		expectedCC.setBits("001".toCharArray());
//		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
//
//		//myComputer.display();
//	}
	
	/**
	 * Test method for {@link simulator.Computer#executeAdd()}. <br>
	 * Computes 2 + 2. R0 <- R2 + R2
	 */
	@Test
	void testExecuteAddR2PlusR2() {

		String[] program =
			{"0001000010000010",  // R0 <- R2 + R2 (#4)
		     "1111000000100101"}; // HALT
		
		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(4, myComputer.getRegisters()[0].get2sCompValue());
		
		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("001".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}
	
	/**
	 * Test method for {@link simulator.Computer#executeAdd()}. <br>
	 * Computes 2 + 3. R0 <- R2 + #3
	 */
	@Test
	void testExecuteAddR2PlusImm3() {

		String[] program =
			{"0001000010100011",  // R0 <- R2 + #3
		     "1111000000100101"}; // HALT
		
		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(5, myComputer.getRegisters()[0].get2sCompValue());
		
		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("001".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}
	
	/**
	 * Test method for {@link simulator.Computer#executeAdd()}. <br>
	 * Computes 2 - 3. R0 <- R2 + #-3
	 */
	@Test
	void testExecuteAddR2PlusImmNeg3() {

		String[] program =
			{"0001000010111101",  // R0 <- R2 + #-3
		     "1111000000100101"}; // HALT
		
		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(-1, myComputer.getRegisters()[0].get2sCompValue());
		
		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("100".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeAdd()}. <br>
	 * Computes 2 + 3. R0 <- R2 + #3
	 */
	@Test
	void testExecuteAddR2PlusImm15() {

		String[] program =
				{"0001000010101111",  // R0 <- R2 + #15
						"1111000000100101"}; // HALT

		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(17, myComputer.getRegisters()[0].get2sCompValue());

		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("001".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}


	/**
	 * Test method for {@link simulator.Computer#executeAnd()}. <br>
	 * Computes 1 AND 3. R0 <- R1 AND R3
	 */
	@Test
	void testExecuteANDR1andR3() {

		String[] program =
				{"0101000001000011",  // R0 <- R1 AND R3
						"1111000000100101"}; // HALT

		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(1, myComputer.getRegisters()[0].get2sCompValue());

		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("001".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeAnd()}. <br>
	 * Computes 1 AND 2. R0 <- R1 AND R2
	 */
	@Test
	void testExecuteANDR1andR2() {

		String[] program =
				{"0101000001000010",  // R0 <- R1 AND R2
						"1111000000100101"}; // HALT

		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(0, myComputer.getRegisters()[0].get2sCompValue());

		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("010".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeAnd()}. <br>
	 * Computes 5 AND 15. R0 <- R5 AND 15
	 */
	@Test
	void testExecuteANDR5andImm15() {

		String[] program =
				{"0101000101101111",  // R0 <- R5 AND 15
						"1111000000100101"}; // HALT

		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(5, myComputer.getRegisters()[0].get2sCompValue());

		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("001".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeAnd()}. <br>
	 * Computes 5 AND -5. R0 <- R5 AND -5
	 */
	@Test
	void testExecuteANDR5andImmNeg5() {

		String[] program =
				{"0101000101111011",  // R0 <- R5 AND -5
						"1111000000100101"}; // HALT

		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(1, myComputer.getRegisters()[0].get2sCompValue());

		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("001".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeLoad()}. <br>
	 * Computes R0 <- offset 2. R0 <- 5.
	 */
	@Test
	void testExecuteLoadR0WithOffset2() {

		String[] program =
				{"0010000000000010", // LD into R0 offset 2
						"0001001000100101",// R1 <- R0 + 5 (This instruction is just used for testing a larger offset.)
						"1111000000100101", // HALT
				"0000000000000101"}; // Ascii for 5

		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(5, myComputer.getRegisters()[0].get2sCompValue());
//
		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("001".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeLoad()}. <br>
	 * Computes R0 <- offset 1. R0 <- -15.
	 */
	@Test
	void testExecuteLoadR0WithOffsetNeg15() {

		String[] program =
				{"0010000000000001", // LD into R0 offset 1
						"1111000000100101", // HALT
						"1111111111110001"}; // Ascii for -15

		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(-15, myComputer.getRegisters()[0].get2sCompValue());
//
		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("100".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeLoad()}. <br>
	 * Computes R0 <- offset 1. R0 <- 0.
	 */
	@Test
	void testExecuteLoadR0WithOffset0() {

		String[] program =
				{"0010000000000001", // LD into R0 offset 1
						"1111000000100101", // HALT
						"0000000000000000"}; // Ascii for 0

		myComputer.loadMachineCode(program);
		myComputer.execute();

		assertEquals(0, myComputer.getRegisters()[0].get2sCompValue());
//
		// Check that CC was set correctly
		BitString expectedCC = new BitString();
		expectedCC.setBits("010".toCharArray());
		assertEquals(expectedCC.get2sCompValue(), myComputer.getCC().get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeBranch()}. <br>
	 * Computes R0 <- offset 1. R0 <- (R2 + 14)). Result is R0 <- 16.
	 * Uses unconditional branching.
	 */
	@Test
	void testUnconditional111BR() {
		String[] program =
				{"0000111000000001", // Branch no matter the condition code.
						"0001000010101111",  // R0 <- R2 + #15 (This line should never get executed)
						"0001000010101110",  // R0 <- R2 + #14
						"1111000000100101"}; // HALT

		myComputer.loadMachineCode(program);
		myComputer.execute();

		// If the BR is unconditional, check
		assertEquals(-1, myComputer.getMemory()[0].substring(4, 3).get2sCompValue());
		assertEquals(16, myComputer.getRegisters()[0].get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeBranch()}. <br>
	 * Computes R0 <- offset 1. R0 <- (R2 + 14)). Result is R0 <- 16.
	 * Uses unconditional branching.
	 */
	@Test
	void testUnconditional000BR() {
		String[] program =
				{"0000000000000001", // Branch no matter the condition code.
						"0001000010101111",  // R0 <- R2 + #15 (This line should never get executed)
						"0001000010101110",  // R0 <- R2 + #14
						"1111000000100101"}; // HALT

		myComputer.loadMachineCode(program);
		myComputer.execute();

		// Check if the BR is unconditional.
		assertEquals(0, myComputer.getMemory()[0].substring(4, 3).get2sCompValue());
		// The result after skipping a single line should be R0 <- 16.
		assertEquals(16, myComputer.getRegisters()[0].get2sCompValue());
	}

	/**
	 * Test method for {@link simulator.Computer#executeBranch()}. <br>
	 * Computes uses to branch statements to branch past a line of addition into R0.
	 * Branching only when positive such that R0 remains to contain 0.
	 */
	@Test
	void test001BR() {
		String[] program =
				{"0001011100100111",  // R3 <- R4 + 7 (This line set the condition code to "001")
						"0000001000000001",  // Branch past next line if CC is "001", if 'N' or 'Z' then result is 17.
						"0001000010101111",  // R0 <- R2 + #15 (This line should never get executed)
						"0000001000000001",  // Branch past next line if CC is "001", if 'N' or 'Z' then result is 17.
						"0001000010101110",  // R0 <- R2 + #14 (This line should never get executed)
						"1111000000100101"}; // HALT
		myComputer.loadMachineCode(program);
		myComputer.execute();
		assertEquals(0, myComputer.getRegisters()[0].get2sCompValue()); // Test that the correct value is added to R2 into R0.
	}

	/**
	 * Test method for {@link simulator.Computer#executeBranch()}. <br>
	 * Computes R0 <- offset 1. R0 <- (R2 + 14)). Result is R0 <- 16.
	 * Branching only when positive.
	 */
	@Test
	void test100BR() {
		String[] program =
				{"0001011100100000",  // R3 <- R4 + 0 (This line set the condition code to "100")
						"0000100000000001",  // Branch past next line if CC is "100", if 'Z' or 'P' then result is 17.
						"0001000010101111",  // R0 <- R2 + #15 (This line should never get executed)
						"0001000010101110",  // R0 <- R2 + #14
						"1111000000100101"}; // HALT
		myComputer.loadMachineCode(program);
		myComputer.execute();

		// If the BR is unconditional, check
		assertEquals(-4, myComputer.getMemory()[1].substring(4, 3).get2sCompValue()); // Correct cc is set from the first instruction
		// If this test passes then the Branch instruction properly branched to the last line before HALT.
		// If the result is 17 then it did not branch because the condition codes were not met.
		assertEquals(16, myComputer.getRegisters()[0].get2sCompValue()); // Test that the correct value is added to R2 into R0.
	}

	/**
	 * Test method for {@link simulator.Computer#executeBranch()}. <br>
	 * Computes R0 <- offset 1. R0 <- (R2 + 14)). Result is R0 <- 16.
	 * Branching only when positive.
	 */
	@Test
	void test010BR() {
		String[] program =
				{"0001000010111110",  // R0 <- R2 + #-2 (Used to set CC to "010")
						"0000010000000001",  // Branch past next line if CC is "010", if 'N' or 'P' then result is 17.
						"0001000010101111",  // R0 <- R2 + #15 (This line should never get executed)
						"0001000010101110",  // R0 <- R2 + #14 (The expected result is R0 <- 16)
						"1111000000100101"}; // HALT
		myComputer.loadMachineCode(program);
		myComputer.execute();

		// If the BR is unconditional, check
		assertEquals(2, myComputer.getMemory()[1].substring(4, 3).get2sCompValue()); // Correct cc is set from the first instruction
		// If this test passes then the Branch instruction properly branched to the last line before HALT.
		// If the result is 17 then it did not branch because the condition codes were not met.
		assertEquals(16, myComputer.getRegisters()[0].get2sCompValue()); // Test that the correct value is added to R2 into R0.
	}

	/**
	 * Test method for {@link simulator.Computer#executeBranch()}. <br>
	 * Computes R0 <- R2 -16 = 14. The condition code should not meet the requirement of
	 * the Branch's CC "010" meaning that no Branching happens and R0 will hold -1 by the end.
	 * Branching only when CC is Zero, requirement that CC is met should be false.
	 */
	@Test
	void test010BRConditionFalse() {
		String[] program =
				{"0001000010110000",  // R0 <- R2 + #-16 (Used to set CC to "100"). If the CC matched BR's "010" then R0 <- -14.
						"0000010000000001",  // Branch past next line if CC is "010", if 'N' or 'P' then result is 17.
						"0001000000111111",  // R0 <- R0 + #1 (This line should get executed and the result in R0 <- -1)
						"1111000000100101"}; // HALT
		myComputer.loadMachineCode(program);
		myComputer.execute();

		// Check that the CC was set to "100"

		assertEquals(-4, myComputer.getCC().get2sCompValue()); // Correct cc is set from the first instruction

		// Check that the BR condition is "010"
		assertEquals(2, myComputer.getMemory()[1].substring(4, 3).get2sCompValue());
		// If this test passes then the Branch instruction properly branched to the last line before HALT.
		// If the result is -14 then it did branch because the condition codes were met.
		assertEquals(-1, myComputer.getRegisters()[0].get2sCompValue()); // Test that the correct value is added to R2 into R0.
	}

}
