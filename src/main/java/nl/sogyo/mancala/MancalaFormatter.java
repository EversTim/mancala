package nl.sogyo.mancala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.sogyo.mancala.backend.Mancala;

public class MancalaFormatter {

	private static final int FIELDWIDTHINTERNAL = 2;
	private static final int KALAHAWIDTHINTERNAL = 2;

	private static final int FIELDWIDTHTOTAL = 4;
	private static final int KALAHAWIDTHTOTAL = 4;

	private static final int SPACERWIDTH = 1;

	private static final int TOTALWIDTH = (2 * KALAHAWIDTHTOTAL) + (6 * FIELDWIDTHTOTAL) + (7 * SPACERWIDTH);

	public static String format(Mancala mancala) {
		List<Integer> stones = mancala.getStoneAmounts();
		List<Integer> kalahas = extractKalahas(stones);
		List<Integer> p1 = extractFieldsPlayerOne(stones);
		List<Integer> p2 = extractFieldsPlayerTwo(stones);
		Collections.reverse(p2);
		StringBuilder build = new StringBuilder();
		build.append(fieldLine(p2));
		build.append('\n');

		build.append(fieldNumbersReverse());
		build.append('\n');

		build.append(kalahaLine(kalahas));

		build.append('\n');

		build.append(fieldNumbers());
		build.append('\n');

		build.append(fieldLine(p1));

		return build.toString();
	}

	private static StringBuilder fieldNumbers() {
		StringBuilder build = new StringBuilder();
		build.append(padRight("", KALAHAWIDTHTOTAL + SPACERWIDTH));
		for (int i = 1; i <= 6; i++) {
			build.append(" ");
			build.append(padLeft(Integer.toString(i), FIELDWIDTHINTERNAL));
			build.append(" ");
			build.append(padRight("", SPACERWIDTH));
		}
		return build;
	}

	private static StringBuilder fieldNumbersReverse() {
		StringBuilder build = new StringBuilder();
		build.append(padRight("", KALAHAWIDTHTOTAL + SPACERWIDTH));
		for (int i = 6; i >= 1; i--) {
			build.append(" ");
			build.append(padLeft(Integer.toString(i), FIELDWIDTHINTERNAL));
			build.append(" ");
			build.append(padRight("", SPACERWIDTH));
		}
		return build;
	}

	private static StringBuilder kalahaLine(List<Integer> kalahaStones) {
		StringBuilder build = new StringBuilder();
		build.append(padRight("", KALAHAWIDTHTOTAL, '-'));
		build.append(padRight("", TOTALWIDTH - (2 * KALAHAWIDTHTOTAL)));
		build.append(padRight("", KALAHAWIDTHTOTAL, '-'));
		build.append('\n');
		build.append("|");
		build.append(padLeft(Integer.toString(kalahaStones.get(1)), KALAHAWIDTHINTERNAL));
		build.append("|");
		build.append(padRight("", TOTALWIDTH - (2 * KALAHAWIDTHTOTAL)));
		build.append("|");
		build.append(padLeft(Integer.toString(kalahaStones.get(0)), KALAHAWIDTHINTERNAL));
		build.append("|");
		build.append('\n');
		build.append(padRight("", KALAHAWIDTHTOTAL, '-'));
		build.append(padRight("", TOTALWIDTH - (2 * KALAHAWIDTHTOTAL)));
		build.append(padRight("", KALAHAWIDTHTOTAL, '-'));
		return build;
	}

	private static StringBuilder fieldLine(List<Integer> fieldStones) {
		StringBuilder build = new StringBuilder();
		build.append(padRight("", KALAHAWIDTHTOTAL + SPACERWIDTH));
		for (int i : fieldStones) {
			build.append(padLeft("", FIELDWIDTHTOTAL, '-'));
			build.append(padRight("", SPACERWIDTH));
		}
		build.append('\n');
		build.append(padRight("", KALAHAWIDTHTOTAL + SPACERWIDTH));
		for (int i : fieldStones) {
			build.append("|");
			build.append(padLeft(Integer.toString(i), FIELDWIDTHINTERNAL));
			build.append("|");
			build.append(padRight("", SPACERWIDTH));
		}
		build.append('\n');
		build.append(padRight("", KALAHAWIDTHTOTAL + SPACERWIDTH));
		for (int i : fieldStones) {
			build.append(padLeft("", FIELDWIDTHTOTAL, '-'));
			build.append(padRight("", SPACERWIDTH));
		}
		build.append('\n');
		return build;
	}

	private static List<Integer> extractKalahas(List<Integer> stones) {
		List<Integer> output = new ArrayList<Integer>(2);
		output.add(stones.get(6));
		output.add(stones.get(13));
		return output;
	}

	private static List<Integer> extractFieldsPlayerOne(List<Integer> stones) {
		List<Integer> output = stones.subList(0, 6);
		return output;
	}

	private static List<Integer> extractFieldsPlayerTwo(List<Integer> stones) {
		List<Integer> output = stones.subList(7, 13);
		return output;
	}

	public static String padLeft(String input, int totalSize) {
		return padLeft(input, totalSize, ' ');
	}

	public static String padRight(String input, int totalSize) {
		return padRight(input, totalSize, ' ');
	}

	public static String padLeft(String input, int totalSize, char toPadWith) {
		StringBuilder output = new StringBuilder(input);
		while (output.length() < totalSize) {
			output.insert(0, toPadWith);
		}
		return output.toString();
	}

	public static String padRight(String input, int totalSize, char toPadWith) {
		StringBuilder output = new StringBuilder(input);
		while (output.length() < totalSize) {
			output.append(toPadWith);
		}
		return output.toString();
	}
}
