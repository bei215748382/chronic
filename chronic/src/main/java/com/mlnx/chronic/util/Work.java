package com.mlnx.chronic.util;

public enum Work {
	Monday_morning(0x1L), Monday_afternoon(0x2L), Tuesday_morning(0x4L), Tuesday_afternoon(
			0x8L), Wednesday_morning(0x10L), Wednesday_afternoon(0x20L), Thursday_morning(
			0x40L), Thursday_afternoon(0x80L), Friday_morning(0x100L), Friday_afternoon(
			0x200L), Saturday_morning(0x400L), Saturday_afternoon(0x800L), Sunday_morning(
			0x1000L), Sunday_afternoon(0x2000L);

	private final long bitmask;

	Work(long bitmask) {
		this.bitmask = bitmask;
	}

	public static boolean isSet(long bitmap, Work work) {

		return (bitmap & work.bitmask) != 0;
	}

	public static long set(long bitmap, Work work) {

		return bitmap | work.bitmask;
	}

	public static long unset(long bitmap, Work work) {

		return bitmap & ~work.bitmask;
	}
}
