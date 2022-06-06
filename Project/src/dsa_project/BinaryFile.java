package dsa_project;

import java.io.*;
class Assert {

    public static void notFalse(boolean flag, String message) {
        if (!flag) {
            System.out.println(message);
            throw new IllegalArgumentException();
        }
    }

    public static void notFalse(boolean flag) {
        if (!flag) {
            System.out.println("Failed notFalse Assertion");
            throw new  IllegalArgumentException();
        }
    }

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            System.out.println(message);
            throw new IllegalArgumentException();
        }
    }

    public static void notNull(Object obj) {
        if (obj == null) {
            System.out.println("Failed notNull Assertion");
            throw new IllegalArgumentException();
        }
    }
}

public class BinaryFile {

    private boolean inputFile;
    private RandomAccessFile file;
    private byte buffer;
    private int buf_length;
    private int total_bits;
    private int bitsleft;
    private int bitsread;

    /**
     * Binary File constructor.  Open a file for reading, or create
     * a file for writing.  If we create a file, and a file already
     * exists with that name, the old file will be removed.
     * @param filename The name of the file to read from or write to
     * @param readOrWrite 'w' or 'W' for an output file (open for writing),
     *        and 'r' or 'R' for an input file (open for reading)
     */
    public BinaryFile(String filename, char readOrWrite) {
        buffer = (byte) 0;
        int buf_length = 0;
        total_bits = 0;
        bitsleft = 0;
        bitsread = 0;
        total_bits = 0;
        buffer=0;
        bitsread = 0;
        try {
            if (readOrWrite == 'w' || readOrWrite == 'W') {
                inputFile = false;
                file = new RandomAccessFile(filename, "rw");
                file.writeInt(0); /* header -- # of bits in the file */
            } else if (readOrWrite == 'r' || readOrWrite == 'R') {
                inputFile = true;
                file = new RandomAccessFile(filename, "r");
                total_bits = file.readInt();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }


    /**
     * Checks to see if we are at the end of a file.  This method is only
     * valid for input files, calling EndOfFile on an output fill will
     * cause the program to halt execution.
     * (This method should probably really throw an
     * exception instead of halting the program on an error, but I'm
     * trying to make your code a little simplier)
     * @return True if we are at the end of an input file, and false otherwise
     */
    public boolean EndOfFile()
    {
        Assert.notFalse(inputFile,"EndOfFile only relevant for input files");
        return bitsread < total_bits;
    }

    /**
     * Write a bit to an output file  This method is only valid for
     * output files, and will halt execution if called on an input file.
     * (This method should probably really throw an
     * exception instead of halting the program on an error, but I'm
     * trying to make your code a little simplier)
     * @param bit The bit to write.  false writes a 0 and true writes a 1.
     */
    public void writeBit(boolean bit)
    {
        byte bit_;
        Assert.notFalse(!inputFile,"Can't write to an input file");
        total_bits++;

        if (bit)
            bit_ = 1;
        else
            bit_ = 0;
        buffer |= (bit_ << (7 - buf_length++));
        try
        {
            if (buf_length == 8)
            {
                file.writeByte(buffer);
                buf_length = 0;
                buffer = 0;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Read a bit froman input file.  This method is only valid for
     * input files, and will halt exeuction if called on an output file.
     * (This method should probably really throw an
     * exception instead of halting the program on an error, but I'm
     * trying to make your code a little simplier)
     * @return The next bit in the input file -- false for 0 and true for 1.
     */
    public boolean readBit()
    {
        Assert.notFalse(inputFile,"Can't read from an output file");
        Assert.notFalse(bitsread < total_bits,"Read past end of file");
        try
        {
            if (bitsleft == 0)
            {
                buffer = file.readByte();
                bitsleft = 8;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        bitsread++;
        return (((buffer >> --bitsleft) & 0x01) > 0);
    }

    /**
     * Close the file (works for input and output files).  Output files will
     * not be properly written to disk if this method is not called.
     */
    public void close() {
        try
        {
            if (!inputFile)
            {
                if (buf_length != 0)
                {
                    while (buf_length < 8)
                    {
                        buffer |= (0 << (7 - buf_length++));
                    }
                    file.writeByte(buffer);
                }
                file.seek(0);
                file.writeInt(total_bits);
            }
            file.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

}
