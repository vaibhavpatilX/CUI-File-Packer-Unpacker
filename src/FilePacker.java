///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name		: 	MarvellousPacker
//	Description 	: 	Command Line (CUI) based application to pack multiple text files 
//					  from a folder into a single packed file.
//	Author			: 	Vaibhav Patil
//	Date			:	19/07/2025
//
///////////////////////////////////////////////////////////////////////////////////////////

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;  

class MarvellousPacker
{
    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Function Name	: 	main
    //	Input			: 	String[] (Command line arguments)
    //	Output			: 	None
    //	Description 	: 	Entry point for the program. 
    //					  Takes folder name as input and creates a packed file with all .txt files.
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
            System.out.println("Enter folder name which contains the files that you want to pack : ");
            String FolderName = sobj.nextLine();

            File fobj = new File(FolderName);

            System.out.println("Note : Packed file gets automatically created in the current directory.");
            System.out.println("Enter the name of packed file that you want to create : ");
            String PackFile = sobj.nextLine();

            File fpackobj = new File(PackFile);
            fpackobj.createNewFile();

            FileOutputStream fout = new FileOutputStream(fpackobj);

            if(fobj.exists())
            {
                File allfiles[] = fobj.listFiles();

                System.out.println("File names are : ");

                byte Buffer[] = new byte[1024];
                int ret = 0, Counter = 0;
                String name;

                for(int i = 0; i < allfiles.length; i++)
                {
                    name = allfiles[i].getName();

                    if(name.endsWith(".txt"))
                    {
                        name = name + " " + (allfiles[i].length());
                        System.out.println("File Name : " + allfiles[i].getName() + 
                                           " with length : " + allfiles[i].length() + " bytes");

                        // Header formation to ensure fixed 100-byte header
                        for(int j = name.length(); j < 100; j++)    
                        {
                            name = name + " ";
                        }

                        // String to byte array conversion
                        byte HeaderByte [] = name.getBytes(StandardCharsets.UTF_8);   

                        // Write header in packed file
                        fout.write(HeaderByte,0,HeaderByte.length); 

                        FileInputStream fiobj = new FileInputStream(allfiles[i]);

                        // Write file contents into packed file
                        while((ret = fiobj.read(Buffer)) != -1)
                        {
                            fout.write(Buffer,0,ret);
                        }  
                        fiobj.close();
                        Counter++;
                    }
                }

                // Summary
                System.out.println("----- Summary -----");
                System.out.println("Number of files scanned : " + allfiles.length);
                System.out.println("Number of files packed successfully : " + Counter);
                System.out.println("Thank you for using Packer Unpacker Application");
            }
            else
            {
                System.out.println("There is no such folder..");
            }
        }
        catch(Exception obj)
        {
            System.out.println("Exception occurred : " + obj);
        }
    } // end of main
} // end of class
