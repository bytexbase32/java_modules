import java.util.Scanner;

public class Program {

    static final int MAX_CHAR = 65536;
    static final int MAX_HEIGHT = 10;
    static final int TOP_N = 10;

    public static void buildFrequency(String input, int[] freq) {

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            freq[c]++;
        }
    }

    public static int countUniqueChars(int[] freq) {

        int total = 0;
        for (int i = 0; i < MAX_CHAR; i++) {
            if (freq[i] > 0)
                total++;
        }
        return total;
    }

    public static void extractChars(int[] freq,char[] chars, int[] counts) {
        
        for (int i = 0, j = 0; i < MAX_CHAR; i++) {
            if (freq[i] > 0) {
                chars[j] = (char)i;
                counts[j] = freq[i];
                j++;
            }
        }
    }

    public static void sort(char[] chars, int[] counts) {
        boolean swapped;
        for (int i = 0; i < counts.length; i++) {
            swapped = false;
            for (int j = 0; j < counts.length - 1 - i; j++) {
                if (counts[j] < counts[j + 1] ||
                (counts[j] == counts[j + 1] && chars[j] > chars[j + 1])) {

                    char tmpChar = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = tmpChar;

                    int tmpCount = counts[j];
                    counts[j] = counts[j + 1];
                    counts[j + 1] = tmpCount;

                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public static int scale(int count, int max, int height) {
        return (count * height) / max;
    }

    public static int ensureMinimumVisible(int barHeight, int count) {
        if (count > 0 && barHeight == 0) {
            return 1;
        }
        return barHeight;
    }

    public static void printHistogram(char[] chars, int[] counts) {
        int barHeight;
        int limit = Math.min(TOP_N, chars.length);
        if (counts.length == 0)
            return;

        int max = counts[0];
        
        for (int level = MAX_HEIGHT; level >= 1; level--) {

            for (int i = 0; i < limit; i++) {
                barHeight = scale(counts[i], max, MAX_HEIGHT);
                barHeight = ensureMinimumVisible(barHeight, counts[i]);
                if (barHeight == level)
                    System.out.print(counts[i] + "  ");
                else if (barHeight > level)
                    System.out.print("#  ");
                else
                    System.out.print("   ");
            }
            System.out.println();
        }
        for (int i = 0; i < limit; i++) {
            System.out.print(chars[i] + "  ");
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int[] freq = new int[MAX_CHAR];

        String input = scanner.nextLine();
        
        buildFrequency(input, freq);

        int total = countUniqueChars(freq);

        char[] chars = new char[total];
        int[] counts = new int[total ];
        
        extractChars(freq, chars, counts);
        sort(chars, counts);
        System.out.println();
        printHistogram(chars, counts);
        System.out.println();
    }
}


























import java.util.Scanner;

public class Program {
    private static final int MAX_CHAR_CODES = 65535;
    private static final byte MAX_HIGH = 10;

    private static int[] getChar(String input) {
        int[] temp = new int[MAX_CHAR_CODES];
        char[] inputAsArray = input.toCharArray();

        for (int i = 0; i < input.length(); i++) {
            temp[inputAsArray[i]]++;    // ASCII:  temp[65 = 'A'] += 1
        }
        return temp;
    }

    private static char[] getTopTenChars(int[] parsedInput) {
        char[] temp = new char[MAX_HIGH];

        for (int i = 0; i < MAX_CHAR_CODES; i++) {
            int iCharCount = parsedInput[i];
            if (iCharCount > 0) {
                for (int j = 0; j < MAX_HIGH; j++) {
                    if (parsedInput[temp[j]] < iCharCount) {
                        temp = insertCharAt(temp, (char) i, j);
                        break;
                    }
                }
            }
        }
        return (temp);
    }

    private static char[] insertCharAt(char[] base, char c, int index) {
        char[] temp = new char[MAX_HIGH];
        for (int i = 0; i < index; i++) {
            temp[i] = base[i];
        }
        temp[index] = c;
        for (int i = index + 1; i < MAX_HIGH; i++) {
            temp[i] = base[i - 1];
        }
        return temp;
    }

    private static void printGraph(char[] topTenChars, int[] charCount) {
        int max = charCount[topTenChars[0]];
        byte maxbarHeight = (byte) (max <= 10 ? max : 10);
        byte totalLines = (byte) (2 + maxbarHeight);
        byte[] graphs = new byte[MAX_HIGH];

        for (int i = 0; i < MAX_HIGH; i++) {
            if (max <= 10) {
                graphs[i] = (byte) charCount[topTenChars[i]];
            } else {
                graphs[i] = (byte) (charCount[topTenChars[i]] * 10 / max);
            }
        }
        System.out.println();
        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < MAX_HIGH; j++) {
                if (topTenChars[j] != 0) {
                    if (i + graphs[j] + 2 == totalLines) {
                        System.out.format("%3d", charCount[topTenChars[j]]);
                    } else if (i == totalLines - 1) {
                        System.out.format("%3c", topTenChars[j]);
                    } else if (i + graphs[j] >= maxbarHeight) {
                        System.out.format("%3c", '#');
                    }
                    if (j != MAX_HIGH - 1 && topTenChars[j + 1] != 0 && i + graphs[j + 1] >= maxbarHeight) {
                        System.out.format("%c", ' ');
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
//        String str = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42";
        int[] charCount = getChar(str);
        char[] topTenChar = getTopTenChars(charCount);
        printGraph(topTenChar, charCount);

        System.exit(0);
    }
}
