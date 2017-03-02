package nl.sogyo.mancala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.sogyo.mancala.backend.Mancala;

public class MancalaFormatter {

	private static final int FIELDSIZEINTERNAL = 3;
	private static final int KALAHASIZEINTERNAL = 3;

	private static final int FIELDSIZETOTAL = 3;
	private static final int KALAHASIZETOTAL = 3;

	private static final int SPACERSIZE = 1;

	private static final int TOTALSIZE = (2 * KALAHASIZETOTAL) + (6 * FIELDSIZETOTAL) + (7 * SPACERSIZE);

	public static String format(Mancala mancala) {
		List<Integer> stones = mancala.getStoneAmounts();
		List<Integer> kalahas = extractKalahas(stones);
		List<Integer> p1 = extractFieldsPlayerOne(stones);
		List<Integer> p2 = extractFieldsPlayerTwo(stones);
		Collections.reverse(p2);
		StringBuilder build = new StringBuilder();
		build.append(fieldLine(p2));
		build.append('\n');

		build.append(emptyLine());
		build.append('\n');

		build.append(kalahaLine(kalahas));

		build.append('\n');

		build.append(emptyLine());
		build.append('\n');

		build.append(fieldLine(p1));

		return build.toString();
	}

	private static StringBuilder emptyLine() {
		return new StringBuilder();
	}

	private static StringBuilder kalahaLine(List<Integer> kalahaStones) {
		StringBuilder build = new StringBuilder();
		build.append(padLeft(Integer.toString(kalahaStones.get(1)), KALAHASIZEINTERNAL));
		build.append(padRight("", TOTALSIZE - (2 * KALAHASIZETOTAL)));
		build.append(padLeft(Integer.toString(kalahaStones.get(0)), KALAHASIZEINTERNAL));
		return build;
	}

	private static StringBuilder fieldLine(List<Integer> fieldStones) {
		StringBuilder build = new StringBuilder();
		build.append(padRight("", KALAHASIZETOTAL + SPACERSIZE));
		for (int i : fieldStones) {
			build.append(padLeft(Integer.toString(i), FIELDSIZEINTERNAL));
			build.append(padRight("", SPACERSIZE));
		}
		build.append(padRight("", KALAHASIZETOTAL));
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
