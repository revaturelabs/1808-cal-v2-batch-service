package com.revature.batchservice.dto;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class Benchmark {

	private int goodGrade;
	private int passingGrade;

	public Benchmark() {
	}

	public Benchmark(int goodGrade, int passingGrade) {
		this.goodGrade = goodGrade;
		this.passingGrade = passingGrade;
	}

	public int getGoodGrade() {
		return goodGrade;
	}

	public void setGoodGrade(int goodGrade) {
		this.goodGrade = goodGrade;
	}

	public int getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Benchmark benchmark = (Benchmark) o;
		return goodGrade == benchmark.goodGrade &&
						passingGrade == benchmark.passingGrade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(goodGrade, passingGrade);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Benchmark.class.getSimpleName() + "[", "]")
						.add("goodGrade=" + goodGrade)
						.add("passingGrade=" + passingGrade)
						.toString();
	}
}
