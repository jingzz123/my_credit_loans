package cn.creditloans.tools.validator.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;

/**
 *  a utility class for reading and writing character
 *  files to and from to disk.  If you want to deal
 *  exclusively in bytes, use the ByteLoader class
 *  in this package.
 *
 *  Note that all methods in the FileUtil will read/write
 *  entire contents of all supplied Readers and Writers
 *  and alway close them before returning.
 *
 *  @see com.ack.util.ByteLoader
 */

public class FileUtil {
    private static final int kFILEBUFSIZE = 128;
    //  private static FileUtil fSingleton;
    
    /**
     * get hold of the singleton instance of FileUtil
     *
     * @return the singleton FileUtil
     */
/*  public static synchronized FileUtil instance()
  {
    if( fSingleton == null )
      fSingleton = new FileUtil();
    return fSingleton;
  }
 */

    /**
     * read the supplied file in its entirety into a string
     *
     * @param the filename to read characters from
     * @return a string holding the file contents
     * @exception IOException reports problems that occurred
     */
    public static String readCharacterFile( String fileName ) throws IOException {
        if( fileName == null )
            throw new IllegalArgumentException("supplied filename to FileUtil was null");
        return readCharacterFile( new FileReader(fileName) );
    }
    
    /**
     * read the supplied file in its entirety into a string
     *
     * @param the file object to read characters from
     * @return a string holding the file contents
     * @exception IOException reports problems that occurred
     */
    public static String readCharacterFile( File theFile ) throws IOException {
        if( theFile == null )
            throw new IllegalArgumentException("supplied File object to FileUtil was null");
        return readCharacterFile( new FileReader(theFile) );
    }
    
    /**
     * read the supplied reader in its entirety into a string
     *
     * @param the Reader object to read characters from
     * @return a string holding the file contents
     * @exception IOException reports problems that occurred
     */
    public static String readCharacterFile( Reader theReader ) throws IOException {
        if( theReader == null )
            throw new IllegalArgumentException("supplied Reader object to FileUtil was null");
        
        try {
            StringWriter sw = new StringWriter();
            char [] text = new char[ kFILEBUFSIZE ];
            int n;
            while( (n = theReader.read(text, 0, kFILEBUFSIZE)) > 0 )
                sw.write(text, 0, n);
            
            return sw.toString();
        }
        finally {
            if( theReader != null )
                theReader.close();
        }
    }
    
    /**
     * write the supplied contents to the named file
     *
     * @param the filename to write string contents to
     * @param the file contents to be written
     * @exception IOException reports problems that occurred
     */
    public static void writeCharacterFile( String fileName, String contents ) throws IOException {
        if( fileName == null )
            throw new IllegalArgumentException("supplied filename to FileUtil was null");
        writeCharacterFile( new FileWriter(fileName), contents );
    }
    
    /**
     * write the supplied contents to the named file
     *
     * @param the File object to write string contents to
     * @param the file contents to be written
     * @exception IOException reports problems that occurred
     */
    public static void writeCharacterFile( File file, String contents ) throws IOException {
        if( file == null )
            throw new IllegalArgumentException("supplied File object to FileUtil was null");
        writeCharacterFile( new FileWriter(file), contents );
    }
    
    /**
     * write the supplied contents to the name file
     *
     * @param the Writer object to write string contents to
     * @param the file contents to be written
     * @exception IOException reports problems that occurred
     */
    public static void writeCharacterFile( Writer fileWriter, String contents ) throws IOException {
        if( fileWriter == null )
            throw new IllegalArgumentException("supplied FileWriter to FileUtil was null");
        
        try {
            StringReader sr = new StringReader(contents);
            char [] text = new char[ kFILEBUFSIZE ];
            int n;
            while( (n = sr.read(text, 0, kFILEBUFSIZE)) > 0 )
                fileWriter.write(text, 0, n);
        }
        finally {
            if( fileWriter != null )
                fileWriter.close();
        }
    }
    
    public static void copy(String srcFilename, String dstFilename) {
        copy(srcFilename, dstFilename, null, false);
    }
    
    public static void copy(File from, File to) {
        copy(from, to, null, false);
    }
    
    public static void copy(String srcFilename, String dstFilename,String[] suffix,boolean copyEmptyDir) {
        copy(new File(srcFilename),new File(dstFilename),suffix,copyEmptyDir);
    }
    
    public static void copy(File from,File to,String[] suffix,boolean copyEmptyDir) {
        if(from.isDirectory()){
            if(copyEmptyDir)
                to.mkdir();
            File[] flist = getFiles(from,suffix);
            for(int i=0;i<flist.length;i++){
                if (flist[i].equals(to))
                    continue;
                else if (flist[i].isFile()) {
                    to.mkdirs();
                    copy(flist[i],new File(to.getPath()+File.separator+flist[i].getName()),suffix,copyEmptyDir) ;
                } else
                    copy(flist[i],new File(to.getPath()+File.separator+flist[i].getName()),suffix,copyEmptyDir);
            }
        }
        else{
            try {
                // Create channel on the source
                FileInputStream fis =  new FileInputStream(from);
                FileChannel srcChannel = fis.getChannel();
                
                // Create channel on the destination
                FileOutputStream fos = new FileOutputStream(to);
                FileChannel dstChannel = fos.getChannel();
                
                // Copy file contents from source to destination
                dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
                
                // Close the channels
                srcChannel.close();
                dstChannel.close();
                fis.close();
                fos.close();
            } catch (IOException e) {}
        }
    }

    public static File[] getFiles(File dir,String suffix[]) {
        if(suffix==null)
            return dir.listFiles();
        else
            return dir.listFiles(new MyFileFilter(suffix));
    }

    static class MyFileFilter implements FileFilter {
        String[] suffix;
        public MyFileFilter(String[] suffix) {
            this.suffix = suffix;
        }
        
        public boolean accept(File file) {
            if(file.isDirectory())
                return true;
            for(int i=0;i<suffix.length;i++)
                if(file.getName().endsWith(suffix[i]))
                    return true;
            return false;
        }
    }

    public abstract static class RecursiveFileHandler {
        
        public void process(File from,File to,String[] suffix,boolean copyEmptyDir) {
            if(from.isDirectory()){
                if(copyEmptyDir)
                    to.mkdir();
                File[] flist = getFiles(from,suffix);
                for(int i=0;i<flist.length;i++){
                    if (flist[i].equals(to))
                        continue;
                    else if (flist[i].isFile()) {
                        to.mkdirs();
                        process(flist[i],new File(to.getPath()+File.separator+flist[i].getName()),suffix,copyEmptyDir) ;
                    } else
                        process(flist[i],new File(to.getPath()+File.separator+flist[i].getName()),suffix,copyEmptyDir);
                }
            }
            else
                handleOne(from, to);
        }
        
        abstract protected void handleOne(File from,File to);
    }
}

