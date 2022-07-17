package psjava.codeforces.contest.c4;

import java.util.*;
import java.io.*;

/**
 * 2022.07.18 - Codeforces - #C Registration Problem
 * <p>
 * Data Structure (HashTable)
 *
 * @see <a href="https://codeforces.com/contest/4/problem/C">https://codeforces.com/contest/4/problem/C</a>
 */
public class ProblemC {

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(ps.br.readLine());

        HashTable hashTable = new HashTable();

        for (int i = 0; i < n; i++) {
            String in = ps.br.readLine();

            int cnt = hashTable.addKey(in);
            if (cnt != -1) {
                ps.sb.append(in).append(cnt).append("\n");
                continue;
            }
            ps.sb.append("OK\n");
        }

        ps.close();
    }

    static class HashTable {

        private static final int HASH_SIZE = 1000;
        private static final int HASH_LEN = 400;
        private static final int HASH_VAL = 17; // Make it a prime

        public String[][] sData;
        public int[][] data;
        public int[] length;

        public HashTable() {
            sData = new String[HASH_SIZE][HASH_LEN];
            data = new int[HASH_SIZE][HASH_LEN];
            length = new int[HASH_SIZE];
        }

        private static int getHashKey(String str) {
            int key = 0;

            for (int i = 0; i < str.length(); i++) {
                key = (key * HASH_VAL) + str.charAt(i);
            }

            // Make the key positive if negative
            if (key < 0) key = -key;

            return key % HASH_SIZE;
        }

        /**
         * Add the key to keys and returns number of inserted times of key (-1 if not present)
         */
        public int addKey(String key) {
            int hashKey = getHashKey(key);

            // The number of inserted times for the same key
            int len = length[hashKey];

            // if present (0 is default)
            if (len != 0) {
                // Check if string exists in keys
                for (int i = 0; i < len; i++) {
                    if (key.equals(sData[hashKey][i])) {
                        data[hashKey][i]++;
                        return data[hashKey][i];
                    }
                }
            }

            // if not present, add the string to keys
            // and increase length by 1
            sData[hashKey][length[hashKey]++] = key;

            // -1 if not present
            return -1;
        }
    }

    private static class ps {

        public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        public static final StringBuilder sb = new StringBuilder();
        public static StringTokenizer st = null;

        public static void nextLine() throws IOException {
            st = new StringTokenizer(br.readLine());
        }

        public static int stoi(String s) {
            return Integer.parseInt(s);
        }

        public static void close() throws IOException {
            bw.write(sb.toString());
            br.close();
            bw.close();
        }
    }
}
