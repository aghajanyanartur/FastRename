//package art.pro.FastRename.service;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//import java.io.File;
//import java.util.Arrays;
//import java.util.Comparator;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class RenameUtilTest {
//
//    private static final String TEST_DIRECTORY = "test_directory";
//    private static final String FILE_EXTENSION = ".txt";
//    private static final String FILE_PREFIX = "file";
//    private static final int START_NUMBER = 1;
//    private static final int INCREMENT_BY = 1;
//    private static final int DIGITS_NUMBER = 3;
//
//    private static RenameUtil renameUtil;
//
//    @BeforeAll
//    static void setUp() {
//        renameUtil = new RenameUtil();
//        File testDirectory = new File(TEST_DIRECTORY);
//        if (!testDirectory.exists()) {
//            assertTrue(testDirectory.mkdir());
//        }
//        createTestFiles(testDirectory);
//    }
//
//    @Test
//    void testRenameToPattern_patternLeading() {
//        String pattern = "test_";
//        boolean patternLeading = true;
//        boolean increment = true;
//        boolean sorted = false;
//
//        renameUtil.renameToPattern(TEST_DIRECTORY, pattern, START_NUMBER, INCREMENT_BY,
//                DIGITS_NUMBER, increment, patternLeading, sorted);
//
//        File[] files = getSortedFiles();
//        assertEquals(3, files.length);
//        assertEquals("test_001.txt", files[0].getName());
//        assertEquals("test_002.txt", files[1].getName());
//        assertEquals("test_003.txt", files[2].getName());
//    }
//
//    @Test
//    void testRenameToPattern_incrementBy2() {
//        String pattern = "test_";
//        boolean patternLeading = true;
//        boolean increment = true;
//        boolean sorted = false;
//
//        renameUtil.renameToPattern(TEST_DIRECTORY, pattern, START_NUMBER, 3,
//                DIGITS_NUMBER, increment, patternLeading, sorted);
//
//        File[] files = getSortedFiles();
//        assertEquals(3, files.length);
//        assertEquals("test_001.txt", files[0].getName());
//        assertEquals("test_004.txt", files[1].getName());
//        assertEquals("test_007.txt", files[2].getName());
//    }
//
//    @Test
//    void testRenameToPattern_patternNotLeading_sorted() {
//        String pattern = "test";
//        boolean patternLeading = false;
//        boolean increment = true;
//        boolean sorted = true;
//
//        renameUtil.renameToPattern(TEST_DIRECTORY, pattern, START_NUMBER, INCREMENT_BY,
//                DIGITS_NUMBER, increment, patternLeading, sorted);
//
//        File[] files = getSortedFiles();
//        assertEquals(3, files.length);
//        assertEquals("001test.txt", files[0].getName());
//        assertEquals("002test.txt", files[1].getName());
//        assertEquals("003test.txt", files[2].getName());
//    }
//
//    private static void createTestFiles(File directory) {
//        File[] files = directory.listFiles();
//        for (File file : files) {
//            file.delete();
//        }
//
//        for (int i = 0; i < 3; i++) {
//            String fileName = FILE_PREFIX + (i + 1) + FILE_EXTENSION;
//            File file = new File(directory, fileName);
//            try {
//                assertTrue(file.createNewFile());
//            } catch (Exception e) {
//                fail("Failed to create test files.");
//            }
//        }
//    }
//
//    private File[] getSortedFiles() {
//        File[] files = new File(TEST_DIRECTORY).listFiles(File::isFile);
//        Arrays.sort(files, Comparator.comparing(File::getName));
//        return files;
//    }
//}