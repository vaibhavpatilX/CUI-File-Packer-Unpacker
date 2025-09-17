///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name		: 	FileUnpacker
//	Description 	: 	Command Line (CUI) based application to unpack a packed file 
//					  back into individual text files.
//	Author			: 	Vaibhav Patil
//	Date			:	19/07/2025
//
///////////////////////////////////////////////////////////////////////////////////////////

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;  

class FileUnpacker
{
    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Function Name	: 	main
    //	Input			: 	String[] (Command line arguments)
    //	Output			: 	None
    //	Description 	: 	Entry point for the program. 
    //					  Reads a packed file and recreates original .txt files with data.
    //	Author			: 	Vaibhav Patil
    //	Date			:	19/07/2025
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String a[])
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("--------- Packer Unpacker CUI Panel --------- ");

        try
        {
            System.out.println("Note : Packed file should be in the current directory.");
            System.out.println("Enter the name of packed file : ");

            String PackFile = sobj.nextLine();
            File fpackobj = new File(PackFile);

            FileInputStream fin = new FileInputStream(fpackobj);
            int Ret = 0, Count = 0;
            byte Header[] = new byte[100];

            if(fpackobj.exists())
            {
                while((Ret = fin.read(Header,0,100)) > 0)
                {
                    String StrHeader = new String(Header, StandardCharsets.UTF_8);

                    String Arr[] = StrHeader.trim().split(" +");

                    File obj = new File(Arr[0]);
                    obj.createNewFile();
                    System.out.println("New file dropped with name : " + Arr[0]);

                    int FileSize = Integer.parseInt(Arr[1]);

                    byte DataArray[] = new byte[FileSize];
                    fin.read(DataArray,0,FileSize);

                    FileOutputStream fout = new FileOutputStream(obj);
                    fout.write(DataArray,0,FileSize);
                    fout.close();

                    Count++;
                }

                // Summary
                System.out.println("----- Summary -----");
                System.out.println("Number of files unpacked successfully : " + Count);
                System.out.println("Thank you for using Packer Unpacker Application");
            }
            else
            {
                System.out.println("There is no such file..");
            }
        }
        catch(Exception obj)
        {
            System.out.println("Exception occurred : " + obj);
        }
    } // end of main
} // end of class
