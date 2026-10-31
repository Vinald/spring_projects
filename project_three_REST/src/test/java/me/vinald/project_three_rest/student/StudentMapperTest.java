package me.vinald.project_three_rest.student;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
	@BeforeEach
	void setUp() {
		System.out.println("Setting up test environment");
	}

	@AfterEach
	void afterEach() {
		System.out.println("Test completed");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("All tests completed");
	}

	@Test
	public void testMethod1() {
		System.out.println("Test method 1 executed");
	}

	@Test
	public void testMethod2() {
		System.out.println("Test method 2 executed");
	}
}
