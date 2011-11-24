package se.janlindblom.libepub.parser;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Unpackager for EPUB files.
 *
 * @author Jan Lindblom <lindblom.jan@gmail.com>
 * @version 0.0.1
 */
public class UnPackager {
    
    private ZipFile zipFile;
    
    private Enumeration zipEntries;
    
    private File targetDirectory;
    
    public UnPackager() throws IOException {
        this.setTargetDirectory(Files.createTempDir());
        this.getTargetDirectory().deleteOnExit();
        this.getTargetDirectory().mkdir();
    }
    
    public UnPackager(File epubFile) throws IOException {
        this();
        this.setZipFile(new ZipFile(epubFile));
        this.setZipEntries(this.getZipFile().entries());
    }
    
    public ArrayList<File> unpack() throws IOException {
        ArrayList files = new ArrayList<File>();
        while (zipEntries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) zipEntries.nextElement();
            if (entry.isDirectory()) {
                File dirEntry = new File(getTargetDirectory().getPath() + File.separator + entry.getName());
//                System.err.println("Creating directory '"+ dirEntry.getCanonicalPath() +"'");
                dirEntry.mkdir();
                dirEntry.deleteOnExit();
                files.add(dirEntry);
                continue;
            }
        }
        getZipFile().close();
        return files;
    }

    /**
     * @return the zf
     */
    private ZipFile getZipFile() {
        return zipFile;
    }

    /**
     * @param zf the zf to set
     */
    private void setZipFile(ZipFile zf) {
        this.zipFile = zf;
    }

    /**
     * @return the zipEntries
     */
    private Enumeration getZipEntries() {
        return zipEntries;
    }

    /**
     * @param zipEntries the zipEntries to set
     */
    private void setZipEntries(Enumeration zipEntries) {
        this.zipEntries = zipEntries;
    }

    /**
     * @return the targetDirectory
     */
    private File getTargetDirectory() {
        return targetDirectory;
    }

    /**
     * @param targetDirectory the targetDirectory to set
     */
    private void setTargetDirectory(File targetDirectory) {
        this.targetDirectory = targetDirectory;
    }
}
