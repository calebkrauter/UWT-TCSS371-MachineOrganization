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
}
