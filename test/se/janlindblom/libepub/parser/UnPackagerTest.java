/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.janlindblom.libepub.parser;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jan
 */
public class UnPackagerTest {
    
    private URL resource;
    private File inputFile;
    
    public UnPackagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        resource = this.getClass().getClassLoader().getResource("resources/9780316000000_MobyDick_r9.epub");
        inputFile = new File(resource.getFile());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of unpack method, of class UnPackager.
     */
    @Test
    public void testUnpack() throws Exception {
        System.out.println("unpack");
        UnPackager instance = new UnPackager(inputFile);
        ArrayList<File> files = instance.unpack();
        Iterator<File> iterator = files.iterator();
        while (iterator.hasNext()) {
            File f = iterator.next();
            if (f.exists()) {
                String exists = f.getCanonicalPath();
                String type = "File";
                if (f.isDirectory()) {
                    type = "Directory";
                }
                System.err.println(type + " '" + exists + "' was extracted.");
            } else {
                fail("File does not exists in file system: '" + f.getCanonicalPath() + "'");
            }
        }
    }
}
