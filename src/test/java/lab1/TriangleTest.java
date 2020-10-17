package lab1;

import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Collections;

public class TriangleTest {

    static class TestingArgs
    {
        ArrayList<String> commandLineArgs = new ArrayList<String>();
        String expectedResult;
    }

    static int testCounter = 0;

    static String GetTestResult(ArrayList<String> args, String expected) throws IOException
    {
        ProcessBuilder builder = new ProcessBuilder(args);
        final Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        testCounter++;
        if (line.equals(expected))
        {
            return "Success";
        }
        else
        {
            return "Error";
        }
    }

    static TestingArgs GetArgsForTesting(String line, String expected)
    {
        TestingArgs testingArgs = new TestingArgs();
        testingArgs.expectedResult = expected;
        testingArgs.commandLineArgs.add("src/test/java/lab1/triangle.exe");
        Collections.addAll(testingArgs.commandLineArgs, line.split("\\s+"));
        return testingArgs;
    }

    public static void main(String[] args)
    {
        FileReader fr;
        try
        {
            fr = new FileReader("src/test/java/lab1/tests.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line, expected, result;
            File resultFile = new File("src/test/java/lab1/result.txt");
            FileWriter fWriter = new FileWriter(resultFile);
            while ((line = reader.readLine()) != null && (expected = reader.readLine())!= null)
            {
                TestingArgs testingArgs = GetArgsForTesting(line, expected);
                result = GetTestResult(testingArgs.commandLineArgs, testingArgs.expectedResult);
                String counter = Integer.toString(testCounter);
                fWriter.write(counter + " " + result + "\n");
            }
            fWriter.close();
        }
        catch (IOException err)
        {
            System.err.println(err.getMessage());
        }
    }
}
