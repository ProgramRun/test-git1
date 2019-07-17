package com.zad.jdk8.nio;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-06 8:05
 */
class FileTest {

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }


    @Test
    void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Class clazz = FileTest.class;
        InputStream inputStream = clazz.getResourceAsStream("/fileTest.txt");
        String data = readFromInputStream(inputStream);

        assertTrue(data.contains(expectedData));
    }

    @Test
    void givenFilePath_whenUsingFilesReadAllBytes_thenFileData() throws IOException, URISyntaxException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Path path = Paths.get(getClass().getClassLoader()
                .getResource("fileTest.txt").toURI());
        byte[] fileBytes = Files.readAllBytes(path);
        String data = new String(fileBytes);

        assertEquals(expectedData, data.trim());
    }

    @Test
    void givenFilePath_whenUsingFilesLines_thenFileData() throws IOException, URISyntaxException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Path path = Paths.get(getClass().getClassLoader()
                .getResource("fileTest.txt").toURI());

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

        assertEquals(expectedData, data.trim());
    }

    @Test
    void givenFileName_whenUsingFileUtils_thenFileData() throws IOException {
        String expectedData = "Hello World from fileTest.txt!!!";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("fileTest.txt").getFile());
        String data = FileUtils.readFileToString(file, "UTF-8");

        assertEquals(expectedData, data.trim());
    }

    @Test
    void givenFileName_whenUsingIOUtils_thenFileData() throws IOException {
        String expectedData = "This is a content of the file";

        FileInputStream fis = new FileInputStream("src/test/resources/fileToRead.txt");
        String data = IOUtils.toString(fis, "UTF-8");

        assertEquals(expectedData, data.trim());
    }

    @Test
    void givenURLName_whenUsingURL_thenFileData() throws IOException {
        String expectedData = "baidu";

        URL urlObject = new URL("https://www.baidu.com");
        URLConnection urlConnection = urlObject.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        String data = readFromInputStream(inputStream);

        assertTrue(data.contains(expectedData));
    }

    @Test
    public void givenFileName_whenUsingJarFile_thenFileData() {
       /* String expectedData = "BSD License";

        Class clazz = Matchers.class;
        InputStream inputStream = clazz.getResourceAsStream("/LICENSE.txt");
        String data = readFromInputStream(inputStream);

        assertTrue(data.contains(expectedData));*/
    }
}
