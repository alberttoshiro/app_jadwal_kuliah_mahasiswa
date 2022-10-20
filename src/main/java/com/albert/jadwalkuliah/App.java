package com.albert.jadwalkuliah;

public class App 
{
	
    public static void main( String[] args )
    {
        FolderListener folderListener = new FolderListener();
        Thread t = new Thread(folderListener);
        t.start();
    }
}
